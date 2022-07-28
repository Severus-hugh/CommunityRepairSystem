package com.repair.repair_system2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.repair_system2.entity.Questions;
import com.repair.repair_system2.mapper.QuestionsMapper;
import org.springframework.stereotype.Service;

@Service
public class QuestionsService extends ServiceImpl<QuestionsMapper, Questions> {
    public boolean saveQuestions(Questions questions){
        return saveOrUpdate(questions);
    }
    public String getNameById(int quesID){
        Questions questions = baseMapper.selectById(quesID);
        return questions.getQuesField();
    }
}
