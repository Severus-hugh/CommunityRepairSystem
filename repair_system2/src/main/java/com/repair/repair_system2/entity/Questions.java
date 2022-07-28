package com.repair.repair_system2.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 维修问题范围
 */
@Data
@TableName(value = "questions")
public class Questions {

    @TableId
    private Integer quesID;
    private String quesField;
}
