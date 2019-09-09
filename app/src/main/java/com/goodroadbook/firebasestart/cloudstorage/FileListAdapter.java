package com.goodroadbook.firebasestart.cloudstorage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.goodroadbook.firebasestart.R;

import java.util.ArrayList;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.ViewHolder>
{
    private Context context = null;
    private ArrayList<UploadInfo> imageItems = null;

    public FileListAdapter(ArrayList<UploadInfo> items, Context context)
    {
        this.imageItems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_item_list, viewGroup, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.titleView.setText(imageItems.get(i).getName());
        viewHolder.contentsView.setText(imageItems.get(i).getPath());
    }

    @Override
    public int getItemCount()
    {
        return imageItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView titleView = null;
        public TextView contentsView = null;

        public ViewHolder(View view) {
            super(view);
            titleView = (TextView)view.findViewById(R.id.memotitle);
            contentsView = (TextView)view.findViewById(R.id.memocontents);
        }

        @Override
        public void onClick(View view)
        {
            ;
        }
    }
}
