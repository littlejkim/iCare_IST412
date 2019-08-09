package icaresystem.Controller;

import icaresystem.Model.AccountList;
import icaresystem.Model.Appointment;
import icaresystem.Model.AppointmentList;
import icaresystem.Model.Prescription;
import icaresystem.Model.PrescriptionList;
import icaresystem.View.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Joseph Hackett
 */
public class MainController {

    Connection conn;
    Statement stat;

    AccountList accountList;
    AppointmentList appointmentList;
    PrescriptionList presList;

    MainFrame frame;

    public MainController(String title) {
        // create main frame
        frame = new MainFrame(title);

        // Connect to databasee
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/icare", "littlejkim", "4johnkim");
            System.out.println("Connection successful");
            stat = conn.createStatement();
        } catch (Exception e) {
            System.out.println("Exception : " + e.toString());
        }

        //Create lists
        accountList = new AccountList();
        appointmentList = new AppointmentList();
        presList = new PrescriptionList();

        // make new account
        frame.getAccount().getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = "'" + frame.getAccount().getField().getText() + "'";
                String query = "INSERT INTO ACCOUNT (NAME) values (" + text + ")";
                System.out.println(text);
                try {
                    stat.executeUpdate(query);
                    System.out.println("Successfully inserted");
                    stat.close();
                } catch (Exception ex) {
                    System.out.println("Exception caught" + ex.toString());
                }
            }
        });

        // authenticate id and password from database
        frame.getHome().getSubmit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pwd = String.valueOf(frame.getHome().getPassword().getPassword());
                if (frame.getHome().getId().getText().equals("a")) {
                    if (pwd.equals("a")) {
                        frame.getHome().getLoggedOn().setText("User is logged on");
                        frame.enableTabs(true);
                    } else {
                        frame.getHome().getLoggedOn().setText("Password is incorrect");
                        frame.enableTabs(false);
                    }
                } else {
                    frame.getHome().getLoggedOn().setText("Username is incorrect");
                    frame.enableTabs(false);
                }
            }
        });

        //Open Create/View/Edit Appointment Panels
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

                            Appointment newApp = new Appointment((appointmentList.getAppList().size() + 1), accountList.getAccountList().get(id - 1), reason);

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
                            if (accountList.getAccountList().get(a - 1).equals(appointmentList.getAppList().get(i).getPatient())) {
                                frame.getAppointment().initViewP(accountList.getAccountList().get(a - 1), appointmentList.getAppList().get(i));
                                frame.getAppointment().revalidate();
                                frame.getAppointment().repaint();
                            } else {
                                frame.getAppointment().getSearchIDT().setText("Try Another Patient");
                            }
                        }
                    }

                    //Open Edit Panel    
                } else if (frame.getAppointment().getEdit().isSelected()) {
                    frame.getAppointment().resetD();
                    if (frame.getAppointment().getSearchIDT().getText().equals("")) {
                        frame.getAppointment().getSearchIDT().setText("Enter Patient ID Here");
                    } else {
                        int a = Integer.parseInt(frame.getAppointment().getSearchIDT().getText());
                        for (int i = 0; i < appointmentList.getAppList().size(); i++) {
                            if (accountList.getAccountList().get(a - 1).equals(appointmentList.getAppList().get(i).getPatient())) {
                                frame.getAppointment().initEditP(accountList.getAccountList().get(a - 1), appointmentList.getAppList().get(i));
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

                                Appointment newApp = new Appointment((appointmentList.getAppList().size() + 1), accountList.getAccountList().get(a - 1), reason);

                                appointmentList.getAppList().add(newApp);
                            }
                        });

                    }

                }
            }

        });
        
        //Open Create/View/Edit Prescription Panels
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

                            Prescription p = new Prescription((presList.getPresList().size() + 1), accountList.getAccountList().get(id - 1), reason);

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
                            if (accountList.getAccountList().get(a - 1).equals(presList.getPresList().get(i).getPatient())) {
                                frame.getPrescription().initViewP(accountList.getAccountList().get(a - 1), presList.getPresList().get(i));
                                frame.getPrescription().revalidate();
                                frame.getPrescription().repaint();
                            } else {
                                frame.getPrescription().getSearchIDT().setText("Try Another Patient");
                            }
                        }
                    }

                    //Open Edit Panel    
                } else if (frame.getPrescription().getEdit().isSelected()) {
                    frame.getPrescription().resetD();
                    if (frame.getPrescription().getSearchIDT().getText().equals("")) {
                        frame.getPrescription().getSearchIDT().setText("Enter Patient ID Here");
                    } else {
                        int a = Integer.parseInt(frame.getPrescription().getSearchIDT().getText());
                        for (int i = 0; i < presList.getPresList().size(); i++) {
                            if (accountList.getAccountList().get(a - 1).equals(presList.getPresList().get(i).getPatient())) {
                                frame.getPrescription().initEditP(accountList.getAccountList().get(a - 1), presList.getPresList().get(i));
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

                                Prescription p = new Prescription((presList.getPresList().size() + 1), accountList.getAccountList().get(a - 1), rx);

                                presList.getPresList().add(p);
                            }
                        });

                    }

                }
            }

        });

    }
}
