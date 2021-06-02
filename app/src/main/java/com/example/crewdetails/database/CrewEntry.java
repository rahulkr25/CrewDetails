package com.example.crewdetails.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "crew")
public class CrewEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String status;
    private String agency;
    private String imageUrl;
    private String wikiUrl;



    @Ignore
    public CrewEntry(String name, String status, String agency, String imageUrl,String wikiUrl) {
        this.name = name;
        this.status =status;
        this.agency=agency;
        this.imageUrl = imageUrl;
        this.wikiUrl=wikiUrl;
    }

    public CrewEntry(int id,String name, String status, String agency, String imageUrl,String wikiUrl) {
        this.id = id;
        this.name = name;
        this.status =status;
        this.agency=agency;
        this.imageUrl = imageUrl;
        this.wikiUrl=wikiUrl;
    }

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }



    public String getStatus() {
        return status;
    }

    public String getAgency() {
        return agency;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

}
