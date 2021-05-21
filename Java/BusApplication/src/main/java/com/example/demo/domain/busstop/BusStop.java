package com.example.demo.domain.busstop;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class BusStop {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    public BusStop() {
    }

    private BusStop(Builder builder){
        id = builder.id;
        name = builder.name;

    }
    public Long getId(){
        return id;
    }
    public String getName(){
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

        public BusStop build(){
            return new BusStop(this);
        }

    }
}
