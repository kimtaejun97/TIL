package com.study.springbatch.classifier;

import org.springframework.batch.item.ItemProcessor;

public class ClassifiableItemProcessor3 implements ItemProcessor<ProcessorInfo, ProcessorInfo> {

    @Override
    public ProcessorInfo process(ProcessorInfo item) throws Exception {
        System.out.printf("ClassifiableItemProcessor 3");
        return item;
    }
}
