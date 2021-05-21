package Dto;

import domain.busline.BusLine;

public class BusLineRequestDto {
    private Integer id;
    private String name;

    public BusLineRequestDto(){}
    public BusLineRequestDto(Builder builder){
        id = builder.id;
        name = builder.name;
    }

    public Integer getId() {
        return id;
    }

    public static class Builder{
        private Integer id;
        private String name;

        public Builder id(Integer val){
            id = val;
            return this;
        }

        public Builder name(String val){
            name = val;
            return this;
        }

        public BusLineRequestDto build(){
            return new BusLineRequestDto(this);
        }

    }

    public BusLine toEntity(){
        return new BusLine.Builder()
                .id(id)
                .name(name)
                .build();
    }
}
