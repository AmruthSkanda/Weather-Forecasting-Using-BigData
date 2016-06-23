package com.rnsit.weather.com.rnsit.weather.model;

import org.apache.hadoop.io.*;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by training on 4/16/16.
 */
public class WFDataPojo implements WritableComparable<WFDataPojo > {

    private LongWritable date;

    public WFDataPojo(LongWritable date, Text city, Text stationCode, DoubleWritable tmax, DoubleWritable tmin, DoubleWritable precipitation) {
        this.date = date;
        this.city = city;
        this.stationCode = stationCode;
        this.tmax = tmax;
        this.tmin = tmin;
        this.precipitation = precipitation;
    }

    public WFDataPojo(){

    }

    private Text city;
    private Text stationCode;
    private DoubleWritable tmax;
    private DoubleWritable tmin;
    private DoubleWritable precipitation;

   /* public IntWritable getYear() {
        return year;
    }

    public void setYear(IntWritable year) {
        this.year = year;
    }*/

    //private IntWritable year;

    public void setDate(LongWritable date) {
        this.date = date;
    }

    public void setStationCode(Text stationCode) {
        this.stationCode = stationCode;
    }

    public void setCity(Text city) {
        this.city = city;
    }

    public void setTmax(DoubleWritable tmax) {
        this.tmax = tmax;
    }

    public void setTmin(DoubleWritable tmin) {
        this.tmin = tmin;
    }

    public void setPrecipitation(DoubleWritable precipitation) {
        this.precipitation = precipitation;
    }

    public LongWritable getDate() {
        return date;
    }

    public DoubleWritable getPrecipitation() {
        return precipitation;
    }

    public DoubleWritable getTmin() {
        return tmin;
    }

    public DoubleWritable getTmax() {
        return tmax;
    }

    public Text getStationCode() {
        return stationCode;
    }

    public Text getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WFDataPojo that = (WFDataPojo) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (precipitation != null ? !precipitation.equals(that.precipitation) : that.precipitation != null)
            return false;
        if (stationCode != null ? !stationCode.equals(that.stationCode) : that.stationCode != null) return false;
        if (tmax != null ? !tmax.equals(that.tmax) : that.tmax != null) return false;
        if (tmin != null ? !tmin.equals(that.tmin) : that.tmin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (stationCode != null ? stationCode.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(WFDataPojo o) {
       int retCode=this.getStationCode().compareTo(o.getStationCode());
       if(retCode!=0){
           return retCode;
       }
        else{
           String mmdd1 = this.getDate().toString().substring(4);
           String mmdd2 = o.getDate().toString().substring(4);
           return mmdd1.compareTo(mmdd2);
       }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        //city.write(dataOutput);
       // date.write(dataOutput);
       // stationCode.write(dataOutput);
        dataOutput.writeLong(date.get());
        dataOutput.writeUTF(stationCode.toString());
        dataOutput.writeDouble(tmax.get());
        ////


    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        //city.readFields(dataInput);

       // date.readFields(dataInput);
        setDate(new LongWritable(dataInput.readLong()));
        setStationCode(new Text(dataInput.readUTF()));
        setTmax(new DoubleWritable(dataInput.readDouble()));
       // stationCode.readFields(dataInput);
        //tmax.readFields(dataInput);
        //tmin.readFields(dataInput);
        //precipitation.readFields(dataInput);
    }

    public String getMonthDay(){
        return this.getDate().toString().substring(4);
    }
}
