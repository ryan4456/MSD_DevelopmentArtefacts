package com.luulsj.www.yuyue.server.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.jfinal.kit.Kv;
import com.jfinal.kit.LogKit;
import com.luulsj.www.yuyue.server.model.Point;
import com.luulsj.www.yuyue.server.util.DistanceUtil;

@ServerEndpoint("/websocket.ws/{token}")
public class WebSocketController {

    private static final Map<String, WebSocketController> WEB_SOCKET_MAP = new ConcurrentHashMap<String, WebSocketController>();
    private static final Map<String, Kv> WEB_KV = new ConcurrentHashMap<String, Kv>();
    private static Map<String, ArrayList<Point>> carMap = new ConcurrentHashMap<>();
    private static Point leadingCar = null;
    // the intersection coordinate
    private static Point intersection = new Point(-36.73678776210702d, 174.7463365484575d, 0);
    // if the car is in the range of the intersection 10 meters,
    // the server will send the car traffic light signals.
    private static final double DISTANCE_THRESHOLD = 100;

    private Session session;
    private String ids;


    @OnOpen
    public void onOpen(@PathParam("token") String ids, Session session){
        this.ids = ids;
        this.session = session;
        WEB_SOCKET_MAP.put(ids, this);

        LogKit.info("new connection！current number of users: " + WEB_SOCKET_MAP.size());
        LogKit.info("session=" + session.getId());
        LogKit.info("ids=" + this.ids);
        carMap.remove(ids);
    }

    /**
     * on close
     */
    @OnClose
    public void onClose(){
        WEB_SOCKET_MAP.remove(ids);
        LogKit.info("有一连接关闭！当前在线人数为" + WEB_SOCKET_MAP.size());
        LogKit.info("session=" + session.getId());
    }

    /**
     * on message
     * @param message message from the client
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        // store the information
        Point point = new Point(message);
        // check the distance
        if(DistanceUtil.checkInRange(point, intersection, DISTANCE_THRESHOLD)){
            ArrayList<Point> pointList = carMap.get(point.getToken());
            if(pointList == null){
                pointList = new ArrayList<>();
                carMap.put(point.getToken(), pointList);
            }
            // compute the direction
            pointList.add(point);
            if(pointList.size() > 1){
                // the car is getting away from the intersection, remove the car
                if(DistanceUtil.getDistance(point, intersection) >=
                        DistanceUtil.getDistance(pointList.get(pointList.size() - 2), intersection)){
                    carMap.remove(point.getToken());
                    if(point.equals(leadingCar)){
                        leadingCar = null;
                    }
                }else{
                    // select as the leading car
                    if(leadingCar == null){
                        leadingCar = point;
                        // pass green light
                        sendMessage(point.getToken(), "0");
                    }else{
                        // compute directions of (leading car, intersection) and (point, intersection)
                        // compare if they are the same direction
                        double leadingDirection = DistanceUtil.bearing(leadingCar, intersection);
                        double pointDirection = DistanceUtil.bearing(point, intersection);
                        double result = Math.abs(leadingDirection - pointDirection);
                        // same direction, send green light
                        if(result < 20 || (result > 170 && result < 190)){
                            sendMessage(point.getToken(), "0");
                        }else{
                            // vertical direction, send red light
                            sendMessage(point.getToken(), "1");
                        }
                        // store the most recent 10 points
                        if(pointList.size() > 10){
                            pointList.remove(0);
                        }
                    }
                }
            }
        }else{
            // clear the element
            if(carMap.get(point.getToken()) != null){
                carMap.remove(point.getToken());
            }
        }
    }

    /**
     * on error
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        LogKit.error("Error happened", error);
    }

    /**
     * send message to all clients
     */
    protected void sendAllMessage(String message){
        sendAllMessage(message, true);
    }

    /**
     * send all message
     * @param message message content
     * @param isThis send to self or not
     */
    protected void sendAllMessage(String message, boolean isThis){
        for(Entry<String, WebSocketController> entry: WEB_SOCKET_MAP.entrySet()){
            // send all message
            WebSocketController me = entry.getValue();
            if (isThis || me != this) {
                me.sendMessage(message);
            }
        }
    }

    /**
     * send message to a specific person
     */
    protected void sendMessage(String ids, String message){
        WebSocketController me = WEB_SOCKET_MAP.get(ids);
        if (me != null) {
            me.sendMessage(message);
        }else{
            this.sendMessage("【System message】" + getNameByIds(ids) + " offline");
        }
    }

    /**
     * send message to client
     */
    protected void sendMessage(String message){
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            LogKit.error("Client exception", e);
        }
        //this.session.getAsyncRemote().sendText(message);
    }

    public static Kv getKvByIds(String ids){
        Kv kv = WEB_KV.get(ids);
        if (kv == null) {
            kv = new Kv();
            WEB_KV.put(ids, kv);
        }
        return kv;
    }

    public static String getNameByIds(String ids){
        return getKvByIds(ids).getStr("name");
    }
}