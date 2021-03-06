package com.study.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JobExecutionListener implements org.springframework.batch.core.JobExecutionListener {

    private final JobRepository jobRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        jobExecution.getExecutionContext().put("name", "jobExecutionName");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        jobExecution.getJobParameters().getString("run.id");

        JobParameters jobParameters = new JobParametersBuilder()
            .addString("name", "user1")
            .toJobParameters();

        JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters);
        if(lastJobExecution != null) {
            lastJobExecution.getStepExecutions()
                .forEach(s -> System.out.println(s.getExitStatus()));
        }
    }
}
