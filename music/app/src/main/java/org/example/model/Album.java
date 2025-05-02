package org.example.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Album {
    
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String yearOfRelease;
    private String language;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "music_director_id")
    @Fetch(value = FetchMode.JOIN)
    private Artist musicDirector;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfBirth) {
        this.yearOfRelease = yearOfBirth;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Artist getMusicDirector() {
        return musicDirector;
    }

    public void setMusicDirector(Artist musicDirector) {
        this.musicDirector = musicDirector;
    }

    
}
