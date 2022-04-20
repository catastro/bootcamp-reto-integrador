package com.bootcamp.paymentservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Document(value = "transaction")
public class Transaction {

    @Id
    private String id;
    @NotEmpty
    private String serviceId;
    @NotEmpty
    private String supplyNumber;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private Double amount;
    private Date createDate;

}
