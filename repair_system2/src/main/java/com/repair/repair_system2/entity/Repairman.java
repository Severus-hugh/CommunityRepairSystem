package com.repair.repair_system2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 维修员
 */
@Data
@TableName(value = "repairman")
public class Repairman {

    @TableId(type = IdType.AUTO)
    private Integer repairId;
    private String repairName;
    private String repairNickname;
    private Integer repairField;
    private String repairTel;
    private Integer ordersCount;
    private Boolean workStatus;//今天是否值班
}
