package com.cskaoyan.project1.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class DruidUtils {
    private static DataSource dataSource;
    static {
        //Druid 符合java数据库连接的规范的 所以肯定有一个datasource
        //如果放在WEB-INF下，必须要提供context或者相关
        //但是如果这么做，工具类就无法在se项目中使用
        //可以利用类加载器去获取一个绝对路径
        //类加载器有一个API可以直接定位到classes目录
        //会定位到当前包（utils的同级目录，即src里）,所以得写在utils的同级
        try {
            InputStream inputStream = DruidUtils.class.getClassLoader().
                    getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDataSource(){
        return dataSource;
    }
    /**
     *  Class.forName("com.mysql.jdbc.Driver");
     *  connection = DriverManager.getConnection("", "", "");
     *  今后获取连接使用下面代码即可，不要自己去getConnection
     *  因为上面没有数据库连接池,会加重服务器负担
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
}
