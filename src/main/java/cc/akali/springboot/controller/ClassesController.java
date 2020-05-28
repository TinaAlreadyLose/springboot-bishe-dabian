package cc.akali.springboot.controller;


import cc.akali.springboot.entity.Classes;
import cc.akali.springboot.entity.Student;
import cc.akali.springboot.service.ClassesService;
import cc.akali.springboot.service.StudentService;
import cc.akali.springboot.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者名字
 * @since 2020-05-27
 */
@RestController
@RequestMapping("/springboot/classes")
@Api(tags = {"操作学生班级表(classes)"})
public class ClassesController {
    @Autowired
    private ClassesService classesService;
    //增
    @ApiOperation(value = "添加班级")
    @PostMapping("addCourse")
    public Result addClass(@ApiParam(value = "添加班级信息") @RequestBody Classes classes) {
        if(classesService.save(classes))
            return Result.ok();
        else return Result.error();
    }
    //删
    @ApiOperation(value = "根据班级id删除信息")
    @DeleteMapping("remove/{id}")
    public Result removeByClassId(@ApiParam(value = "班级id")@PathVariable String id) {
        Classes classes = classesService.getById(id);
        if (classes!=null) {
            if (classesService.removeById(id))
                return Result.ok();
            else return Result.error();
        } else return Result.error();
    }
    //查
    @ApiOperation(value = "查询班级列表")
    @GetMapping("findAll")
    public Result findAll() {
        List<Classes> list = classesService.list(null);
        return Result.ok().data("list", list);
    }
    @ApiOperation(value = "根据学生id进行查询")
    @GetMapping("findClass/{id}")
    public Result findClassById(@PathVariable String id) {
        Classes classes = classesService.getById(id);
        if (!StringUtils.isEmpty(classes)) {
            return Result.ok().data("teacher", classes);
        } else return Result.error();
    }
}

