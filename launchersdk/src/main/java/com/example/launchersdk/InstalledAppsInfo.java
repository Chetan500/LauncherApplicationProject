package com.example.launchersdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InstalledAppsInfo
{
    private Context context;

    public InstalledAppsInfo(Context context)
    {
        this.context = context;
    }

    public List<InstalledAppInfo> getInstalledAppsInfoList()
    {
        List<InstalledAppInfo> installedAppInfoList = new ArrayList<>();
        List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);

        for (int i = 0; i < packageInfoList.size(); i++)
        {
            PackageInfo packageInfo = packageInfoList.get(i);

            if (packageInfo != null && (packageInfo.applicationInfo != null && packageInfo.activities != null))
            {
            InstalledAppInfo installedAppInfo = new InstalledAppInfo(
                    packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString(),
                    packageInfo.packageName, packageInfo.activities[0].name, packageInfo.versionName,
                    packageInfo.versionCode, packageInfo.applicationInfo.loadIcon(context.getPackageManager()));

            installedAppInfoList.add(installedAppInfo);
            }
        }

        Collections.sort(installedAppInfoList, new NameComparator());

        return installedAppInfoList;
    }
}
