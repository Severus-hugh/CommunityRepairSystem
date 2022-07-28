package com.repair.repair_system2.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 小区居民
 */

@Data
@TableName(value = "resident")
public class Resident {

    @TableId
    private Integer residentId;
    private String wxId;
    private String residentName;
    private String residentTel;
    private String residentMail;
    private Integer currentCommunity;
    private Boolean isExist;
}
