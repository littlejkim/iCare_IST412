package icaresystem.Controller;

import icaresystem.Model.AccountList;
import icaresystem.Model.Account;
import icaresystem.Model.Appointment;
import icaresystem.Model.AppointmentList;
import icaresystem.Model.Prescription;
import icaresystem.Model.PrescriptionList;
import icaresystem.View.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Joseph Hackett
 */
public class MainController {

    Connection conn;
    Statement stat;
    ArrayList<Account> accountList = null;
    AppointmentList appointmentList;
    PrescriptionList presList;

    MainFrame frame;

    public MainController(String title) throws IOException, FileNotFoundException, ClassNotFoundException {
        // create main frame
        frame = new MainFrame(title);


        //Create lists
        appointmentList = new AppointmentList();
        presList = new PrescriptionList();
        AccountList list = new AccountList();
        accountList = list.getAccountList();

        // authenticate id and password from database
        frame.getHome().getSubmit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("autneticate");
                
                for(int i = 0; i < accountList.size(); i++) {
                    if(accountList.get(i).getId().equals(frame.getHome().getId().getText()) && accountList.get(i).getPassword().equals(new String(frame.getHome().getPassword().getPassword()))) {
                        frame.getHome().getLoggedOn().setText("User is logged on");
                        frame.enableTabs(true);
                    } else {
                        frame.getHome().getLoggedOn().setText("ID or Password is incorrect");
                        frame.enableTabs(false);
                    }
                    
                }
            }
        });
        
        // add user to database
        frame.getHome().getRegister().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = frame.getHome().getId().getText();
                String password = new String(frame.getHome().getPassword().getPassword());
                Account newac = new Account(1, id, password);
  
                ArrayList<Account> list = accountList;
                list.add(newac);
                try {
                    FileOutputStream fileOut = new FileOutputStream("info.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(list);
                    out.close();
                    fileOut.close();
                    System.out.println("User Saved to Database");
                    System.out.println("ID: " + id);
                    System.out.println("Password: " + password);
                    frame.getHome().getLoggedOn().setText("User registration complete");
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
            
        });

//        //Open Create/View/Edit Appointment Panels
        frame.getAppointment().getSelect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open Create Panel
                if (frame.getAppointment().getCreate().isSelected()) {
                    frame.getAppointment().resetD();
                    frame.getAppointment().initCreateP();
                    frame.getAppointment().revalidate();
                    frame.getAppointment().repaint();

                    //Create Appointment
                    frame.getAppointment().getCreateA().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int id = Integer.parseInt(frame.getAppointment().getIdT().getText());
                            String reason = frame.getAppointment().getReasonT().getText();

                            Appointment newApp = new Appointment((appointmentList.getAppList().size() + 1), accountList.get(id - 1), reason);

                            appointmentList.getAppList().add(newApp);
                        }
                    });

                    //Open View Panel    
                } else if (frame.getAppointment().getView().isSelected()) {
                    frame.getAppointment().resetD();
                    if (frame.getAppointment().getSearchIDT().getText().equals("")) {
                        frame.getAppointment().getSearchIDT().setText("Enter Patient ID Here");
                    } else {
                        int a = Integer.parseInt(frame.getAppointment().getSearchIDT().getText());
                        for (int i = 0; i < appointmentList.getAppList().size(); i++) {
                            if (accountList.get(a - 1).equals(appointmentList.getAppList().get(i).getPatient())) {
                                frame.getAppointment().initViewP(accountList.get(a - 1), appointmentList.getAppList().get(i));
                                frame.getAppointment().revalidate();
                                frame.getAppointment().repaint();
                            } else {
                                frame.getAppointment().getSearchIDT().setText("Try Another Patient");
                            }
                        }
                    }
//
//                    //Open Edit Panel    
                } else if (frame.getAppointment().getEdit().isSelected()) {
                    frame.getAppointment().resetD();
                    if (frame.getAppointment().getSearchIDT().getText().equals("")) {
                        frame.getAppointment().getSearchIDT().setText("Enter Patient ID Here");
                    } else {
                        int a = Integer.parseInt(frame.getAppointment().getSearchIDT().getText());
                        for (int i = 0; i < appointmentList.getAppList().size(); i++) {
                            if (accountList.get(a - 1).equals(appointmentList.getAppList().get(i).getPatient())) {
                                frame.getAppointment().initEditP(accountList.get(a - 1), appointmentList.getAppList().get(i));
                                frame.getAppointment().revalidate();
                                frame.getAppointment().repaint();
                            } else {
                                frame.getAppointment().getSearchIDT().setText("Try Another Patient");
                            }
                        }

                        frame.getAppointment().getCreateA().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for(int i = 0; i < appointmentList.getAppList().size(); i++) {
                                    if(appointmentList.getAppList().get(i).getPatient().getPatientId() == a)
                                    {
                                        appointmentList.getAppList().remove(i);
                                    }
                                }

                                String reason = frame.getAppointment().getReasonT().getText();

                                Appointment newApp = new Appointment((appointmentList.getAppList().size() + 1), accountList.get(a - 1), reason);

                                appointmentList.getAppList().add(newApp);
                            }
                        });

                    }

                }
            }

        });
//        
//        //Open Create/View/Edit Prescription Panels
        frame.getPrescription().getSelect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open Create Panel
                if (frame.getPrescription().getCreate().isSelected()) {
                    frame.getPrescription().resetD();
                    frame.getPrescription().initCreateP();
                    frame.getPrescription().revalidate();
                    frame.getPrescription().repaint();

                    //Create Appointment
                    frame.getPrescription().getCreateP().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int id = Integer.parseInt(frame.getPrescription().getIdT().getText());
                            String reason = frame.getPrescription().getRxT().getText();

                            Prescription p = new Prescription((presList.getPresList().size() + 1), accountList.get(id - 1), reason);

                            presList.getPresList().add(p);
                        }
                    });

                    //Open View Panel    
                } else if (frame.getPrescription().getView().isSelected()) {
                    frame.getPrescription().resetD();
                    if (frame.getPrescription().getSearchIDT().getText().equals("")) {
                        frame.getPrescription().getSearchIDT().setText("Enter Patient ID Here");
                    } else {
                        int a = Integer.parseInt(frame.getPrescription().getSearchIDT().getText());
                        for (int i = 0; i < presList.getPresList().size(); i++) {
                            if (accountList.get(a - 1).equals(presList.getPresList().get(i).getPatient())) {
                                frame.getPrescription().initViewP(accountList.get(a - 1), presList.getPresList().get(i));
                                frame.getPrescription().revalidate();
                                frame.getPrescription().repaint();
                            } else {
                                frame.getPrescription().getSearchIDT().setText("Try Another Patient");
                            }
                        }
                    }
//
//                    //Open Edit Panel    
                } else if (frame.getPrescription().getEdit().isSelected()) {
                    frame.getPrescription().resetD();
                    if (frame.getPrescription().getSearchIDT().getText().equals("")) {
                        frame.getPrescription().getSearchIDT().setText("Enter Patient ID Here");
                    } else {
                        int a = Integer.parseInt(frame.getPrescription().getSearchIDT().getText());
                        for (int i = 0; i < presList.getPresList().size(); i++) {
                            if (accountList.get(a - 1).equals(presList.getPresList().get(i).getPatient())) {
                                frame.getPrescription().initEditP(accountList.get(a - 1), presList.getPresList().get(i));
                                frame.getPrescription().revalidate();
                                frame.getPrescription().repaint();
                            } else {
                                frame.getPrescription().getSearchIDT().setText("Try Another Patient");
                            }
                        }

                        frame.getPrescription().getCreateP().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for(int i = 0; i < presList.getPresList().size(); i++) {
                                    if(presList.getPresList().get(i).getPatient().getPatientId() == a)
                                    {
                                        presList.getPresList().remove(i);
                                    }
                                }

                                String rx = frame.getPrescription().getRxT().getText();

                                Prescription p = new Prescription((presList.getPresList().size() + 1), accountList.get(a - 1), rx);

                                presList.getPresList().add(p);
                            }
                        });

                    }

                }
            }

        });

    }
}
