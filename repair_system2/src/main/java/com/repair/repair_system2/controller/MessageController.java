package com.repair.repair_system2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.repair.repair_system2.entity.Message;
import com.repair.repair_system2.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/find")
    public List<Message> findAll(){
        return messageService.list();
    }
    @PostMapping("/save")
    public boolean save(@RequestBody Message message){
        return messageService.saveMessage(message);
    }

//    @PostMapping("/commit")
//    public boolean commit(@RequestBody String title,@RequestBody String content,@RequestParam String announceTime){
//        return messageService.saveMess(title,content,announceTime);
//    }

    @DeleteMapping("/{messageId}")
    public boolean delete(@PathVariable Integer messageId){
        return messageService.removeById(messageId);
    }
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> messageIds){
        return messageService.removeBatchByIds(messageIds);
    }
    @GetMapping("/page")
    public IPage<Message> findPage(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam String title
    ){
        IPage<Message> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",title);
        queryWrapper.orderByDesc("announceTime");
        IPage<Message> messageIPage = messageService.page(page,queryWrapper);

        return messageIPage;

    }
}
