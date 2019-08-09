/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaresystem.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author Shane
 */
public final class AccountList {


    ArrayList<Account> accountList = null;
    public AccountList() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("info.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        accountList = (ArrayList<Account>) in.readObject();
        in.close();
        fileIn.close();
    }
    /**
     * @return the accountList
     */
    public ArrayList<Account> getAccountList() {
        return accountList;
    }
}