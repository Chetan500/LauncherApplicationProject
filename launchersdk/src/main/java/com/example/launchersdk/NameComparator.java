package com.example.launchersdk;

import java.util.Comparator;

public class NameComparator implements Comparator<InstalledAppInfo>
{

    @Override
    public int compare(InstalledAppInfo installedAppInfo1, InstalledAppInfo installedAppInfo2)
    {
        return installedAppInfo1.getAppName().compareTo(installedAppInfo2.getAppName());
    }
}
