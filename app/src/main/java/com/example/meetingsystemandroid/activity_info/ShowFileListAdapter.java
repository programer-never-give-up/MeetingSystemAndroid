package com.example.meetingsystemandroid.activity_info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.meetingsystemandroid.R;

import org.w3c.dom.Text;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowFileListAdapter extends RecyclerView.Adapter<ShowFileListAdapter.FileListItemViewHolder> {

    private Context mContext;

    public ShowFileListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public FileListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_file_list_text, parent, false);
        return new FileListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FileListItemViewHolder holder, int position) {
        holder.setFileName("这是测试用的文件名.txt");
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class FileListItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.show_activity_file_list_item)
        TextView mFileName;

        public FileListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setFileName(String name) {
            mFileName.setText(name);
        }
    }
}
