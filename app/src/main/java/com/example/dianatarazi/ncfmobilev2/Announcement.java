package com.example.dianatarazi.ncfmobilev2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Announcement {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("title")
    @Expose
    private HashMap<String, String> title;

    @SerializedName("content")
    @Expose
    private HashMap<String, String> content;

    private Date lastRefresh;


    //Contstructors

    public Announcement() {}

    public Announcement(@NonNull int id, String date, String slug, String status, String link, HashMap<String, String> title, HashMap<String, String> content, Date lastRefresh) {
        this.id = id;
        this.date = date;
        this.slug = slug;
        this.status = status;
        this.link = link;
        this.title = title;
        this.content = content;
        this.lastRefresh = lastRefresh;
    }


    //Getters

    @NonNull
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getSlug() {
        return slug;
    }

    public String getStatus() {
        return status;
    }

    public String getLink() {
        return link;
    }

    public HashMap<String, String> getTitle() {
        return title;
    }

    public HashMap<String, String> getContent() {
        return content;
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }


    //Setters

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(HashMap<String, String> title) {
        this.title = title;
    }

    public void setContent(HashMap<String, String> content) {
        this.content = content;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }
}
