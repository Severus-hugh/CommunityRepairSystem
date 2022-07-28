package com.repair.repair_system2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.repair_system2.entity.Message;
import com.repair.repair_system2.mapper.MessageMapper;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends ServiceImpl<MessageMapper, Message> {
    public boolean saveMessage(Message message){
        return saveOrUpdate(message);
    }

//    public boolean saveMess(String title, String content, String announceTime) {
//        Message message = baseMapper.saveMess(title,content,announceTime);
//        return MessageMapper.saveMess(title,content,announceTime);
//    }
}
