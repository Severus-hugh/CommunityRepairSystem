package com.repair.repair_system2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.repair_system2.entity.Repairman;
import com.repair.repair_system2.mapper.RepairmanMapper;
import com.repair.repair_system2.mapper.ResidentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairmanService extends ServiceImpl<RepairmanMapper, Repairman> {

    @Autowired
    private RepairmanMapper repairmanMapper;

    public boolean saveRepairman(Repairman repairman){
        return saveOrUpdate(repairman);
    }
    public String getNameById(Integer id){
        //String repairman = repairmanMapper.selectNameById(id);
        if(id==null){
            return "暂无";
        }else{
            Repairman repairman = baseMapper.selectById(id);
            return repairman.getRepairName();
        }

    }


}
