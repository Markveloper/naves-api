package com.prueba.naves_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "nave")
public class Nave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String series;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Nave(Long id, String name, String series) {
        this.id = id;
        this.name = name;
        this.series = series;
    }

    public Nave() {
    }
}
