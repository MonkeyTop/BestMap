package com.example.zyb.bestmap.mine.info;

import android.graphics.Bitmap;

/**
 * 新闻Model
 * <p>
 * Created by zyb on 2016/9/13.
 */
public class NewsInfo {
    //存储加载完成的图片
    private Bitmap newsBitmap;
    //新闻详情地址
    private String newsDetailUrl;
    //新闻图片地址
    private String urlImgAddress;
    //新闻标题
    private String newsTitle;
    //新闻概要
    private String newsSummary;

    public Bitmap getNewsBitmap() {
        return newsBitmap;
    }

    public void setNewsBitmap(Bitmap newsBitmap) {
        this.newsBitmap = newsBitmap;
    }

    public String getNewsDetailUrl() {
        return newsDetailUrl;
    }

    public void setNewsDetailUrl(String newsDetailUrl) {
        this.newsDetailUrl = newsDetailUrl;
    }

    public String getUrlImgAddress() {
        return urlImgAddress;
    }

    public void setUrlImgAddress(String urlImgAddress) {
        this.urlImgAddress = urlImgAddress;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsSummary() {
        return newsSummary;
    }

    public void setNewsSummary(String newsSummary) {
        this.newsSummary = newsSummary;
    }
}
