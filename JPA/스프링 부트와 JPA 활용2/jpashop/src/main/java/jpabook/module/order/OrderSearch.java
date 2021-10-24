package jpabook.module.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
