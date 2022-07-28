package com.repair.repair_system2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 小区相关通知
 */
@Data
@TableName(value = "message")
public class Message {

    @TableId(type = IdType.AUTO)
    private Integer messageID;
    private String title;
    private String content;
    private String announceTime;
}
