package com.rnsit.weather.controller;

import com.rnsit.weather.com.rnsit.weather.model.WFDataPojo;
import com.rnsit.weather.mappers.WFMeanCalculationMapper;
import com.rnsit.weather.partitioner.WeatherPartitioner;
import com.rnsit.weather.reducers.WFMeanCalculationReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by training on 4/16/16.
 */
public class WFDriver extends Configured implements Tool {


    @Override
    public int run(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = new Job(conf, "WFDriver");
        job.setJarByClass(WFDriver.class);
        job.setMapperClass(WFMeanCalculationMapper.class);
        //job.setCombinerClass(WFMeanCalculationReducer.class);
        job.setReducerClass(WFMeanCalculationReducer.class);
        job.setMapOutputKeyClass(WFDataPojo.class);
        job.setMapOutputValueClass(WFDataPojo.class);
        job.setOutputKeyClass(Text.class);
        job.setNumReduceTasks(1);
        job.setPartitionerClass(WeatherPartitioner.class);
        job.setOutputValueClass(DoubleWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        // Execute job and return status
        return job.waitForCompletion(true) ? 0 : 1;
    }


    public static void main(String[] args) throws Exception{
        int res = ToolRunner.run(new Configuration(), new WFDriver(), args);
        System.exit(res);
    }
}