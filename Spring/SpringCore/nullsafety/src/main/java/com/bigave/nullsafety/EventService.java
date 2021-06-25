package com.bigave.nullsafety;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    // return null을 허용하지 않음
    @NonNull
    public String createEvent(@NonNull String name){ //name null을 허용하지 않음.
        return null;
    }
}
