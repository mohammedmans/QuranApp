package com.quran.administrator.quranapp.Model;

public class HadethItem {
    String title;
    String content;

    public HadethItem() {
        title="";
        content = "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public void AddLine(String line){
        content= content+"\n"+line;
    }
}
