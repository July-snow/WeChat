package service;

import model.Users;
import org.apache.ibatis.session.SqlSession;
import po.TextMessage;
import util.DBUtil;
import util.MessageUtil;

import java.util.Date;
import java.util.Map;


/**
 * @Author: Silence
 * @Date: Create in 20:28 2017/11/14
 * @Description:
 */
public class WechatService {


    private static final String MESSAGE_TEXT= "text";
    private static final String MESSAGE_IMAGE= "image";
    private static final String MESSAGE_VOICE= "voice";
    private static final String MESSAGE_VIDEO= "video";
    private static final String MESSAGE_LINK= "link";
    private static final String MESSAGE_LOCATION= "location";
    private static final String MESSAGE_EVENT= "event";
    private static final String MESSAGE_SUBSCRIBE= "subscribe";
    private static final String MESSAGE_UNSUBSCRIBE= "unsubscribe";
    private static final String MESSAGE_CLICK= "CLICK";
    private static final String MESSAGE_VIEW= "VIEW";


    public static String initText(String ToUserName,String FromUserName,String content){

        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(ToUserName);
        textMessage.setToUserName(FromUserName);
        textMessage.setMsgType(MESSAGE_TEXT);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setContent(content);
        return MessageUtil.messageToXml(textMessage);
    }

    //主菜单信息
    public static String menuText(){
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注，请按以下提示进行操作：\n\r");
        sb.append("1、情况介绍\n\r");
        sb.append("2、信息查询\n\r");
        sb.append("回复？调出菜单\n\r");
        return sb.toString();
    }

    public static String firstText(){
        StringBuffer sb = new StringBuffer();
        sb.append("我也不知道，你是怎么看见时光不在\n\r");
        sb.append("只能听到风吹过，留下无边的念想\n\r");
        return sb.toString();
    }

    public static String secondText(){
        StringBuffer sb = new StringBuffer();
        sb.append("在盛夏的时光中，我没能看到你的脸\n\r");
        sb.append("唯有徒留自己在，无边的寒冬空望远方\n\r");

        return sb.toString();
    }

    public static String introduceText(){
        StringBuffer sb = new StringBuffer();
        sb.append("在那个教一的4楼，有着一间神奇的房子，它有个神奇的名字叫419，那有着一群欢乐的逗比，每天都有欢笑声传出。\n\r");

        return sb.toString();
    }

    public static String autoReply(Map<String,String> map){
        SqlSession sqlSession = DBUtil.getSqlSession();
        String sqlGetUser = "model.usermapping.getUser";
        String FromUserName = map.get("FromUserName");
        String ToUserName = map.get("ToUserName");
        String MsgType = map.get("MsgType");
        String content = map.get("Content");
        String message =null;

        if (MESSAGE_TEXT.equals(MsgType)){
            if("!".equals(content)||"！".equals(content)){
                content=firstText();

            }else if("#".equals(content)){
                content=secondText();
            }else if("419".equals(content)){
                content=introduceText();
            }else if("?".equals(content)||"？".equals(content)){
                content=menuText();
            }else{
                Users users = sqlSession.selectOne(sqlGetUser,content);
                if (users!=null){
                    content = users.getName()+"\n\r"+users.getTel()+"\n\r"+users.getEmail()+"\n\r";
                }
            }
            message = WechatService.initText(ToUserName, FromUserName, content);
        }else if (MESSAGE_EVENT.equals(MsgType)){
            String eventType = map.get("Event");
            if(MESSAGE_SUBSCRIBE.equals(eventType)){
                message = menuText();
            }else if(MESSAGE_UNSUBSCRIBE.equals(eventType)){

            }
        }
        return message;
    }
}
