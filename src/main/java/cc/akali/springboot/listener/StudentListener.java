package cc.akali.springboot.listener;

import cc.akali.springboot.entity.Student;
import cc.akali.springboot.entity.excel.StudentExcel;
import cc.akali.springboot.service.StudentService;
import cc.akali.springboot.utils.IStringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * @Author: dan
 * @Date: 2020/5/28 16:14
 * @Description:
 */
public class StudentListener extends AnalysisEventListener<StudentExcel> {
    public StudentService studentService;

    public StudentListener(StudentService studentService) {
        this.studentService = studentService;
    }

    public StudentListener() {
    }

    @Override
    public void invoke(StudentExcel studentExcel, AnalysisContext analysisContext) {
        if (studentExcel != null) {
            Student student = new Student();
            student.setName(studentExcel.getName());
            student.setTitle(studentExcel.getTitle());
            student.setClassId(IStringUtil.excelStr(studentExcel.getClassId()));
            student.setGroupId(IStringUtil.excelStr(studentExcel.getGroupId()));
            studentService.save(student);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
