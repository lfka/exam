package com.study.onlineexam.controller;


import com.study.onlineexam.constant.CRUDConstant;
import com.study.onlineexam.entity.Question;
import com.study.onlineexam.service.QuestionService;
import com.study.onlineexam.vo.ResponseVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈则法
 * @since 2022-01-06
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    @ApiOperation("查询所有题目")
    public ResponseVo queryQuestionList(){
        List<Question> questionList = questionService.queryQuestionList();
        if (questionList != null) {
            return ResponseVo.result(CRUDConstant.QUERY_SUCCESS_CODE, CRUDConstant.QUERY_SUCCESS_MESSAGE, questionList);
        }else{
            return ResponseVo.result(CRUDConstant.QUERY_FAIL_CODE, CRUDConstant.QUERY_FAIL_MESSAGE);
        }
    }
}

