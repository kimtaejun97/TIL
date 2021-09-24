package com.shop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "createdDate")
    private LocalDateTime createdDateTime;

    @Column(name = "lastModifeidDate")
    private LocalDateTime lastModifiedDateTime;
}
