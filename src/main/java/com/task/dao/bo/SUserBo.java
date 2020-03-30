package com.task.dao.bo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity(name = "susers")
public class SUserBo {

    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_guid")
    private String guid;

    @Column(name = "user_name")
    private String username;

}
