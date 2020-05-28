package cc.akali.springboot.service.impl;

import cc.akali.springboot.entity.Grade;
import cc.akali.springboot.entity.Teacher;
import cc.akali.springboot.entity.excel.TeacherExcel;
import cc.akali.springboot.entity.po.GradeQuery;
import cc.akali.springboot.entity.po.GradeQueryParent;
import cc.akali.springboot.entity.po.GradeStudent;
import cc.akali.springboot.entity.vo.teacher.Caption;
import cc.akali.springboot.entity.vo.teacher.Member;
import cc.akali.springboot.entity.vo.teacher.TeacherQuery;
import cc.akali.springboot.listener.TeacherListener;
import cc.akali.springboot.mapper.TeacherMapper;
import cc.akali.springboot.service.GradeService;
import cc.akali.springboot.service.TeacherService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

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
    @Autowired
    GradeService gradeService;

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

    @Override
    public GradeQuery<GradeStudent> getByMemberId(String id) {
        QueryWrapper<Grade> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", id);
        List<Grade> gradeList = gradeService.list(wrapper);
        GradeQuery<GradeStudent> result = new GradeQuery<>();
        result.setId(id);
        List<GradeStudent> gradeStudentList = new ArrayList<>();
        for (Grade grade : gradeList) {
            GradeStudent gradeStudent = new GradeStudent();
            BeanUtils.copyProperties(grade, gradeStudent);
            gradeStudentList.add(gradeStudent);
        }
        result.setChildren(gradeStudentList);
        return result;
    }

    @Override
    public GradeQueryParent<GradeQuery<GradeStudent>> getByCaptionId(String id) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Teacher> teacherList = this.list(wrapper);
        GradeQueryParent<GradeQuery<GradeStudent>> result = new GradeQueryParent<>();
        result.setId(id);
        List<GradeQuery<GradeStudent>> gradeQueryList = new ArrayList<>();
        for (Teacher teacher : teacherList) {
            GradeQuery<GradeStudent> gradeQuery = this.getByMemberId(teacher.getId());
            gradeQueryList.add(gradeQuery);
        }
        result.setChildren(gradeQueryList);
        return result;
    }

    @Override
    public boolean saveByExcel(MultipartFile file, TeacherService teacherService) {
        try{
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, TeacherExcel.class, new TeacherListener(teacherService)).sheet().doRead();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String downLoad()  {
        String projectPath = System.getProperty("user.dir");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String filePath=projectPath+"\\src\\main\\resources\\static\\excel\\";
        String fileName=uuid+".xls";
        EasyExcel.write(filePath + fileName, TeacherExcel.class).sheet("教师表").doWrite(this.getData());
        return filePath + fileName;
    }
    private List<TeacherExcel>getData(){
        List<Teacher> teacherList=this.list(null);
        List<TeacherExcel> teacherExcelList = new ArrayList<>();
        for (Teacher teacher : teacherList) {
            TeacherExcel teacherExcel = new TeacherExcel();
            BeanUtils.copyProperties(teacher,teacherExcel);
            teacherExcelList.add(teacherExcel);
        }
        return teacherExcelList;
    }
}
