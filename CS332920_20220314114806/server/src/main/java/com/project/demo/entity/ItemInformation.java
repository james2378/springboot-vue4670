package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *物品信息：(ItemInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ItemInformation")
public class ItemInformation implements Serializable {

    //ItemInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_information_id")
    private Integer item_information_id;
   // 物品类型
   @Basic
    private String item_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
