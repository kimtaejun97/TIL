package ch2.item07;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INTTIAL_CAPACITY = 16;

    public Stack(){
        elements = new Object[DEFAULT_INTTIAL_CAPACITY];
    }

    public void push(Object e){
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop(){
        if(size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] =null;
        return result;
    }


    public void ensureCapacity(){
        if(elements.length ==size)
            elements = Arrays.copyOf(elements, size*2 +1);
    }


}
