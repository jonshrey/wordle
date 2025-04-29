package org.fantasy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    private Integer matches;
    private Integer wins;
    private Integer losses;
    private Integer noresults;
    private Integer points;

    public Team() {

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getMatches() {
        return matches;
    }
    public void setMatches(Integer matches) {
        this.matches = matches;
    }
    public Integer getWins() {
        return wins;
    }
    public void setWins(Integer wins) {
        this.wins = wins;
    }
    public Integer getLosses() {
        return losses;
    }
    public void setLosses(Integer losses) {
        this.losses = losses;
    }
    public Integer getNoresults() {
        return noresults;
    }
    public void setNoresults(Integer noresults) {
        this.noresults = noresults;
    }
    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }

    
    
}
