package com.mini.server.tools;

import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mini.server.dao.DaoException;

public class JDBC {
    
    private JDBC() {
        
    }

    /* 数据源 */
    private static ComboPooledDataSource cpds =null;
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    
    /* 数据源配置*/
    static {
//    	System.out.println("JDBC static......");
        try {
        	driver="com.mysql.jdbc.Driver";
        	
//        	url="jdbc:mysql://192.168.1.21:3306/zypush";
//        	user="zypush";
//        	password="zypush";
     
            url="jdbc:mysql://127.0.0.1:3306/dg";
            user="dg";
            password="dg*#123DG";
        	
            cpds=new ComboPooledDataSource();
            
            if(cpds==null){            	
            	System.err.println("cpds is null");
            }
            
            cpds.setDriverClass(driver);
            cpds.setJdbcUrl(url);
            cpds.setUser(user);
            cpds.setPassword(password);
            cpds.setPreferredTestQuery("select 1");
            cpds.setIdleConnectionTestPeriod(18000);
            cpds.setMaxIdleTime(25000);
            cpds.setTestConnectionOnCheckout(true);
            cpds.setMinPoolSize(5);
            cpds.setInitialPoolSize(10);
            cpds.setMaxPoolSize(100);

//            System.out.println("------------------------------------------");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   public static ComboPooledDataSource getDataSource() {
//	   System.out.println("get DataSource");
       return cpds;
   }
   
   public static void freeDataSources() throws SQLException {
       cpds.close();
   }
   
   public static Connection getConnectWithOutTransaction() {
       Connection conn = null;
       try {
           conn = cpds.getConnection();
       }
       catch (SQLException e) {
           e.printStackTrace();
       }
       return conn;
   }
   
   public static void closeConnect(Connection conn) {
       if(conn!=null) {
       try {
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
       }
   }
   
   private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
   
    /**
     * 根据数据库的默认连接参数获取数据库的Connection对象，并绑定到当前线程上
     * @return 成功，返回Connection对象，否则返回null
     */
    public static synchronized Connection getConnection() {
        Connection conn = tl.get();                               // 先从当前线程上取出连接实例
        if (null == conn){                           // 如果当前线程上没有Connection的实例
            try {
                conn = cpds.getConnection();                      // 从连接池中取出一个连接实例
                tl.set(conn);                                     // 把它绑定到当前线程上
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 获取事务管理器
     * @return 事务管理实例
     */
    public static synchronized TransactionManager getTranManager() {
        return new TransactionManager(getConnection());
    }

    /**
     * 关闭数据库连接，并卸装线程绑定
     * 要关闭数据库连接实例
     */
    protected static void close(Connection conn) throws DaoException {
        if (conn != null) {
            try {
                tl.remove();                                       // 卸装线程绑定
                conn.close();
            }
            catch (SQLException e) {
                throw new DaoException("关闭连接时出现异常", e);
            }
        }
    }
}
