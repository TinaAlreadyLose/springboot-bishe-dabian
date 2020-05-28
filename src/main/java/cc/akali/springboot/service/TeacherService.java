package cc.akali.springboot.service;

import cc.akali.springboot.entity.Teacher;
import cc.akali.springboot.entity.po.GradeQuery;
import cc.akali.springboot.entity.po.GradeQueryParent;
import cc.akali.springboot.entity.po.GradeStudent;
import cc.akali.springboot.entity.vo.teacher.Caption;
import cc.akali.springboot.entity.vo.teacher.TeacherQuery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者名字
 * @since 2020-05-27
 */
public interface TeacherService extends IService<Teacher> {

    List<Caption> getAllByGroup();

    Map<String, Object> getByPageCondition(long current, long limit, TeacherQuery teacherQuery);

    GradeQuery<GradeStudent> getByMemberId(String id);

    GradeQueryParent<GradeQuery<GradeStudent>> getByCaptionId(String id);
}
