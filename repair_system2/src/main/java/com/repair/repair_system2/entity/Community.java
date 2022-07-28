package com.repair.repair_system2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 小区
 */
//@Table(name="community")
@Data
//@NoArgsConstructor    无参构造
//@AllArgsConstructor   有参构造
@TableName(value="community")
public class Community {

    @TableId(type = IdType.AUTO)
    private Integer communityId;
    private String communityName;
    private String communityAddress;
    private Date startTime;
    private Date finishTime;




}
