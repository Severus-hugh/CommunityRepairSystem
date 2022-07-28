package com.repair.repair_system2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.repair_system2.entity.Holiday;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface HolidayMapper extends BaseMapper<Holiday> {
    @Select("select * from holiday where repairmanId = #{repairmanId}")
    List<Holiday> selectByRepId(Integer repairmanId);
//    @Insert("insert into holiday (repairmanId,holidayTime,holidayReason) values(#{repairmanId},#{holidayTime},#{holidayReason})")
//    boolean saveHoliday(Integer repairmanId,Date holidayTime, String holidayReason);

}
