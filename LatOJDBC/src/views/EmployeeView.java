/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.DepartmentController;
import controllers.EmployeeController;
import controllers.JobController;
import controllers.JobHistoryController;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Employee;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tools.DBConnection;

/**
 *
 * @author FES
 */
public class EmployeeView extends javax.swing.JInternalFrame {

    DBConnection connection = new DBConnection();
    EmployeeController ec = new EmployeeController(connection.getConnection());
    JobHistoryController jhc = new JobHistoryController(connection.getConnection());
    JobController jc = new JobController(connection.getConnection());
    DepartmentController dc = new DepartmentController(connection.getConnection());
    List<Employee> employeeList = new ArrayList<>();
    List<models.Job> jobList = new ArrayList<>();
    List<models.Department> departmentList = new ArrayList<>();

    DefaultTableModel tableModel;

    /**
     * Creates new form NewJInternalFrame
     */
    public EmployeeView() {
        initComponents();
        showAllEmployeeTable(ec.getAllData());
        showAllEmployee(ec.getAllData());
        showAllJob(jc.getAll());
        showAllDepartment(dc.seachBy(""));
        setComboBox();
    }

    private boolean konfirmasi() {
        if (tfEmployeeEmployeeId.getText().equals("")
                || tfEmployeeFirstName.getText().equals("")
                || tfEmployeeLastName.getText().equals("")
                || tfEmployeeEmail.getText().equals("")
                || tfEmployeePhoneNumber.getText().equals("")
                || tfEmployeeHireDate.getText().equals("")
                || cbEmployeeJobId.getSelectedIndex() == 0
                || cbEmployeeDepartmentId.getSelectedIndex() == 0
                || cbEmployeeManagerId.getSelectedIndex() == 0
                || tfEmployeeSalary.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong");
            return false;
        }
        return true;
    }

    private void clear() {
        tfEmployeeEmployeeId.setEnabled(true);
        tfEmployeeEmployeeId.setText("");
        tfEmployeeFirstName.setText("");
        tfEmployeeLastName.setText("");
        tfEmployeeEmail.setText("");
        tfEmployeePhoneNumber.setText("");
        tfEmployeeHireDate.setText("");
        cbEmployeeJobId.setSelectedIndex(0);
        tfEmployeeSalary.setText("");
        tfEmployeeCommissionPct.setText("");
        cbEmployeeManagerId.setSelectedIndex(0);
        cbEmployeeDepartmentId.setSelectedIndex(0);
        tfEmployeeSearch.setText("");
    }

    private boolean isEmpty() {
        return ec.searchData(tfEmployeeEmployeeId.getText(), true).isEmpty();
    }

    private void setComboBox() {
        for (models.Job data : jobList) {
            cbEmployeeJobId.addItem(data.getId() + " - " + data.getName());
        }
        for (Employee data : employeeList) {
            cbEmployeeManagerId.addItem(data.getEmployeeId() + " - " + data.getFirst_name() + " " + data.getLast_name());
        }
        for (models.Department data : departmentList) {
            cbEmployeeDepartmentId.addItem(data.getId() + " - " + data.getName());
        }
    }

    private void showAllEmployeeTable(List<Employee> employees) {
        Object[] columnNames = {"Nomor", "Employee ID", "First Name", "Last Name", "Email", "Phone Number", "Hire Date", "Job ID", "Salary", "Commission PCT", "Manager ID", "Department ID"};
        Object[][] data = new Object[employees.size()][columnNames.length];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = (i + 1);
            data[i][1] = employees.get(i).getEmployeeId();
            data[i][2] = employees.get(i).getFirst_name();
            data[i][3] = employees.get(i).getLast_name();
            data[i][4] = employees.get(i).getEmail();
            data[i][5] = employees.get(i).getPhone_number();
            data[i][6] = employees.get(i).getHire_date();
            data[i][7] = employees.get(i).getJob_id();
            data[i][8] = employees.get(i).getSalary();
            data[i][9] = employees.get(i).getCommission_pct();
            data[i][10] = employees.get(i).getManager_id();
            data[i][11] = employees.get(i).getDepartment_id();
        }
        tableModel = new DefaultTableModel(data, columnNames);
        tbEmployee.setModel(tableModel);
    }

    private void showAllJob(List<models.Job> jobs) {
        jobList = jobs;
    }

    private void showAllDepartment(List<models.Department> departments) {
        departmentList = departments;
    }

    private void showAllEmployee(List<Employee> employees) {
        employeeList.clear();
        employeeList = employees;
    }

    private void filterhuruf(KeyEvent a) {
        if (Character.isAlphabetic(a.getKeyChar())) {
            a.consume();
            JOptionPane.showMessageDialog(null, "Hanya Bisa Memasukan Karakter Angka");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnEmployeeMain = new javax.swing.JPanel();
        pnEmployeeMainNorth = new javax.swing.JPanel();
        lblEmployeeTitle = new javax.swing.JLabel();
        pnEmployeeMainWest = new javax.swing.JPanel();
        lblEmployeeEmployeeId = new javax.swing.JLabel();
        lblEmployeeFirstName = new javax.swing.JLabel();
        lblEmployeeLastName = new javax.swing.JLabel();
        lblEmployeeEmail = new javax.swing.JLabel();
        lblEmployeePhoneNumber = new javax.swing.JLabel();
        lblEmployeeHireDate = new javax.swing.JLabel();
        lblEmployeeJobId = new javax.swing.JLabel();
        lblEmployeeSalary = new javax.swing.JLabel();
        lblEmployeeCommissionPct = new javax.swing.JLabel();
        lblEmployeeManagerId = new javax.swing.JLabel();
        lblEmployeeDepartmentId = new javax.swing.JLabel();
        spcEmployees1 = new javax.swing.JSeparator();
        lblEmployeeSearch = new javax.swing.JLabel();
        pnEmployeeMainCenter = new javax.swing.JPanel();
        tfEmployeeEmployeeId = new javax.swing.JTextField();
        tfEmployeeFirstName = new javax.swing.JTextField();
        tfEmployeeLastName = new javax.swing.JTextField();
        tfEmployeeEmail = new javax.swing.JTextField();
        tfEmployeePhoneNumber = new javax.swing.JTextField();
        tfEmployeeHireDate = new javax.swing.JTextField();
        cbEmployeeJobId = new javax.swing.JComboBox<>();
        tfEmployeeSalary = new javax.swing.JTextField();
        tfEmployeeCommissionPct = new javax.swing.JTextField();
        cbEmployeeManagerId = new javax.swing.JComboBox<>();
        cbEmployeeDepartmentId = new javax.swing.JComboBox<>();
        pnEmployeeMainCenterContent1 = new javax.swing.JPanel();
        btEmployeeInsert = new javax.swing.JButton();
        btEmployeeDelete = new javax.swing.JButton();
        pnEmployeeMainCenterContent2 = new javax.swing.JPanel();
        tfEmployeeSearch = new javax.swing.JTextField();
        pnEmployeeMainCenterContent3 = new javax.swing.JPanel();
        spcEmployees2 = new javax.swing.JSeparator();
        btEmployeeSearch = new javax.swing.JButton();
        chbEmployeeisGetById = new javax.swing.JCheckBox();
        spcEmployees3 = new javax.swing.JSeparator();
        btEmployeeClear = new javax.swing.JButton();
        btReport = new javax.swing.JButton();
        pnEmployeeMainSouth = new javax.swing.JPanel();
        scpEmployee = new javax.swing.JScrollPane();
        tbEmployee = new javax.swing.JTable();

        setClosable(true);
        setVisible(true);

        pnEmployeeMain.setLayout(new java.awt.BorderLayout(1, 1));

        lblEmployeeTitle.setText("EMPLOYEES");
        pnEmployeeMainNorth.add(lblEmployeeTitle);

        pnEmployeeMain.add(pnEmployeeMainNorth, java.awt.BorderLayout.PAGE_START);

        pnEmployeeMainWest.setLayout(new java.awt.GridLayout(14, 1));

        lblEmployeeEmployeeId.setText("Employee ID");
        pnEmployeeMainWest.add(lblEmployeeEmployeeId);

        lblEmployeeFirstName.setText("First Name");
        pnEmployeeMainWest.add(lblEmployeeFirstName);

        lblEmployeeLastName.setText("Last Name");
        pnEmployeeMainWest.add(lblEmployeeLastName);

        lblEmployeeEmail.setText("Email");
        pnEmployeeMainWest.add(lblEmployeeEmail);

        lblEmployeePhoneNumber.setText("Phone Number");
        pnEmployeeMainWest.add(lblEmployeePhoneNumber);

        lblEmployeeHireDate.setText("Hire Date");
        pnEmployeeMainWest.add(lblEmployeeHireDate);

        lblEmployeeJobId.setText("Job Id");
        pnEmployeeMainWest.add(lblEmployeeJobId);

        lblEmployeeSalary.setText("Salary");
        pnEmployeeMainWest.add(lblEmployeeSalary);

        lblEmployeeCommissionPct.setText("Commission Pct");
        pnEmployeeMainWest.add(lblEmployeeCommissionPct);

        lblEmployeeManagerId.setText("Manager Id");
        pnEmployeeMainWest.add(lblEmployeeManagerId);

        lblEmployeeDepartmentId.setText("Department Id");
        pnEmployeeMainWest.add(lblEmployeeDepartmentId);
        pnEmployeeMainWest.add(spcEmployees1);

        lblEmployeeSearch.setText("Search/Delete");
        pnEmployeeMainWest.add(lblEmployeeSearch);

        pnEmployeeMain.add(pnEmployeeMainWest, java.awt.BorderLayout.LINE_START);

        pnEmployeeMainCenter.setLayout(new java.awt.GridLayout(14, 1));

        tfEmployeeEmployeeId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmployeeEmployeeIdActionPerformed(evt);
            }
        });
        tfEmployeeEmployeeId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfEmployeeEmployeeIdKeyTyped(evt);
            }
        });
        pnEmployeeMainCenter.add(tfEmployeeEmployeeId);
        pnEmployeeMainCenter.add(tfEmployeeFirstName);
        pnEmployeeMainCenter.add(tfEmployeeLastName);
        pnEmployeeMainCenter.add(tfEmployeeEmail);
        pnEmployeeMainCenter.add(tfEmployeePhoneNumber);

        tfEmployeeHireDate.setToolTipText("format: yyyy-mm-dd");
        pnEmployeeMainCenter.add(tfEmployeeHireDate);

        cbEmployeeJobId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cbEmployeeJobId.setToolTipText("");
        cbEmployeeJobId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEmployeeJobIdActionPerformed(evt);
            }
        });
        pnEmployeeMainCenter.add(cbEmployeeJobId);

        tfEmployeeSalary.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfEmployeeSalaryKeyTyped(evt);
            }
        });
        pnEmployeeMainCenter.add(tfEmployeeSalary);
        pnEmployeeMainCenter.add(tfEmployeeCommissionPct);

        cbEmployeeManagerId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cbEmployeeManagerId.setToolTipText("");
        cbEmployeeManagerId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEmployeeManagerIdActionPerformed(evt);
            }
        });
        pnEmployeeMainCenter.add(cbEmployeeManagerId);

        cbEmployeeDepartmentId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cbEmployeeDepartmentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEmployeeDepartmentIdActionPerformed(evt);
            }
        });
        pnEmployeeMainCenter.add(cbEmployeeDepartmentId);

        pnEmployeeMainCenterContent1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btEmployeeInsert.setText("Save");
        btEmployeeInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmployeeInsertActionPerformed(evt);
            }
        });
        pnEmployeeMainCenterContent1.add(btEmployeeInsert);

        btEmployeeDelete.setText("Delete");
        btEmployeeDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmployeeDeleteActionPerformed(evt);
            }
        });
        pnEmployeeMainCenterContent1.add(btEmployeeDelete);

        pnEmployeeMainCenter.add(pnEmployeeMainCenterContent1);

        pnEmployeeMainCenterContent2.setLayout(new java.awt.GridLayout(1, 0));
        pnEmployeeMainCenterContent2.add(tfEmployeeSearch);

        pnEmployeeMainCenter.add(pnEmployeeMainCenterContent2);

        pnEmployeeMainCenterContent3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        pnEmployeeMainCenterContent3.add(spcEmployees2);

        btEmployeeSearch.setText("Search");
        btEmployeeSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmployeeSearchActionPerformed(evt);
            }
        });
        pnEmployeeMainCenterContent3.add(btEmployeeSearch);

        chbEmployeeisGetById.setText("Get By ID");
        chbEmployeeisGetById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbEmployeeisGetByIdActionPerformed(evt);
            }
        });
        pnEmployeeMainCenterContent3.add(chbEmployeeisGetById);
        pnEmployeeMainCenterContent3.add(spcEmployees3);

        btEmployeeClear.setText("Clear");
        btEmployeeClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmployeeClearActionPerformed(evt);
            }
        });
        pnEmployeeMainCenterContent3.add(btEmployeeClear);

        btReport.setText("REPORT");
        btReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportActionPerformed(evt);
            }
        });
        pnEmployeeMainCenterContent3.add(btReport);

        pnEmployeeMainCenter.add(pnEmployeeMainCenterContent3);

        pnEmployeeMain.add(pnEmployeeMainCenter, java.awt.BorderLayout.CENTER);

        scpEmployee.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scpEmployee.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEmployeeMouseClicked(evt);
            }
        });
        scpEmployee.setViewportView(tbEmployee);

        javax.swing.GroupLayout pnEmployeeMainSouthLayout = new javax.swing.GroupLayout(pnEmployeeMainSouth);
        pnEmployeeMainSouth.setLayout(pnEmployeeMainSouthLayout);
        pnEmployeeMainSouthLayout.setHorizontalGroup(
            pnEmployeeMainSouthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
        );
        pnEmployeeMainSouthLayout.setVerticalGroup(
            pnEmployeeMainSouthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnEmployeeMain.add(pnEmployeeMainSouth, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnEmployeeMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnEmployeeMain, javax.swing.GroupLayout.PREFERRED_SIZE, 670, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfEmployeeEmployeeIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmployeeEmployeeIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmployeeEmployeeIdActionPerformed

    private void btEmployeeInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmployeeInsertActionPerformed
//        ec.insert(jTFEmployeeEmployeeId.getText(), jTFEmployeeFirstName.getText(), jTFEmployeeLastName.getText(), jTFEmployeeEmail.getText(), jTFEmployeePhoneNumber.getText(), jTFEmployeeHireDate.getText(), jTFEmployeeJobId.getText(), jTFEmployeeSalary.getText(), jTFEmployeeCommissionPct.getText(), jTFEmployeeManagerId.getText(), jTFEmployeeDepartmentId.getText());
        if (konfirmasi()) {
            if (isEmpty()) {
                JOptionPane.showMessageDialog(null, ec.insert(
                        tfEmployeeEmployeeId.getText(), tfEmployeeFirstName.getText(), tfEmployeeLastName.getText(),
                        tfEmployeeEmail.getText(), tfEmployeePhoneNumber.getText(), tfEmployeeHireDate.getText(),
                        cbEmployeeJobId.getSelectedItem().toString().split(" - ")[0], tfEmployeeSalary.getText(),
                        tfEmployeeCommissionPct.getText(),
                        cbEmployeeManagerId.getSelectedItem().toString().split(" - ")[0],
                        cbEmployeeDepartmentId.getSelectedItem().toString().split(" - ")[0]));
                clear();
            } else {
                try {
                    int reply = JOptionPane.showConfirmDialog(null,
                            "Anda yakin akan melakukan perubahan data?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                    );
                    if (reply == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, ec.update(tfEmployeeEmployeeId.getText(), tfEmployeeFirstName.getText(),
                                tfEmployeeLastName.getText(), tfEmployeeEmail.getText(), tfEmployeePhoneNumber.getText(),
                                tfEmployeeHireDate.getText(), cbEmployeeJobId.getSelectedItem().toString().split(" - ")[0],
                                tfEmployeeSalary.getText(), tfEmployeeCommissionPct.getText(),
                                cbEmployeeManagerId.getSelectedItem().toString().split(" - ")[0],
                                cbEmployeeDepartmentId.getSelectedItem().toString().split(" - ")[0]));
                        clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            showAllEmployeeTable(ec.getAllData());
        }
    }//GEN-LAST:event_btEmployeeInsertActionPerformed

    private void btEmployeeDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmployeeDeleteActionPerformed
//        JOptionPane.showConfirmDialog(null, ec.delete(jTFEmployeeSearch.getText()));
        if (tfEmployeeSearch.equals("")) {
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong");
        } else {
            try {
                int reply = JOptionPane.showConfirmDialog(null,
                        "Anda yakin akan menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                );
                if (reply == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, ec.delete(tfEmployeeEmployeeId.getText()));
                    tfEmployeeSearch.setText("");
                    showAllEmployeeTable(ec.getAllData());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        showAllEmployeeTable(ec.getAllData());
    }//GEN-LAST:event_btEmployeeDeleteActionPerformed

    private void btEmployeeSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmployeeSearchActionPerformed
        if (!chbEmployeeisGetById.isSelected()) {
            showAllEmployeeTable(ec.searchData(tfEmployeeSearch.getText(), chbEmployeeisGetById.isSelected()));
        } else {
            Employee data = ec.getById(tfEmployeeSearch.getText());
            tfEmployeeEmployeeId.setText(data.getEmployeeId() + "");
            tfEmployeeEmployeeId.setEnabled(false);
            tfEmployeeFirstName.setText(data.getFirst_name());
            tfEmployeeLastName.setText(data.getLast_name());
            tfEmployeeEmail.setText(data.getEmail());
            tfEmployeePhoneNumber.setText(data.getPhone_number());
            tfEmployeeHireDate.setText(data.getHire_date());
            for (int i = 0; i < cbEmployeeJobId.getItemCount(); i++) {
                if (cbEmployeeJobId.getItemAt(i).split(" - ")[0].equals(data.getJob_id())) {
                    cbEmployeeJobId.setSelectedIndex(i);
                }
            }
            tfEmployeeSalary.setText(data.getSalary() + "");
            tfEmployeeCommissionPct.setText(data.getCommission_pct() + "");
            for (int i = 0; i < cbEmployeeManagerId.getItemCount(); i++) {
                if (cbEmployeeManagerId.getItemAt(i).split(" - ")[0].equals(data.getManager_id() + "")) {
                    cbEmployeeManagerId.setSelectedIndex(i);
                }
            }
            for (int i = 0; i < cbEmployeeDepartmentId.getItemCount(); i++) {
                if (cbEmployeeDepartmentId.getItemAt(i).split(" - ")[0].equals(data.getDepartment_id() + "")) {
                    cbEmployeeDepartmentId.setSelectedIndex(i);
                }
            }
        }
    }//GEN-LAST:event_btEmployeeSearchActionPerformed

    private void chbEmployeeisGetByIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbEmployeeisGetByIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbEmployeeisGetByIdActionPerformed

    private void cbEmployeeJobIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEmployeeJobIdActionPerformed

    }//GEN-LAST:event_cbEmployeeJobIdActionPerformed

    private void tbEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEmployeeMouseClicked
        tfEmployeeEmployeeId.setText(tbEmployee.getValueAt(tbEmployee.getSelectedRow(), 1).toString());
        tfEmployeeEmployeeId.setEnabled(false);
        tfEmployeeFirstName.setText(tbEmployee.getValueAt(tbEmployee.getSelectedRow(), 2).toString());
        tfEmployeeLastName.setText(tbEmployee.getValueAt(tbEmployee.getSelectedRow(), 3).toString());
        tfEmployeeEmail.setText(tbEmployee.getValueAt(tbEmployee.getSelectedRow(), 4).toString());
        tfEmployeePhoneNumber.setText(tbEmployee.getValueAt(tbEmployee.getSelectedRow(), 5).toString());
        tfEmployeeHireDate.setText(tbEmployee.getValueAt(tbEmployee.getSelectedRow(), 6).toString());
        for (int i = 0; i < cbEmployeeJobId.getItemCount(); i++) {
            if (cbEmployeeJobId.getItemAt(i).split(" - ")[0].equals(tbEmployee.getValueAt(tbEmployee.getSelectedRow(), 7).toString())) {
                cbEmployeeJobId.setSelectedIndex(i);
            }
        }
        tfEmployeeSalary.setText(tbEmployee.getValueAt(tbEmployee.getSelectedRow(), 8).toString());
        tfEmployeeCommissionPct.setText(tbEmployee.getValueAt(tbEmployee.getSelectedRow(), 9).toString());
        for (int i = 0; i < cbEmployeeManagerId.getItemCount(); i++) {
            if (cbEmployeeManagerId.getItemAt(i).split(" - ")[0].equals(tbEmployee.getValueAt(tbEmployee.getSelectedRow(), 10).toString())) {
                cbEmployeeManagerId.setSelectedIndex(i);
            }
        }
        for (int i = 0; i < cbEmployeeDepartmentId.getItemCount(); i++) {
            if (cbEmployeeDepartmentId.getItemAt(i).split(" - ")[0].equals(tbEmployee.getValueAt(tbEmployee.getSelectedRow(), 11).toString())) {
                cbEmployeeDepartmentId.setSelectedIndex(i);
            }
        }
    }//GEN-LAST:event_tbEmployeeMouseClicked

    private void btEmployeeClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmployeeClearActionPerformed
        clear();
    }//GEN-LAST:event_btEmployeeClearActionPerformed

    private void tfEmployeeSalaryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEmployeeSalaryKeyTyped
        filterhuruf(evt);
    }//GEN-LAST:event_tfEmployeeSalaryKeyTyped

    private void cbEmployeeManagerIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEmployeeManagerIdActionPerformed

    }//GEN-LAST:event_cbEmployeeManagerIdActionPerformed

    private void cbEmployeeDepartmentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEmployeeDepartmentIdActionPerformed

    }//GEN-LAST:event_cbEmployeeDepartmentIdActionPerformed

    private void tfEmployeeEmployeeIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEmployeeEmployeeIdKeyTyped
        filterhuruf(evt);
    }//GEN-LAST:event_tfEmployeeEmployeeIdKeyTyped

    private void btReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportActionPerformed
        // TODO add your handling code here
//        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(Popul.cr);
//        try {
//            String fileName = "./src/Report2nd/report1.jrxml";
//            String filetoFill = "./src/Report2nd/report1.jasper";
//            JasperCompileManager.compileReport(fileName);
//            Map param = new HashMap();
//            JasperFillManager.fillReport(filetoFill, param, connection.getConnection());
//            JasperPrint jp = JasperFillManager.fillReport(filetoFill, param, connection.getConnection());
//            JasperViewer.viewReport(jp, false);
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//        }
//
////        try {
////            InputStream in = new FileInputStream(new File("C:\\Users\\Pandu\\Documents\\GitHub\\lembaranBaru\\LatOJDBC\\src\\Report2nd\\employeeDataTable.jrxml"));
////            JasperDesign jd = JRXmlLoader.load(in);
////            String 
////            
////        } catch (Exception e) {
////        }

        try {
            String fileName = "./src/Report2nd/report1.jrxml";
            String filetoFill = "./src/Report2nd/report1.jasper";
            JasperCompileManager.compileReport(fileName);
            Map param = new HashMap();
            JasperFillManager.fillReport(filetoFill, param, connection.getConnection());
            JasperPrint jp = JasperFillManager.fillReport(filetoFill, param, connection.getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }//GEN-LAST:event_btReportActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEmployeeClear;
    private javax.swing.JButton btEmployeeDelete;
    private javax.swing.JButton btEmployeeInsert;
    private javax.swing.JButton btEmployeeSearch;
    private javax.swing.JButton btReport;
    private javax.swing.JComboBox<String> cbEmployeeDepartmentId;
    private javax.swing.JComboBox<String> cbEmployeeJobId;
    private javax.swing.JComboBox<String> cbEmployeeManagerId;
    private javax.swing.JCheckBox chbEmployeeisGetById;
    private javax.swing.JLabel lblEmployeeCommissionPct;
    private javax.swing.JLabel lblEmployeeDepartmentId;
    private javax.swing.JLabel lblEmployeeEmail;
    private javax.swing.JLabel lblEmployeeEmployeeId;
    private javax.swing.JLabel lblEmployeeFirstName;
    private javax.swing.JLabel lblEmployeeHireDate;
    private javax.swing.JLabel lblEmployeeJobId;
    private javax.swing.JLabel lblEmployeeLastName;
    private javax.swing.JLabel lblEmployeeManagerId;
    private javax.swing.JLabel lblEmployeePhoneNumber;
    private javax.swing.JLabel lblEmployeeSalary;
    private javax.swing.JLabel lblEmployeeSearch;
    private javax.swing.JLabel lblEmployeeTitle;
    private javax.swing.JPanel pnEmployeeMain;
    private javax.swing.JPanel pnEmployeeMainCenter;
    private javax.swing.JPanel pnEmployeeMainCenterContent1;
    private javax.swing.JPanel pnEmployeeMainCenterContent2;
    private javax.swing.JPanel pnEmployeeMainCenterContent3;
    private javax.swing.JPanel pnEmployeeMainNorth;
    private javax.swing.JPanel pnEmployeeMainSouth;
    private javax.swing.JPanel pnEmployeeMainWest;
    private javax.swing.JScrollPane scpEmployee;
    private javax.swing.JSeparator spcEmployees1;
    private javax.swing.JSeparator spcEmployees2;
    private javax.swing.JSeparator spcEmployees3;
    private javax.swing.JTable tbEmployee;
    private javax.swing.JTextField tfEmployeeCommissionPct;
    private javax.swing.JTextField tfEmployeeEmail;
    private javax.swing.JTextField tfEmployeeEmployeeId;
    private javax.swing.JTextField tfEmployeeFirstName;
    private javax.swing.JTextField tfEmployeeHireDate;
    private javax.swing.JTextField tfEmployeeLastName;
    private javax.swing.JTextField tfEmployeePhoneNumber;
    private javax.swing.JTextField tfEmployeeSalary;
    private javax.swing.JTextField tfEmployeeSearch;
    // End of variables declaration//GEN-END:variables
}
