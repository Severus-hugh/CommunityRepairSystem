package com.repair.repair_system2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 管理者，包括超管
 */
@Data
@TableName(value="manager")
public class Manager {
    @TableId(type = IdType.AUTO)
    private Integer managerId;
    //@JsonIgnore 前台数据不显示该属性
    private String managerPassword;
    private String managerWx;
    private String managerName;
    private String managerNickname;
//    private String manageCommunityId;
    private String managerTel;
    private String managerMail;
}
