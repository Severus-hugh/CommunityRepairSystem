package com.repair.repair_system2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.repair_system2.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
//    @Insert("insert into message (title,content,announceTime) values (#{title},#{content},#{announceTime})")
//     boolean saveMess(String title, String content, String announceTime) ;
}
