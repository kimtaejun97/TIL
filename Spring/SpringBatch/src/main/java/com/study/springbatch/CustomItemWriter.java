package com.study.springbatch;

import java.util.List;
import org.springframework.batch.item.ItemWriter;

public class CustomItemWriter implements ItemWriter<Member> {

    @Override
    public void write(List<? extends Member> items) throws Exception {
        items.forEach(i -> System.out.println(i.getName()));
    }
}
