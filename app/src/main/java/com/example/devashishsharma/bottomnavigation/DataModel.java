package com.example.devashishsharma.bottomnavigation;

/**
 * Created by DEVASHISH SHARMA on 20-04-2018.
 */

public class DataModel {
    private String name,type,version,feature;
    public DataModel(String name, String type, String version, String feature)
    {
        this.name=name;
        this.type=type;
        this.version=version;
        this.feature=feature;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public String getFeature() {
        return feature;
    }


}
