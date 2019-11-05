package javacore.fastjson;

import com.alibaba.fastjson.JSON;

/**
 * Created by www6v on 2019/7/26.
 */
public class FastjsonTest {
    public static void main(String args[]) {
        String s = "{\"method\":\"logup\",\"version\":\"6.9\",\"rpc_id\":\"abc\",\"mtype\":1,\"type\":1,\"stype\":1,\"ts\":111,\"aid\":\"aid1\",\"rid\":\"rid1\",\"sid\":\"sid1\",\"uid\":\"uid1\",\"streamid\":\"sid1\",\"data\":{\"rtt\":1,\"delay\":1,\"audio\":{\"br\":1,\"lostpre\":1,\"vol\":1,\"mime\":\"opus\"},\"video\":{\"br\":1,\"lostpre\":1,\"frt\":1,\"w\":1,\"h\":1,\"mime\":\"opus\"}}}";
        RtcClinetLog r = JSON.parseObject(s, RtcClinetLog.class);

        System.out.print( r.getData().getVideo().getBr() );
    }
}
