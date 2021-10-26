package jpabook.module.order;

import com.querydsl.core.annotations.QueryProjection;
import jpabook.module.member.Address;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class SimpleOrderQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Address address;

    public SimpleOrderQueryDto(Long id, String name, LocalDateTime orderDate, OrderStatus status, Address address){
        this.orderId = id;
        this.name = name;
        this.orderDate = orderDate;
        this.status = status;
        this.address = address;
    }
}
