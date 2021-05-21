package com.example.demo.domain.busline;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@NoArgsConstructor
@Entity
public class BusLine {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Builder
    public BusLine(Long id, String name){
        this.id = id;
        this.name =name;
    }

//    private BusLine(Builder builder){
//        id = builder.id;
//        name = builder.name;
//
//    }
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
//        public BusLine build(){
//            return new BusLine(this);
//        }
//
//    }
}


