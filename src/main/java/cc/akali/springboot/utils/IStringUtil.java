package cc.akali.springboot.utils;

/**
 * @Author: dan
 * @Date: 2020/5/28 16:20
 * @Description:
 */
public class IStringUtil {
    private IStringUtil() {
    }

    public static String excelStr(String string) {
        if(string.contains(","))
            return string.replace(",", "");
        int num = string.indexOf(".");
        if (num != -1) {
            return string.substring(0, num);
        }
        return string;
    }
}
