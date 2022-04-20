package com.bootcamp.favoriteservice.entity.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;

import java.time.ZonedDateTime;

@Getter
@Setter
public class BaseEntity {

    @Id
    protected Long id;

    @CreatedBy
    @Column("createdBy")
    protected String createdBy;

    @CreatedDate
    @Column("createdDate")
    protected ZonedDateTime createdDate;

    @LastModifiedBy
    @Column("lastModifiedBy")
    protected String lastModifiedBy;

    @LastModifiedDate
    @Column("lastModifiedDate")
    protected ZonedDateTime lastModifiedDate;
}
