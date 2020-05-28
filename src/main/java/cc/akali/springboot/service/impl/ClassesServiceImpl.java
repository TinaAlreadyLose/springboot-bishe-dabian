package cc.akali.springboot.service.impl;

import cc.akali.springboot.entity.Classes;
import cc.akali.springboot.mapper.ClassesMapper;
import cc.akali.springboot.service.ClassesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者名字
 * @since 2020-05-27
 */
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {

}
