package util;


import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import po.TextMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Silence
 * @Date: Create in 16:24 2017/11/14
 * @Description:
 */
public class MessageUtil {


    public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        /** 
        * @author: Yc 
        * @time 2017/11/14 16:30 
        * @param request 
        * @return java.util.Map<java.lang.String,java.lang.String> 
        * @description: xml文件转为Map集合
        */
        Map<String,String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream is = request.getInputStream();
        Document doc = reader.read(is);
        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for (Element element : list) {
            map.put(element.getName(),element.getText());
        }
        is.close();
        return map;

    }

    public static String messageToXml(TextMessage textMessage){
        /**
        * @author: Yc
        * @time 2017/11/14 16:34
        * @param
        * @return java.lang.String
        * @description: 将定义的信息类转换为xml
        */
        XStream xStream = new XStream();
        xStream.alias("xml",TextMessage.class);
        String str = xStream.toXML(textMessage);
        return str;
    }
}
