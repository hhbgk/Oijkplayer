package com.open.player.browser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.open.player.R;
import com.open.player.browser.bean.VideoFile;

import java.util.List;

/**
 * Des:
 * Author:
 * Date:21-5-10
 * UpdateRemark:
 */
public class VideoFileAdapter extends BaseAdapter {
    private final List<VideoFile> dataList;

    public VideoFileAdapter(List<VideoFile> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        if (dataList != null) return dataList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (dataList == null || dataList.size() == 0) return null;
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_file_item, parent, false);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.nameTextView = convertView.findViewById(R.id.tv_text);
            convertView.setTag(viewHolder);
        }
        viewHolder.nameTextView.setText(dataList.get(position).getName());
        return convertView;
    }

    final static class ViewHolder {
        public TextView nameTextView;
    }

}
