

package com.josecuriel.sumipedido.model.orden;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;



public class LazyOrdenDataModel extends LazyDataModel<OrdenDTO>{
    
    private List<OrdenDTO> datasource;

    public LazyOrdenDataModel(List<OrdenDTO> datasource) {
        
        this.datasource = datasource;
    }
    
    public OrdenDTO getRowData(String rowKey){
        
        for (OrdenDTO dto : datasource) {
            if (dto.getNumOrden().equals(rowKey)) {
                return dto;
            }
        }
        return null;
    }
    
    public Object getRowKey(OrdenDTO dto) {
        return dto.getItemorden();
    }
 
     public List<OrdenDTO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<OrdenDTO> data = new ArrayList<OrdenDTO>();
 
        //filter
        for(OrdenDTO car : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(car.getClass().getField(filterProperty).get(car));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(car);
            }
        }
 
        //sort
        if(sortField != null) {
//            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
    
}
