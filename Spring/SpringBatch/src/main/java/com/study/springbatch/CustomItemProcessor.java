package com.study.springbatch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

public class CustomItemProcessor implements ItemProcessor<Member, Member> {

    @Override
    public Member process(Member item) throws Exception {
        String name = item.getName();
        item.setName(name.toUpperCase());

        return item;
    }
}
