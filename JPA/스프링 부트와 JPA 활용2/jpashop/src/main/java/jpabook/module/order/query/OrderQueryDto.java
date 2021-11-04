package jpabook.module.order.query;

import jpabook.module.member.Address;
import jpabook.module.order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Address address;
    private List<OrderItemQueryDto> orderItems;

    public OrderQueryDto(Long id, String name, LocalDateTime orderDate, OrderStatus status, Address address){
        this.orderId = id;
        this.name = name;
        this.orderDate = orderDate;
        this.status = status;
        this.address = address;
    }
}
