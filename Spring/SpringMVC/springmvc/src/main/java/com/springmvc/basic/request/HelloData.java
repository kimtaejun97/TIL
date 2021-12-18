package com.springmvc.basic.request;

import lombok.Data;

@Data // Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor
public class HelloData {

    private String username;
    private int age;
}
