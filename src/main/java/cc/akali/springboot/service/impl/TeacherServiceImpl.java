package cc.akali.springboot.service.impl;

import cc.akali.springboot.entity.Teacher;
import cc.akali.springboot.entity.vo.teacher.Caption;
import cc.akali.springboot.entity.vo.teacher.Member;
import cc.akali.springboot.entity.vo.teacher.TeacherQuery;
import cc.akali.springboot.mapper.TeacherMapper;
import cc.akali.springboot.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public List<Caption> getAllByGroup() {
        //查询所有的教师组组长
        QueryWrapper<Teacher> wrapperCaption = new QueryWrapper<>();
        wrapperCaption.eq("parent_id", 0);
        List<Teacher> captainTeachers = this.list(wrapperCaption);
        //查询所有的教师组员工
        QueryWrapper<Teacher> wrapperMember = new QueryWrapper<>();
        wrapperMember.ne("parent_id", 0);
        //封装最后的数据
        List<Caption> finalList = new ArrayList<>();
        List<Teacher> memberTeachers = this.list(wrapperMember);
        for (Teacher captainTeacher : captainTeachers) {
            Caption caption = new Caption();
            BeanUtils.copyProperties(captainTeacher, caption);
            List<Member> members = new ArrayList<>();
            for (Teacher memberTeacher : memberTeachers) {
                if (memberTeacher.getParentId().equals(captainTeacher.getId())) {
                    Member member = new Member();
                    BeanUtils.copyProperties(memberTeacher, member);
                    members.add(member);
                }
            }
            caption.setChildren(members);
            finalList.add(caption);
        }
        return finalList;
    }
    @Override
    public Map<String, Object> getByPageCondition(long current, long limit, TeacherQuery teacherQuery) {
        Page<Teacher> teacherPage = new Page<>(current, limit);
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(teacherQuery.getName())) {
            wrapper.like("name", teacherQuery.getName());
        }
        if (!StringUtils.isEmpty(teacherQuery.getParentId())) {
            if (teacherQuery.getParentId().equals("0")) {
                wrapper.eq("parent_id", 0);
            }else wrapper.ne("parent_id", 0);
        }
        wrapper.orderByDesc("gmt_create");

        this.page(teacherPage, wrapper);

        Long total = teacherPage.getTotal();
        List<Teacher> records = teacherPage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("records", records);
        return map;
    }
}
