package com.repair.repair_system2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 *订单：包括已受理，未受理，历史订单
 */

@Data
@TableName(value = "orders")
public class Orders {

    @TableId(type = IdType.AUTO)
    private Integer orderId;
    private Integer orderCommunityId;
    private String orderAddress;
    private Integer questionId;
    private String orderDescription;
    private Integer residentId;
    private String contactTel;//用来联系的电话，可能并不是报修居民自己的电话
    private Date createTime;
    private String suitableTime;  //居民选择的合适时间，没用
    private Integer managerId;
    private Date acceptTime;
    private Integer repairmanId;
    private Date repairTime;//开始维修时间，没用
    private Date endTime;
    private Integer currentStatus;  //当前的订单状态：
                                    //1--新订单，未受理
                                    //2--已受理并派单
                                    //3--已派单待维修
                                    //4--已维修
                                    //5--被取消
    private Boolean orderAttribute;//维修设施属性：1--公共设施，2--家用设施
}
