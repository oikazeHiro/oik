package com.oik.util.uncategorized;

/**
 * @author :BiJie
 * @version 1.0
 * @crete
 * @date :2022/6/17  11:26
 */
import java.math.BigDecimal;


/**
 * 距离跑龙套
 *
 * @author LEAF
 * @date 2022-09-07
 */
public class DistanceUtil {
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：千米)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static BigDecimal getDistance(double lat1, double lng1, double lat2,
                                         double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        BigDecimal bd = new BigDecimal(s);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);    //保留小数点后两位
        BigDecimal decimal = new BigDecimal(1000);
        return bd.multiply(decimal);
    }
}
