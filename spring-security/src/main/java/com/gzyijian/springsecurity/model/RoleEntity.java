package com.gzyijian.springsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by zmjiangi on 2018-5-11.
 */
@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable {

    @Id
    @Column(name = "r_id")
    private Long id;
    @Column(name = "r_name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
