package com.josecuriel.sumipedido.util.db;

//CONSULTAR: TIPOS DE DATOS GENERICOS O GENERIC

import java.util.List;

public interface crud<Cualquiercosa> {

    public boolean create(Cualquiercosa c);

    public boolean delete(Object key);

    public boolean update(Cualquiercosa c);

    public Cualquiercosa read(Object key);
    
    public List<Cualquiercosa> readAll();
    
}
