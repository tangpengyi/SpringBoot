package com.tpy.springboot_jdbc_druid.utils;

import com.tpy.springboot_jdbc_druid.api.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class JDBCUtils {

    @Autowired
    private DataSource dataSource;

    //查询数据，返回数据集
    public ResponseResult queryData(String sSql, Object ... params) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //3.发送sql语句
            ps = dataSource.getConnection().prepareStatement(sSql);

            for(int i = 1; i <= params.length; i++){
                ps.setObject(i,params[i-1]);
            }

            //4.执行
            rs = ps.executeQuery();
            //循环取出雇员的名字，薪水，部门的编号
            while (rs.next()) {
                String color_no = rs.getString("color_no");
                String color_name = rs.getString("color_name");
                System.out.println(color_no+"===="+color_name);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
            // TODO: handle exception
        } finally {
            try {
                close(ps,rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  执行SQL语句，比如update、delete
     * @param sSql
     * @param params 需要传入参数，这些参数必须严格按照注入参数的顺序
     * @return
     */
    public ResponseResult executeSql(String sSql,Object ... params) {

        PreparedStatement ps = null;
        int rs = 0;
        try {
            ps = dataSource.getConnection().prepareStatement(sSql);
            for(int i = 1; i <= params.length; i++){
                ps.setObject(i,params[i-1]);
            }

            rs = ps.executeUpdate();

            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(200);
            responseResult.setMsg("修改"+rs+"几行");
            return responseResult;
        } catch (SQLException e) {
            e.printStackTrace();
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(500);
            responseResult.setMsg(e.getMessage());
            return responseResult;
        } finally {
            try {
                close(ps,null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param sSql
     * @param map key就是他们的注入参数的顺序，value就是参数值
     * @return
     */
    public ResponseResult executeQueryMap(String sSql,Map map) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //3.发送sql语句
            ps = dataSource.getConnection().prepareStatement(sSql);

            for(int i = 1; i <= map.size(); i++){
                ps.setObject(i,map.get(i));
            }

            //4.执行
            rs = ps.executeQuery();
            //循环取出雇员的名字，薪水，部门的编号
            while (rs.next()) {
                String color_no = rs.getString("color_no");
                String color_name = rs.getString("color_name");
                System.out.println(color_no+"===="+color_name);
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
            // TODO: handle exception
        } finally {
            try {
                close(ps,rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(Statement statement,ResultSet rs) throws SQLException {
        if(statement != null)
            statement.close();

        if(rs != null)
            rs.close();
    }
}

