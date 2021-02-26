package com.xandone.yblog.utils;

import com.xandone.yblog.pojo.ArchiveBean;

import java.util.Comparator;
import java.util.Date;

/**
 * @author ：xandone
 * created on  ：2021/2/26 10:33
 * description：
 */
public class ComparatorDate implements Comparator<ArchiveBean> {

    public int compare(ArchiveBean obj1, ArchiveBean obj2) {
        //   return t1.getTradetime().compareTo(t2.getTradetime());  // 时间格式不好，不然可以直接这样比较
        Date d1, d2;
        try {
            d1 = DateUtils.string2Date(obj1.getPostTime());
            d2 = DateUtils.string2Date(obj2.getPostTime());
            if (d1 == null) {
                return 1;
            }
            if (d2 == null) {
                return -1;
            }
            if (d1.before(d2)) {
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            return 0;
        }

    }
}
