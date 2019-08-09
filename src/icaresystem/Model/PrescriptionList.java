/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaresystem.Model;

import java.util.ArrayList;

/**
 *
 * @author Shane
 */
public class PrescriptionList {
    
    private ArrayList<Prescription> presList = new ArrayList<Prescription>();
    
    public PrescriptionList(){
        
    }

    /**
     * @return the presList
     */
    public ArrayList<Prescription> getPresList() {
        return presList;
    }

    /**
     * @param presList the presList to set
     */
    public void setPresList(ArrayList<Prescription> presList) {
        this.presList = presList;
    }
}
