package com.repair.repair_system2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.repair_system2.entity.Complaint;
import com.repair.repair_system2.entity.Orders;
import com.repair.repair_system2.mapper.ComplaintMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService extends ServiceImpl<ComplaintMapper, Complaint> {
    public boolean updateComplaint(Complaint complaint){
        //投诉只能修改，不能新增
        //功能：回复，修改回复状态
        return updateById(complaint);
    }
    @Autowired
    private ComplaintMapper complaintMapper;

    public boolean insertNew(Integer orderId,String complainContent,Integer residentId,Integer repairmanId){
        //查找orderId对应的订单，将residentId和repairmanId都存进去

        return complaintMapper.saveNew(orderId,complainContent,residentId,repairmanId);
    }

    public List<Complaint> getComplaint(Integer residentId) {
        return complaintMapper.selectComplaint(residentId);
    }

    public boolean saveN(Complaint complaint) {
        return saveOrUpdate(complaint);
    }

    public boolean saveComplaint(Complaint complaint) {
        return saveOrUpdate(complaint);
    }
}
