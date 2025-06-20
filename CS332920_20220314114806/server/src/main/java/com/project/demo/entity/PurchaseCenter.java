package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *求购中心：(PurchaseCenter)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "PurchaseCenter")
public class PurchaseCenter implements Serializable {

    //PurchaseCenter编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_center_id")
    private Integer purchase_center_id;
   // 求购物品
   @Basic
    private String buy_goods;
   // 物品类型
   @Basic
    private String item_type;
   // 物品图片
   @Basic
    private String item_picture;
   // 收购价格
   @Basic
    private String purchasing_price;
   // 求购数量
   @Basic
    private String purchase_quantity;
   // 求购规格
   @Basic
    private String buy_specifications;
   // 发布时间
   @Basic
    private Timestamp release_time;
   // 买家账号
   @Basic
    private Integer buyer_account;
    // 点击数
    @Basic
    private Integer hits;
    // 点赞数
    @Basic
    private Integer praise_len;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
