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
public class AppointmentList {
    
    private ArrayList<Appointment> appList = new ArrayList<Appointment>();
    
    public AppointmentList() {
        
    }

    /**
     * @return the appList
     */
    public ArrayList<Appointment> getAppList() {
        return appList;
    }

    /**
     * @param appList the appList to set
     */
    public void setAppList(ArrayList<Appointment> appList) {
        this.appList = appList;
    }
    
}
