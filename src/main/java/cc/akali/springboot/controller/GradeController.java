package cc.akali.springboot.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者名字
 * @since 2020-05-27
 */
@RestController
@RequestMapping("/springboot/grade")
@Api(tags = "操作学生答辩得分表(grade)")
@CrossOrigin
public class GradeController {

}

