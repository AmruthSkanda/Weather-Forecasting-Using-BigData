package com.rnsit.weather.parsers;

import com.rnsit.weather.com.rnsit.weather.model.WFDataPojo;
import org.apache.hadoop.io.Text;


import java.util.*;

/**
 * Created by training on 4/16/16.
 */
public class WFCsvParser {

    public static final int startId=0;
    public static final int endId=11;
    public static final int dateStartIndex=11;
    public static final int dateEndIndex=17;
   /* public static final int yearStartIndex=11;
    public static final int yearEndIndex=15;*/
  //  static String text = "AG000060590195612PRCP    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I-9999       0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I    0  I";
    public static String[] parse(String value){
        String[] data = value.toString().split("\\s+");
        List<String> list = new ArrayList<String>(Arrays.asList(data));
        List<String> ll=new ArrayList<String>();
        ll.add("E");
        ll.add("G");
        ll.add("I");
        ll.add("S");
        list.removeAll(ll);

        String[] strData=new String[list.size()];
        list.toArray(strData);
        String stationId=strData[0];

        strData[0]=stationId.substring(startId,endId).concat("~").concat(stationId.substring(dateStartIndex,dateEndIndex));
       /* for(String str:strData) {
            System.out.println(str);
        }*/
        //System.out.println(year);
        return strData;
    }

   public static Map<Integer,String> mapp=new HashMap<Integer,String>() ;
    static {
        for(int i=1;i<=31;i++) {
            mapp.put(i,(Integer.valueOf(i).toString().length()>1)? Integer.valueOf(i).toString():"0"+i);
        }
    }



    public static void main(String[] args) {
      // parse(text);
        //*String stCode="AG000060590189201TMAX";
       // System.out.println(stCode.substring(0,11));
       // System.out.println(stCode.substring(15,17));
        //*
       // System.out.println(mapp.get(6));

        String s= "999.9";
        if(s.matches("[0-9]")){
            System.out.println("Numb");
        }
    }


}
