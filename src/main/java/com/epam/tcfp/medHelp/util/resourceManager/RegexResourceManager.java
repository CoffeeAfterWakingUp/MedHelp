package com.epam.tcfp.medHelp.util.resourceManager;

import java.util.ResourceBundle;

public class RegexResourceManager {
    private static RegexResourceManager instance;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("Regular_Expressions");

    private RegexResourceManager(){}

    public static RegexResourceManager getInstance(){
        if(instance == null){
            instance = new RegexResourceManager();
        }
        return instance;
    }

    public String getValue(String key){
        return resourceBundle.getString(key);
    }


}
