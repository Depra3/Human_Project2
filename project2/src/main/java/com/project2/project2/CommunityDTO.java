package com.project2.project2;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "community")
public class CommunityDTO {

    @Id
    private String _id;
    private Integer num;
    private String title;
    private String content;
    private String author;
    private Integer join;
    private Date reg_date;
    private Date mod_date;

    // public CommunityDTO(String id, Integer num, String title, String content, String author, Date req_date, Date mod_date){
    //     this.id = id;
    //     this.num = num;
    //     this.title = title;
    //     this.content = content;
    //     this.author = author;
    //     this.req_date = req_date;
    //     this.mod_date = mod_date;
    // }

    public String getId() {
        return _id;
    }
    public void setId(String _id) {
        this._id = _id;
    }
    public Integer getNum() {
        return num;
    }
    public void setId(Integer num) {
        this.num = num;
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Integer getJoin(){
        return join;
    }
    public void setJoin(Integer join){
        this.join = join;
    }
    public Date getReg_date() {
        return reg_date;
    }
    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }
    public Date getMod_date() {
        return mod_date;
    }
    public void setMod_date(Date mod_date) {
        this.mod_date = mod_date;
    }
    
    @Override
    public String toString(){
        return "CommunityDTO{ title=" + title + ", content=" + content + 
                            ", author=" + author + ",req_date=" + reg_date + ",mod_date=" + mod_date + "}";
    }
}
