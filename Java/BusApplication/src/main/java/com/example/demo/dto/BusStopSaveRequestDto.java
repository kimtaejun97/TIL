package com.example.demo.dto;

import com.example.demo.domain.busstop.BusStop;

public class BusStopSaveRequestDto {
    private Long id;
    private String name;

    public BusStopSaveRequestDto(){}
    public BusStopSaveRequestDto(Builder builder){
        id = id;
        name = name;
    }

    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder{
        private Long id;
        private String name;

        public Builder(){}

        public Builder id(Long val){
            id = val;
            return this;
        }

        public Builder name(String val){
            name =val;
            return this;
        }

        public BusStopSaveRequestDto build(){
            return new BusStopSaveRequestDto(this);
        }

    }

    public BusStop toEntity(){
        return new BusStop.Builder()
                .id(id)
                .name(name)
                .build();
    }

}
