package cc.akali.springboot.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: dan
 * @Date: 2020/5/28 11:59
 * @Description:
 */
@Data
public class GradeQuery<T>{
    @ApiModelProperty(value = "教师成员id")
    private String id;
    @ApiModelProperty(value = "成员管理的学生信息")
    private List<T> children;
}
