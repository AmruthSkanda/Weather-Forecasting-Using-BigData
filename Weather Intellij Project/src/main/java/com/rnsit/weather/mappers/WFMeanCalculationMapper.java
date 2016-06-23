package com.rnsit.weather.mappers;

import com.rnsit.weather.com.rnsit.weather.model.WFDataPojo;

import com.rnsit.weather.parsers.WFCsvParser;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;//what error?wit let me see


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by training on 4/16/16.
 */



public  class WFMeanCalculationMapper extends Mapper<Object, Text, WFDataPojo, WFDataPojo> {
    public static Map<Integer,String> mapp=new HashMap<Integer,String>() ;
    static {
        for(int i=1;i<=31;i++) {
            mapp.put(i,(Integer.valueOf(i).toString().length()>1)? Integer.valueOf(i).toString():"0"+i);
        }
    }



    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        if (value.toString().contains("TMAX")) {
            String[] arrayValues = WFCsvParser.parse(value.toString());
            String stationId = arrayValues[0];
            String[] stationArr = stationId.split("~");
            String date = stationArr[1];//195612
           // String year = stationArr[1];
            String stationCode = stationArr[0];
            String yyyymmdd;

            for (int i = 1; i < arrayValues.length; i++) {
                if (arrayValues[i].matches("[0-9]*")) {
                    if (i <= 31) {
                        yyyymmdd = date.concat(mapp.get(i));//19561202
                        WFDataPojo wfDataPojo = new WFDataPojo();
                        wfDataPojo.setDate(new LongWritable(Long.parseLong(yyyymmdd)));
                       // wfDataPojo.setYear(new IntWritable(Integer.parseInt(year)));
                        wfDataPojo.setStationCode(new Text(stationCode));
                        wfDataPojo.setTmax(new DoubleWritable(Double.parseDouble(arrayValues[i])/10.0));
                        context.write(wfDataPojo, wfDataPojo);
                    }
                }
            }
        }
    }
}



