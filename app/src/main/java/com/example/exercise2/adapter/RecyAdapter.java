package com.example.exercise2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.exercise2.R;
import com.example.exercise2.bean.RecentBean;

import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter {
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    public RecyAdapter(Context context, List<RecentBean> recent) {
        this.context = context;
        this.recent = recent;
    }

    private Context context;
    private List<RecentBean> recent;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 1) {
            View view = View.inflate(context, R.layout.item1, null);
            ViewHolder1 viewHolder1 = new ViewHolder1(view);
            return viewHolder1;
        } else {
            View view = View.inflate(context, R.layout.item2, null);
            ViewHolder2 viewHolder2 = new ViewHolder2(view);
            return viewHolder2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (getItemViewType(i) == 1) {
            ViewHolder1 holder1 = (ViewHolder1) viewHolder;
            Glide.with(context).load(recent.get(i).getThumbnail()).into(holder1.img);
            holder1.title.setText(recent.get(i).getTitle());
        } else {
            ViewHolder2 holder2 = (ViewHolder2) viewHolder;
            Glide.with(context).load(recent.get(i).getThumbnail()).into(holder2.img);
            holder2.title.setText(recent.get(i).getTitle());
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(i);
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClick.onItemLong(i);
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return recent.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item1_img);
            title = itemView.findViewById(R.id.item1_title);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item2_img);
            title = itemView.findViewById(R.id.item2_title);
        }
    }

    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public interface OnClick{
        void onItemClick(int postison);
        void onItemLong(int postion);
    }
}
