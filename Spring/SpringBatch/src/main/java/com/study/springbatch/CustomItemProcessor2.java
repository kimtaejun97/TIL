package com.study.springbatch;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor2 implements ItemProcessor<Member, Member> {


    @Override
    public Member process(Member item) throws Exception {
        item.setName(item.getName() + " P2");
        return item;
    }
}
