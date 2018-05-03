package uuid;

import java.util.UUID;

/**
 * Created by wangwei110 on 2018/4/16.
 */
public class UUIDtest {

    public static String generateSpanId() {
        return String.valueOf(UUID.randomUUID().getLeastSignificantBits()).replace("-", "");
    }

    public static void main(String args[]) {
        for (int i = 0; i < 100; i++) {
           System.out.println(generateSpanId());
        }
    }
}
