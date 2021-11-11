package jpabook.module.order.query;

import jpabook.module.member.Address;
import jpabook.module.order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderFlatDto {
    private Long orderId;
    private String username;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Address address;

    private String itemName;
    private int orderPrice;
    private int count;

    public OrderFlatDto(Long orderId, String username, LocalDateTime orderDate,
                        OrderStatus status, Address address, String itemName, int orderPrice, int count) {
        this.orderId = orderId;
        this.username = username;
        this.orderDate = orderDate;
        this.status = status;
        this.address = address;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
