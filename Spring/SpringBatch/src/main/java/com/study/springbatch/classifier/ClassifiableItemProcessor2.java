package com.study.springbatch.classifier;

import org.springframework.batch.item.ItemProcessor;

public class ClassifiableItemProcessor2 implements ItemProcessor<ProcessorInfo, ProcessorInfo> {

    @Override
    public ProcessorInfo process(ProcessorInfo item) throws Exception {
        System.out.printf("ClassifiableItemProcessor 2");
        return item;
    }
}
