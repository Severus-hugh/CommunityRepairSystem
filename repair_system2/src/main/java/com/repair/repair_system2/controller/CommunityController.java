package com.repair.repair_system2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repair.repair_system2.entity.Community;
import com.repair.repair_system2.mapper.CommunityMapper;
import com.repair.repair_system2.service.CommunityService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/community")
public class CommunityController {

//    @Autowired
//    private CommunityMapper communityMapper;
    @Autowired
    private CommunityService communityService;

    //要改到管理者类里
//    @PostMapping
//    public boolean login(@RequestBody UserDTO userDTO){
//        return communityService.login(user);
//    }
    //增，改
    @PostMapping
    public boolean save(@RequestBody Community community){
        //新增，更新
        //return communityService.save(community);
        return communityService.saveCommunity(community);
    }

    //查询
    @GetMapping("/find")
    public List<Community> findAll(){
//        List<Community> all = communityMapper.findAll();
//        return all;
        return communityService.list();
    }
    //删除
    @DeleteMapping("/{communityId}") //{communityId}和PathVariable对应的communityId必须同名
    public boolean delete(@PathVariable Integer communityId){
        //return communityMapper.deleteById(communityId);
        return communityService.removeById(communityId);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> communityIds){
        return communityService.removeBatchByIds(communityIds);
    }
    //分页
    //RequestParam 接收此类数据
    //映射后的接口路径：/community/page?pageNum=1&pageSize=10
    //limit分页对应的两个数据，第一个参数是分页的起始项 = (pageNum - 1) * pageSize; 第二个参数为每页条数，不变
//    @GetMapping("/page")
//    public Map<String,Object> findPage(@RequestParam Integer pageNum,
//                                       @RequestParam Integer pageSize,
//                                       @RequestParam String communityName){
//        pageNum = (pageNum - 1) * pageSize;
//        communityName = "%" + communityName + "%";
//        List<Community> data = communityMapper.selectPage(pageNum,pageSize,communityName);
//        Integer total = communityMapper.selectTotal(communityName);
//        Map<String,Object> res = new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
//        return res;
//    }
    @GetMapping("/page")
    public IPage<Community> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String communityName){
        //可以用@RequestParam(defaultValue = "")来避免不传入参数报错
        //当传入多个参数时（指除name外还传入了其他参数），由于使用了@RequestParam(defaultValue = "")默认值“”，而自动拼接为“%%”
        //这在数据库中是无法模糊匹配的，所以应当对其进行判断
        //当参数不为空时，才进行拼接
        //if(!"".equals(communityAddress)){queryWrapper.like("communityAddress",communityAddress);}
        IPage<Community> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Community> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("communityName",communityName);
        queryWrapper.orderByDesc("communityId");//按主键倒序排列，放在这才有用
        IPage<Community> communityIPage = communityService.page(page, queryWrapper);
        return communityIPage;
    }
}
