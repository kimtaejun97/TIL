package jpabook.api;

import jpabook.module.member.Address;
import jpabook.module.order.*;
import jpabook.module.order.query.OrderFlatDto;
import jpabook.module.order.query.OrderItemQueryDto;
import jpabook.module.order.query.OrderQueryDto;
import jpabook.module.orderproduct.OrderItem;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class OrdeApiController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    // 엔티티 노출.
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1(){
        List<Order> all = orderService.findOrders(new OrderSearch());

        // 프록시 강제 초기화.
       for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream()
                    .forEach(o->o.getItem().getName());
        }
        return all;
    }

    // DTO로 조회.
    @GetMapping("/api/v2/orders")
    public Result<OrderDto> ordersV2(){
        List<Order> orders = orderService.findOrders(new OrderSearch());
        List<OrderDto> collect = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    // DTO로 조회 최적화
    @GetMapping("api/v3/orders")
    public Result<OrderDto> ordersV3(){
        List<Order> orders = orderService.findOrdersWithMemberDeliveryItem(new OrderSearch());
        List<OrderDto> collect = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    //default_batch_fetch_size 사용
    @GetMapping("api/v3.1/orders")
    public Result<OrderDto> ordersV3_1(){
        List<Order> orders = orderService.findOrdersWithMemberDelivery(new OrderSearch());
        List<OrderDto> collect = orders.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    // DTO에 바로 담아 조회
    @GetMapping("api/v4/orders")
    public Result<OrderQueryDto> ordersV4(){
        List<OrderQueryDto> orders = orderService.findOrderDto();

        return new Result(orders);
    }

    // DTO로 바로 조회 최적화. -> 쿼리 2개(order, orderItems)
    @GetMapping("api/v5/orders")
    public Result<OrderQueryDto> ordersV5(){
        List<OrderQueryDto> orders = orderService.findOrderDtoOptimization();

        return new Result(orders);
    }

    // DTO로 바로 조회, 플랫 데이터 최적화 -> 쿼리 1개
    @GetMapping("api/v6/orders")
    public Result<OrderQueryDto> ordersV6(){
        List<OrderFlatDto> flats = orderService.findOrderDtoFlatOptimization();

        // order와 OrderItem key, value로 묶기.
        Map<OrderQueryDto, List<OrderItemQueryDto>> collect = flats.stream()
                .collect(Collectors.groupingBy(o -> new OrderQueryDto(o.getOrderId(), o.getUsername(), o.getOrderDate(), o.getStatus(), o.getAddress()),
                        Collectors.mapping(o -> new OrderItemQueryDto(o.getOrderId(), o.getItemName(), o.getOrderPrice(), o.getCount()), Collectors.toList())));

        // key에는 Order, value에서는 orderItem을 꺼내 사용할 수 있다.
        List<OrderQueryDto> orderDtos = collect.entrySet().stream()
                .map(e -> new OrderQueryDto(e.getKey().getOrderId(), e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getStatus(), e.getKey().getAddress(),
                        e.getValue()))
                .collect(Collectors.toList());


        return new Result(orderDtos);
    }


    @Data
    static class OrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order){
            this.orderId = order.getId();
            this.name = order.getMember().getName();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress();
            this.orderItems = order.getOrderItems().stream()
                    .map(oi -> new OrderItemDto(oi))
                    .collect(Collectors.toList());;
        }
    }
    @Data
    static class OrderItemDto{
        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem){
            this.itemName = orderItem.getItem().getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count = orderItem.getCount();
        }
    }

    @Data
    static class Result<T>{
        private T data;

        public Result(T data){
            this.data = data;
        }
    }



}
