package com.homethunder.domain.service;

import com.homethunder.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {
    private Customer customer;
    private List<Service> services;

    private Integer cost;

    private String comment;
    private OrderStatus status;
}
