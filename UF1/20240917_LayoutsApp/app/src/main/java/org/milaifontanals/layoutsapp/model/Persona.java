package org.milaifontanals.layoutsapp.model;

import java.util.ArrayList;
import java.util.List;

public class Persona {


    private static List<Persona> persones;
    public static List<Persona> getPersones(){
        if(persones == null) {
            persones = new ArrayList<>();
            persones.add(new Persona("PacoX", "Gutiérrez", "1111111H",Sexe.ALTRES, null));
            persones.add(new Persona("Maria", "Nana", "2222222H",Sexe.DONA, null));
            persones.add(new Persona("Joan", "Gómez", "3333333H",Sexe.HOME, null));
            persones.add(new Persona("Pep", "Pérez", "144444444H",Sexe.HOME, null));
            persones.add(new Persona("Sara", "Sánchez", "5555555H",Sexe.ALTRES, null));
        }
        return persones;
    }

    private String nom;
    private String cognoms;
    private String NIF;
    private Sexe sexe;
    private Provincia provincia;

    public Persona(String nom, String cognoms, String NIF, Sexe sexe, Provincia provincia) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.NIF = NIF;
        this.sexe = sexe;
        this.provincia = provincia;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}
