package com.repair.repair_system2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repair.repair_system2.entity.Complaint;
import com.repair.repair_system2.service.ComplaintService;
import com.repair.repair_system2.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private OrdersService ordersService;

//    @PostMapping("/savenew")
//    public boolean saveNew(@RequestBody Integer orderId,@RequestBody String complaintContent){
//        Integer residentId = ordersService.getResidentById(orderId);
//        Integer repairmanId = ordersService.getRepairmanById(orderId);
//        //这个方法不好用，以后有空改
//        System.out.println("residentId"+residentId);
//
//        return complaintService.insertNew(orderId,complaintContent,residentId,repairmanId);
//    }
    @PostMapping("/saven")
    public boolean saveN(@RequestBody Complaint complaint){
        return complaintService.saveN(complaint);
    }
    @GetMapping("/findend")
    public List<Complaint> findEnd(@RequestParam Integer residentId){
        //应该根据传回的用户名在resident表中找residentId
        //再根据residentId在orders表中找所有报修的订单
        //再在complaint表中找所有end==1的数据
        //为了方便，直接在complaint中加入了residentId和repairmanId
        //从前台传参为用户ID
        return complaintService.getComplaint(residentId);
    }
    @GetMapping("/page")
    public IPage<Complaint> findPage(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam Integer orderId,
            @RequestParam Integer repairmanId,
            @RequestParam Integer isEnd
    ){
        IPage<Complaint> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Complaint> queryWrapper = new QueryWrapper<>();
        if(orderId!=0){
            queryWrapper.like("orderId",orderId);
        }
        if(repairmanId!=0){
            queryWrapper.like("repairmanId",repairmanId);
        }
        if(isEnd!=0){
            queryWrapper.like("isEnd",isEnd);
        }
        queryWrapper.orderByDesc("complaintID");
        IPage<Complaint> complaintIPage = complaintService.page(page,queryWrapper);
        return complaintIPage;
    }


    @PostMapping("/update")
    public boolean update(@RequestBody Complaint complaint){
        return complaintService.saveComplaint(complaint);
    }
}
