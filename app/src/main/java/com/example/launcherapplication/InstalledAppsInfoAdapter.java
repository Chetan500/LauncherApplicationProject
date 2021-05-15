package com.example.launcherapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.launchersdk.InstalledAppInfo;

import java.util.ArrayList;
import java.util.List;

public class InstalledAppsInfoAdapter extends RecyclerView.Adapter<InstalledAppsInfoAdapter.InstalledAppsInfoViewHolder> implements Filterable
{
    private Context context;
    private List<InstalledAppInfo> installedAppInfoList = null;
    private List<InstalledAppInfo> installedAppInfoFilteredList = null;
    private RecyclerView rvInstalledAppsInfo = null;
    private View.OnClickListener installAppInfoItemClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int position = rvInstalledAppsInfo.getChildLayoutPosition(v);
            InstalledAppInfo installedAppInfo = installedAppInfoFilteredList.get(position);
            launchExternalApp(installedAppInfo.getPackageName());
        }
    };

    public InstalledAppsInfoAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView)
    {
        rvInstalledAppsInfo = recyclerView;
        super.onAttachedToRecyclerView(recyclerView);
    }

    private void launchExternalApp(String packageName)
    {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null)
        {
            context.startActivity(intent);
        }
    }

    public void updateInstalledAppInfoList(List<InstalledAppInfo> itemsList)
    {
        this.installedAppInfoList = itemsList;
        this.installedAppInfoFilteredList = itemsList;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                String query = constraint.toString();

                if (query.isEmpty())
                {
                    installedAppInfoFilteredList = installedAppInfoList;
                }
                else
                {
                    List<InstalledAppInfo> filteredList = new ArrayList<>();
                    for (InstalledAppInfo installedAppInfo : installedAppInfoList)
                    {
                        if (installedAppInfo.getAppName().toLowerCase().contains(query.toLowerCase()))
                        {
                            filteredList.add(installedAppInfo);
                        }
                    }

                    installedAppInfoFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = installedAppInfoFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                installedAppInfoFilteredList = (ArrayList<InstalledAppInfo>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public final static class InstalledAppsInfoViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvAppName, tvPackageName, tvClassName, tvVersionName, tvVersionCode;
        private ImageView imgAppIcon;

        public InstalledAppsInfoViewHolder(@NonNull View itemView)
        {
            super(itemView);
            init();
        }

        private void init()
        {
            imgAppIcon = (ImageView) itemView.findViewById(R.id.imgAppIcon);
            tvAppName = (TextView) itemView.findViewById(R.id.txtAppName);
            tvPackageName = (TextView) itemView.findViewById(R.id.txtPackageName);
            tvClassName = (TextView) itemView.findViewById(R.id.txtClassName);
            tvVersionName = (TextView) itemView.findViewById(R.id.txtVersionName);
            tvVersionCode = (TextView) itemView.findViewById(R.id.txtVersionCode);
        }

        public TextView getTvAppName()
        {
            return tvAppName;
        }

        public TextView getTvPackageName()
        {
            return tvPackageName;
        }

        public TextView getTvClassName()
        {
            return tvClassName;
        }

        public TextView getTvVersionName()
        {
            return tvVersionName;
        }

        public TextView getTvVersionCode()
        {
            return tvVersionCode;
        }

        public ImageView getImgAppIcon()
        {
            return imgAppIcon;
        }
    }

    @NonNull
    @Override
    public InstalledAppsInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appinfo_view, parent, false);
        view.setOnClickListener(installAppInfoItemClickListener);
        return new InstalledAppsInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstalledAppsInfoViewHolder holder, int position)
    {
        InstalledAppInfo installedAppInfo = installedAppInfoFilteredList.get(position);
        holder.getImgAppIcon().setImageDrawable(installedAppInfo.getIcon());
        holder.getTvAppName().setText(installedAppInfo.getAppName());
        holder.getTvPackageName().setText(installedAppInfo.getPackageName());
        holder.getTvClassName().setText(installedAppInfo.getClassName());
        holder.getTvVersionName().setText(installedAppInfo.getVersionName());
        holder.getTvVersionCode().setText(String.valueOf(installedAppInfo.getVersionCode()));

    }

    @Override
    public int getItemCount()
    {
        return (installedAppInfoFilteredList != null ? installedAppInfoFilteredList.size() : 0);
    }
}
