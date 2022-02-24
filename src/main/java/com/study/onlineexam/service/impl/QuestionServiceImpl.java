package com.study.onlineexam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.study.onlineexam.entity.Question;
import com.study.onlineexam.mapper.QuestionMapper;
import com.study.onlineexam.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 陈则法
 * @since 2022-01-06
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> queryQuestionList() {
        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper();
        return questionMapper.selectList(lambdaQueryWrapper);
    }
}
