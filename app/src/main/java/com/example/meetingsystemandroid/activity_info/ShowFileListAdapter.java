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

import java.util.ArrayList;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowFileListAdapter extends RecyclerView.Adapter<ShowFileListAdapter.FileListItemViewHolder> {

    private Context mContext;
    private ArrayList<ActivityBean.ActivityFile> mFileList;
    private ShowActivityPresenter mPresenter;

    public ShowFileListAdapter(Context context, ShowActivityPresenter presenter) {
        mContext = context;
        mFileList = new ArrayList<>();
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public FileListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_file_list_text, parent, false);
        return new FileListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FileListItemViewHolder holder, int position) {
        ActivityBean.ActivityFile file = mFileList.get(position);
        holder.setFile(file.getFileName(), file.getFileSrc());
    }

    public void setFileList(ArrayList<ActivityBean.ActivityFile> fileList) {
        mFileList.addAll(fileList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFileList.size();
    }

    class FileListItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.show_activity_file_list_item)
        TextView mFileName;

        private String mFileSrc;

        public FileListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setFile(String name, String src) {
            mFileName.setText(name);
            mFileSrc = src;
        }

        @OnClick(R.id.show_activity_file_list_item_download)
        public void downLoadFile() {
            mPresenter.downloadActivityFile(mFileSrc+mFileName);
        }

    }
}
