package com.repair.repair_system2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repair.repair_system2.entity.Community;
import com.repair.repair_system2.entity.Resident;
import com.repair.repair_system2.service.CommunityService;
import com.repair.repair_system2.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resident")
public class ResidentController {
    @Autowired
    private ResidentService residentService;
    @Autowired
    private CommunityService communityService;
    //改，假删
    @PostMapping("/update")
    public boolean update(@RequestBody Resident resident){
        return residentService.updateById(resident);
    }
    //查
    @GetMapping("/")
    public List<Resident> findAll(){
        return residentService.list();
    }
    //真删
    @DeleteMapping("/{residentId}")
    public boolean delete(@PathVariable Integer residentId){
        return residentService.removeById(residentId);
    }
    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Resident> residentIds){
        return residentService.removeBatchByIds(residentIds);
    }
    //分页展示
    @GetMapping("/page")
    public Map<String,Object> findPage(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam String residentName,
            @RequestParam String residentTel,
            @RequestParam Integer currentCommunity
    ){
        HashMap<String,Object> map1=new HashMap<>();
        IPage<Resident> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Resident> queryWrapper = new QueryWrapper<>();
        if(!"".equals(residentName)){queryWrapper.like("residentName",residentName);}
        if(!"".equals(residentTel)){queryWrapper.like("residentTel",residentTel);}
        if(currentCommunity!=0){queryWrapper.like("currentCommunity",currentCommunity);}

        IPage<Resident> residentIPage = residentService.page(page, queryWrapper);
        List<Resident> records = residentIPage.getRecords();
        long total = residentIPage.getTotal();
        List<Map<String,Object>> list=new ArrayList<>();
        if(records!=null){
            for(Resident r:records){
                HashMap<String,Object> map=new HashMap<>();
                map.put("re",r);
                map.put("name",communityService.getNameById(r.getCurrentCommunity()));
                list.add(map);
            }
        }
        map1.put("list",list);
        map1.put("total",total);
        return map1;
    }



}
