package javacore.other;

import org.apache.commons.lang3.StringEscapeUtils;

public class Temp {

    public static void main(String args[]) {
        String json = "{&quot;width&quot;:1, &quot;height&quot;:2 }";
        String newJson = StringEscapeUtils.unescapeHtml4(json);
        System.out.println(newJson);

    }
}