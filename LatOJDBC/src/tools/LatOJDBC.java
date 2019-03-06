package tools;

import controllers.EmployeeController;
import controllers.UserController;
import daos.DepartmentDAO;
import daos.RegionDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import models.Region;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tools.DBConnection;

/**
 *
 * @author milhamafemi
 */
public class LatOJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBConnection connection = new DBConnection();
        //System.out.println(connection.getConnection());
        DepartmentDAO ddao= new DepartmentDAO(connection.getConnection());
        RegionDAO rdao = new RegionDAO(connection.getConnection());
        UserController uc = new UserController(connection.getConnection());
        //dengan constructor
        Region r= new Region();
        //tanpa constructor
//        Region r= new Region();
//        r.setId(5);
//        r.setName("Jauh seklai");
//        rdao.insert(r);
//        System.out.println(rdao.update(r));
//        for (Region region : rdao.getAll()) {
//            System.out.println("ID      : " + region.getId());
//            System.out.println("Name    : " + region.getName());
//        }
//   for (Region region : rdao.getData(20, true)) {
//            System.out.println("ID      : " + region.getId());
//            System.out.println("Name    : " + region.getName());
//        }
        EmployeeController ec = new EmployeeController(connection.getConnection());
//        System.out.println(ec.delete("210"));
//        System.out.println(uc.login("admin", "admin"));

//        try {
//            InputStream in = new FileInputStream(new File("C:\\Users\\Pandu\\Documents\\GitHub\\lembaranBaru\\LatOJDBC\\src\\Report2nd\\employeeDataTable.jrxml"));
////            String reportPath = "C:\\Users\\Pandu\\Documents\\GitHub\\lembaranBaru\\LatOJDBC\\src\\Report2nd\\employeeDataTable.jrxml";
//            JasperDesign jd = JRXmlLoader.load(in);
////            String sql = "SELECT * FROM EMPLOYEES";
////            JRDesignQuery newQuery = new JRDesignQuery();
////            newQuery.setText(sql);
////            jd.setQuery(newQuery);
//            JasperReport jr = JasperCompileManager.compileReport(jd);
//            HashMap njr = new HashMap();
//            JasperPrint jp = JasperFillManager.fillReport(jr, njr, connection.getConnection());
//            JasperViewer.viewReport(jp, false);
//            OutputStream os = new FileOutputStream(new File("D:\\report"));
//            JasperExportManager.exportReportToPdfStream(jp, os);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        
        
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


    }
}


