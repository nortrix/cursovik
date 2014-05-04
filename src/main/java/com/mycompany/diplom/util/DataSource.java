//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package com.mycompany.diplom.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
/**
 *
 * @since Jan 29, 2014
 * @author dmorari
 */
public class DataSource {

	
  
        private static DataSource self;
        static {
          System.out.println("Initializing the connection pool");
          String connectionUrl = "jdbc:h2:./src/main/resources/database/database;AUTO_SERVER=TRUE"; //ApplicationProperties.getString("database.connection.url");
          String connectionUserName = "sa"; //ApplicationProperties.getString("database.connection.username");
          String connectionPassword = "sa"; //ApplicationProperties.getString("database.connection.password");
          String connectionDriver = "org.h2.Driver"; //ApplicationProperties.getString("database.connection.driver");

          self = new DataSource(connectionDriver, connectionUrl, connectionUserName, connectionPassword);
        }


        public static DataSource instance(){
          return self;
        }
        

	private PoolingDataSource dataSource = null;

	private DataSource(String driver, String url, String user,
			String password) {

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
		}
		dataSource = setupDataSource(url, user, password);
	}

	public synchronized Connection connection() {
		try {
			
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void close(Connection con) {

		try {		
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(ResultSet rs, Statement stmt) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
		}
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception ex) {

		}

	}

	public static void close(Statement stmt) {

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception ex) {

		}

	}

	private PoolingDataSource setupDataSource(String connectURI, String user,
			String password) {
		//
		// First, we'll need a ObjectPool that serves as the
		// actual pool of connections.
		//
		// We'll use a GenericObjectPool instance, although
		// any ObjectPool implementation will suffice.
		//
		GenericObjectPool.Config config = new GenericObjectPool.Config();
		config.maxActive = 150;
		config.maxIdle = 100;
		config.minIdle = 30;
		config.maxWait = 1000;

		ObjectPool connectionPool = new GenericObjectPool(null, config);

		//
		// Next, we'll create a ConnectionFactory that the
		// pool will use to create Connections.
		// We'll use the DriverManagerConnectionFactory,
		// using the connect string passed in the command line
		// arguments.
		//
//		Properties p = new Properties();
//		p.setProperty("user", SQLConstants.USER_NAME);
//		p.setProperty("password", SQLConstants.PASSWORD);
//		p.setProperty("useUnicode", "true");
//		p.setProperty("characterEncoding", "UTF-8");
		
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
				connectURI, user, password);
//		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
//				connectURI, p);
		//
		// Now we'll create the PoolableConnectionFactory, which wraps
		// the "real" Connections created by the ConnectionFactory with
		// the classes that implement the pooling functionality.
		//
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
				connectionFactory, connectionPool, null, null, false, true);

		//
		// Finally, we create the PoolingDriver itself,
		// passing in the object pool we created.
		//
		PoolingDataSource poolingDataSource = new PoolingDataSource(connectionPool);

		return poolingDataSource;
	}

}
