package cc.akali.springboot.entity.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: dan
 * @Date: 2020/5/28 12:08
 * @Description:
 */
@Data
public class GradeStudent {
    @ApiModelProperty(value = "学生id")
    private String id;
    @ApiModelProperty(value = "成绩")
    private BigDecimal mark;
}
