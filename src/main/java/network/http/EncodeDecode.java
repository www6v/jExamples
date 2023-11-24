package network.http;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class EncodeDecode {
    public static void main(String[] args) {
        try {
//            String url = "测试中文串";
            String url = "LH:=GPJYVALUE(2,1,2)/10000-GPJYVALUE(2,2,2)/10000;\nJG:=GPJYVALUE(9,2,2)/10000-GPJYVALUE(8,2,2)/10000;\nLGT:=GPJYVALUE(18,1,2)/10000-GPJYVALUE(18,2,2)/10000;\nYZ:=GPJYVALUE(17,1,2)/10000-GPJYVALUE(17,2,2)/10000;\n龙虎榜资金（亿）:LH,NODRAW,COLORSNOW;\n游资资金（亿）:YZ,NODRAW,COLORRED;\n机构资金（亿）:JG,NODRAW,COLORYELLOW;\n沪股通资金（亿）:LGT,NODRAW,COLORPURPLE;\nSTICKLINE(机构资金（亿）>0,0,机构资金（亿）,3.5,-1),COLORYELLOW;\nSTICKLINE(机构资金（亿）<0,机构资金（亿）,0,3.5,-1),COLORYELLOW;\nSTICKLINE(沪股通资金（亿）>0,0,沪股通资金（亿）,4,-1),COLORPURPLE;\nSTICKLINE(沪股通资金（亿）<0,沪股通资金（亿）,0,4,-1),COLORPURPLE;\nSTICKLINE(游资资金（亿）>0,0,游资资金（亿）,3,0),COLOR0000AA;\nSTICKLINE(游资资金（亿）>0,0,游资资金（亿）,2,0),COLOR0000CC;\nSTICKLINE(游资资金（亿）>0,0,游资资金（亿）,1,0),COLOR0000FF;\nSTICKLINE(游资资金（亿）<0,游资资金（亿）,0,3,0),COLOR00AA00;\nSTICKLINE(游资资金（亿）<0,游资资金（亿）,0,2,0),COLOR00CC00;\nSTICKLINE(游资资金（亿）<0,游资资金（亿）,0,1,0),COLOR00FF00;\nDRAWTEXT_FIX( CURRBARSCOUNT = 1 AND NOT(SUM(LH,60)>0) AND NOT(SUM(LH,60)<0) ,0.5,0.1,0,'提示：该股近三个月未上龙虎榜主力席位'),COLORWHITE;\n";
            System.out.println(url);

            //通过utf-8编码把中文字符串转化为application/x-www-form-urlencodedMIME字符串
            String encodeUrl = URLEncoder.encode(url, "utf-8");
            System.out.println(encodeUrl);

            //同样通过utf-8编码把application/x-www-form-urlencodedMIME字符串解码为原来的字符串
            String decodeUrl = URLDecoder.decode(encodeUrl, "utf-8");
            System.out.println(decodeUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
