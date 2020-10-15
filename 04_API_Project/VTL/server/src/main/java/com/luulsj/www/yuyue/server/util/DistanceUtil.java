package com.luulsj.www.yuyue.server.util;

import com.luulsj.www.yuyue.server.model.Point;

public class DistanceUtil {


    public static boolean checkInRange(Point point, Point intersection, double threshold){
        double distance = getDistance(point, intersection);
        return distance <= threshold;
    }

    private static double getAngle(Point point, Point intersection) {
        if(point.getLatitude() > intersection.getLatitude()){
            Point temp = point;
            point = intersection;
            intersection = temp;
        }

        double y = Math.sin(point.getLongitude()-intersection.getLongitude()) * Math.cos(intersection.getLongitude());
        double x = Math.cos(point.getLatitude())*Math.sin(intersection.getLatitude()) - Math.sin(point.getLatitude())*Math.cos(intersection.getLatitude())*Math.cos(intersection.getLongitude()-point.getLongitude());
        double brng = Math.atan2(y, x);

        brng = Math.toDegrees(brng);
        if(brng < 0)
            brng = brng +360;
        return brng;
    }

    public static double bearing(Point point, Point intersection){
        double lat1 = point.getLatitude();
        double lon1 = point.getLongitude();
        double lat2 = intersection.getLatitude();
        double lon2 = intersection.getLongitude();
        double longitude1 = lon1;
        double longitude2 = lon2;
        double latitude1 = Math.toRadians(lat1);
        double latitude2 = Math.toRadians(lat2);
        double longDiff = Math.toRadians(longitude2-longitude1);
        double y = Math.sin(longDiff)*Math.cos(latitude2);
        double x = Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);
        return (Math.toDegrees(Math.atan2(y, x))+360)%360;
    }


    private static final  double EARTH_RADIUS = 6378137;//赤道半径(单位m)

    /**
     * 转化为弧度(rad)
     * */
    private static double rad(double d)
    {
        return d * Math.PI / 180.0d;
    }

    /**
     * 基于余弦定理求两经纬度距离
     * @return 返回的距离，单位m
     * */
    public static double getDistance(Point point, Point intersection) {
        double lon1 = point.getLongitude();
        double lat1 = point.getLatitude();
        double lon2 = intersection.getLongitude();
        double lat2 = intersection.getLatitude();
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        //此处加上double类型转换是因为对于在几百的距离差值之前计算为0，无法达到预期效果
        s = (double)Math.round(s * 10000) / 10000;
        s = s * 10000/ 10000;
        return s * 1000d;
    }

    public static void main(String[] args) {
        Point intersection = new Point(-36.73678776210702d, 174.7463365489d, 0);
        Point point1 = new Point(-36.73678776210702d, 174.746336458457d, 0);
        Point point2 = new Point(-36.73678776210702d, 174.746336508457d, 0);


        System.out.println(getDistance(intersection, point1));
        System.out.println(getDistance(intersection, point2));
    }

}
