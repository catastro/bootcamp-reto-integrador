package com.bootcamp.channelpayment.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Transaction {

    private String id;
    private String serviceId;
    private String supplyNumber;
    private Double amount;
    private Date createDate;

}
