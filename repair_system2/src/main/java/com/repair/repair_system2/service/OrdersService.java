package com.repair.repair_system2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.repair_system2.entity.Orders;
import com.repair.repair_system2.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersService extends ServiceImpl<OrdersMapper, Orders> {
    @Autowired
    private OrdersMapper ordersMapper;
    public boolean saveOrders(Orders orders){
        return save(orders);
    }
    public boolean updateOrders(Orders orders){
        return updateById(orders);
    }
//    public int saveNew_wx (Integer residentId, Integer orderCommunityId, String orderAddress,
//                               Date suitableTime, Date createTime,String tel,Integer questionId,
//                           String questionDescription){
//        return ordersMapper.insert(residentId,orderCommunityId,orderAddress,suitableTime,createTime,tel,questionId,questionDescription);
//    }

//    public Integer getResidentById(Integer orderId) {
//        return ordersMapper.selectResident(orderId);
//    }
//
//    public Integer getRepairmanById(Integer orderId) {
//        return ordersMapper.selectRepairman(orderId);
//    }

    //维修员接单的订单
    public List<Orders> findByRepairman(Integer currentStatus, Integer repairmanId) {
        return ordersMapper.findByRepairman(currentStatus,repairmanId);
    }

    public Boolean repairOrder(String endTime,Integer orderId) {
        return ordersMapper.repairOrder(endTime,orderId);
    }
    //找维修员的所有订单
    public List<Orders> findByRepair(Integer repairmanId){
        return ordersMapper.findByRepair(repairmanId);
    }
}
