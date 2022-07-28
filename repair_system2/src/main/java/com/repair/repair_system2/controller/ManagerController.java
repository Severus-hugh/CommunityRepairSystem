package com.repair.repair_system2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repair.repair_system2.entity.Community;
import com.repair.repair_system2.entity.Manager;
import com.repair.repair_system2.entity.Resident;
import com.repair.repair_system2.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    //增，改
    @PostMapping("/save")
    public boolean save(@RequestBody Manager manager){
        return managerService.saveManger(manager);
    }
    //查询
    @GetMapping("/find")
    public List<Manager> findAll(){
        return managerService.list();
    }
    //真删
    @DeleteMapping("/{managerId}")
    public boolean delete(@PathVariable Integer managerId){
        return managerService.removeById(managerId);
    }
    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> managerIds){
        return managerService.removeBatchByIds(managerIds);
    }
    //分页
    @GetMapping("/page")
    public IPage<Manager> findPage(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     @RequestParam String managerName,
                                     @RequestParam String managerTel){

        IPage<Manager> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Manager> queryWrapper = new QueryWrapper<>();
        if(!"".equals(managerName)){queryWrapper.like("managerName",managerName);}
        if(!"".equals(managerTel)){queryWrapper.like("managerTel",managerTel);}
        IPage<Manager> managerIPage = managerService.page(page, queryWrapper);
        return managerIPage;
    }
}
