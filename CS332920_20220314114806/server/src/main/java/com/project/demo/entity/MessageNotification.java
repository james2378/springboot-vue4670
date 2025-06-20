package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *消息通知：(MessageNotification)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "MessageNotification")
public class MessageNotification implements Serializable {

    //MessageNotification编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_notification_id")
    private Integer message_notification_id;
   // 求购物品
   @Basic
    private String buy_goods;
   // 物品类型
   @Basic
    private String item_type;
   // 求购规格
   @Basic
    private String buy_specifications;
   // 卖家
   @Basic
    private Integer seller;
   // 留言时间
   @Basic
    private Timestamp message_time;
   // 买家账号
   @Basic
    private Integer buyer_account;
   // 留言内容
   @Basic
    private String message_content;
   // 回复内容
   @Basic
    private String reply_content;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
