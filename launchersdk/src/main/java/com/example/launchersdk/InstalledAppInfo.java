package com.example.launchersdk;

import android.graphics.drawable.Drawable;

public class InstalledAppInfo
{
    private String appName = "", packageName = "", className = "", versionName = "";
    private int versionCode = 0;
    private Drawable icon = null;

    public InstalledAppInfo(String appName, String packageName, String className, String versionName, int versionCode, Drawable icon)
    {
        setAppName(appName);
        setPackageName(packageName);
        setClassName(className);
        setVersionName(versionName);
        setVersionCode(versionCode);
        setIcon(icon);
    }

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getVersionName()
    {
        return versionName;
    }

    public void setVersionName(String versionName)
    {
        this.versionName = versionName;
    }

    public int getVersionCode()
    {
        return versionCode;
    }

    public void setVersionCode(int versionCode)
    {
        this.versionCode = versionCode;
    }

    public Drawable getIcon()
    {
        return icon;
    }

    public void setIcon(Drawable icon)
    {
        this.icon = icon;
    }
}
