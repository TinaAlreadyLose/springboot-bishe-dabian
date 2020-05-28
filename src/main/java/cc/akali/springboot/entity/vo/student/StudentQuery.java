package cc.akali.springboot.entity.vo.student;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: dan
 * @Date: 2020/5/28 8:29
 * @Description:
 */
@Data
public class StudentQuery {
    @ApiModelProperty(value = "学生姓名")
    private String name;
    @ApiModelProperty(value = "学生班级id")
    private String classId;
    @ApiModelProperty(value = "分组id")
    private String groupId;
    @ApiModelProperty(value = "最低分数")
    private BigDecimal minGrade;
    @ApiModelProperty(value = "最高分数")
    private BigDecimal maxGrade;
}
