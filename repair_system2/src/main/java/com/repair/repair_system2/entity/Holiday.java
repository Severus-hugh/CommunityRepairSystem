package com.repair.repair_system2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value="holiday")
public class Holiday {

    @TableId(type = IdType.AUTO)
    private Integer holidayId;
    private Integer repairmanId;
    private String holidayTime;
    private String holidayReason;
    private Integer isAgreed;
}
