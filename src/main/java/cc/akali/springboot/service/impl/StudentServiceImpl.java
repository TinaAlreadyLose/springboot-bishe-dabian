package cc.akali.springboot.service.impl;

import cc.akali.springboot.entity.Student;
import cc.akali.springboot.entity.Teacher;
import cc.akali.springboot.entity.excel.StudentExcel;
import cc.akali.springboot.entity.excel.TeacherExcel;
import cc.akali.springboot.entity.vo.student.StudentQuery;
import cc.akali.springboot.listener.StudentListener;
import cc.akali.springboot.listener.TeacherListener;
import cc.akali.springboot.mapper.StudentMapper;
import cc.akali.springboot.service.StudentService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public Map<String, Object> getByPageCondition(long current, long limit, StudentQuery studentQuery) {
        Page<Student> studentPage = new Page<>(current, limit);
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(studentQuery.getName()))
            wrapper.like("name", studentQuery.getName());
        if(!StringUtils.isEmpty(studentQuery.getClassId()))
            wrapper.eq("class_id", studentQuery.getClassId());
        if(!StringUtils.isEmpty(studentQuery.getGroupId()))
            wrapper.eq("group_id", studentQuery.getGroupId());
        if(!StringUtils.isEmpty(studentQuery.getMinGrade()))
            wrapper.ge("final_grade", studentQuery.getMinGrade());
        if (!StringUtils.isEmpty(studentQuery.getMaxGrade()))
            wrapper.le("final_grade", studentQuery.getMaxGrade());

        wrapper.orderByDesc("gmt_create");

        this.page(studentPage, wrapper);
        Long total = studentPage.getTotal();
        List<Student> records = studentPage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("records", records);
        return map;
    }

    @Override
    public boolean saveByExcel(MultipartFile file, StudentService studentService) {
        try{
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, StudentExcel.class, new StudentListener(studentService)).sheet().doRead();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String downLoad() {
        String projectPath = System.getProperty("user.dir");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String filePath=projectPath+"\\src\\main\\resources\\static\\excel\\";
        String fileName=uuid+".xls";
        EasyExcel.write(filePath + fileName, StudentExcel.class).sheet("学生表").doWrite(this.getData());
        return filePath + fileName;
    }
    private List<StudentExcel>getData(){
        List<Student> studentList=this.list(null);
        List<StudentExcel> studentExcelList = new ArrayList<>();
        for (Student student : studentList) {
            StudentExcel studentExcel = new StudentExcel();
            BeanUtils.copyProperties(student,studentExcel);
            studentExcelList.add(studentExcel);
        }
        return studentExcelList;
    }

}
