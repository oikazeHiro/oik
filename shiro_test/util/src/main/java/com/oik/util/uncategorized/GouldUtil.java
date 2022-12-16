package com.oik.util.uncategorized;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * <p>
 * 高德地图工具类
 * <p>
 *
 * @className: GouldUtil
 * @author: LEAF
 * @verison: 1.00
 * @create: 2022-09-09 10:12
 */
@Component
public class GouldUtil {

    //在高德申请的应用Key
    public static final String GOULD_KEY = "b51d48bb6158909f69d8135b9c3d737c";

    //申请的账户Key

    /**
     * 0.根据地址名称得到两个地址间的距离
     *
     * @param start 起始位置
     * @param start 结束位置
     * @return 两个地址间的距离
     */
    public long getDistanceByAddress(String start, String end) {
        String startLonLat = getLonLat(start);
        String endLonLat = getLonLat(end);
        long dis = getDistance(startLonLat, endLonLat);
        return dis;
    }

    /**
     * 1.地址转换为经纬度
     *
     * @param address 地址
     * @return 经纬度
     */
    public String getLonLat(String address) {
        // 返回输入地址address的经纬度信息, 格式是 经度,纬度
        String queryUrl = "http://restapi.amap.com/v3/geocode/geo?key=" + GOULD_KEY + "&address=" + address;
        String queryResult = getResponse(queryUrl); // 高德接品返回的是JSON格式的字符串
        JSONObject job = JSONObject.parseObject(queryResult);
        JSONObject jobJSON = JSONObject
                .parseObject(job.get("geocodes").toString().substring(1, job.get("geocodes").toString().length() - 1));
        String DZ = jobJSON.get("location").toString();
//		System.out.println("经纬度：" + DZ);
        return DZ;
    }

    /**
     * 将经纬度getLng， getLat 通过getAMapByLngAndLat方法转换地址
     *
     * @param getLng 经度
     * @param getLat 纬度
     * @return 地址名称
     * @throws Exception
     */
    public String getAMapByLngAndLat(String getLng, String getLat) throws Exception {
        String url;
        try {
            url = "http://restapi.amap.com/v3/geocode/regeo?output=JSON&location=" + getLng + "," + getLat
                    + "&key=" + GOULD_KEY + "&radius=0&extensions=base";
            System.out.println(getLat
            );
            System.out.println(url);
            String queryResult = getResponse(url); // 高德接品返回的是JSON格式的字符串
            if (queryResult == null) {
                return "-1";
            }
            // 将获取结果转为json 数据
            JSONObject obj = JSONObject.parseObject(queryResult);
            if (obj.get("status").toString().equals("1")) {
                // 如果没有返回-1
                JSONObject regeocode = obj.getJSONObject("regeocode");
                if (regeocode.size() > 0) {
                    // 在regeocode中拿到 formatted_address 具体位置
                    String formatted = regeocode.get("formatted_address").toString();
                    return formatted;

                } else {
                    System.out.println("未找到相匹配的地址！");
                    return "-1";

                }
            } else {
                System.out.println("请求错误！");
                return "-1";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "-1";
    }

    /**
     * 2.根据两个定位点的经纬度算出两点间的距离
     *
     * @param startLonLat 起始经纬度
     * @param endLonLat   结束经纬度（目标经纬度）
     * @return 两个定位点之间的距离
     */
    private long getDistance(String startLonLat, String endLonLat) {
        // 返回起始地startAddr与目的地endAddr之间的距离，单位：米
        Long result = new Long(0);
        String queryUrl = "http://restapi.amap.com/v3/distance?key=" + GOULD_KEY + "&origins=" + startLonLat + "&destination="
                + endLonLat;
        String queryResult = getResponse(queryUrl);
        JSONObject job = JSONObject.parseObject(queryResult);
        JSONArray ja = job.getJSONArray("results");
        JSONObject jobO = JSONObject.parseObject(ja.getString(0));
        result = Long.parseLong(jobO.get("distance").toString());
//		System.out.println("距离2：" + result);
        return result;
    }

    /**
     * 3.发送请求
     *
     * @param serverUrl 请求地址
     */
    private static String getResponse(String serverUrl) {
        // 用JAVA发起http请求，并返回json格式的结果
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
