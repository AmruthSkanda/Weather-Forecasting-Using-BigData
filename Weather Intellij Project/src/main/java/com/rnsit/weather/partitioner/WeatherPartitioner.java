package com.rnsit.weather.partitioner;

//import com.rnsit.weather.com.rnsit.weather.model.WFDataPojo;
import com.rnsit.weather.com.rnsit.weather.model.WFDataPojo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by root on 15/5/16.
 */
public class WeatherPartitioner extends Partitioner<WFDataPojo, WFDataPojo> {


    @Override
    public int getPartition(WFDataPojo wfDataPojo, WFDataPojo wfDataPojo2, int i) {
        return (Integer.parseInt(wfDataPojo.getStationCode().toString().substring(3))& Integer.MAX_VALUE) % i;

    }
}
