package com.study.springbatch;

import java.util.ArrayList;
import java.util.List;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class CustomItemReader implements ItemStreamReader<Member> {

    private final List<Member> items;
    private int index;
    private boolean restartable;

    public CustomItemReader(List<Member> items) {
        this.items = new ArrayList<>(items);
        this.index = 0;
        this. restartable = false;
    }

    @Override
    public Member read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Member item = null;

        if(this.index < this.items.size()) {
            item = this.items.get(index++);
            System.out.println("Read Item: " + item);
        }

        if(index == 8 && !restartable) {
            throw  new RuntimeException("Restart is required");
        }

        return item;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("Reader Open");
        if(executionContext.containsKey("index")) {
            index = executionContext.getInt("index");
            this.restartable = true;
        }
        else {
            executionContext.put("index", index);
        }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        executionContext.put("index", index);
        System.out.println("Reader Update");
    }

    @Override
    public void close() throws ItemStreamException {
        System.out.println("Reader Close, 리소스 해제.");
    }
}
