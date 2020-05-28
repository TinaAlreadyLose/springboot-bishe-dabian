package cc.akali.springboot.entity.vo.teacher;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: dan
 * @Date: 2020/5/27 20:55
 * @Description:
 */
@Data
public class Member {
    @ApiModelProperty(value = "教师id")
    private String id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "教师手机号")
    private String phone;
}
