package com.tqz.datapermission.v2.core.helper;

import cn.hutool.core.convert.Convert;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Formatter;

/**
 * 数据库助手
 *
 * @author Lion Li
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataBaseHelper {

    public static String findInSet(Object var1, String var2) {
        String var = Convert.toStr(var1);
        return formatted("find_in_set('%s' , %s) <> 0", var, var2);
    }

    private static String formatted(String sqlStr, String str, String var2) {
        Formatter formatter = new Formatter();
        return formatter.format(sqlStr, str, var2).toString();
    }
}
