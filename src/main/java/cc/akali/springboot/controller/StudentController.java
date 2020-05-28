package cc.akali.springboot.controller;


import cc.akali.springboot.entity.Student;
import cc.akali.springboot.entity.vo.student.StudentQuery;
import cc.akali.springboot.service.StudentService;
import cc.akali.springboot.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者名字
 * @since 2020-05-27
 */
@RestController
@RequestMapping("/springboot/student")
@Api(tags = {"操作学生表"})
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;

    //增
    @ApiOperation(value = "添加学生")
    @PostMapping("addStudent")
    public Result addStudent(@ApiParam(value = "添加学生信息") @RequestBody Student student) {
        if(studentService.save(student))
            return Result.ok();
        else return Result.error();
    }

    //TODO 通过excel批量给学生添加信息
    //删
    @ApiOperation(value = "根据学生id删除信息")
    @DeleteMapping("remove/{id}")
    public Result removeByStudentId(@ApiParam(value = "学生id")@PathVariable String id) {
        Student student = studentService.getById(id);
        if (student!=null) {
            if (studentService.removeById(id))
                return Result.ok();
            else return Result.error();
        } else return Result.error();
    }
    @ApiOperation(value = "根据学生ids批量删除信息")
    @DeleteMapping("removeByIds")
    public Result removeByStudentIds(@ApiParam(value = "学生id列表")@RequestBody List<String> ids) {
        if(studentService.removeByIds(ids))
            return Result.ok();
        else return Result.error();
    }
    //查
    @ApiOperation(value = "查询学生列表")
    @GetMapping("findAll")
    public Result findAll() {
        List<Student> list = studentService.list(null);
        return Result.ok().data("list", list);
    }
    @ApiOperation(value = "带分页的条件(模糊)查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@ApiParam(value = "当前页数") @PathVariable long current, @ApiParam(value = "每页显示记录数") @PathVariable long limit, @ApiParam(value = "查询条件,null为跳过查询,参数可选,name为模糊查询,classId,groupID,minGrade,MaxGrade皆为可选参数") @RequestBody(required = false) StudentQuery studentQuery) {
        Map<String, Object> map = studentService.getByPageCondition(current, limit, studentQuery);
        return Result.ok().data(map);
    }
    @ApiOperation(value = "根据学生id进行查询")
    @GetMapping("findStudent/{id}")
    public Result findTeacherById(@PathVariable String id) {
        Student student = studentService.getById(id);
        if (!StringUtils.isEmpty(student)) {
            return Result.ok().data("teacher", student);
        } else return Result.error();
    }
    //TODO 将学生信息导出到excel
    //改
    @ApiOperation(value = "根据id修改学生的信息")
    @PutMapping("updateStudent/{id}")
    public Result updateStudentById(@ApiParam(value = "学生id") @PathVariable String id, @ApiParam("修改学生信息,可选字段student.name, student.title,\n student.classId,student.groupId,\n student.finalGrade")@RequestBody Student student) {
        student.setId(id);
        if(studentService.updateById(student))
            return Result.ok();
        else return Result.error();
    }
}

