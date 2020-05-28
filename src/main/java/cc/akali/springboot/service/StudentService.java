package cc.akali.springboot.service;

import cc.akali.springboot.entity.Student;
import cc.akali.springboot.entity.vo.student.StudentQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者名字
 * @since 2020-05-27
 */
public interface StudentService extends IService<Student> {

    Map<String, Object> getByPageCondition(long current, long limit, StudentQuery studentQuery);

    boolean saveByExcel(MultipartFile file, StudentService studentService);

    String downLoad();
}
