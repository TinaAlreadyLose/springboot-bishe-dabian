package cc.akali.springboot.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: dan
 * @Date: 2020/5/28 12:04
 * @Description:
 */
@Data
public class GradeQueryParent<T> {
    @ApiModelProperty(value = "教师组长id")
    private String id;
    @ApiModelProperty(value = "组长管理的组员信息")
    private List<T> children;
}
