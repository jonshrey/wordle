package org.example.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Song {
    
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.JOIN)
    private List<Artist> artists = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.JOIN)
    private Album album;
    
    private String language;
    private String genre;
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
    public List<Artist> getArtists() {
        return artists;
    }
    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
    public void addArtist(Artist artist) {
        this.artists.add(artist);
        artist.getSongs().add(this);
    }
    public void removeArtist(Artist artist) {
        this.artists.remove(artist);
        artist.getSongs().remove(this);
    }

    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    
}
