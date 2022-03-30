package com.study.springbatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

public class CustomJobParametersIncrementer implements JobParametersIncrementer {

    @Override
    public JobParameters getNext(JobParameters parameters) {
        String date = new SimpleDateFormat("yyyyMMdd-hhmmss").format(new Date());
        return new JobParametersBuilder(parameters).addString("run.date", date).toJobParameters();
    }
}
