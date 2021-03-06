package cc.akali.springboot.service.impl;

import cc.akali.springboot.entity.Grade;
import cc.akali.springboot.mapper.GradeMapper;
import cc.akali.springboot.service.GradeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者名字
 * @since 2020-05-27
 */
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Override
    public boolean deleteById(String column,String id) {
        QueryWrapper<Grade> wrapper = new QueryWrapper<>();
        wrapper.eq(column, id);
        return this.remove(wrapper);
    }
}
