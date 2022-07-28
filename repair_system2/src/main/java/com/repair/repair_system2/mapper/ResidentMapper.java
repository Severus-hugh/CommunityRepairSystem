package com.repair.repair_system2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.repair_system2.entity.Resident;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ResidentMapper extends BaseMapper<Resident> {
    @Select("SELECT residentId FROM resident WHERE residentName = #{residentName};")
    int getResidentId(String residentName);
}
