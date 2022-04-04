package com.study.springbatch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class PasscheckingListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        ExitStatus exitCode = stepExecution.getExitStatus();

        if(!exitCode.getExitCode().equals(ExitStatus.FAILED.getExitCode())) {
            return new ExitStatus("PASS");
        }
        return exitCode;
    }
}
