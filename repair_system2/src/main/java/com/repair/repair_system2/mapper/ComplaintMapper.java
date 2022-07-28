package com.repair.repair_system2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.repair_system2.entity.Complaint;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ComplaintMapper extends BaseMapper<Complaint> {

    @Insert("INSERT into complaint (orderId, complaintContent,residentId,repairmanId) VALUES" +
            " (#{orderId},#{complaintContent},#{residentId},#{repairmanId})")
    Boolean saveNew (Integer orderId,String complaintContent,Integer residentId,Integer repairmanId);

    //@Select("SELECT * from complaint where residentId = #{residentId} and isEnd = 1")
    @Select("SELECT * from complaint where residentId = #{residentId}")
    List<Complaint> selectComplaint(Integer residentId);
}
