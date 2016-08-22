/**
 * Site:http://www.tjitcast.com
 */
package com.mini.server.tools;
import java.sql.Connection;
import java.sql.SQLException;

import com.mini.server.dao.DaoException;

/**
 * 事务管理器
 */
public class TransactionManager {

    private Connection conn;
    protected TransactionManager(Connection conn) {
        this.conn = conn;
    }

    /** 开启事务 */
    public void beginTransaction() throws DaoException {
        try {
            conn.setAutoCommit(false);                       // 把事务提交方式改为手工提交
        }
        catch (SQLException e) {
            throw new DaoException("开户事务时出现异常", e);
        }
    }

    /** 提交事务并关闭连接 */
    public void commitAndClose() throws DaoException {
        try {
            conn.commit();                                   // 提交事务
        }
        catch (SQLException e) {
            throw new DaoException("提交事务时出现异常", e);
        }
        finally {
            JDBC.close(conn);
        }
    }

    /** 回滚并关闭连接 */
    public void rollbackAndClose() throws DaoException {
        try {
            conn.rollback();
        }
        catch (SQLException e) {
            throw new DaoException("回滚事务时出现异常", e);
        }
        finally {
            JDBC.close(conn);
        }
    }
}
