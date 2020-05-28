package cc.akali.springboot.controller;


import cc.akali.springboot.entity.Teacher;
import cc.akali.springboot.entity.po.GradeQuery;
import cc.akali.springboot.entity.po.GradeQueryParent;
import cc.akali.springboot.entity.po.GradeStudent;
import cc.akali.springboot.entity.vo.teacher.Caption;
import cc.akali.springboot.entity.vo.teacher.TeacherQuery;
import cc.akali.springboot.service.TeacherService;
import cc.akali.springboot.utils.Result;
import com.sun.xml.internal.ws.developer.StreamingAttachment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
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

    @PostMapping("addByExcel")
    @ApiOperation(value = "通过excel批量添加教师")
    public Result addByExcel(MultipartFile file) {
        if(teacherService.saveByExcel(file, teacherService))
            return Result.ok();
        else return Result.error();
    }

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

    @ApiOperation(value = "根据教师id查询其评价所有学生的成绩")
    @GetMapping("memberFindGrad/{id}")
    public Result memberFindStuGrade(@ApiParam(value = "教师成员id") @PathVariable String id) {
       GradeQuery<GradeStudent> result= teacherService.getByMemberId(id);
        return Result.ok().data("result",result);
    }
    @ApiOperation(value = "教师组长查询其成员评价的成绩")
    @GetMapping("captionFindGrad/{id}")
    public Result captionFindStuGrade(@ApiParam(value = "教师组长id") @PathVariable String id) {
        GradeQueryParent<GradeQuery<GradeStudent>> result = teacherService.getByCaptionId(id);
        return Result.ok().data("result",result);
    }

    @ApiOperation(value = "把教师的数据导出到excel")
    @GetMapping("download")
    public Result download()  {
        String url=teacherService.downLoad();
        if(url!=null)
            return Result.ok().data("url", url);
        else return Result.error();
    }
    //修改
    @ApiOperation(value = "根据教师id修改")
    @PutMapping("updateTeacher/{id}")
    public Result updateTeacher(@ApiParam(value = "讲师id") @PathVariable String id, @ApiParam(value = "修改教师的内容,name,parent_id选填") @RequestBody Teacher teacher) {
        teacher.setId(id);
        if (teacherService.updateById(teacher))
            return Result.ok();
        else return Result.error();
    }
}

