package com.epam.tcfp.medHelp.util.resourceManager;

import java.util.ResourceBundle;

public class DBResourceManager {
    private static DBResourceManager instance = new DBResourceManager();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("ConnectionPool");


    private DBResourceManager(){}

    public static DBResourceManager getInstance(){
        if(instance == null){
            instance = new DBResourceManager();
        }
        return instance;
    }

    public String getValue(String key){
        return resourceBundle.getString(key);
    }
}
