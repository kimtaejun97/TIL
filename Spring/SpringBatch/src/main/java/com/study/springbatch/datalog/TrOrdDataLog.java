package com.study.springbatch.datalog;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrOrdDataLog {

    private Long dataSeq;

    private String dataTypeDtls;

    private String data;

    private Long ordNo;

    private String dataType;

    private String prdNo;

    private String mallClf;

    private String mallClfDtlCd;

    private String createIp;

    private String createDt;

    private Long createNo;
}
