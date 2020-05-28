package cc.akali.springboot.service;

import cc.akali.springboot.entity.Grade;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者名字
 * @since 2020-05-27
 */
public interface GradeService extends IService<Grade> {

    boolean deleteById(String column,String id);
}
