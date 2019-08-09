/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import icaresystem.Controller.MainController;
import icaresystem.Model.*;
import icaresystem.View.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Terry
 */
public class App {
     public static void main(String[] args) {
         try {
             MainController mc = new MainController("iCare");
         } catch (IOException ex) {
             Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
