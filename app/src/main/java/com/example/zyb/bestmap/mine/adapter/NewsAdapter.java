package com.example.zyb.bestmap.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zyb.bestmap.R;
import com.example.zyb.bestmap.mine.info.NewsInfo;

import java.util.List;

/**
 * Created by zyb on 2016/9/13.
 */
public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<NewsInfo> list;

    public NewsAdapter(Context context, List<NewsInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_news, null);
            viewHolder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_summary = (TextView) convertView.findViewById(R.id.tv_summary);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (list.get(position).getNewsBitmap() != null) {
            viewHolder.iv_image.setImageBitmap(list.get(position).getNewsBitmap());
        } else {
            //如果没有图片，则将imageview控件隐藏
            viewHolder.iv_image.setVisibility(View.GONE);
        }
        viewHolder.tv_title.setText(list.get(position).getNewsTitle());
        viewHolder.tv_summary.setText(list.get(position).getNewsSummary());
        return convertView;
    }

    public class ViewHolder {
        ImageView iv_image;
        TextView tv_title;
        TextView tv_summary;
    }
}
