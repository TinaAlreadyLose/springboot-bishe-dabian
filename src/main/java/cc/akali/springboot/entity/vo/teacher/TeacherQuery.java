package cc.akali.springboot.entity.vo.teacher;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: dan
 * @Date: 2020/5/28 7:00
 * @Description:
 */
@Data
public class TeacherQuery {
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "父id,,组长的父id是0,组员的父id是组长的id")
    private String parentId;
}
