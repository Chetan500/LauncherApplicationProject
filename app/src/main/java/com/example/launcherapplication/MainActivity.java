package com.example.launcherapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.launchersdk.InstalledAppInfo;
import com.example.launchersdk.InstalledAppsInfo;
import com.example.launchersdk.LauncherSdkMain;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView rvInstalledAppsInfo;
    private InstalledAppsInfoAdapter installedAppsInfoAdapter;
    private InstalledAppsInfo installedAppsInfo;
    private List<InstalledAppInfo> installedAppInfoList;
    private BroadcastReceiver appInstallUninstallBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        registerBroadcastReciever();
    }

    @Override
    protected void onDestroy()
    {
        unregisterReceiver(appInstallUninstallBroadcastReceiver);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchview_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_menu_item);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search App Name!");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                installedAppsInfoAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume()
    {
        loadInstalledAppsInfo();
        super.onResume();
    }

    private void init()
    {
        rvInstalledAppsInfo = (RecyclerView) findViewById(R.id.rvInstalledAppsInfo);
        rvInstalledAppsInfo.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        installedAppsInfoAdapter = new InstalledAppsInfoAdapter(MainActivity.this);
        installedAppInfoList = new ArrayList<>();
        rvInstalledAppsInfo.setAdapter(installedAppsInfoAdapter);
    }

    private void loadInstalledAppsInfo()
    {
        installedAppsInfo = LauncherSdkMain.getInstance().getInstalledAppsInfoInstance(MainActivity.this);
        installedAppInfoList = installedAppsInfo.getInstalledAppsInfoList();
        installedAppsInfoAdapter.updateInstalledAppInfoList(installedAppInfoList);
    }

    private void registerBroadcastReciever()
    {
        appInstallUninstallBroadcastReceiver = new AppInstallUninstallBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addDataScheme("package");
        registerReceiver(appInstallUninstallBroadcastReceiver, intentFilter);
    }
}