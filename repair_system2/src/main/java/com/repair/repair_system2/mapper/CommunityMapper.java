package com.repair.repair_system2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.repair_system2.entity.Community;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
public interface CommunityMapper extends BaseMapper<Community> {

//    @Select("SELECT * FROM community")
//    List<Community> findAll();
//
//    @Insert("insert into community(communityId, communityName, communityAddress, startTime, finishTime) " +
//            "values (#{communityId},#{communityName},#{communityAddress},#{startTime},#{finishTime})")
//    int insert(Community community);
//
//    int update(Community community);
//
//    @Delete("delete from community where communityId = #{communityId}")
//    Integer deleteById(@Param("communityId") Integer communityId);
//
//    @Select("SELECT * FROM community where communityName like #{communityName} limit #{pageNum}, #{pageSize}")
//    List<Community> selectPage(Integer pageNum, Integer pageSize,String communityName);
//    由于重名重写了selectPage
//
//    @Select("select count(*) from community where communityName like #{communityName} ")
//    Integer selectTotal(String communityName);
}
