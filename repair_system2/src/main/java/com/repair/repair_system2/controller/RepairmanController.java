package com.repair.repair_system2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repair.repair_system2.entity.Repairman;
import com.repair.repair_system2.service.QuestionsService;
import com.repair.repair_system2.service.RepairmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repairman")
public class RepairmanController {

    @Autowired
    private RepairmanService repairmanService;
    @Autowired
    private QuestionsService questionsService;

    //增，改
    @PostMapping("/save")
    public boolean save(@RequestBody Repairman repairman){
        return repairmanService.saveRepairman(repairman);
    }
    //查
    @GetMapping("/find")
    public List<Repairman> findAll(){
        return repairmanService.list();
    }
    //删
    @DeleteMapping("/{repairmanId}")
    public boolean delete(@PathVariable Integer repairmanId){
        return repairmanService.removeById(repairmanId);
    }
    //批量删
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> repairmanIds){
        return repairmanService.removeBatchByIds(repairmanIds);
    }
    //分页
    @GetMapping("/page")
    public Map<String,Object> findPage(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam String repairName,
            @RequestParam String repairTel,
            @RequestParam Integer repairField
    ){
        HashMap<String,Object> maps = new HashMap<>();
        IPage<Repairman> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Repairman> queryWrapper = new QueryWrapper<>();
        //判断条件
        if(!"".equals(repairName)){queryWrapper.like("repairName",repairName);}
        if(!"".equals(repairTel)){queryWrapper.like("repairTel",repairTel);}
        System.out.println(
               "rr"+ repairField
        );
        if(repairField!=0){queryWrapper.like("repairField",repairField);}

        IPage<Repairman> repairmanIPage = repairmanService.page(page,queryWrapper);
        List<Repairman> records = repairmanIPage.getRecords();
        long total = repairmanIPage.getTotal();
        List<Map<String,Object>> list = new ArrayList<>();
        if(records!=null){
            for(Repairman r:records){
                HashMap<String,Object> map = new HashMap<>();
                map.put("re",r);
                System.out.println("rrr"+r.getRepairField());
                map.put("name",questionsService.getNameById(r.getRepairField()));
                list.add(map);
            }
        }
        maps.put("list",list);
        maps.put("total",total);
        return maps;
    }
}
