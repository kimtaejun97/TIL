package jpabook.module.order;

import jpabook.module.member.Address;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleOrderDto{
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order){
        this.orderId = order.getId();
        this.name = order.getMember().getName(); // Lazy 초기화
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.address = order.getDelivery().getAddress(); // Lazy 초기화
    }

}
