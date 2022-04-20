package com.bootcamp.favoriteservice.entity;

import com.bootcamp.favoriteservice.entity.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("favorite")
public class Favorite extends BaseEntity {


    @Column("serviceId")
    private String serviceId;
    private String type;
    private String name;
    private int status;
}
