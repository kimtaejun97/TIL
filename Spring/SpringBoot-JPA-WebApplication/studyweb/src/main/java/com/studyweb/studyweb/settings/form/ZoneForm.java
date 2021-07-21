package com.studyweb.studyweb.settings.form;

import lombok.Data;


@Data
public class ZoneForm {

    private String zoneName;

    public String getCity(){
        return zoneName.substring(0,zoneName.indexOf("("));
    }

    public String getProvince(){
        return zoneName.substring(zoneName.indexOf("/")+1);
    }

    public String getLocalNameOfCity(){
        return zoneName.substring(zoneName.indexOf("(")+1, zoneName.indexOf(")"));
    }

}
