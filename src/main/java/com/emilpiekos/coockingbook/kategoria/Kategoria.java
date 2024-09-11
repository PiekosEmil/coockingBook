package com.emilpiekos.coockingbook.kategoria;

import com.emilpiekos.coockingbook.przepis.Przepis;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Kategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany (mappedBy = "kategoria")
    private List<Przepis> przepisList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Przepis> getPrzepisList() {
        return przepisList;
    }

    public void setPrzepisList(List<Przepis> przepisList) {
        this.przepisList = przepisList;
    }
}
