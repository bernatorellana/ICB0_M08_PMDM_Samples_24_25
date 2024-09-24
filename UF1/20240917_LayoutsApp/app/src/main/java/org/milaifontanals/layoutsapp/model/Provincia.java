package org.milaifontanals.layoutsapp.model;

import org.milaifontanals.layoutsapp.R;

import java.util.ArrayList;
import java.util.List;

public class Provincia {

    private static List<Provincia> provincies;
    public static List<Provincia> getProvincies(){
        if(provincies == null) {
            provincies = new ArrayList<>();
            provincies.add(new Provincia(1, "Barcelona"));
            provincies.add(new Provincia(2, "Girona"));
            provincies.add(new Provincia(3, "Tarragona"));
            provincies.add(new Provincia(4, "Lleida"));
        }
        return provincies;
    }
    
    private int id;
    private String nom;

    public Provincia(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
