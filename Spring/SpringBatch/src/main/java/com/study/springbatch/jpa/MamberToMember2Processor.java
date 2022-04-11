package com.study.springbatch.jpa;

import com.study.springbatch.Member;
import org.springframework.batch.item.ItemProcessor;

public class MamberToMember2Processor implements ItemProcessor<Member, Member2> {

    @Override
    public Member2 process(Member item) throws Exception {
        return new Member2(item.getId(), item.getName());
    }
}
