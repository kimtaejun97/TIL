package com.example.demo.dto;

import com.example.demo.domain.busline.BusLine;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BusLineSaveRequestDto {
    private Long id;
    private String name;

//    public BusLineSaveRequestDto(Builder builder){
//        id = builder.id;
//        name = builder.name;
//    }
//
//    public Long getId(){
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public static class Builder{
//        private Long id;
//        private String name;
//
//        public Builder(){}
//
//        public Builder id(Long val){
//            id = val;
//            return this;
//        }
//
//        public Builder name(String val){
//            name =val;
//            return this;
//        }
//
//        public BusLineSaveRequestDto build(){
//            return new BusLineSaveRequestDto(this);
//        }
//
//    }
    @Builder
    public BusLineSaveRequestDto(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public BusLine toEntity(){
        return BusLine.builder()
                .id(id)
                .name(name)
                .build();
    }
}
