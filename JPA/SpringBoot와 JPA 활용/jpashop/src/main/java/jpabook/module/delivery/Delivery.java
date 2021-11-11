package jpabook.module.delivery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpabook.module.member.Address;
import jpabook.module.order.Order;
import jpabook.module.order.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;
    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public static Delivery createDelivery(Address address) {
        Delivery delivery =new Delivery();
        delivery.setAddress(address);
        delivery.setStatus(DeliveryStatus.READY);

        return delivery;
    }
}

