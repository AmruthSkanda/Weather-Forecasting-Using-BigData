package com.rnsit.weather.parsers;

import com.rnsit.weather.com.rnsit.weather.model.WFDataPojo;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by training on 4/16/16.
 */
public class WFMyComparator {

    public static void main(String[] args) {
        WFDataPojo w= new WFDataPojo();
       // w.setCity(new Text("aaaa"));
        w.setDate(new LongWritable(20160501));
        w.setStationCode(new Text("AG000060590"));

        WFDataPojo w1= new WFDataPojo();
        w1.setStationCode(new Text("AG000060590"));
        w1.setDate(new LongWritable(20160502));

        WFDataPojo w2= new WFDataPojo();
        w2.setStationCode(new Text("BG000060590"));
        w2.setDate(new LongWritable(20160503));

        List<WFDataPojo> l= new ArrayList<WFDataPojo>();
        l.add(w);
        l.add(w2);
        l.add(w1);

        for(WFDataPojo wl : l){
            System.out.println(wl.getStationCode());
        }

        System.out.println("===============after=============");

        Collections.sort(l);


        for(WFDataPojo wl : l){
            System.out.println(wl.getStationCode()+"::"+wl.getDate());
        }

    }

}
