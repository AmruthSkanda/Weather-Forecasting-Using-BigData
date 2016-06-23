package com.rnsit.weather.reducers;

import com.rnsit.weather.com.rnsit.weather.model.WFDataPojo;
import com.rnsit.weather.mappers.WFMeanCalculationMapper;
import org.apache.commons.lang.ArrayUtils;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by training on 4/16/16.
 */
public class WFMeanCalculationReducer extends Reducer<WFDataPojo,WFDataPojo,Text,DoubleWritable> {


    private DoubleWritable result = new DoubleWritable();

    public void reduce(WFDataPojo key, Iterable<WFDataPojo> values,
                       Context context
    ) throws IOException, InterruptedException {
        List<Double> keyList = new ArrayList<Double>();
        List<Double> valList = new ArrayList<Double>();
        for(WFDataPojo val : values){
            keyList.add(Double.parseDouble(val.getDate().toString().substring(0, 4)));
            valList.add(Double.parseDouble(val.getTmax().toString()));
        }
        Double[] years=new Double[keyList.size()];
        keyList.toArray(years);
        Double[] tmaxs=new Double[valList.size()];
        valList.toArray(tmaxs);
        double x1[] = new double[] {2016,2017,2018,2019,2020};
        double y1[]= new double[5];
        double theta[]=WFRegressionCalculationReducer.lreg(ArrayUtils.toPrimitive(years), ArrayUtils.toPrimitive(tmaxs));
      /*  System.out.println(theta[1]+ "  "+theta[0]);
        Logger l= Logger.getLogger("WFMeanCalculationReducer");
        l.info(theta[1]+ "  "+ theta[0]+"  date:"+key.getMonthDay()+"  station:"+key.getStationCode());*/
        for (int i=0;i<5;i++){
            y1[i]=theta[1]*x1[i]+theta[0];
            context.write(new Text(key.getStationCode()+":"+(int)x1[i]+key.getMonthDay()), new DoubleWritable(y1[i]));
        }
        /*Double sum = 0.0; int cnt=0;
        for (DoubleWritable val : values) {
            sum += val.get();
            cnt++;
        }
        sum/=cnt;
       result.set(sum);*/


    }

}
