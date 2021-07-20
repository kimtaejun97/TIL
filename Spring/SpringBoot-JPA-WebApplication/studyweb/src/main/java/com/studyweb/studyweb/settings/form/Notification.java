package com.studyweb.studyweb.settings.form;

import lombok.Data;

@Data
public class Notification {

    private boolean studyCreatedByEmail;
    private boolean studyCreatedByWeb;

    private boolean studyEnrollmentResultByEmail;
    private boolean studyEnrollmentResultByWeb;

    private boolean studyUpdatedByEmail;
    private boolean studyUpdatedByWeb;



}
