package com.example.cinemates.model;

import com.example.cinemates.model.api.MovieModel;

import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 20:54
 */
public class Section {

    private String sectionName;
    private List<MovieModel> sectionItems;

    public Section(String sectionName, List<MovieModel> sectionItems) {
        this.sectionName = sectionName;
        this.sectionItems = sectionItems;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<MovieModel> getSectionItems() {
        return sectionItems;
    }

    public void setSectionItems(List<MovieModel> sectionItems) {
        this.sectionItems = sectionItems;
    }
}
