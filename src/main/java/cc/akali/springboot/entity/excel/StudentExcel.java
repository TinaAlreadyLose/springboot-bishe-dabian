package cc.akali.springboot.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: dan
 * @Date: 2020/5/28 14:30
 * @Description:
 */
@Data
public class StudentExcel {
    @ApiModelProperty(value = "学生姓名")
    @ExcelProperty(index = 0,value = "学生姓名")
    private String name;

    @ApiModelProperty(value = "毕设题目")
    @ExcelProperty(index = 1,value = "毕设题目")
    private String title;

    @ApiModelProperty(value = "学生班级id")
    @ExcelProperty(index = 2,value = "学生班级id")
    private String classId;

    @ExcelProperty(index = 3,value = "所属答辩小组")
    @ApiModelProperty(value = "分组id")
    private String groupId;
}
