package com.repair.repair_system2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.repair_system2.entity.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
    @Select("select * from orders where currentStatus = #{status} and residentId = #{residentId}")
    List<Orders> findByCurrentStatus(Integer status,Integer residentId);

//    @Insert("INSERT INTO orders (orderCommunityId, orderAddress, " +
//            "questionId, orderDescription, residentId, contactTel," +
//            "createTime,suitableTime) VALUES(#{orderCommunityId},#{orderAddress}," +
//            "#{questionId},#{questionDescription},#{residentId},#{tel}," +
//            "#{createTime},#{suitableTime})")
//    int insert(Integer residentId, Integer orderCommunityId,
//               String orderAddress, Date suitableTime, Date createTime,String tel,
//               Integer questionId, String questionDescription);

    @Select("SELECT residentId from orders where orderId = #{orderId}")
    Integer selectResident(Integer orderId);

    @Select("SELECT repairmanId from orders where orderId = #{orderId}")
    Integer selectRepairman(Integer orderId);

    @Select("select * from orders where currentStatus = #{currentStatus} and repairmanId = #{repairmanId}")
    List<Orders> findByRepairman(Integer currentStatus, Integer repairmanId);

    @Update("update orders set currentStatus = 4, endTime = #{endTime} where orderId =#{orderId}")
    Boolean repairOrder(String endTime,Integer orderId);

    @Select("select * from orders where repairmanId = #{repairmanId}")
    List<Orders> findByRepair(Integer repairmanId);
}
