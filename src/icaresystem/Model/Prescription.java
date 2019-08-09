/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaresystem.Model;

/**
 *
 * @author ericgutierrez
 */
public class Prescription {
    
    private int presNum;
    private Account patient;
    private String RX;

    public Prescription(int num, Account patient, String RX) {
        this.presNum = num;
        this.patient = patient;
        this.RX = RX;
    }


    public Account getPatient() {
        return patient;
    }

    public void setPatient(Account patient) {
        this.patient = patient;
    }

    /**
     * @return the presNum
     */
    public int getPresNum() {
        return presNum;
    }

    /**
     * @param presNum the presNum to set
     */
    public void setPresNum(int presNum) {
        this.presNum = presNum;
    }

    /**
     * @return the RX
     */
    public String getRX() {
        return RX;
    }

    /**
     * @param RX the RX to set
     */
    public void setRX(String RX) {
        this.RX = RX;
    }
    
 
    
}
