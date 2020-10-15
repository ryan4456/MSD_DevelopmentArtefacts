package com.luulsj.www.yuyue.server.model;

import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

public class Point {

    private double latitude;
    private double longitude;
    private double speed;
    private String token;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(token, point.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    public Point(String message){
        JSONObject messageObject = JSONObject.parseObject(message);
        setLatitude(messageObject.getDouble("latitude"));
        setLongitude(messageObject.getDouble("longitude"));
        if(messageObject.containsKey("speed")){
            try {
                setSpeed(messageObject.getDouble("speed"));
            }catch (NullPointerException e){
            }
        }
        setToken(messageObject.getString("token"));

    }

    public Point(double latitude, double longitude, double speed) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Point{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", speed=" + speed +
                ", token='" + token + '\'' +
                '}';
    }
}
