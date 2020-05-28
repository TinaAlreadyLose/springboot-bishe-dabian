package cc.akali.springboot.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: dan
 * @Date: 2020/5/28 14:29
 * @Description:
 */
@Data
public class TeacherExcel {
    @ExcelProperty(index = 0,value = "教师姓名")
    @ApiModelProperty(value = "姓名")
    private String name;

    @ExcelProperty(index = 1,value = "教师手机号")
    @ApiModelProperty(value = "教师手机号")
    private String phone;

    @ExcelProperty(index = 2,value = "教师父id(组长id)")
    @ApiModelProperty(value = "父id,,组长的父id是0,组员的父id是组长的id")
    private String parentId;
}
