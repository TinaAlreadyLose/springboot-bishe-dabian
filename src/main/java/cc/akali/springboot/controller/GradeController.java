package cc.akali.springboot.controller;


import cc.akali.springboot.entity.Classes;
import cc.akali.springboot.entity.Grade;
import cc.akali.springboot.entity.Student;
import cc.akali.springboot.service.GradeService;
import cc.akali.springboot.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者名字
 * @since 2020-05-27
 */
@RestController
@RequestMapping("/springboot/grade")
@Api(tags = "操作学生答辩得分表(GradeQuery)")
@CrossOrigin
public class GradeController {
    @Autowired
    GradeService gradeService;
    //增
    @ApiOperation(value = "添加学生")
    @PostMapping("addStudent")
    public Result addStudent(@ApiParam(value = "添加学生信息") @RequestBody Grade grade) {
        if(gradeService.save(grade))
            return Result.ok();
        else return Result.error();
    }
    //删除
    @ApiOperation(value = "根据学生id删除成绩")
    @DeleteMapping("removeByStudent/{id}")
    public Result removeByStudentId(@ApiParam(value = "学生id")@PathVariable String id) {
        if(gradeService.deleteById("student_id",id))
            return Result.ok();
        else return Result.error();
    }
    @ApiOperation(value = "根据学生id删除成绩")
    @DeleteMapping("removeByTeacher/{id}")
    public Result removeByTeacherId(@ApiParam(value = "学生id")@PathVariable String id) {
        if(gradeService.deleteById("teacher_id",id))
            return Result.ok();
        else return Result.error();
    }
    //查
    @ApiOperation(value = "根据学生id查询成绩")
    @GetMapping("findByStudent/{id}")
    public Result findByStuID(@PathVariable String id) {
        return Result.ok();
    }
    //改
    @ApiOperation(value = "根据id修改班级的信息")
    @PutMapping("updateStudent/{id}")
    public Result updateStudentById(@ApiParam(value = "成绩id") @PathVariable String id, @ApiParam("修改班级信息")@RequestBody Grade grade) {
        grade.setId(id);
        if(gradeService.updateById(grade))
            return Result.ok();
        else return Result.error();
    }
}

