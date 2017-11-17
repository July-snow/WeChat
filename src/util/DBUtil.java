package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @Author: Silence
 * @Date: Create in 21:42 2017/11/14
 * @Description:
 */
public class DBUtil {
    private static SqlSessionFactory sessionFactory;
    private static Reader reader;
    private DBUtil(){}
    private static String RESOURCE = "mybatis-config.xml";

    static {
        //使用mybatis的Resources加载mybatis的配置文件（它也加载关联的映射文件）
        try {
            reader = Resources.getResourceAsReader(RESOURCE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建sqlSessionFactory
        sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }
    public static SqlSession getSqlSession(){
        return sessionFactory.openSession();
    }
}
