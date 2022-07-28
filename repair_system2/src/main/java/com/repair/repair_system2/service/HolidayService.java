package com.repair.repair_system2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.repair_system2.entity.Holiday;
import com.repair.repair_system2.mapper.HolidayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HolidayService extends ServiceImpl<HolidayMapper, Holiday> {
    @Autowired
    private HolidayMapper holidayMapper;

    public boolean saveHoliday(Holiday holiday){
        //新增请假
        return saveOrUpdate(holiday);
    }

    public List<Holiday> findRepair(Integer repairmanId) {
        return holidayMapper.selectByRepId(repairmanId);
    }

//    public boolean saveHoliday(Integer repairmanId,Date holidayTime,String holidayReason){
//        return holidayMapper.saveHoliday(repairmanId,holidayTime,holidayReason);
//    }
}
