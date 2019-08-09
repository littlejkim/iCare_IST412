/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaresystem.View;

import icaresystem.Model.Account;
import icaresystem.Model.Appointment;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Eric Gutierrez
 */

//RuleB Builder Design Pattern
public class AppointmentPanel extends JPanel {
    
    private JPanel selectP;
    private JPanel defaultP;

    private JRadioButton create;
    private JRadioButton view;
    private JRadioButton edit;
    private JButton select;
    private JLabel searchIDL;
    private JTextField searchIDT;
    private ButtonGroup group;
    
    private JLabel idL;
    private JLabel reasonL;
    private JTextField idT;
    private JTextField reasonT;
    private JButton createA;
    
    public AppointmentPanel() {
        initSelectP();
        initCreateD();
        setLayout(new GridLayout(1,1,20,20));
        this.add(selectP);
        this.add(defaultP);
    }
    
    private void initSelectP() {
        setSelectP(new JPanel());
        getSelectP().setLayout(new GridLayout(6,0,20,20));
        getSelectP().setBackground(Color.white);
        
        setCreate(new JRadioButton("Create Appointment"));
        setView(new JRadioButton("View Appointment"));
        setEdit(new JRadioButton("Edit Appointment"));
        
        setGroup(new ButtonGroup());
        getGroup().add(getCreate());
        getGroup().add(getView());
        getGroup().add(getEdit());
        
        setSelect(new JButton("Select"));
        setSearchIDL(new JLabel("Enter Patient ID:"));
        getSearchIDL().setForeground(Color.black);
        setSearchIDT(new JTextField());
        
        getSelectP().add(getCreate());
        getSelectP().add(getView());
        getSelectP().add(getEdit());
        getSelectP().add(getSearchIDL());
        getSelectP().add(getSearchIDT());
        getSelectP().add(getSelect());
    }
    
    public void initCreateD() {
        setDefaultP(new JPanel());
        getDefaultP().setBackground(Color.black);
    }
    
    public void resetD() {
        getDefaultP().removeAll();
    }
    
    public void initViewP(Account a, Appointment b) {
        getDefaultP().setLayout(new GridLayout(0,1));
        
        JPanel topP = new JPanel();
        topP.setBackground(Color.gray);
        topP.setLayout(new GridLayout(0,2,20,20));
        
        setIdL(new JLabel("Patient Name: "));
        setReasonL(new JLabel("Reason For Appointment: "));
        
        setIdT(new JTextField(a.getPatientFullName()));
        setReasonT(new JTextField(b.getAppointmentDescription()));
        
        topP.add(getIdL());
        topP.add(getIdT());
        topP.add(getReasonL());
        topP.add(getReasonT());
        
        getDefaultP().add(topP);
    }
    
    public void initEditP(Account a, Appointment b) {
        getDefaultP().setLayout(new GridLayout(0,1));
        
        //Create top panel
        JPanel topP = new JPanel();
        topP.setBackground(Color.gray);
        topP.setLayout(new GridLayout(0,2,20,20));
        
        setIdL(new JLabel("Patient ID"));
        setReasonL(new JLabel("Reason for Appointment:"));
        
        setIdT(new JTextField(Integer.toString(a.getPatientId())));
        setReasonT(new JTextField(b.getAppointmentDescription()));
        
        topP.add(getIdL());
        topP.add(getIdT());
        topP.add(getReasonL());
        topP.add(getReasonT());
        
        //Create submit panel
        JPanel botP = new JPanel();
        setCreateA(new JButton("Update"));
        botP.add(getCreateA());
        
        getDefaultP().add(topP);
        getDefaultP().add(botP);
    }
    public void initCreateP() {
        getDefaultP().setLayout(new GridLayout(0,1));
        
        //Create top panel
        JPanel topP = new JPanel();
        topP.setBackground(Color.gray);
        topP.setLayout(new GridLayout(0,2,20,20));
        
        setIdL(new JLabel("Enter Patient ID"));
        setReasonL(new JLabel("Enter Reason For Appointment"));
        
        setIdT(new JTextField());
        setReasonT(new JTextField());
        
        topP.add(getIdL());
        topP.add(getIdT());
        topP.add(getReasonL());
        topP.add(getReasonT());
        
        //Create submit panel
        JPanel botP = new JPanel();
        setCreateA(new JButton("Create"));
        botP.add(getCreateA());
        
        getDefaultP().add(topP);
        getDefaultP().add(botP);
    }

    /**
     * @return the selectP
     */
    public JPanel getSelectP() {
        return selectP;
    }

    /**
     * @param selectP the selectP to set
     */
    public void setSelectP(JPanel selectP) {
        this.selectP = selectP;
    }

    /**
     * @return the create
     */
    public JRadioButton getCreate() {
        return create;
    }

    /**
     * @param create the create to set
     */
    public void setCreate(JRadioButton create) {
        this.create = create;
    }

    /**
     * @return the view
     */
    public JRadioButton getView() {
        return view;
    }

    /**
     * @param view the view to set
     */
    public void setView(JRadioButton view) {
        this.view = view;
    }

    /**
     * @return the edit
     */
    public JRadioButton getEdit() {
        return edit;
    }

    /**
     * @param edit the edit to set
     */
    public void setEdit(JRadioButton edit) {
        this.edit = edit;
    }

    /**
     * @return the select
     */
    public JButton getSelect() {
        return select;
    }

    /**
     * @param select the select to set
     */
    public void setSelect(JButton select) {
        this.select = select;
    }

    /**
     * @return the group
     */
    public ButtonGroup getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(ButtonGroup group) {
        this.group = group;
    }

    /**
     * @return the idL
     */
    public JLabel getIdL() {
        return idL;
    }

    /**
     * @param idL the idL to set
     */
    public void setIdL(JLabel idL) {
        this.idL = idL;
    }

    /**
     * @return the reasonL
     */
    public JLabel getReasonL() {
        return reasonL;
    }

    /**
     * @param reasonL the reasonL to set
     */
    public void setReasonL(JLabel reasonL) {
        this.reasonL = reasonL;
    }

    /**
     * @return the idT
     */
    public JTextField getIdT() {
        return idT;
    }

    /**
     * @param idT the idT to set
     */
    public void setIdT(JTextField idT) {
        this.idT = idT;
    }

    /**
     * @return the reasonT
     */
    public JTextField getReasonT() {
        return reasonT;
    }

    /**
     * @param reasonT the reasonT to set
     */
    public void setReasonT(JTextField reasonT) {
        this.reasonT = reasonT;
    }

    /**
     * @return the createA
     */
    public JButton getCreateA() {
        return createA;
    }

    /**
     * @param createA the createA to set
     */
    public void setCreateA(JButton createA) {
        this.createA = createA;
    }

    /**
     * @return the defaultP
     */
    public JPanel getDefaultP() {
        return defaultP;
    }

    /**
     * @param defaultP the defaultP to set
     */
    public void setDefaultP(JPanel defaultP) {
        this.defaultP = defaultP;
    }

    /**
     * @return the searchIDL
     */
    public JLabel getSearchIDL() {
        return searchIDL;
    }

    /**
     * @param searchIDL the searchIDL to set
     */
    public void setSearchIDL(JLabel searchIDL) {
        this.searchIDL = searchIDL;
    }

    /**
     * @return the searchIDT
     */
    public JTextField getSearchIDT() {
        return searchIDT;
    }

    /**
     * @param searchIDT the searchIDT to set
     */
    public void setSearchIDT(JTextField searchIDT) {
        this.searchIDT = searchIDT;
    }
}

