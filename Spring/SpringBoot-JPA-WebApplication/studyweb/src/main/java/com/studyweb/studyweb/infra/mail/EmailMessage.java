package com.studyweb.studyweb.infra.mail;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmailMessage {

    private String to;
    private String subject;
    private String text;
}
