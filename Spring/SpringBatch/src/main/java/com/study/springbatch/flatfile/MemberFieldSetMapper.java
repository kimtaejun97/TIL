package com.study.springbatch.flatfile;

import com.study.springbatch.Member;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class MemberFieldSetMapper implements FieldSetMapper<Member> {

    @Override
    public Member mapFieldSet(FieldSet fieldSet) throws BindException {
        if(fieldSet == null){
            return null;
        }

        Member member = new Member();
        member.setName(fieldSet.readString("name"));
        member.setId(fieldSet.readString("id"));

        return member;
    }
}
