package com.example.launchersdk;

import android.content.Context;

public class LauncherSdkMain
{
    private static LauncherSdkMain launcherSdkMain = null;

    public final static LauncherSdkMain getInstance()
    {
        if (launcherSdkMain == null)
        {
            launcherSdkMain = new LauncherSdkMain();
        }

        return launcherSdkMain;
    }

    public final InstalledAppsInfo getInstalledAppsInfoInstance(Context context)
    {
        InstalledAppsInfo installedAppsInfo = new InstalledAppsInfo(context);
        return installedAppsInfo;
    }
}
