package com.paidy.restaurant.database;


import com.paidy.restaurant.config.SystemConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabasePool {

	private DatabasePool(){}
	
	private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;
    
    static {
        config.setJdbcUrl(SystemConfig.DB_URL);
        config.setDriverClassName(SystemConfig.DB_DRIVER);
        config.setUsername(SystemConfig.DB_USERNAME);
        config.setPassword(SystemConfig.DB_PASSWORD);
        config.setMinimumIdle(SystemConfig.DB_MIN_POOL_SIZE);
        config.setMaximumPoolSize(SystemConfig.DB_MAX_POOL_SIZE);
        config.setConnectionTimeout(SystemConfig.DB_CON_TIMEOUT);
        config.setAutoCommit(true);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        
        ds = new HikariDataSource(config);
    }

    
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    
    public static void releaseConnectionToPool(Connection con )throws Exception{
		if(con !=null){con.close();con=null;}
	}

}
