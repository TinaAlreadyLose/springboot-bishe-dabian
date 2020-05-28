package cc.akali.springboot.listener;

import cc.akali.springboot.entity.Teacher;
import cc.akali.springboot.entity.excel.TeacherExcel;
import cc.akali.springboot.service.TeacherService;
import cc.akali.springboot.utils.IStringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @Author: dan
 * @Date: 2020/5/28 14:45
 * @Description:
 */
@Slf4j
public class TeacherListener extends AnalysisEventListener<TeacherExcel> {

    public TeacherService teacherService;

    public TeacherListener(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public TeacherListener() {}

    @Override
    public void invoke(TeacherExcel teacherExcel, AnalysisContext analysisContext) {
        if (teacherExcel != null) {
            Teacher teacher = new Teacher();
            teacher.setName(teacherExcel.getName());
            teacher.setPhone(teacherExcel.getPhone());
//            //解决科学技术的逗号
//            if (teacherExcel.getParentId().contains(","))
//                teacherExcel.setParentId(teacherExcel.getParentId().replace(",", ""));
//            int num = teacherExcel.getParentId().indexOf(".");
//            //解决小数字自动填充.0
//            if(num!=-1)
//                teacherExcel.setParentId(teacherExcel.getParentId().substring(0,num));
//            teacher.setParentId(teacherExcel.getParentId());
            teacher.setParentId(IStringUtil.excelStr(teacherExcel.getParentId()));
//            log.info(teacherExcel.getParentId());
            teacherService.save(teacher);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
