package com.shop.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue()
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "work_city")),
            @AttributeOverride(name = "street", column = @Column(name = "work_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "work_zipcode"))
    })
    private Address workAddress;

    @ElementCollection
    @CollectionTable(name = "member_favorite_food", joinColumns = @JoinColumn (name = "member_id"))
    @Column(name = "food_name")
    private List<String> favoriteFood = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = PERSIST)
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
        order.setMember(this);
    }

}
