package cc.akali.springboot.entity.vo.teacher;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: dan
 * @Date: 2020/5/27 20:52
 * @Description:
 */
@Data
public class Caption {
    @ApiModelProperty(value = "教师id")
    private String id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "教师手机号")
    private String phone;
    @ApiModelProperty(value = "教师组员,体现一对多关系")
    private List<Member> children;
}
