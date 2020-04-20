package com.dyjs.meeting.controller;

import com.dyjs.meeting.dao.*;
import com.dyjs.meeting.service.NewsService;
import com.dyjs.meeting.service.impl.NewsServiceImpl;
import com.dyjs.meeting.util.BaseResponse;
import com.dyjs.meeting.util.BaseResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api("新闻相关接口")
@RestController
@RequestMapping("basic-meeting")
public class NewsController {

    @PostMapping("/queryNews")
    public BaseResponse query(Integer page) throws IOException {
        if(page==null){
            page = 1;
        }
        String url = "http://www.dyjs.com/htm/list/12_"+page+".htm";
        Document document= Jsoup.connect(url).get();
        if(document==null){
            return BaseResponseUtil.error(500,"咨询获取失败");
        }
        Elements elements = document.getElementsByClass("xinxiall");
        PageInfo<Title> pageInfo = new PageInfo<>();
        List<Title> list = new ArrayList<>();
        for(Element element:elements){
            Element element1= element.getElementsByClass("fonta").get(0);
            Element href = element1.getElementsByTag("a").get(0);
            Element content = element1.getElementsByTag("p").get(0);
            Element element2 = element.getElementsByClass("daten").get(0);
            Title title=new Title();
            title.setContent(content.html());
            title.setTitle(element1.attr("title"));
            title.setHref(href.attr("href"));
            title.setDate(element2.attr("title"));
            list.add(title);
        }
        return BaseResponseUtil.success(list);
    }

    @PostMapping("newsDetail")
    public BaseResponse getDetail(String href) throws IOException {
        String url= "http://www.dyjs.com/";
        href = href.substring(6);
        url = url+href;
        Document document= Jsoup.connect(url).get();
        Element element = document.getElementById("ClsDetail");
        Element h2 = element.getElementsByTag("h2").get(0);
        Element content = element.getElementById("ContentDetail");
        NewsDetail newsDetail = new NewsDetail();
        newsDetail.setTitle(h2.attr("title"));
        if(document==null){
            return BaseResponseUtil.error(500,"咨询获取失败");
        }
        List list = new ArrayList();
        list=this.getListByDoucument(list,document);

        Elements element1 = document.getElementsByClass("Pages");
        if(element1.size()!=0){
            Element page= element1.get(0);
            if(page.getElementsByTag("a").size()!=0){
                Elements pages = page.getElementsByTag("a");
                for(Element element2:pages){
                   String str= element2.attr("href").substring(6);
                   String href1="http://www.dyjs.com/";
                    Document document1= Jsoup.connect(href1+str).get();
                    list=this.getListByDoucument(list,document1);
                }
            }
        }
        newsDetail.setList(list);
        return BaseResponseUtil.success(newsDetail);
    }

    public List getListByDoucument(List list,Document document){
        Element element = document.getElementById("ClsDetail");
        Element h2 = element.getElementsByTag("h2").get(0);
        Element content = element.getElementById("ContentDetail");
        NewsDetail newsDetail = new NewsDetail();
        newsDetail.setTitle(h2.attr("title"));
        Elements elements = content.getElementsByTag("p");
        for(Element p : elements){

            if(p.getElementsByTag("img").size()==0){
                Section section = new Section();
                section.setName("text");
                String text = p.html();
                text=text.replaceAll("&nbsp;"," ");
                text=text.replaceAll("\"","");
                text=text.replaceAll("<br>","");
                while(text.indexOf("<")!=-1){
                    String front=text.substring(0,text.indexOf("<"));
                    String after = text.substring(text.indexOf(">")+1);
                    text = front+after;
                }
                section.setContent(text);
                if(section.getContent().trim().length()!=0){
                    list.add(section);
                }
            }else{
                Elements elements1 = p.getElementsByTag("img");
                for(Element element1:elements1){
                    String imgUrl ="http://www.dyjs.com/";
                    Section section = new Section();
                    section.setName("img");
                    String src=element1.attr("src").substring(6);
                    imgUrl=imgUrl+src;
                    section.setContent(imgUrl);
                    list.add(section);
                }
                Section section = new Section();
                section.setName("text");
                String text = p.html();
                text=text.replaceAll("&nbsp;"," ");
                text=text.replaceAll("\"","");
                text=text.replaceAll("<br>","");
                while(text.indexOf("<")!=-1){
                    String front=text.substring(0,text.indexOf("<"));
                    String after = text.substring(text.indexOf(">")+1);
                    text = front+after;
                }
                section.setContent(text);
                if(section.getContent().trim().length()!=0){
                    list.add(section);
                }
            }
        }
        return list;
    }



}
