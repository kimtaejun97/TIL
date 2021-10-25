package jpabook.api;

import jpabook.module.order.Order;
import jpabook.module.order.OrderSearch;
import jpabook.module.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
ManytoOne, OneToOne 에서의 성능 최적화
 */
@RequiredArgsConstructor
@RestController
public class orderSimpleApiController {

    private final OrderService orderService;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        return orderService.findOrders(new OrderSearch());
    }

}
