package com.study.springbatch;

import java.util.List;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;

public class CustomItemWriter implements ItemStreamWriter<Member> {

    @Override
    public void write(List<? extends Member> items) throws Exception {
        items.forEach(i -> System.out.println(i.getName()));
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("Writer Open");
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("Writer Update");
    }

    @Override
    public void close() throws ItemStreamException {
        System.out.println("Writer Close");
    }
}
