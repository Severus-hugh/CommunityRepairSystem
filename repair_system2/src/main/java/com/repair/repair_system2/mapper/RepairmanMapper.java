package com.repair.repair_system2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.repair_system2.entity.Repairman;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RepairmanMapper extends BaseMapper<Repairman> {

    @Select("SELECT repairName FROM repairman WHERE repairId = #{id};")
    String selectNameById(int id);
}
