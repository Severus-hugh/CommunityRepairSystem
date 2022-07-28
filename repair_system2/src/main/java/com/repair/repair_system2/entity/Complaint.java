package com.repair.repair_system2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 投诉
 */
@Data
@TableName(value = "complaint")
public class Complaint {
    @TableId(type = IdType.AUTO)
    private Integer complaintID;
    private Integer orderId;
    private String complaintContent;
    private String reply;
    private Integer isEnd;
    private Integer residentId;
    private Integer repairmanId;


}
