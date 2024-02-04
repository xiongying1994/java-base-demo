package Tool.jsonpash;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import java.util.List;

/**
 * jayway工具，按路径取json内容
 *
 * @Author: xiongying
 * @Date: 2022/11/28 11:26
 */
public class PraseJson {
    public static void main(String[] args) {
        String resp = "{\n" +
                "  \"store\": {\n" +
                "    \"book\": [{\n" +
                "      \"category\": \"reference\",\n" +
                "      \"author\": \"Nigel Rees\",\n" +
                "      \"title\": \"Sayings of the Century\",\n" +
                "      \"isbn\": 234,\n" +
                "      \"price\": 8.95\n" +
                "    }, {\n" +
                "      \"category\": \"fiction\",\n" +
                "      \"author\": \"Evelyn Waugh\",\n" +
                "      \"title\": \"Sword of Honour\",\n" +
                "      \"isbn\": null,\n" +
                "      \"price\": 129.95432\n" +
                "    }, {\n" +
                "      \"category\": \"fiction\",\n" +
                "      \"author\": \"Herman Melville\",\n" +
                "      \"title\": \"Moby Dick\",\n" +
                "      \"isbn\": \"0-553-21311-3\",\n" +
                "      \"price\": 3121233124899\n" +
                "    }, {\n" +
                "      \"category\": \"fiction\",\n" +
                "      \"author\": \"J. R. R. Tolkien\",\n" +
                "      \"title\": \"The Lord of the Rings\",\n" +
                "      \"isbn\": \"0-395-19395-8\",\n" +
                "      \"price\": 2299\n" +
                "    }\n" +
                "    ],\n" +
                "    \"bicycle\": {\n" +
                "      \"color\": \"red\",\n" +
                "      \"price\": 1995\n" +
                "    }\n" +
                "  }\n" +
                "}\n";
        ReadContext context = JsonPath.parse(resp);

        List<Object> list = (List<Object>) context.read("store.book[*].isbn");
        System.out.println(list);
        StringBuilder s1 = new StringBuilder();
        for (Object pa : list) {
            s1.append(pa).append(",");
        }
        System.out.println(s1.toString());
    }
}
