package com.study.springbatch.flatfile;

import lombok.Setter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;

@Setter
public class DefaultLineMapper<T> implements LineMapper<T> {

    private LineTokenizer lineTokenizer;
    private FieldSetMapper<T> fieldSetMapper;

    @Override
    public T mapLine(String line, int lineNumber) throws Exception {
        return fieldSetMapper.mapFieldSet(lineTokenizer.tokenize(line));
    }
}
