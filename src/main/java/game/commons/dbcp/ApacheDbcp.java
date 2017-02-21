package game.commons.dbcp;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import java.sql.*;
import java.util.*;

/**
 * Created by Administrator on 2017/2/21.
 */
public class ApacheDbcp {

    private static BasicDataSource ds = null;

    static {
        init();
    }

    private static void init(){
        if (ds !=null){
            try {
                ds.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ds = null;
        }
        Properties p = new Properties();
        p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        p.setProperty("url", "jdbc:mysql://localhost:3306/majon?useUnicode=true&amp;characterEncoding=UTF-8");
        p.setProperty("password", "1234");
        p.setProperty("username", "root");
        p.setProperty("maxActive", "30");
        p.setProperty("maxIdle", "10");
        p.setProperty("maxWait", "1000");
        p.setProperty("removeAbandoned", "false");
        p.setProperty("removeAbandonedTimeout", "120");
        p.setProperty("testOnBorrow", "true");
        p.setProperty("logAbandoned", "true");
        try {
            ds = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized Connection getConnection(){
        Connection conn = null;
        if (ds==null)init();
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static List<PoMap> executeQuerySQL(String sqlStr){
        List<PoMap> list = new ArrayList<PoMap>();
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsd = null;
        try {
            ps = conn.prepareStatement(sqlStr);
            rs = ps.executeQuery();
            rsd = rs.getMetaData();
            List<String> columnNames = new ArrayList<String>();
            for (int i=1,len=rsd.getColumnCount();i<=len;i++){
                columnNames.add(rsd.getColumnName(i));
            }
            while (rs.next()){
                PoMap map = new PoMap();
                for (String name:columnNames){
                    map.put(name.toLowerCase(),rs.getString(name));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
            if (ps!=null)
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
