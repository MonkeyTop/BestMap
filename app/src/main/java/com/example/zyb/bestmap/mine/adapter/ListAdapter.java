package com.example.zyb.bestmap.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zyb.bestmap.R;
import com.example.zyb.bestmap.mine.info.ViolationInfo;

import java.util.List;

/**
 * Created by zyb on 2016/9/21.
 */
public class ListAdapter extends BaseAdapter {
    private List<ViolationInfo> mDate;
    private Context mContext;

    public ListAdapter(Context mContext, List mDate) {
        this.mContext = mContext;
        this.mDate = mDate;
    }

    @Override
    public int getCount() {
        return mDate.size();
    }

    @Override
    public Object getItem(int position) {
        return mDate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.query_listitem_citys, null);
        ViolationInfo model = mDate.get(position);
        TextView txt_name = (TextView) view.findViewById(R.id.txt_name);
        //ImageView image=(ImageView)view.findViewById(R.id.iv_1);
        txt_name.setText(model.getTextName());
        txt_name.setTag(model.getNameId());
        return view;
    }
}
