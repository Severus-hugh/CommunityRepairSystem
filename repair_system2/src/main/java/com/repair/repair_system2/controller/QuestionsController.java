package com.repair.repair_system2.controller;


import com.repair.repair_system2.entity.Questions;
import com.repair.repair_system2.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionsController {
    @Autowired
    private QuestionsService questionsService;

    @GetMapping("/find")
    public List<Questions> findAll(){
        return questionsService.list();
    }
}
