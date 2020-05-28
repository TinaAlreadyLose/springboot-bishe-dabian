package cc.akali.springboot.controller;


import cc.akali.springboot.entity.Teacher;
import cc.akali.springboot.entity.vo.teacher.Caption;
import cc.akali.springboot.entity.vo.teacher.TeacherQuery;
import cc.akali.springboot.service.TeacherService;
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
 * 前端控制器
 * </p>
 *
 * @author 作者名字
 * @since 2020-05-27
 */
@RestController
@RequestMapping("/springboot/teacher")
@Api(tags = "操作教师表")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    //添加
    @ApiOperation(value = "添加教师成员")
    @PostMapping("addTeacherMember")
    public Result addTeacherMember(@ApiParam(value = "必填字段 teacher.name,teacher.phone,teacher.parent_id") @RequestBody Teacher teacher) {
        if (teacherService.save(teacher))
            return Result.ok();
        else return Result.error();
    }

    @ApiOperation(value = "添加教师组长")
    @PostMapping("addTeacherCaption")
    public Result addTeacherCaption(@ApiParam(value = "必填字段 teacher.name,teacher.phone") @RequestBody Teacher teacher) {
        if (teacherService.save(teacher))
            return Result.ok();
        else return Result.error();
    }
    //TODO 通过excel批量添加教师

    //删除
    @ApiOperation(value = "根据id删除教师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(value = "讲师id") @PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher == null)
            return Result.error();
        else {
            if (teacherService.removeById(id))
                return Result.ok();
            else return Result.error();
        }
    }

    @ApiOperation(value = "根据ids批量删除教师")
    @DeleteMapping("removeByIds")
    public Result removeTeacher(@ApiParam(value = "讲师id列表") @RequestBody List<String> ids) {
        if (teacherService.removeByIds(ids))
            return Result.ok();
        else return Result.error();
    }

    //查询
    @ApiOperation(value = "查询所有讲师列表")
    @GetMapping("findAll")
    public Result findAll() {

        List<Teacher> list = teacherService.list(null);
        return Result.ok().data("list", list);
    }

    @ApiOperation(value = "查询所有讲师列表(按教师组分类)")
    @GetMapping("findAllByGroup")
    public Result findAllByGroup() {
        List<Caption> list = teacherService.getAllByGroup();
        return Result.ok().data("list", list);
    }

    @ApiOperation(value = "带分页的条件(模糊)查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@ApiParam(value = "当前页数") @PathVariable long current, @ApiParam(value = "每页显示记录数") @PathVariable long limit, @ApiParam(value = "查询条件,null为跳过查询,参数可选,name为模糊查询,parentId为正常查询") @RequestBody(required = false) TeacherQuery teacherQuery) {
        Map<String, Object> map = teacherService.getByPageCondition(current, limit, teacherQuery);
        return Result.ok().data(map);
    }

    @ApiOperation(value = "根据教师id进行查询")
    @GetMapping("findTeacher/{id}")
    public Result findTeacherById(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        if (!StringUtils.isEmpty(teacher)) {
            return Result.ok().data("teacher", teacher);
        } else return Result.error();
    }
    //TODO 把所有的数据导出到excel
    //修改
    @ApiOperation(value = "根据教师id修改")
    @PutMapping("updateTeacher/{id}")
    public Result updateTeacher(@ApiParam(value = "讲师id")@PathVariable  String id,@ApiParam(value = "修改教师的内容,name,parent_id选填")@RequestBody Teacher teacher) {
        teacher.setId(id);
        if(teacherService.updateById(teacher))
            return Result.ok();
        else return Result.error();
    }
}

