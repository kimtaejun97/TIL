package com.studyweb.studyweb.modules.zone;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Setter @EqualsAndHashCode(of="id")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Zone {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String localNameOfCity;

    @Column(nullable = true)
    private String province;

    @Override
    public String toString() {
        return city +"("+ localNameOfCity + ")/" + province;

    }
}
