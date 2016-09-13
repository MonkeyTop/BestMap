package com.example.zyb.bestmap.mine.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zyb.bestmap.R;
import com.example.zyb.bestmap.mine.adapter.NewsAdapter;
import com.example.zyb.bestmap.mine.info.NewsInfo;
import com.example.zyb.bestmap.util.CommonTool;
import com.example.zyb.bestmap.util.Function;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyb on 2016/9/13.
 */
public class NewsActivity extends Activity {
    ListView listView;
    List<NewsInfo> list;
    NewsInfo newsInfo;
    NewsAdapter newsAdapter;
    //获取数据成功
    private final static int GET_DATA_SUCCEED = 1;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_DATA_SUCCEED:
                    List<NewsInfo> list = (List<NewsInfo>) msg.obj;
                    //新闻列表适配器
                    newsAdapter = new NewsAdapter(NewsActivity.this, list);
                    listView.setAdapter(newsAdapter);
                    //设置点击事件
                    listView.setOnItemClickListener(new ItemClickListener());
                    Toast.makeText(getApplicationContext(), String.valueOf(list.size()), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        //初始化视图
        initView();
        //初始化数据
        initData();
    }

    private void initView() {
        list = new ArrayList<NewsInfo>();
        listView = (ListView) findViewById(R.id.lv_listView);
    }

    public void initData() {
        //开启一个线程执行耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                //获取网络数据
                String result = CommonTool.getRequest("http://news.qq.com/china_index.shtml", "gbk");
                Log.e("结果------------->", result);
                //解析新闻数据
                List<NewsInfo> list = Function.parseHtmlData(result);
                for (int i = 0; i < list.size(); i++) {
                    newsInfo = list.get(i);
                    //获取新闻图片
                    Bitmap bitmap = BitmapFactory.decodeStream(CommonTool.getImgInputStream(list.get(i).getUrlImgAddress()));
                    newsInfo.setNewsBitmap(bitmap);
                }
                handler.sendMessage(handler.obtainMessage(GET_DATA_SUCCEED, list));
            }
        }).start();
    }

    /**
     * 新闻列表点击事件
     */
    public class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            NewsInfo temp = (NewsInfo) newsAdapter.getItem(i);
            Toast.makeText(getApplicationContext(), temp.getNewsTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}
