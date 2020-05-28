package cc.akali.springboot.service.impl;

import cc.akali.springboot.entity.Group;
import cc.akali.springboot.entity.Student;
import cc.akali.springboot.mapper.GroupMapper;
import cc.akali.springboot.service.GroupService;
import cc.akali.springboot.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者名字
 * @since 2020-05-27
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {
    @Autowired
    StudentService studentService;
    @Override
    public boolean deleteById(String id) {
        //模拟外键功能stu_group(id)=>student(group_id)
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", id);
        List<Student> students = studentService.list(wrapper);
//        System.out.println(students);
        if (students.isEmpty()) {
            return this.removeById(id);
        } else return false;
    }
}
