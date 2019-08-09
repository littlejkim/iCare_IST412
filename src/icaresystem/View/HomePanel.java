/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaresystem.View;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Terry
 */
public class HomePanel extends JPanel {
    private JTextField id;
    private JPasswordField password;
    private JButton submit;
    private JLabel loggedOn;
    private JButton register;
    public HomePanel() {
        id = new JTextField(10);
        password = new JPasswordField(10);
        submit = new JButton("Log on");
        loggedOn = new JLabel("used not logged on");;
        register = new JButton("Register");
        this.add(new JLabel("Home"));
        this.add(new JLabel("ID"));
        this.add(id);
        this.add(new JLabel("Password"));
        this.add(password);
        this.add(submit);
        this.add(loggedOn);
        this.add(register);
    }

    
    
    /**
     * @return the id
     */
    public JTextField getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(JTextField id) {
        this.id = id;
    }

    /**
     * @return the password
     */
    public JPasswordField getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    /**
     * @return the submit
     */
    public JButton getSubmit() {
        return submit;
    }

    /**
     * @param submit the submit to set
     */
    public void setSubmit(JButton submit) {
        this.submit = submit;
    }

    /**
     * @return the loggedOn
     */
    public JLabel getLoggedOn() {
        return loggedOn;
    }

    /**
     * @param loggedOn the loggedOn to set
     */
    public void setLoggedOn(JLabel loggedOn) {
        this.loggedOn = loggedOn;
    }

    /**
     * @return the register
     */
    public JButton getRegister() {
        return register;
    }

    /**
     * @param register the register to set
     */
    public void setRegister(JButton register) {
        this.register = register;
    }
    
    
}
