package com.repair.repair_system2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.repair_system2.entity.Community;
import com.repair.repair_system2.entity.Manager;
import com.repair.repair_system2.mapper.ManagerMapper;
import org.springframework.stereotype.Service;

@Service
public class ManagerService extends ServiceImpl<ManagerMapper, Manager> {
    public boolean saveManger(Manager manager){
        return saveOrUpdate(manager);
    }
    public String getNameById(Integer id){
        if(id==null){
            return "暂无";
        }else{
            Manager manager = baseMapper.selectById(id);
            return manager.getManagerName();
        }

    }
}
