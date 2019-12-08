/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.PrefixSelectionComboBox;

/**
 *
 * @author VASPAR
 */
public class AdminController implements Initializable {

    @FXML
    private AnchorPane left_of_split;
    @FXML
    private VBox left_of_split_vbox;
    @FXML
    private Button evaluator;
    @FXML
    private FontAwesomeIconView evaluator_icon;
    @FXML
    private Button student;
    @FXML
    private FontAwesomeIconView student_icon;
    @FXML
    private Button report;
    @FXML
    private MaterialDesignIconView report_icon;
    @FXML
    private AnchorPane right_of_split;
    @FXML
    private AnchorPane evaluator_anchor_pane;
    @FXML
    private TextField searchBox;
    @FXML
    private Button searchButton;
    @FXML
    private TableView<Evaluator> table;
    @FXML
    private TableColumn<Evaluator, Integer> column_no;
    @FXML
    private TableColumn<Evaluator, String> column_college;
    @FXML
    private TableColumn<Evaluator, String> column_branch;
    @FXML
    private TableColumn<Evaluator, Integer> column_sem;
    @FXML
    private TableColumn<Evaluator, String> column_name;
    @FXML
    private TableColumn<Evaluator, String> column_email;
    @FXML
    private TableColumn<Evaluator, String> column_sub_code;
    @FXML
    private TableColumn<Evaluator, String> column_sub_name;
    @FXML
    private TableColumn<Evaluator, Character> column_tp;
    @FXML
    private TableColumn<Evaluator, LocalDate> column_deadline;
    @FXML
    private TextField name_text_field;
    @FXML
    private TextField email_text_field;
    @FXML
    private TextField sub_code_text_field;
    @FXML
    private TextField sub_name_text_field;
    @FXML
    private PrefixSelectionComboBox ev_college_combo_box;
    @FXML
    private PrefixSelectionComboBox ev_branch_combo_box;
    @FXML
    private PrefixSelectionComboBox ev_sem_combo_box;
    @FXML
    private Button add_button;
    @FXML
    private Button delete_button;
    @FXML
    private CheckBox theory_cb;
    @FXML
    private CheckBox practical_cb;
    @FXML
    private DatePicker date_picker;
    @FXML
    private AnchorPane student_anchor_pane;
    @FXML
    private Button stu_add_button;
    @FXML
    private PrefixSelectionComboBox stu_college_combo_box;
    @FXML
    private PrefixSelectionComboBox stu_branch_combo_box;
    @FXML
    private PrefixSelectionComboBox stu_sem_combo_box;
    @FXML
    private TableView<Student> tree_table;

    @FXML
    private TableColumn<Student, Integer> column_tree_no;

    @FXML
    private TableColumn<Student, String> column_tree_id;

    @FXML
    private TableColumn<Student, String> column_tree_name;
    @FXML
    private TableView<ObservableList> rpt_table1;
    @FXML
    private TableView<Report> rpt_table;
    @FXML
    private TableColumn<Report, Float> column_rpt_int1;
    @FXML
    private TableColumn<Report, Float> column_rpt_int2;
    @FXML
    private TableColumn<Report, Float> column_rpt_atd;
    @FXML
    private TableColumn<Report, Float> column_rpt_asg;
    @FXML
    private TableColumn<Report, Float> column_rpt_qz;
    @FXML
    private TableColumn<Report, Float> column_rpt_proj;
    @FXML
    private TableColumn<Report, Float> column_rpt_pres;
    @FXML
    private TableColumn<Report, Float> column_rpt_viva;
    @FXML
    private TableColumn<Report, String> column_rpt_id;
    @FXML
    private TableColumn<Report, String> column_rpt_name;
    @FXML
    private TableColumn<Report, Float> column_rpt_marks;

    @FXML
    private AnchorPane report_anchor_pane;
    @FXML
    private Button export_button;
    @FXML
    private Button revaluate_button;
    @FXML
    private PrefixSelectionComboBox rpt_college_combo_box;
    @FXML
    private PrefixSelectionComboBox rpt_branch_combo_box;
    @FXML
    private PrefixSelectionComboBox rpt_sem_combo_box;
    @FXML
    private PrefixSelectionComboBox rpt_subject_combo_box;
    @FXML
    private RadioButton rbTheory;
    @FXML
    private RadioButton rbPractical;
    @FXML
    private RadioButton rbOverall;

    private int choice = 0;
    private int db_choice = 0;
    private int table_no = 0;
    private final static double layout_x = 41.0;
    private static double layout_y = 130.0;
    private final String[] college = {"CSPIT", "DEPSTAR"};
    private final String[] branch_cspit = {"CE", "IT", "EC", "EE", "ME", "CL"};
    private final String[] branch_depstar = {"CE", "IT", "CSE"};
    private final String[] sem = {"1", "2", "3", "4", "5", "6", "7", "8"};
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        AdminController.email = email;
    }
    
    private static String email;

    Connection con = null;
    Statement stmt = null;
    PreparedStatement prstmt = null;
    ResultSet rs = null;
    
     List<String> subject = new ArrayList<>();
     List<String> collegeL = new ArrayList<>();
     List<String> branchL = new ArrayList<>();
     List<String> semL = new ArrayList<>();
     
     Alert a=new Alert(Alert.AlertType.NONE);
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connectToDB();
        ev_college_combo_box.setItems(FXCollections.observableArrayList(college));
        ev_sem_combo_box.setItems(FXCollections.observableArrayList(sem));
        
        /*try {
            String sql = "SELECT `SUB_NAME`, `SUB_CODE` FROM `CSPIT`.`CE_EVALUATOR`"; 
            prstmt = con.prepareStatement(sql);
             rs = prstmt.executeQuery();
            while (rs.next()) {
                    subject.add(rs.getString("SUB_CODE") + " | " + rs.getString("SUB_NAME"));
            }
            //subject.add("Overall");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        subject.clear();
        collegeL.clear();
        branchL.clear();
        semL.clear();
        reinitialize();
        //rpt_subject_combo_box.setItems(FXCollections.observableArrayList(subject));
        
        column_no.setCellValueFactory(new PropertyValueFactory<>("no"));
        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        column_college.setCellValueFactory(new PropertyValueFactory<>("college"));
        column_branch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        column_sem.setCellValueFactory(new PropertyValueFactory<>("sem"));
        column_sub_code.setCellValueFactory(new PropertyValueFactory<>("subCode"));
        column_sub_name.setCellValueFactory(new PropertyValueFactory<>("subName"));
        column_tp.setCellValueFactory(new PropertyValueFactory<>("tp"));
        column_deadline.setCellValueFactory(new PropertyValueFactory<>("dt"));
        
        column_tree_no.setCellValueFactory(new PropertyValueFactory<>("no"));
        column_tree_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_tree_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        column_rpt_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_rpt_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_rpt_int1.setCellValueFactory(new PropertyValueFactory<>("int1"));
        column_rpt_int2.setCellValueFactory(new PropertyValueFactory<>("int2"));
        column_rpt_atd.setCellValueFactory(new PropertyValueFactory<>("atd"));
        column_rpt_asg.setCellValueFactory(new PropertyValueFactory<>("asg"));
        column_rpt_qz.setCellValueFactory(new PropertyValueFactory<>("qz"));
        column_rpt_proj.setCellValueFactory(new PropertyValueFactory<>("proj"));
        column_rpt_pres.setCellValueFactory(new PropertyValueFactory<>("pres"));
        column_rpt_viva.setCellValueFactory(new PropertyValueFactory<>("viva"));
        column_rpt_marks.setCellValueFactory(new PropertyValueFactory<>("marks"));
        
        column_tree_no.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        column_tree_no.setOnEditCommit((CellEditEvent<Student, Integer> t) -> {
            ((Student) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setNo((t.getNewValue()));
        });
        column_tree_id.setCellFactory(TextFieldTableCell.forTableColumn());
        column_tree_id.setOnEditCommit((CellEditEvent<Student, String> t) -> {
            ((Student) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setId(t.getNewValue());
        });
        column_tree_name.setCellFactory(TextFieldTableCell.forTableColumn());
        column_tree_name.setOnEditCommit((CellEditEvent<Student, String> t) -> {
            ((Student) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setName(t.getNewValue());
        });
        /*dataEv=FXCollections.observableArrayList();
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Evaluator> filteredData = new FilteredList<>(dataEv, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ev -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (ev.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (ev.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (ev.getSem().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (ev.getCollege().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (ev.getBranch().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (ev.getSubCode().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (ev.getSubName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (ev.getTp().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Evaluator> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);*/
    }

    private ObservableList<Evaluator> dataEv;
    private ObservableList<Student> dataSt;
    
    public void reinitialize(){
        try {
            String sql = "SELECT `SUB_NAME`, `SUB_CODE`, `COLLEGE`, `BRANCH`, `SEM` FROM `CSPIT`.`CE_EVALUATOR`"; /*WHERE `COLLEGE`=? AND `BRANCH`=? AND `SEM`=? ";*/
            prstmt = con.prepareStatement(sql);
            /*prstmt.setString(1, rpt_college_combo_box.getValue().toString());
            prstmt.setString(2, rpt_branch_combo_box.getValue().toString());
            prstmt.setString(3, rpt_sem_combo_box.getValue().toString());*/
            rs = prstmt.executeQuery();
            while (rs.next()) {
                if(!subject.contains(rs.getString("SUB_CODE")))
                    subject.add(rs.getString("SUB_CODE") + " | " + rs.getString("SUB_NAME"));
                if(!collegeL.contains(rs.getString("COLLEGE")))
                    collegeL.add(rs.getString("COLLEGE"));
                if(!branchL.contains(rs.getString("BRANCH")))
                    branchL.add(rs.getString("BRANCH"));
                if(!semL.contains(rs.getString("SEM")))
                    semL.add(rs.getString("SEM"));
            }
            //subject.add("Overall");
            stu_college_combo_box.setItems(FXCollections.observableArrayList(collegeL));
            stu_branch_combo_box.setItems(FXCollections.observableArrayList(branchL));
            stu_sem_combo_box.setItems(FXCollections.observableArrayList(semL));
            rpt_college_combo_box.setItems(FXCollections.observableArrayList(collegeL));
            rpt_branch_combo_box.setItems(FXCollections.observableArrayList(branchL));
            rpt_sem_combo_box.setItems(FXCollections.observableArrayList(semL));
            rpt_subject_combo_box.setItems(FXCollections.observableArrayList(subject));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buildData() {
        db_choice = 1;

        try {
            String SQL = new String();
            if (choice == 1) {
                dataEv = FXCollections.observableArrayList();
                SQL = "SELECT * FROM CSPIT.CE_EVALUATOR";
            } else if (choice == 2) {
                dataSt = FXCollections.observableArrayList();
                SQL = "SELECT * FROM `" + stu_college_combo_box.getValue().toString() + "`.`" + stu_sem_combo_box.getValue().toString() + stu_branch_combo_box.getValue().toString() + "_STUDENT`";
            }
            rs = con.createStatement().executeQuery(SQL);
            while (rs.next()) {
                if (choice == 1) {
                    dataEv.add(new Evaluator(rs.getInt("NO"), rs.getString("SEM"), rs.getString("NAME"), rs.getString("EMAIL"), rs.getString("COLLEGE"), rs.getString("BRANCH"), rs.getString("SUB_CODE"), rs.getString("SUB_NAME"), rs.getString("T/P"), LocalDate.parse(rs.getString("DEADLINE"))));
                    table.setItems(dataEv);
                } else if (choice == 2) {
                    dataSt.add(new Student(rs.getInt("PID"), rs.getString("SID"), rs.getString("SNAME")));
                    tree_table.setItems(dataSt);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error on Building Data");
        }
    }

    public void connectToDB() {
        /*DB*/
        assert table != null : "fx:id=\"tableview\" was not injected: check your FXML file 'Admin.fxml'.";

        DBConnection objDbClass = new DBConnection();
        try {
            con = objDbClass.getConnection();
        } catch (ClassNotFoundException | SQLException ce) {
            System.out.println(ce.getMessage());
        }
    }

    public void insertData(String sem, String name, String email, String college, String branch, String subCode, String subName, String tp, LocalDate dt) {
        try {
            //if(validateEvaluator(sem, name, email, college, branch, subCode, subName, tp, dt)){
                if (checkSubDuplicate(sem, college, branch, subCode)) {
                    createSubTable(college, branch, sem, subCode, tp);
                }
            String sql = "INSERT INTO `CSPIT`.`CE_EVALUATOR` (`COLLEGE`, `BRANCH`,`SEM`,`NAME`, `EMAIL`, `SUB_CODE`, `SUB_NAME`, `T/P`, `DEADLINE`) VALUES (?,?,?,?,?,?,?,?,?)";
            prstmt = con.prepareStatement(sql);
            //prstmt.setInt(1, no);
            prstmt.setString(1, college);
            prstmt.setString(2, branch);
            prstmt.setString(3, sem);
            prstmt.setString(4, name);
            prstmt.setString(5, email);
            prstmt.setString(6, subCode);
            prstmt.setString(7, subName);
            prstmt.setString(8, tp);
            prstmt.setDate(9, Date.valueOf(dt));
            prstmt.executeUpdate();
            addSubjects(college, branch, sem, subCode, tp);
            insertSignData(name, email);
            switch (tp) {
                case "T":
                    insertPedTData(subName, subCode,sem);
                    break;
                case "P":
                    insertPedPData(subName, subCode,sem);
                    break;
                case "B":
                    insertPedTData(subName, subCode,sem);
                    insertPedPData(subName, subCode,sem);
                    break;
                default:
                    break;
            }
            subject.clear();
            collegeL.clear();
            branchL.clear();
            semL.clear();
            reinitialize();
            //}
            
            //subject.add(subCode+" | "+subName);
            //rpt_subject_combo_box.setItems(FXCollections.observableArrayList(subject));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addSubjects(String college, String branch, String sem, String subCode, String tp) throws SQLException {
        try {
            String sql;
            if ("B".equals(tp)) {
                //sql="ALTER TABLE `"+college+"`.`"+sem+branch+"_STUDENT` ADD COLUMN `"+subCode+"-"+tp+"` FLOAT(5) NOT NULL SET DEFAULT 0.0";
                stmt = con.createStatement();
                stmt.addBatch("ALTER TABLE `" + college + "`.`" + sem + branch + "_STUDENT` ADD COLUMN `" + subCode + "-T` FLOAT(5) NOT NULL DEFAULT 0.0");
                stmt.addBatch("ALTER TABLE `" + college + "`.`" + sem + branch + "_STUDENT` ADD COLUMN `" + subCode + "-P` FLOAT(5) NOT NULL DEFAULT 0.0");
                stmt.executeBatch();
            } else {
                sql = "ALTER TABLE `" + college + "`.`" + sem + branch + "_STUDENT` ADD COLUMN `" + subCode + "-" + tp + "` FLOAT(5) NOT NULL DEFAULT 0.0";
                stmt = con.createStatement();
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkDuplicate(String sem, String name, String email, String college, String branch, String subCode, String subName) {
        try {
            String sql = "SELECT * FROM `CSPIT`.`CE_EVALUATOR` WHERE `COLLEGE`=? AND `BRANCH`=? AND `SEM`=? AND `NAME`=? AND `EMAIL`=? AND `SUB_CODE`=? AND `SUB_NAME`=?";
            prstmt = con.prepareStatement(sql);
            //prstmt.setInt(1, no);
            prstmt.setString(1, college);
            prstmt.setString(2, branch);
            prstmt.setString(3, sem);
            prstmt.setString(4, name);
            prstmt.setString(5, email);
            prstmt.setString(6, subCode);
            prstmt.setString(7, subName);
            rs = prstmt.executeQuery();
            if (!rs.next()) {
                System.out.println("No Duplicate Rows");
                return true;
            } else {
                System.out.println("Duplicate Rows");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean checkSubDuplicate(String sem, String college, String branch, String subCode) {
        try {
            String sql = "SELECT `SUB_CODE` FROM `CSPIT`.`CE_EVALUATOR` WHERE `COLLEGE`=? AND `BRANCH`=? AND `SEM`=? AND `SUB_CODE`=?";
            prstmt = con.prepareStatement(sql);
            //prstmt.setInt(1, no);
            prstmt.setString(1, college);
            prstmt.setString(2, branch);
            prstmt.setString(3, sem);
            prstmt.setString(4, subCode);
            rs = prstmt.executeQuery();
            if (!rs.next()) {
                System.out.println("No Duplicate Sub Rows");
                return true;
            } else {
                System.out.println("Duplicate Sub Rows");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void createSubTable(String college, String branch, String sem, String subCode, String tp) {
        String sql;
        
        try {
            stmt = con.createStatement();
            if(!"B".equals(tp)){
                sql = "CREATE TABLE `" + college + "`.`" + sem + branch + "_" + subCode + "-" + tp + "` (\n"
                    + "  `PID` INT NOT NULL AUTO_INCREMENT UNIQUE,\n"
                    + "  `INT1` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `INT2` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `ATD` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `ASG` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `QZ` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `PROJ` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `PRES` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `VIVA` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  PRIMARY KEY (`PID`))";
                stmt.addBatch(sql);
                for(int i=1;i<=200;i++){
                    stmt.addBatch("INSERT INTO `"+college+"`.`"+sem+branch+"_"+subCode+"-"+tp+"` (`INT1`, `INT2`, `ATD`, `ASG`, `QZ`, `PROJ`, `PRES`, `VIVA`) VALUES ('0', '0', '0', '0', '0', '0', '0', '0')");
                }
                stmt.executeBatch();
            }
            else if("B".equals(tp)){
                sql ="CREATE TABLE `" + college + "`.`" + sem + branch + "_" + subCode + "-T` (\n"
                    + "  `PID` INT NOT NULL AUTO_INCREMENT UNIQUE,\n"
                    + "  `INT1` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `INT2` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `ATD` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `ASG` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `QZ` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `PROJ` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `PRES` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `VIVA` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  PRIMARY KEY (`PID`))";
                stmt.addBatch(sql);
                sql = "CREATE TABLE `" + college + "`.`" + sem + branch + "_" + subCode + "-P` (\n"
                    + "  `PID` INT NOT NULL AUTO_INCREMENT UNIQUE,\n"
                    + "  `INT1` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `INT2` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `ATD` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `ASG` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `QZ` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `PROJ` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `PRES` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  `VIVA` FLOAT NOT NULL DEFAULT 0,\n"
                    + "  PRIMARY KEY (`PID`))";
                stmt.addBatch(sql);
                for(int i=1;i<=200;i++){
                    stmt.addBatch("INSERT INTO `"+college+"`.`"+sem+branch+"_"+subCode+"-T` (`INT1`, `INT2`, `ATD`, `ASG`, `QZ`, `PROJ`, `PRES`, `VIVA`) VALUES ('0', '0', '0', '0', '0', '0', '0', '0')");
                    stmt.addBatch("INSERT INTO `"+college+"`.`"+sem+branch+"_"+subCode+"-P` (`INT1`, `INT2`, `ATD`, `ASG`, `QZ`, `PROJ`, `PRES`, `VIVA`) VALUES ('0', '0', '0', '0', '0', '0', '0', '0')");
                }
                int a1[]=stmt.executeBatch();
                System.out.println(a1.length);
            }  

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeSubTable(String college, String branch, String sem, String subCode, String tp) {
       String sql;
        
        try {
            stmt = con.createStatement();
            if("B".equals(tp)){
                sql = "DROP TABLE `" + college + "`.`" + sem + branch + "_" + subCode + "-T`";
                stmt.addBatch(sql);
                sql = "DROP TABLE `" + college + "`.`" + sem + branch + "_" + subCode + "-P`";
                stmt.addBatch(sql);
                stmt.executeBatch();
            }
            else if(!"B".equals(tp)){
                sql = "DROP TABLE `" + college + "`.`" + sem + branch + "_" + subCode + "-"+tp+"`";
                stmt.execute(sql);
            }     

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertSignData(String name, String email) {
        /*try {
            String sql = "INSERT INTO `CSPIT`.`CE_EV_SIGN` (`NAME`, `EMAIL`) VALUES (?,?)";
            prstmt = con.prepareStatement(sql);
            //prstmt.setInt(1, no);
            prstmt.setString(1, name);
            prstmt.setString(2, email);
            prstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        try {
            String sql = "SELECT `EMAIL` FROM `CSPIT`.`CE_EV_SIGN` WHERE `EMAIL` IN (?) AND `NAME` IN (?)";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, email);
            prstmt.setString(2, name);
            rs = prstmt.executeQuery();
            if (!rs.next()) {
                String sqlD = "INSERT INTO `CSPIT`.`CE_EV_SIGN` (`NAME`, `EMAIL`) VALUES (?,?)";
                prstmt = con.prepareStatement(sqlD);
                prstmt.setString(1, name);
                prstmt.setString(2, email);
                prstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertPedTData(String subName, String subCode, String sem) {
        try {
            String sql = "INSERT INTO `CSPIT`.`CE_PEDGY_TH` (`SUB_NAME`, `SUB_CODE`, `SEM`) VALUES (?,?,?)";
            prstmt = con.prepareStatement(sql);
            //prstmt.setInt(1, no);
            prstmt.setString(1, subName);
            prstmt.setString(2, subCode);
            prstmt.setString(3, sem);
            prstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertPedPData(String subName, String subCode, String sem) {
        try {
            String sql = "INSERT INTO `CSPIT`.`CE_PEDGY_PR` (`SUB_NAME`, `SUB_CODE`, `SEM`) VALUES (?,?,?)";
            prstmt = con.prepareStatement(sql);
            //prstmt.setInt(1, no);
            prstmt.setString(1, subName);
            prstmt.setString(2, subCode);
            prstmt.setString(3,sem);
            prstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeSignData(String name, String email) throws SQLException {
        try {
            String sql = "SELECT `EMAIL` FROM `CSPIT`.`CE_EVALUATOR` WHERE `EMAIL` IN (?) AND `NAME` IN (?)";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, email);
            prstmt.setString(2, name);
            rs = prstmt.executeQuery();
            if (!rs.next()) {
                String sqlD = "DELETE FROM `CSPIT`.`CE_EV_SIGN` WHERE `NAME`=? AND `EMAIL`=?";
                prstmt = con.prepareStatement(sqlD);
                prstmt.setString(1, name);
                prstmt.setString(2, email);
                prstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removePedTData(String subName, String subCode, String tp) throws SQLException {
        try {
            String sql = "SELECT `SUB_CODE` FROM `CSPIT`.`CE_EVALUATOR` WHERE `SUB_CODE` IN (?) AND `T/P` IN (?)";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, subCode);
            prstmt.setString(2, tp);
            rs = prstmt.executeQuery();
            if (!rs.next()) {
                String sqlD = "DELETE FROM `CSPIT`.`CE_PEDGY_TH` WHERE `SUB_NAME`=? AND `SUB_CODE`=?";
                prstmt = con.prepareStatement(sqlD);
                prstmt.setString(1, subName);
                prstmt.setString(2, subCode);
                //prstmt.setString(3, tp);
                prstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removePedPData(String subName, String subCode, String tp) throws SQLException {
        try {
            String sql = "SELECT `SUB_CODE` FROM `CSPIT`.`CE_EVALUATOR` WHERE `SUB_CODE` IN (?) AND `T/P` IN (?)";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, subCode);
            prstmt.setString(2, tp);
            rs = prstmt.executeQuery();
            /*int size = 0;
            if (rs != null) {
                rs.last();
                size = rs.getRow() + 1;
                System.out.println("PED_P"+size);
            }*/
            if (!rs.next()) {
                String sqlD = "DELETE FROM `CSPIT`.`CE_PEDGY_PR` WHERE `SUB_NAME`=? AND `SUB_CODE`=?";
                prstmt = con.prepareStatement(sqlD);
                prstmt.setString(1, subName);
                prstmt.setString(2, subCode);
                //prstmt.setString(3, tp);
                prstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeData(int no) {
        try {
            //System.out.println("Removed from CE.EVALUATOR");
            String sql = "DELETE FROM CSPIT.CE_EVALUATOR WHERE NO=?";
            prstmt = con.prepareStatement(sql);
            prstmt.setInt(1, no);
            prstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void handleEvaluatorAction(ActionEvent event) {
        evaluator_anchor_pane.setDisable(false);
        evaluator_anchor_pane.setVisible(true);
        student_anchor_pane.setDisable(true);
        student_anchor_pane.setVisible(false);
        report_anchor_pane.setDisable(true);
        report_anchor_pane.setVisible(false);
        choice = 1;
        //connectToDB();
        buildData();
    }

    @FXML
    private void handleStudentAction(ActionEvent event) {
        student_anchor_pane.setDisable(false);
        student_anchor_pane.setVisible(true);
        evaluator_anchor_pane.setDisable(true);
        evaluator_anchor_pane.setVisible(false);
        report_anchor_pane.setDisable(true);
        report_anchor_pane.setVisible(false);
        choice = 2;
    }

    @FXML
    private void handleReportAction(ActionEvent event) {
        report_anchor_pane.setDisable(false);
        report_anchor_pane.setVisible(true);
        student_anchor_pane.setDisable(true);
        student_anchor_pane.setVisible(false);
        evaluator_anchor_pane.setDisable(true);
        evaluator_anchor_pane.setVisible(false);
        choice = 3;
    }

    @FXML
    private void onCollegeSelection(ActionEvent event) {
        switch (choice) {
            /*case 2:
                if (stu_college_combo_box.getValue().toString().equals("DEPSTAR")) {
                    stu_branch_combo_box.setItems(FXCollections.observableArrayList(branch_depstar));
                } else {
                    stu_branch_combo_box.setItems(FXCollections.observableArrayList(branch_cspit));
                }
                break;
            case 3:
                if (rpt_college_combo_box.getValue().toString().equals("DEPSTAR")) {
                    rpt_branch_combo_box.setItems(FXCollections.observableArrayList(branch_depstar));
                } else {
                    rpt_branch_combo_box.setItems(FXCollections.observableArrayList(branch_cspit));
                }
                break;*/
            case 1:
                if (ev_college_combo_box.getValue().toString().equals("DEPSTAR")) {
                    ev_branch_combo_box.setItems(FXCollections.observableArrayList(branch_depstar));
                } else {
                    ev_branch_combo_box.setItems(FXCollections.observableArrayList(branch_cspit));
                }
                /*ev_branch_combo_box.setValue("");
                name_text_field.setPromptText("Name");
                name_text_field.setText("");
                email_text_field.setPromptText("Email");
                email_text_field.setText("");*/
                break;
            default:
                break;
        }
    }

    @FXML
    private void onBranchSelection(ActionEvent event) {
        
    }
    
    @FXML
    private void onSubjectSelection(ActionEvent event) {
        try {
            //For Report
            String tp=" ";
            String t[]=rpt_subject_combo_box.getValue().toString().trim().split(" | ",3);
            String sql = "SELECT `T/P` FROM `CSPIT`.`CE_EVALUATOR` WHERE `SUB_NAME`=? AND `SUB_CODE`=? AND `COLLEGE`=? AND `BRANCH`=? AND `SEM`=?";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, t[2]);
            prstmt.setString(2, t[0]);
            prstmt.setString(3, rpt_college_combo_box.getValue().toString());
            prstmt.setString(4, rpt_branch_combo_box.getValue().toString());
            prstmt.setString(5, rpt_sem_combo_box.getValue().toString());
            rs=prstmt.executeQuery();
            while(rs.next()){
                tp=rs.getString("T/P");
            }
            switch (tp) {
                case "B":
                    rbPractical.setDisable(false);
                    rbTheory.setDisable(false);
                    rbOverall.setDisable(false);
                    break;
                case "T":
                    rbPractical.setDisable(true);
                    rbTheory.setDisable(false);
                    rbOverall.setDisable(false);
                    break;
                case "P":
                    rbPractical.setDisable(false);
                    rbTheory.setDisable(true);
                    rbOverall.setDisable(false);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NullPointerException e){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
        }
    }

    @FXML
    private void onSemSelection(ActionEvent event) {
        try{
        switch (choice) {
            case 2:
                //connectToDB();
                buildData();
                break;
            case 3:
                //rpt_subject_combo_box.setDisable(true);
                //rbTheory.setDisable(true);
                //rbPractical.setDisable(true);
                
                break;
            case 1:
                break;
            default:
                break;
        }
        }catch(NullPointerException e){
            a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
        }
    }

    @FXML
    private void onButtonAdd(ActionEvent event) {
        try{
        table_no++;
        String tp = new String();
        if (theory_cb.isSelected() && practical_cb.isSelected()) {
            tp = "B";
        } else if (practical_cb.isSelected()) {
            tp = "P";
        } else if (theory_cb.isSelected()) {
            tp = "T";
        }
        Evaluator ev = new Evaluator(table_no, ev_sem_combo_box.getValue().toString(), name_text_field.getText(), email_text_field.getText(), ev_college_combo_box.getValue().toString(), ev_branch_combo_box.getValue().toString(), sub_code_text_field.getText(), sub_name_text_field.getText(), tp, date_picker.getValue());
        //connectToDB();
        if(validateEvaluator(ev_sem_combo_box.getValue().toString(), name_text_field.getText(), email_text_field.getText(), ev_college_combo_box.getValue().toString(), ev_branch_combo_box.getValue().toString(), sub_code_text_field.getText(), sub_name_text_field.getText(), tp, date_picker.getValue())){
            if (checkDuplicate(ev_sem_combo_box.getValue().toString(), name_text_field.getText(), email_text_field.getText(), ev_college_combo_box.getValue().toString(), ev_branch_combo_box.getValue().toString(), sub_code_text_field.getText(), sub_name_text_field.getText())) {
            insertData(ev_sem_combo_box.getValue().toString(), name_text_field.getText(), email_text_field.getText(), ev_college_combo_box.getValue().toString(), ev_branch_combo_box.getValue().toString(), sub_code_text_field.getText(), sub_name_text_field.getText(), tp, date_picker.getValue());
            //table.getItems().add(ev);
            buildData();
            }
        }
        else{
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
            }
        }catch(NullPointerException e){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
        }

    }

    public void removeSubject(String college, String branch, String sem, String subCode, String tp) {
        try {
            String sql;
            if ("B".equals(tp)) {
                //sql="ALTER TABLE `"+college+"`.`"+sem+branch+"_STUDENT` ADD COLUMN `"+subCode+"-"+tp+"` FLOAT(5) NOT NULL SET DEFAULT 0.0";
                stmt = con.createStatement();
                stmt.addBatch("ALTER TABLE `" + college + "`.`" + sem + branch + "_STUDENT` DROP COLUMN `" + subCode + "-T`");
                stmt.addBatch("ALTER TABLE `" + college + "`.`" + sem + branch + "_STUDENT` DROP COLUMN `" + subCode + "-P`");
                stmt.executeBatch();
            } else {
                sql = "ALTER TABLE `" + college + "`.`" + sem + branch + "_STUDENT` DROP COLUMN `" + subCode + "-" + tp + "`";
                stmt = con.createStatement();
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onButtonDelete(ActionEvent event) throws SQLException {
        try {
            Evaluator selectedItem = table.getSelectionModel().getSelectedItem();
            //connectToDB();
            removeData(selectedItem.no);
            table.getItems().remove(selectedItem);
            removeSubject(selectedItem.college, selectedItem.branch, selectedItem.sem, selectedItem.subCode, selectedItem.tp);
            removeSignData(selectedItem.name, selectedItem.email);
            removeSubTable(selectedItem.college, selectedItem.branch, selectedItem.sem, selectedItem.subCode,selectedItem.tp);
            switch (selectedItem.tp) {
                case "T":
                    removePedTData(selectedItem.subName, selectedItem.subCode, selectedItem.tp);
                    break;
                case "P":
                    removePedPData(selectedItem.subName, selectedItem.subCode, selectedItem.tp);
                    break;
                case "B":
                    removePedTData(selectedItem.subName, selectedItem.subCode, selectedItem.tp);
                    removePedPData(selectedItem.subName, selectedItem.subCode, selectedItem.tp);
                    break;
                default:
                    break;
            }
            removeStudentData(selectedItem.college, selectedItem.branch, selectedItem.sem);
            buildData();
            subject.clear();
            collegeL.clear();
            branchL.clear();
            semL.clear();
            reinitialize();
            table_no--;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void removeStudentData(String college,String branch,String sem){
        try {
            String sql="TRUNCATE `"+college+"`.`"+sem+branch+"_STUDENT`";
            stmt=con.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onTheorySelect(ActionEvent event) {
    }

    @FXML
    private void onPracticalSelect(ActionEvent event) {
    }

    @FXML
    private void onStu_add_btn(ActionEvent event) {
        /*FileChooser fp=new FileChooser();
        File fs=fp.showOpenDialog(new AdminDriver().copy_stage);*/
        tree_table.getItems().add(new Student(0, "", ""));
    }

    @FXML
    private void onDBSaveBtn(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        //connectToDB();
        String sqlTc = "TRUNCATE TABLE `" + stu_college_combo_box.getValue().toString() + "`.`" + stu_sem_combo_box.getValue().toString() + stu_branch_combo_box.getValue().toString() + "_STUDENT`";
        stmt = con.createStatement();
        stmt.executeUpdate(sqlTc);
        String sql = "INSERT INTO `" + stu_college_combo_box.getValue().toString() + "`.`" + stu_sem_combo_box.getValue().toString() + stu_branch_combo_box.getValue().toString() + "_STUDENT` (`PID`, `SID`, `SNAME`) VALUES (?,?,?)";
        prstmt = con.prepareStatement(sql);
        try {
            Student st;
            for (int i = 0; i < tree_table.getItems().size(); i++) {
                st = tree_table.getItems().get(i);
                prstmt.setInt(1, st.getNo());
                prstmt.setString(2, st.getId());
                prstmt.setString(3, st.getName());
                prstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        catch(NullPointerException e){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
        }
    }

    @FXML
    private void onDwnldEX(ActionEvent event) {
        try {
            //connectToDB();
            String sql = "SELECT `PID`, `SID`, `SNAME` FROM `" + stu_college_combo_box.getValue().toString() + "`.`" + stu_sem_combo_box.getValue().toString() + stu_branch_combo_box.getValue().toString() + "_STUDENT`";
            prstmt = con.prepareStatement(sql);
            rs = prstmt.executeQuery(sql);
            FileChooser fp = new FileChooser();
            FileChooser.ExtensionFilter extension=new FileChooser.ExtensionFilter("SHEET(*.xlsx)", "*.xlsx");
            fp.getExtensionFilters().add(extension);
            File fs = fp.showSaveDialog(AdminDriver.copyStage);
            Path path = Paths.get(fs.getAbsolutePath());//"C:\\Users\\VASPAR ASPI\\Desktop\\TRIAL1.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet ws = wb.createSheet(fs.getName());
            XSSFRow header = ws.createRow(0);
            header.createCell(0).setCellValue("No.");
            header.createCell(1).setCellValue("ID");
            header.createCell(2).setCellValue("NAME");
            ws.autoSizeColumn(0);
            ws.autoSizeColumn(1);
            ws.autoSizeColumn(2);
            ws.setColumnWidth(1, 3 * 1500);
            ws.setColumnWidth(2, 3 * 2560);
            int i = 1;
            while (rs.next()) {
                XSSFRow row = ws.createRow(i);
                row.createCell(0).setCellValue(rs.getInt("PID"));
                row.createCell(1).setCellValue(rs.getString("SID"));
                row.createCell(2).setCellValue(rs.getString("SNAME"));
                i++;
            }

            try (OutputStream fout = Files.newOutputStream(path)) {
                wb.write(fout);
                fout.flush();
                fout.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
        catch(NullPointerException e){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
        }
    }

    @FXML
    private void onImportEX(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
        try {
            //connectToDB();
            String sqlTc = "TRUNCATE TABLE `" + stu_college_combo_box.getValue().toString() + "`.`" + stu_sem_combo_box.getValue().toString() + stu_branch_combo_box.getValue().toString() + "_STUDENT`";
            stmt = con.createStatement();
            stmt.executeUpdate(sqlTc);
            FileChooser fp = new FileChooser();
            FileChooser.ExtensionFilter extension=new FileChooser.ExtensionFilter("SHEET(*.xlsx)", "*.xlsx");
            fp.getExtensionFilters().add(extension);
            File fs = fp.showOpenDialog(AdminDriver.copyStage);
            Path path = Paths.get(fs.getAbsolutePath());
            InputStream is = Files.newInputStream(path);
            //Scanner sc=new Scanner(new File("F:\\bin\\SGP_Project\\SGP_TRIAL.xlsx"));
            XSSFWorkbook wb = new XSSFWorkbook(is);
            XSSFSheet ws = wb.getSheetAt(0);
            XSSFRow row;
            String sql = "INSERT INTO `" + stu_college_combo_box.getValue().toString() + "`.`" + stu_sem_combo_box.getValue().toString() + stu_branch_combo_box.getValue().toString() + "_STUDENT` (`PID`, `SID`, `SNAME`) VALUES (?,?,?)";
            prstmt = con.prepareStatement(sql);
            for (int i = 1; i <= ws.getLastRowNum(); i++) {
                row = ws.getRow(i);
                //prstmt.setInt(1, no);
                prstmt.setDouble(1, row.getCell(0).getNumericCellValue());
                prstmt.setString(2, row.getCell(1).getStringCellValue());
                prstmt.setString(3, row.getCell(2).getStringCellValue());
                prstmt.executeUpdate();
                buildData();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        catch(NullPointerException e){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
        }
    }
    

    @FXML
    private void onExport(ActionEvent event) throws IOException, DocumentException {
        try{
        String t[]=rpt_subject_combo_box.getValue().toString().trim().split(" | ",3);
        if(rbTheory.isSelected()){
            colName.clear();
            colName.add("PID");
            colName.add("SID");
            colName.add("SNAME");
            getColumnName(rpt_college_combo_box.getValue().toString(),rpt_sem_combo_box.getValue().toString()+rpt_branch_combo_box.getValue().toString()+"_"+t[0]+"-T");
            colName.remove("VIVA");
            colName.add(t[0]+"-T");
            savePDF(colName.size());
        }
        else if(rbPractical.isSelected()){
            colName.clear();
            colName.add("PID");
            colName.add("SID");
            colName.add("SNAME");
            getColumnName(rpt_college_combo_box.getValue().toString(),rpt_sem_combo_box.getValue().toString()+rpt_branch_combo_box.getValue().toString()+"_"+t[0]+"-P");
            //colName.remove("VIVA");
            colName.add(t[0]+"-P");
            savePDF(colName.size());
        }
        else if(rbOverall.isSelected()){
            colName.clear();
            getColumnName(rpt_college_combo_box.getValue().toString(),rpt_sem_combo_box.getValue().toString()+rpt_branch_combo_box.getValue().toString()+"_STUDENT");
            savePDF(colName.size());
        }
        }catch(NullPointerException e){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
        }
    }

    @FXML
    private void onRevaluate(ActionEvent event) {
        try {
            String t[]=rpt_subject_combo_box.getValue().toString().trim().split(" | ",3);
            String sql = "UPDATE `CSPIT`.`CE_EVALUATOR` SET `LOCKED`='U' WHERE `COLLEGE`=? AND `BRANCH`=? AND `SEM`=? AND `SUB_CODE`=? AND `SUB_NAME`=? ";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, rpt_college_combo_box.getValue().toString());
            prstmt.setString(2, rpt_branch_combo_box.getValue().toString());
            prstmt.setString(3, rpt_sem_combo_box.getValue().toString());
            prstmt.setString(4, t[0]);
            prstmt.setString(5, t[2]);
            prstmt.executeUpdate();
        } catch (SQLException ex) {
            a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Report not generated yet");
                a.setTitle("Error");
                a.show();
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NullPointerException e){
            a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
        }
    }
    
    @FXML
    private void onTheory(ActionEvent event){
        try{
        rpt_table1.setDisable(true);
        rpt_table1.setVisible(false);
        rpt_table.setDisable(false);
        rpt_table.setVisible(true);
        String t[]=rpt_subject_combo_box.getValue().toString().trim().split(" | ",3);
        if("L".equals(getLockedStatus(t[0], t[2]))){
             loadTReport();
        }   
        }catch(NullPointerException e){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
        }
    }
    
    @FXML
    private void onPractical(ActionEvent event){
        try{
        rpt_table1.setDisable(true);
        rpt_table1.setVisible(false);
        rpt_table.setDisable(false);
        rpt_table.setVisible(true);
        String t[]=rpt_subject_combo_box.getValue().toString().trim().split(" | ",3);
        if("L".equals(getLockedStatus(t[0], t[2]))){
              loadPReport();
        }   
        }catch(NullPointerException e){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
        }
    }
    
    @FXML
    private void onOverall(ActionEvent event){
        try{
        rpt_table.setDisable(true);
        rpt_table.setVisible(false);
        rpt_table1.setDisable(false);
        rpt_table1.setVisible(true);
        String t[]=rpt_subject_combo_box.getValue().toString().trim().split(" | ",3);
        if("L".equals(getLockedStatus(t[0], t[2]))){
              buildDynamicTable();
        }   
        }catch(NullPointerException e){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
        }
    }
    
    public String getLockedStatus(String subCode, String subName) {
        try {
            String lockStatus;
            String sql = "SELECT `LOCKED` FROM `CSPIT`.`CE_EVALUATOR` WHERE `COLLEGE`=? AND `BRANCH`=? AND `SEM`=? AND `SUB_CODE`=? AND `SUB_NAME`=? ";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, rpt_college_combo_box.getValue().toString());
            prstmt.setString(2, rpt_branch_combo_box.getValue().toString());
            prstmt.setString(3, rpt_sem_combo_box.getValue().toString());
            prstmt.setString(4, subCode);
            prstmt.setString(5, subName);
            rs = prstmt.executeQuery();
            while (rs.next()) {
                lockStatus = rs.getString("LOCKED");
                return lockStatus;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    private ObservableList<Report> dataRpt;
    String sqlRpt;
    public void loadTReport(){
        try {
            column_rpt_viva.setVisible(false);
            String t[]=rpt_subject_combo_box.getValue().toString().trim().split(" | ",3);
            System.out.println(Arrays.toString(t));
            dataRpt = FXCollections.observableArrayList();
            sqlRpt="SELECT S.`SNAME`, S.`SID`, S.`"+t[0]+"-T`, C.* FROM `"+rpt_college_combo_box.getValue().toString()+"`.`"+rpt_sem_combo_box.getValue().toString()+rpt_branch_combo_box.getValue().toString()+"_STUDENT` S LEFT JOIN `"+rpt_college_combo_box.getValue().toString()+"`.`"+rpt_sem_combo_box.getValue().toString()+rpt_branch_combo_box.getValue().toString()+"_"+t[0]+"-T` C USING (`PID`)";         
            stmt=con.createStatement();
            rs=stmt.executeQuery(sqlRpt);
            while(rs.next()){
                dataRpt.add(new Report(rs.getString("SID"),rs.getString("SNAME"),rs.getFloat(t[0]+"-T"),rs.getFloat("INT1"),rs.getFloat("INT2"),rs.getFloat("ATD"),rs.getFloat("ASG"),rs.getFloat("QZ"),rs.getFloat("PROJ"),rs.getFloat("PRES")));
                rpt_table.setItems(dataRpt);
            }
        } catch (SQLException ex) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Report not generated yet");
            a.setTitle("Error");
            a.show();
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadPReport(){
        try {
            column_rpt_viva.setVisible(true);
            String t[]=rpt_subject_combo_box.getValue().toString().trim().split(" | ",3);
            dataRpt = FXCollections.observableArrayList();
            sqlRpt="SELECT S.`SNAME`, S.`SID`, S.`"+t[0]+"-P`, C.* FROM `"+rpt_college_combo_box.getValue().toString()+"`.`"+rpt_sem_combo_box.getValue().toString()+rpt_branch_combo_box.getValue().toString()+"_STUDENT` S LEFT JOIN `"+rpt_college_combo_box.getValue().toString()+"`.`"+rpt_sem_combo_box.getValue().toString()+rpt_branch_combo_box.getValue().toString()+"_"+t[0]+"-P` C USING (`PID`)";         
            prstmt=con.prepareStatement(sqlRpt);
            rs=prstmt.executeQuery();
            while(rs.next()){
                dataRpt.add(new Report(rs.getString("SID"),rs.getString("SNAME"),rs.getFloat(t[0]+"-P"),rs.getFloat("INT1"),rs.getFloat("INT2"),rs.getFloat("ATD"),rs.getFloat("ASG"),rs.getFloat("QZ"),rs.getFloat("PROJ"),rs.getFloat("PRES"),rs.getFloat("VIVA")));
                rpt_table.setItems(dataRpt);
            }
        } catch (SQLException ex) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Report not generated yet");
            a.setTitle("Error");
            a.show();
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void onChangePassword(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = (Parent) FXMLLoader.load(getClass().getResource("ChangePassword.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:C:\\Users\\VASPAR ASPI\\Documents\\NetBeansProjects\\Admin\\dist\\Admin.png"));
            stage.setTitle("Change Password");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    List<String> colName=new ArrayList<>();
    public void getColumnName(String schema,String table){
        try {
            int count=0;
            String sql="SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE `TABLE_SCHEMA`=? AND `TABLE_NAME`=?";
            prstmt=con.prepareStatement(sql);
            prstmt.setString(1, schema);
            prstmt.setString(2, table);
            rs=prstmt.executeQuery();
            while(rs.next()){
                if(!colName.contains(rs.getString("COLUMN_NAME")))
                    colName.add(rs.getString("COLUMN_NAME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ObservableList<ObservableList> data;
    public void buildDynamicTable(){
        data = FXCollections.observableArrayList();
        try {
            //SQL FOR SELECTING ALL OF CUSTOMER
            sqlRpt = "SELECT * FROM `"+rpt_college_combo_box.getValue().toString()+"`.`"+rpt_sem_combo_box.getValue().toString()+rpt_branch_combo_box.getValue().toString()+"_STUDENT`";
            //ResultSet
            rs = con.createStatement().executeQuery(sqlRpt);

            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             *********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                rpt_table1.getColumns().addAll(col);
                switch (i) {
                    case 0:
                        col.setVisible(false);
                        break;
                    case 1:
                        col.setText("Id");
                        break;
                    case 2:
                        col.setText("Name");
                        break;
                    default:
                        break;
                }
                //System.out.println("Column [" + i + "] ");
            }

            /**
             * ******************************
             * Data added to ObservableList *
             *******************************
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                //System.out.println("Row [1] added " + row);
                data.add(row);
            }

            //FINALLY ADDED TO TableView
            rpt_table1.setItems(data);
        } catch (SQLException e) {
            System.out.println("Error on Building Data");
        }
    }
    
    public void savePDF(int size) throws IOException, DocumentException{
        try {
            //int row=1;
            FileChooser fp = new FileChooser();
            FileChooser.ExtensionFilter extension=new FileChooser.ExtensionFilter("PDF(*.pdf)", "*.pdf");
            fp.getExtensionFilters().add(extension);
            File fs = fp.showSaveDialog(AdminDriver.copyStage);
            Path path = Paths.get(fs.getAbsolutePath());//"C:\\Users\\VASPAR ASPI\\Desktop\\TRIAL1.xlsx");
            OutputStream os = Files.newOutputStream(path);
            Document doc=new Document();
            PdfWriter.getInstance(doc, os);
            doc.open();
            //int size=colName.size();
            PdfPTable tbl=new PdfPTable(size-1);
             /*float[] widths=new float[size-1];
             widths[0]=500f;
             widths[1]=1500f;
             for(int i=2;i<size-1;i++){
                 widths[i]=500f;
             }
            tbl.setTotalWidth(widths);*/
            PdfPCell cell;
            //Add column header
            cell=new PdfPCell(new Phrase("Id"));
            cell.setFixedHeight(75f);
            tbl.addCell(cell);
            cell=new PdfPCell(new Phrase("Name"));
            cell.setFixedHeight(75f);
            tbl.addCell(cell);
            for(int i=3;i<size;i++){
                cell=new PdfPCell(new Phrase(colName.get(i)));
                cell.setFixedHeight(75f);
                tbl.addCell(cell);
            }
            tbl.setHeaderRows(1);
            //Add table data
            prstmt=con.prepareStatement(sqlRpt);
            rs=prstmt.executeQuery();
            while(rs.next()){
                tbl.addCell(rs.getString("SID"));
                tbl.addCell(rs.getString("SNAME"));
                for(int i=3;i<size;i++){
                    tbl.addCell(String.valueOf(rs.getFloat(colName.get(i))));
                }
            }
            doc.add(tbl);
            doc.close();
        } catch (DocumentException | IOException | SQLException ex) {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Report not generated yet");
                a.setTitle("Error");
                a.show();
                //Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public boolean validateEvaluator(String sem, String name, String email, String college, String branch, String subCode, String subName, String tp, LocalDate dt){
        if(!"".equals(sem) && !"".equals(name) && !"".equals(email) && !"".equals(college) && !"".equals(branch) && !"".equals(subCode) && !"".equals(subName) && !"".equals(tp) && !"".equals(Date.valueOf(dt).toString())){
            return true;
        }
        else{
            return false;
        }
    }
}
