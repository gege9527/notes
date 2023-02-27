
import cn.hutool.core.util.XmlUtil;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


public class App {
    public static void main(String[] args) {
        if(args.length < 0 ){
            return;
        } else {
            String str = readLocalFile(args[0],args[1]);
            // 把Xml转成jsonObject
            JSONObject jsonObject = xmlToJson(str);
            System.out.println(jsonObject.toString());
            System.out.println("------------------------------------");
            // 把jsonObject转成xml
            
        }
        
    }

    public static String readLocalFile(String type, String path) {
        String filePath = "C:\\Users\\LCL\\Desktop\\java\\XMLFile.xml";
        String str = "";
        try {
            byte[]  bytes = Files.readAllBytes(Paths.get(filePath));
            str = new String(bytes, StandardCharsets.UTF_8);
            if(type == "xml"){
                String xmlData = jsonToXml(jsonObject);
                System.out.println(xmlData);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 把xml转成JSONObject
     *
     * @param xml xml数据
     * @return 转换后的JSONObject数据
     */
    public static JSONObject xmlToJson(String xml) {
        // 去除xml开头和结尾的双引号
        StringBuilder sb = new StringBuilder();
        sb.append(xml);
        if (sb.charAt(0) == '\"') {
            sb.deleteCharAt(0);
        }
        if (sb.charAt(sb.length() - 1) == '\"') {
            sb.deleteCharAt(sb.length() - 1);
        }
        Map<String, Object> stringObjectMap = XmlUtil.xmlToMap(sb.toString());
        String json = JSONObject.toJSONString(stringObjectMap);
        return JSONObject.parseObject(json, JSONObject.class);
    }

    /**
     * 把JSONObject转成xml
     *
     * @param json JSONObject数据
     * @return 转换后的xml
     */
    public static String jsonToXml(JSONObject json) {
        Map tmpMap = json.toJavaObject(Map.class);
        return XmlUtil.mapToXmlStr(tmpMap);
    }
}
