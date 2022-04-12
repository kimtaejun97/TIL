package com.study.springbatch.classifier;

import org.springframework.batch.item.ItemProcessor;

public class ClassifiableItemProcessor implements ItemProcessor<ProcessorInfo, ProcessorInfo> {

    @Override
    public ProcessorInfo process(ProcessorInfo item) throws Exception {
        System.out.printf("ClassifiableItemProcessor 1");
        return item;
    }
}
