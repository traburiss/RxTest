package com.litc.rxtest.appinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by Traburiss on 2016/2/19.
 * Version 1.0
 * Description
 */

@Data
@AllArgsConstructor
@Accessors(prefix = "m")
public class AppInfo implements Comparable<Object>{
    long mLastUpdateTime;
    String mName;
    String mIcon;

    @Override
    public int compareTo(Object another) {
        AppInfo appInfo = (AppInfo)another;
        return getName().compareTo(appInfo.getName());
    }
}
