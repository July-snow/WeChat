package test;

import model.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import util.DBUtil;

import java.io.IOException;
import java.io.Reader;

/**
 * @Author: Silence
 * @Date: Create in 21:38 2017/11/14
 * @Description:
 */
public class Main {
    public static void main(String[] args) throws IOException {

        SqlSession sqlSession =  DBUtil.getSqlSession();
        String sql = "model.usermapping.getUser";

        Users users = sqlSession.selectOne(sql,"郭艳");
        System.out.println(users);
    }
}
