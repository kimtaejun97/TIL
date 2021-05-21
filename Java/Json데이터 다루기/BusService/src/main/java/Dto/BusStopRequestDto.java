package Dto;

import domain.busstop.BusStop;

public class BusStopRequestDto {
    private Integer id;
    private String name;

    public BusStopRequestDto(){}
    public BusStopRequestDto(Builder builder){
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

        public BusStopRequestDto build(){
            return new BusStopRequestDto(this);
        }

    }

    public BusStop toEntity(){
        return new BusStop.Builder()
                .id(id)
                .name(name)
                .build();
    }
}
