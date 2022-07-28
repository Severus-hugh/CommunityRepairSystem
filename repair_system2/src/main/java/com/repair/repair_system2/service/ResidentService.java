package com.repair.repair_system2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.repair_system2.entity.Resident;
import com.repair.repair_system2.mapper.ResidentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidentService extends ServiceImpl<ResidentMapper, Resident> {

    @Autowired
    private ResidentMapper residentMapper;

    public boolean saveResident(Resident resident){
        return saveOrUpdate(resident);
    }
    public String getNameById(int id){
        Resident resident = baseMapper.selectById(id);
        return resident.getResidentName();
    }
    public Integer getIdByName(String name){
        return residentMapper.getResidentId(name);
    }
}
