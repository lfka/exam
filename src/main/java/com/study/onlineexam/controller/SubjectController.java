package com.study.onlineexam.controller;
import com.study.onlineexam.constant.CRUDConstant;
import com.study.onlineexam.entity.Subject;
import com.study.onlineexam.service.SubjectService;
import com.study.onlineexam.vo.ResponseVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈则法
 * @since 2022-01-05
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     * 查询所有课程
     * @return
     */
    @GetMapping
    @ApiOperation("查询所有课程")
    public ResponseVo querySubjectList(){
        List<Subject> subjectList = subjectService.queryList();
        if (subjectList != null){
            return ResponseVo.result(CRUDConstant.QUERY_SUCCESS_CODE, CRUDConstant.QUERY_SUCCESS_MESSAGE,subjectList);
        }else{
            return ResponseVo.result(CRUDConstant.QUERY_FAIL_CODE, CRUDConstant.QUERY_FAIL_MESSAGE);
        }
    }

    /**
     * 根据课程ID查询某个课程
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据课程ID查询某个课程")
    public ResponseVo findSubjectById(@PathVariable("id") Long id){
        Subject subject = subjectService.findSubjectById(id);
        if(subject != null){
            return ResponseVo.result(CRUDConstant.QUERY_SUCCESS_CODE, CRUDConstant.QUERY_SUCCESS_MESSAGE,subject);
        } else{
            return ResponseVo.result(CRUDConstant.QUERY_FAIL_CODE, CRUDConstant.QUERY_FAIL_MESSAGE);
        }
    }

    /**
     * 添加课程
     * @param subject
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加课程")
    public ResponseVo addSubject(Subject subject){
        subject.setUserId(1L);
        subject.setAuthor("lisi");
        subject.setState(1);
        boolean i = subjectService.save(subject);
        if (i == true){
            return ResponseVo.result(CRUDConstant.INSERT_SUCCESS_CODE, CRUDConstant.INSERT_SUCCESS_MESSAGE);
        }else {
            return ResponseVo.result(CRUDConstant.INSERT_FAIL_CODE, CRUDConstant.INSERT_FAIL_MESSAGE);
        }
    }

    /**
     * 修改课程
     * @param subject
     * @return
     */
    @PostMapping("/edit")
    @ApiOperation("修改课程")
    public ResponseVo edit(Subject subject){
        if (subject.getId() == null || subjectService.findSubjectById(subject.getId()) == null){
            return ResponseVo.result(CRUDConstant.UPDATE_FAIL_CODE, CRUDConstant.UPDATE_FAIL_MESSAGE);
        }
        boolean i = subjectService.updateById(subject);
        if (i == true){
            return ResponseVo.result(CRUDConstant.UPDATE_SUCCESS_CODE, CRUDConstant.UPDATE_SUCCESS_MESSAGE);
        }else {
            return ResponseVo.result(CRUDConstant.UPDATE_FAIL_CODE, CRUDConstant.UPDATE_FAIL_MESSAGE);
        }
    }
}

