package javacore.string;

/**
 * Created by www6v on 2019/9/20.
 */
public class StringTest {
    public static void main(String args[]) {
//        String abnormal = "{\"stype\":2,\"version\":\"1.0\",\"mtype\":0,\"rpc_id\":\"1100000\",\"uid\":\"966\",\"rid\":\"789\",\"type\":1,\"sid\":\"1\",\"data\":{\"rtt\":0,\"delay\":1174,\"audio\":{\"br\":0,\"lostpre\":0,\"vol\":0,\"mime\":\"opus\"},\"video\":{\"mime\":\"VP8\",\"br\":487458,\"w\":640,\"lostpre\":0,\"frt\":15,\"h\":480}},\"streamid\":\"4905f372-700e-4541-83ac-d664f43602ea\",\"ts\":\"1568886810\",\"aid\":\"URtc-h4r1txxy\",\"method\":\"logup\"}\n";
        String normal = "{\"method\":\"logup\",\"version\":\"1.0\",\"mtype\":1,\"type\":2,\"stype\":1,\"streamid\":\"d0711f6ddc0a10dec81011338814_1\",\"ts\":1568886804,\"data\":{\"rtt\":0,\"delay\":0,\"audio\":{\"br\":39298,\"lostpre\":0,\"vol\":0,\"mime\":\"opus\"},\"video\":{\"br\":309890,\"lostpre\":0,\"frt\":30,\"w\":352,\"h\":288,\"mime\":\"VP8\"}},\"aid\":\"URtc-h4r1txxy\",\"rid\":\"159\",\"sid\":\"\",\"uid\":\"android_465f8fc034ca4795b1bcf09e81fe12e3\",\"rpc_id\":\"android_native_20190919175324_100000000408\"}\n";

        int i = normal.indexOf("rpc_id");
        int length = "rpc_id\":\"".length();

        int index = i + length;

        String substring = normal.substring(index, index + 10);
//        System.out.println(substring);

        if( substring.contains("android") || substring.contains("ios") ||substring.contains("win") ) {
            System.out.print("true");
        }

        System.out.print("false");

//        System.out.print(i);
    }
}
