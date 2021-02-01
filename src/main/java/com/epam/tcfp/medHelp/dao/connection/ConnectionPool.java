package com.epam.tcfp.medHelp.dao.connection;

import com.epam.tcfp.medHelp.util.constants.DBParameter;
import com.epam.tcfp.medHelp.util.resourceManager.DBResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private final static Logger LOGGER = LogManager.getLogger();
    private BlockingQueue<Connection> availableConnections;
    private static volatile ConnectionPool instance;


    private ConnectionPool(){
        setDBParameters();
        initPoolData();
    }

    private void setDBParameters(){
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBParameter.DB_URL);
        this.user = dbResourceManager.getValue(DBParameter.DB_USER);
        this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        try{
            this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
        }catch (NumberFormatException e){
            poolSize = 5;
        }
    }

    private void initPoolData(){
        Connection connection;
        try{
            Class.forName(driverName);
            availableConnections = new ArrayBlockingQueue<>(poolSize);
            for(int i = 0; i < poolSize; i++){
                connection = DriverManager.getConnection(url,user,password);
                availableConnections.put(connection);
            }

        }catch (ClassNotFoundException e ){
            LOGGER.error("Can't find database driver class",e);
        }catch (SQLException e){
            LOGGER.error("SQLException in ConnectionPool",e);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

    public synchronized Connection takeConnection(){
        Connection connection = null;
        if(availableConnections.size() == 0){
            LOGGER.warn("All connections are used");
        }
        else {
            try {
                connection = availableConnections.take();
            } catch (InterruptedException e) {
                LOGGER.error("Error connecting to the data source", e);
            }
        }
        return connection;
    }

    public synchronized void releaseConnection(Connection connection){
        if(connection != null){
            try{
                availableConnections.put(connection);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
    }


    public static ConnectionPool getInstance(){
        ConnectionPool result = instance;
        if(result == null){
            synchronized (ConnectionPool.class){
                result = instance;
                if(result == null){
                    instance = result = new ConnectionPool();
                }
            }
        }
        return result;
    }

}
