package com.repair.repair_system2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repair.repair_system2.entity.Orders;
import com.repair.repair_system2.mapper.OrdersMapper;
import com.repair.repair_system2.service.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private QuestionsService questionsService;
    @Autowired
    private ResidentService residentService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private RepairmanService repairmanService;
    @Autowired
    private OrdersMapper ordersMapper;
    //增，改
    @PostMapping("/save")
    public boolean save(@RequestBody Orders orders){
        return ordersService.saveOrders(orders);
    }
    @PostMapping("/updateOrder")
    public boolean updateOrder(@RequestBody Orders orders){
        return ordersService.updateOrders(orders);
    }
    @PostMapping("/update")
    public boolean update(@RequestBody Orders orders){
        return ordersService.updateById(orders);
    }
    //查所有
    @GetMapping("/find")
    public List<Orders> findAll(){
        return ordersService.list();
    }
    //真删
    @DeleteMapping("/{orderId}")
    public boolean delete(@PathVariable Integer orderId){
        return ordersService.removeById(orderId);
    }
    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Orders> orderIds){
        return ordersService.removeBatchByIds(orderIds);
    }
    //分页显示
    @GetMapping("/page")
    public Map<String,Object> findPage(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam Integer orderId,
            @RequestParam Integer orderCommunityId,
            @RequestParam Integer questionId,
            @RequestParam Integer repairmanId,
            @RequestParam Integer currentStatus
    ){
        HashMap<String,Object> maps = new HashMap<>();
        IPage<Orders> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        if(orderId!=0){queryWrapper.like("orderId",orderId);}
        //queryWrapper.like("orderId",orderId);
        if(orderCommunityId!=0){queryWrapper.like("orderCommunityId",orderCommunityId);}
        //queryWrapper.like("orderCommunityId",orderCommunityId);
        if(questionId!=0){queryWrapper.like("questionId",questionId);}
        //queryWrapper.like("questionId",questionId);
        if(repairmanId!=0){queryWrapper.like("repairmanId",repairmanId);}
        //queryWrapper.like("repairmanId",repairmanId);
        if(currentStatus!=0){queryWrapper.like("currentStatus",currentStatus);}
        //queryWrapper.like("currentStatus",currentStatus);

//        queryWrapper.like(StringUtil.isNotEmpty(specials),)
        queryWrapper.orderByDesc("orderId");
        IPage<Orders> ordersIPage = ordersService.page(page,queryWrapper);
        List<Orders> records = ordersIPage.getRecords();
        long total = ordersIPage.getTotal();
        List<Map<String,Object>> list=new ArrayList<>();
        if(records!=null){
            for(Orders r:records){
                String status;
                HashMap<String,Object> map = new HashMap<>();
                map.put("re",r);
                map.put("community",communityService.getNameById(r.getOrderCommunityId()));
                map.put("question",questionsService.getNameById(r.getQuestionId()));
                System.out.println(
                        "repair"+r.getRepairmanId()
                );
                map.put("repair",repairmanService.getNameById(r.getRepairmanId()));
                map.put("resident",residentService.getNameById(r.getResidentId()));
                map.put("manager",managerService.getNameById(r.getManagerId()));
                if(r.getCurrentStatus()==1){
                    status = "待转派";
                }else if(r.getCurrentStatus()==2){
                    status = "已转派";
                }else if(r.getCurrentStatus()==3){
                    status = "修理中";
                }else{
                    status = "已修复";
                }
                map.put("status",status);
                list.add(map);
            }
        }
        maps.put("list",list);
        maps.put("total",total);
        return maps;
    }
    //查询不同状态的订单
    @GetMapping("/findcommited")
    public List<Orders> findCommited(@RequestParam Integer currentStatus,@RequestParam Integer residentId){
        return ordersMapper.findByCurrentStatus(currentStatus,residentId);
    }

    //用户新建订单
//    @PostMapping("/neworder")
//    public int newOrder(
//            @RequestBody String residentName,
//            @RequestBody String orderComId,
//            @RequestBody String orderAddress,
//            @RequestBody String suitTime,
//            @RequestBody String tel,
//            @RequestBody String quesField,
//            @RequestBody String orderDescription
//    ) throws ParseException {
//        //根据输入的报修人的名字来查找Id,Warlock对应10000007
//        Integer residentId = residentService.getIdByName(residentName);
//        //小区编号
//        Integer orderCommunityId = Integer.valueOf(orderComId);//String转Integer
//        //合适时间
//        Date suitableTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(suitTime);
//        //订单问题分类
//        Integer questionId = Integer.valueOf(quesField);
//        //订单创建时间
//        Date createTime = new Date();
//        //SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//        //修改了当前时间的格式，如果存入数据库的createTime显示错误格式就改这
//        return ordersService.saveNew_wx(residentId,orderCommunityId,orderAddress,suitableTime,createTime,tel,questionId,orderDescription);
//
//    }
    @PostMapping("/neworderone")
    public boolean newOrders(@RequestBody Orders orders){
        //需要从前台传当前时间的参数
        //直接在数据库中设置默认时间了
        return ordersService.saveOrders(orders);
    }

    //维修员的已接单的订单
    @GetMapping("/findreporder")
    public List<Orders> findRepOrder(@RequestParam Integer currentStatus,@RequestParam Integer repairmanId){
        return ordersService.findByRepairman(currentStatus,repairmanId);
    }

    //维修员修复已接订单
    @PostMapping("/repairorder")
    public Boolean repairOrder(@RequestBody Orders orders){
        Date date = new Date();
        int orderId = orders.getOrderId();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String endTime = dateFormat.format(date);
//        Integer in = Integer.parseInt(orderId);
        return ordersService.repairOrder(endTime,orderId);
    }

    //查询维修员的所有订单
    @GetMapping("/findreporders")
    public List<Orders> findRepOrds(@RequestParam Integer repairmanId){
        return ordersService.findByRepair(repairmanId);
    }
}
