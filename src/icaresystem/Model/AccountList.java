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
public final class AccountList {

    private ArrayList<Account> accountList = new ArrayList<Account>();

    public AccountList() {
    }
    /**
     * @return the accountList
     */
    public ArrayList<Account> getAccountList() {
        return accountList;
    }
}