package servlet;

import org.dom4j.DocumentException;
import service.WechatService;
import util.CheckUtil;
import util.MessageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Author: Silence
 * @Date: Create in 14:26 2017/11/14
 * @Description:
 */
public class WeChatServlet extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getRequestURI());
        System.out.println(req.getQueryString());
        String signature = req.getParameter("signature");
        System.out.println(signature);
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        PrintWriter out = resp.getWriter();
        System.out.println("********");
        if(CheckUtil.checkSignature(signature, timestamp, nonce)){
            out.print(echostr);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        try {
            Map<String,String> map = MessageUtil.xmlToMap(req);

            out.print(WechatService.autoReply(map));
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }
}
