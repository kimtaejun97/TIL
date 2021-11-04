package jpabook.api;

import jpabook.module.order.*;
import jpabook.module.order.SimpleOrderDto;
import jpabook.module.order.simplequery.SimpleOrderQueryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/*
ManytoOne, OneToOne 에서의 성능 최적화
 */
@RequiredArgsConstructor
@RestController
public class OrderSimpleApiController {

    private final OrderService orderService;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        return orderService.findOrders(new OrderSearch());
    }

    @GetMapping("/api/v2/simple-orders")
    public Result ordersV2(){
        List<SimpleOrderDto> orderDtos = orderService.findOrders(new OrderSearch()).stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return new Result(orderDtos);
    }

    @GetMapping("/api/v3/simple-orders")
    public Result ordersV3(){
        List<SimpleOrderDto> orderDtos = orderService.findOrdersWithMemberDelivery(new OrderSearch())
                .stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return new Result(orderDtos);
    }

    @GetMapping("/api/v4/simple-orders")
    public Result ordersV4(){
        List<SimpleOrderQueryDto> orderDtos = orderService.findSimpleOrderDto(new OrderSearch());
        return new Result(orderDtos);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T orders;
    }




}
