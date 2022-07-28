package com.repair.repair_system2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repair.repair_system2.entity.Holiday;
import com.repair.repair_system2.mapper.HolidayMapper;
import com.repair.repair_system2.service.HolidayService;
import com.repair.repair_system2.service.RepairmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/holiday")
public class HolidayController {
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private RepairmanService repairmanService;
    @PostMapping("/save")
    public boolean saveHoliday(@RequestBody Holiday holiday){
        return holidayService.saveHoliday(holiday);
    }
    @PostMapping("/update")
    public boolean updateHoliday(@RequestBody Holiday holiday){
        return holidayService.updateById(holiday);
    }
//    public boolean saveHoliday(@RequestBody Integer repairmanId,@RequestBody Date holidayTime,@RequestBody String holidayReason){
//        return holidayService.saveHoliday(repairmanId,holidayTime,holidayReason);
//    }
    //根据维修员Id查找请假表
    @GetMapping("/find")
    public List<Holiday> findAll(){
        return holidayService.list();
    }
//    public List<Holiday> findByRepairmanId(@RequestParam Integer repairmanId){
//        return holidayMapper.selectByRepId(repairmanId);
//    }
    @GetMapping("/page")
    public Map<String,Object> findpage(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam Integer repairmanId
    ){
        HashMap<String,Object> maps = new HashMap<>();
        IPage<Holiday> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Holiday> queryWrapper = new QueryWrapper<>();
        //判断
        if(repairmanId!=0){
            queryWrapper.like("repairmanId",repairmanId);
        }
        queryWrapper.orderByDesc("holidayId");
        IPage<Holiday> holidayIPage = holidayService.page(page,queryWrapper);
        List<Holiday> records = holidayIPage.getRecords();
        long total = holidayIPage.getTotal();
        List<Map<String,Object>> list = new ArrayList<>();
        if(records!=null){
            for(Holiday r:records){
                String agree;
                HashMap<String,Object> map = new HashMap<>();
                map.put("re",r);
                map.put("name",repairmanService.getNameById(r.getRepairmanId()));
                if(r.getIsAgreed()==0) {
                    agree = "待审核";
                }
                else if(r.getIsAgreed()==1){
                    agree = "已通过";
                }else{
                    agree = "未通过";
                }
                map.put("agree",agree);
                list.add(map);
            }
        }
        maps.put("list",list);
        maps.put("total",total);
        return maps;
    }
}
