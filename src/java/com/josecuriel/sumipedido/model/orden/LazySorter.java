

package com.josecuriel.sumipedido.model.orden;

import org.primefaces.model.SortOrder;



public class LazySorter implements Comparable<OrdenDTO>{

     private String sortField;
     
    private SortOrder sortOrder;
     
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    public int compare(OrdenDTO dto1, OrdenDTO dto2) {
        try {
            Object value1 = OrdenDTO.class.getField(this.sortField).get(dto1);
            Object value2 = OrdenDTO.class.getField(this.sortField).get(dto2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int compareTo(OrdenDTO o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
