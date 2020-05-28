package cc.akali.springboot.controller;


import cc.akali.springboot.entity.Group;
import cc.akali.springboot.entity.Teacher;
import cc.akali.springboot.service.GroupService;
import cc.akali.springboot.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
@RequestMapping("/springboot/group")
@Api(tags = {"操作学生答辩分组表"})
@CrossOrigin
public class GroupController {
    @Autowired
    GroupService groupService;
    //添加
    @ApiOperation(value = "添加答辩组")
    @PostMapping("addGroup")
    public Result addGroup(@ApiParam(value = "必填字段 group.name,group.teacher_id(对此小组负责教师组组长的id)") @RequestBody Group group) {
        if (groupService.save(group))
            return Result.ok();
        else return Result.error();
    }
    //删除
    @ApiOperation(value = "根据id删除答辩组")
    @DeleteMapping("remove/{id}")
    public Result removeGroup(@ApiParam(value = "讲师id") @PathVariable String id){
        if(groupService.deleteById(id))
            return Result.ok();
        else return Result.error();
    }
    //查询
    @ApiOperation(value = "查询答辩组列表")
    @GetMapping("findAll")
    public Result findAll() {
        List<Group> list = groupService.list(null);
        return Result.ok().data("list", list);
    }
    @ApiOperation(value = "根据答辩小组id进行查询")
    @GetMapping("findTeacher/{id}")
    public Result findTeacherById(@PathVariable String id) {
        Group group = groupService.getById(id);
        if (!StringUtils.isEmpty(group)) {
            return Result.ok().data("group", group);
        } else return Result.error();
    }

    //改
    @ApiOperation(value = "根据教师id修改")
    @PutMapping("updateGroup/{id}")
    public Result updateTeacher(@ApiParam(value = "讲师id") @PathVariable String id, @ApiParam(value = "修改答辩小组的内容") @RequestBody Group group) {
        group.setId(id);
        if (groupService.updateById(group))
            return Result.ok();
        else return Result.error();
    }
}

