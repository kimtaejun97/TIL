package com.study.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;


public class MyTasklet implements Tasklet {

    private final String name;

    public MyTasklet(String name) {
        this.name = name;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//        if (name.equals("myStep1")){
//            throw new RuntimeException("Failed Step 1");
//        }
        ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
        Integer count = (Integer) jobExecutionContext.get("count");
        if (count == null) {
            jobExecutionContext.put("count", 1);
        } else {
            jobExecutionContext.put("count", count + 1);
        }

        ExecutionContext stepExecutionContext = chunkContext.getStepContext().getStepExecution().getExecutionContext();
        stepExecutionContext.put("name", name);

//        Thread.sleep(3000);
        System.out.println(stepExecutionContext.get("name") + " was executed");
        System.out.println("지금까지 실행된 Step 수: " + jobExecutionContext.get("count"));

        return RepeatStatus.FINISHED;
    }
}
