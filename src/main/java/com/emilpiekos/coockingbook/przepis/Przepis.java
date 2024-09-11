package com.emilpiekos.coockingbook.przepis;

import com.emilpiekos.coockingbook.kategoria.Kategoria;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
public class Przepis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    @Column(length = 1000)
    private String description;
    private String imageUrl;
    @Column(name = "likes", columnDefinition = "Integer default 0")
    private Integer likes = 0;

    @Nonnull
    @ManyToOne
    Kategoria kategoria;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Nonnull
    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(@Nonnull Kategoria kategoria) {
        this.kategoria = kategoria;
    }
}
