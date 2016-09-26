package com.example.zyb.bestmap.util;

import android.util.Log;

import com.example.zyb.bestmap.mine.info.NewsInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description:解析新闻数据
 * <p>
 * Created by zyb on 2016/9/13.
 */
public class Function {
    public static List<NewsInfo> parseHtmlData(String result) {
        List<NewsInfo> list = new ArrayList<>();
        //正则表达式
        Pattern pattern = Pattern.compile("<a target=\"_blank\" class=\"pic\" href=\"([^\"]*)\"><img class=\"picto\" src=\"([^\"]*)\"></a><em class=\"f14 l24\"><a target=\"_blank\" class=\"linkto\" href=\"[^\"]*\">([^</a>]*)</a></em><p class=\"l22\">([^</p>]*)</p>");
        Matcher matcher = pattern.matcher(result);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            NewsInfo newsInfo = new NewsInfo();
            newsInfo.setNewsDetailUrl(matcher.group(1).trim());
            newsInfo.setUrlImgAddress(matcher.group(2).trim());
            newsInfo.setNewsTitle(matcher.group(3).trim());
            newsInfo.setNewsSummary(matcher.group(4).trim());

            stringBuffer.append("详情页地址：" + matcher.group(1).trim() + "\n");
            stringBuffer.append("图片地址：" + matcher.group(2).trim() + "\n");
            stringBuffer.append("标题：" + matcher.group(3).trim() + "\n");
            stringBuffer.append("概要：" + matcher.group(4).trim() + "\n\n");
            list.add(newsInfo);
        }
        //查看解析是否出错
        Log.e("----------------->", stringBuffer.toString());
        return list;
    }
}
