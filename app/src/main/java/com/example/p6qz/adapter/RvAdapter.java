package com.example.p6qz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.p6qz.R;
import com.example.p6qz.bean.RvBean;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter {
    private ArrayList<RvBean.DataBean.DatasBean> list;
    private Context context;

    public RvAdapter(ArrayList<RvBean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_list, parent, false);
            return new ViewHolder_list(view);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.layout_two, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 1:
                ViewHolder_list viewHolder_list = (ViewHolder_list) holder;
                viewHolder_list.textview_list.setText(list.get(position).getId()+"");
                Glide.with(context).load(list.get(position).getEnvelopePic()).into(viewHolder_list.imageview_list);
                break;
            case 2:
                ViewHolder viewHolder = (ViewHolder) holder;

                Glide.with(context).load(list.get(position).getEnvelopePic()).into(viewHolder.imageview1_two);
                Glide.with(context).load(list.get(position).getEnvelopePic()).into(viewHolder.imageview2_two);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 1;
        }
        return 2;
    }

    public static
    class ViewHolder_list extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView imageview_list;
        public TextView textview_list;

        public ViewHolder_list(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.imageview_list = (ImageView) rootView.findViewById(R.id.imageview_list);
            this.textview_list = (TextView) rootView.findViewById(R.id.textview_list);
        }

    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView imageview1_two;
        public ImageView imageview2_two;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.imageview1_two = (ImageView) rootView.findViewById(R.id.imageview1_two);
            this.imageview2_two = (ImageView) rootView.findViewById(R.id.imageview2_two);
        }

    }
}
