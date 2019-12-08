/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.PrefixSelectionComboBox;

/**
 * FXML Controller class
 *
 * @author VASPAR
 */
public class EvaluatorController implements Initializable {

    @FXML
    private SplitPane split_pane;
    @FXML
    private AnchorPane left_of_split;
    @FXML
    private VBox left_of_split_vbox;
    @FXML
    private Button pedagogy;
    @FXML
    private Button evaluate;
    @FXML
    private Button report;
    @FXML
    private AnchorPane right_of_split;
    @FXML
    private AnchorPane pedagogy_anchor_pane;
    @FXML
    private PrefixSelectionComboBox<String> pd_college_combo_box;
    @FXML
    private PrefixSelectionComboBox<String> pd_branch_combo_box;
    @FXML
    private Button onDone;
    @FXML
    private PrefixSelectionComboBox<String> pd_sub_combo_box;
    @FXML
    private Label pd_tlabelInt1;
    @FXML
    private TextField pd_tInt1M;
    @FXML
    private Label pd_tlabelInt2;
    @FXML
    private TextField pd_tInt2M;
    @FXML
    private Label pd_tlabelAtd;
    @FXML
    private TextField pd_tAtdM;
    @FXML
    private Label labelTOutOf;
    @FXML
    private Label labelPOutOf;
    @FXML
    private Label labelDeadline;
    @FXML
    private PrefixSelectionComboBox<String> pd_sem_combo_box;
    @FXML
    private TextField pd_tInt1Pr;
    @FXML
    private TextField pd_tInt2Pr;
    @FXML
    private TextField pd_tAtdPr;
    @FXML
    private Label pd_tlabelAsg;
    @FXML
    private TextField pd_tAsgM;
    @FXML
    private Label pd_tlabelQz;
    @FXML
    private TextField pd_tQzM;
    @FXML
    private Label pd_tlabelProj;
    @FXML
    private TextField pd_tProjM;
    @FXML
    private TextField pd_tAsgPr;
    @FXML
    private TextField pd_tQzPr;
    @FXML
    private TextField pd_tProjPr;
    @FXML
    private Label pd_tlabelPres;
    @FXML
    private TextField pd_tPresM;
    @FXML
    private TextField pd_tPresPr;
    @FXML
    private Label pd_plabelInt1;
    @FXML
    private TextField pd_pInt1M;
    @FXML
    private Label pd_plabelInt2;
    @FXML
    private TextField pd_pInt2M;
    @FXML
    private Label pd_plabelAtd;
    @FXML
    private TextField pd_pAtdM;
    @FXML
    private TextField pd_pInt1Pr;
    @FXML
    private TextField pd_pInt2Pr;
    @FXML
    private TextField pd_pAtdPr;
    @FXML
    private Label pd_plabelAsg;
    @FXML
    private TextField pd_pAsgM;
    @FXML
    private Label pd_plabelQz;
    @FXML
    private TextField pd_pQzM;
    @FXML
    private Label pd_plabelProj;
    @FXML
    private TextField pd_pProjM;
    @FXML
    private TextField pd_pAsgPr;
    @FXML
    private TextField pd_pQzPr;
    @FXML
    private TextField pd_pProjPr;
    @FXML
    private Label pd_plabelPres;
    @FXML
    private TextField pd_pPresM;
    @FXML
    private Label pd_plabelViva;
    @FXML
    private TextField pd_pVivaM;
    @FXML
    private TextField pd_pPresPr;
    @FXML
    private TextField pd_pVivaPr;
    @FXML
    private Label valueDeadline;
    @FXML
    private Label valueTOutOf;
    @FXML
    private Label valuePOutOf;
    @FXML
    private Line pdVLine;
    @FXML
    private Label pd_labelTheory;
    @FXML
    private Label pd_labelPractical;
    @FXML
    private Line pdHLine;
    @FXML
    private AnchorPane evaluate_anchor_pane;
    @FXML
    private PrefixSelectionComboBox ev_college_combo_box;
    @FXML
    private PrefixSelectionComboBox ev_branch_combo_box;
    @FXML
    private PrefixSelectionComboBox ev_sub_combo_box;
    @FXML
    private RadioButton rbTheory;
    @FXML
    private RadioButton rbPractical;
    @FXML
    private Label ev_tlabelInt1;
    @FXML
    private TextField ev_tInt1M;
    @FXML
    private Label ev_tlabelInt2;
    @FXML
    private TextField ev_tInt2M;
    @FXML
    private Label ev_tlabelAtd;
    @FXML
    private TextField ev_tAtdM;
    @FXML
    private PrefixSelectionComboBox ev_sem_combo_box;
    @FXML
    private Label ev_tlabelAsg;
    @FXML
    private TextField ev_tAsgM;
    @FXML
    private Label ev_tlabelQz;
    @FXML
    private TextField ev_tQzM;
    @FXML
    private Label ev_tlabelProj;
    @FXML
    private TextField ev_tProjM;
    @FXML
    private Label ev_tlabelPres;
    @FXML
    private TextField ev_tPresM;
    @FXML
    private Label ev_plabelInt1;
    @FXML
    private TextField ev_pInt1M;
    @FXML
    private Label ev_plabelInt2;
    @FXML
    private TextField ev_pInt2M;
    @FXML
    private Label ev_plabelAtd;
    @FXML
    private TextField ev_pAtdM;
    @FXML
    private Label ev_plabelAsg;
    @FXML
    private TextField ev_pAsgM;
    @FXML
    private Label ev_plabelQz;
    @FXML
    private TextField ev_pQzM;
    @FXML
    private Label ev_plabelProj;
    @FXML
    private TextField ev_pProjM;
    @FXML
    private Label ev_plabelPres;
    @FXML
    private TextField ev_pPresM;
    @FXML
    private Label ev_plabelViva;
    @FXML
    private TextField ev_pVivaM;
    @FXML
    private Line evVLine;
    @FXML
    private Label ev_labelTheory;
    @FXML
    private Label ev_labelPractical;
    @FXML
    private Line evHLine;
    @FXML
    private Button onSubmit;
    @FXML
    private Button onFinalize;
    @FXML
    private Button nxt_btn;
    @FXML
    private MaterialDesignIconView rightArrow;
    @FXML
    private Button pre_btn;
    @FXML
    private MaterialDesignIconView leftArrow;
    @FXML
    private Label labelName;
    @FXML
    private Label valueOfName;
    @FXML
    private Label labelId;
    @FXML
    private Label valueOfId;
    @FXML
    private TextField searchBox;
    @FXML
    private Button searchButton;
    @FXML
    private FontAwesomeIconView searchImage;
    @FXML
    private AnchorPane report_anchor_pane;
    @FXML
    private PrefixSelectionComboBox rpt_college_combo_box;
    @FXML
    private PrefixSelectionComboBox rpt_branch_combo_box;
    @FXML
    private PrefixSelectionComboBox rpt_sub_combo_box;
    @FXML
    private TableView<Student> rptTable;
    @FXML
    private TableColumn<Student, String> column_id;
    @FXML
    private TableColumn<Student, String> column_name;
    @FXML
    private TableColumn<Student, Integer> column_marks;
    @FXML
    private PrefixSelectionComboBox rpt_sem_combo_box;
    @FXML
    private Button DwnldPDF;

    int choice = 0, id = 1;
    int db_choice = 0;
    float sum = 0, sum1 = 0;
    String tp;
    boolean nxt = true, pre = true;

    float int1TM, int2TM, atdTM, asgTM, qzTM, projTM, presTM;
    float int1PM, int2PM, atdPM, asgPM, qzPM, projPM, presPM, vivaPM;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        EvaluatorController.email = email;
    }

    private static String email;

    List<String> college = new ArrayList<>();
    List<String> branch = new ArrayList<>();
    List<String> subject = new ArrayList<>();
    List<String> sem = new ArrayList<>();
    //ObservableList<String> sub_code;

    Connection con = null;
    Statement stmt = null;
    PreparedStatement prstmt = null;
    ResultSet rs = null;

    Alert a = new Alert(Alert.AlertType.NONE);

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connectToDB();
        column_marks.setCellValueFactory(new PropertyValueFactory<>("marks"));
        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            String sql = "SELECT `COLLEGE`, `BRANCH`, `SEM`, `SUB_NAME`, `SUB_CODE`, `DEADLINE`,`T/P` FROM `CSPIT`.`CE_EVALUATOR` WHERE `EMAIL`=?";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, email);
            rs = prstmt.executeQuery();

            while (rs.next()) {
                if (!college.contains(rs.getString("COLLEGE"))) {
                    college.add(rs.getString("COLLEGE"));
                }
                if (!branch.contains(rs.getString("BRANCH"))) {
                    branch.add(rs.getString("BRANCH"));
                }
                if (!subject.contains(rs.getString("SUB_CODE") + " | " + rs.getString("SUB_NAME"))) {
                    subject.add(rs.getString("SUB_CODE") + " | " + rs.getString("SUB_NAME"));
                }
                if (!sem.contains(rs.getString("SEM"))) {
                    sem.add(rs.getString("SEM"));
                }

            }
            column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            column_marks.setCellValueFactory(new PropertyValueFactory<>("marks"));
            pd_college_combo_box.setItems(FXCollections.observableArrayList(college));
            pd_branch_combo_box.setItems(FXCollections.observableArrayList(branch));
            pd_sem_combo_box.setItems(FXCollections.observableArrayList(sem));
            pd_sub_combo_box.setItems(FXCollections.observableArrayList(subject));
            ev_college_combo_box.setItems(FXCollections.observableArrayList(college));
            ev_branch_combo_box.setItems(FXCollections.observableArrayList(branch));
            ev_sem_combo_box.setItems(FXCollections.observableArrayList(sem));
            ev_sub_combo_box.setItems(FXCollections.observableArrayList(subject));
            rpt_college_combo_box.setItems(FXCollections.observableArrayList(college));
            rpt_branch_combo_box.setItems(FXCollections.observableArrayList(branch));
            rpt_sem_combo_box.setItems(FXCollections.observableArrayList(sem));
            rpt_sub_combo_box.setItems(FXCollections.observableArrayList(subject));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public char knowTP(String subCode, String subName) throws SQLException {
        try {
            //String[] t = pd_sub_combo_box.getValue().toString().trim().split(" | ", 3);
            String sql = "SELECT `T/P` FROM `CSPIT`.`CE_EVALUATOR` WHERE `SUB_NAME`=? AND `SUB_CODE`=? AND `COLLEGE`=? AND `BRANCH`=? AND `SEM`=? AND `EMAIL`=?";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, subName);
            prstmt.setString(2, subCode);
            switch (choice) {
                case 1:
                    prstmt.setString(3, pd_college_combo_box.getValue());
                    prstmt.setString(4, pd_branch_combo_box.getValue());
                    prstmt.setString(5, pd_sem_combo_box.getValue());
                    break;
                case 2:
                    prstmt.setString(3, ev_college_combo_box.getValue().toString());
                    prstmt.setString(4, ev_branch_combo_box.getValue().toString());
                    prstmt.setString(5, ev_sem_combo_box.getValue().toString());
                    break;
                case 3:
                    prstmt.setString(3, rpt_college_combo_box.getValue().toString());
                    prstmt.setString(4, rpt_branch_combo_box.getValue().toString());
                    prstmt.setString(5, rpt_sem_combo_box.getValue().toString());
                    break;
                default:
                    break;
            }
            prstmt.setString(6, email);
            rs = prstmt.executeQuery();
            while (rs.next()) {
                tp = rs.getString("T/P");
            }
            switch (tp) {
                case "T":
                    return 't';
                case "P":
                    return 'p';
                case "B":
                    return 'b';
                default:
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 'f';
    }

    public void connectToDB() {
        /*DB*/
        assert rptTable != null : "fx:id=\"tableview\" was not injected: check your FXML file 'Evaluator.fxml'.";

        DBConnection objDbClass = new DBConnection();
        try {
            con = objDbClass.getConnection();
            System.out.println("Connection Done");
        } catch (ClassNotFoundException | SQLException ce) {
            System.out.println("Error");
            System.out.println(ce.getMessage());
        }
    }
    private PedagogyTh dataPedTh;
    private PedagogyPr dataPedPr;

    public void buildData(String subCode, String subName) throws SQLException {
        db_choice = 1;

        //try {
        String SQL[] = new String[2];
        //String SQLP=new String();
        if (choice == 1) {
            SQL[0] = "SELECT * FROM `CSPIT`.`CE_PEDGY_TH` WHERE `SUB_NAME`=? AND `SUB_CODE`=? AND `SEM`=?";
            SQL[1] = "SELECT * FROM `CSPIT`.`CE_PEDGY_PR` WHERE `SUB_NAME`=? AND `SUB_CODE`=? AND `SEM`=?";
            int i = 0;
            while (i < 2) {
                prstmt = con.prepareStatement(SQL[i]);
                prstmt.setString(1, subName);
                prstmt.setString(2, subCode);
                prstmt.setString(3, pd_sem_combo_box.getValue());
                rs = prstmt.executeQuery();
                while (rs.next()) {
                    if (i == 0) {
                        pd_tInt1M.setText(String.valueOf(rs.getInt("INTERNAL-1_M")));
                        pd_tInt1Pr.setText(String.valueOf(rs.getFloat("INTERNAL-1_P")));
                        pd_tInt2M.setText(String.valueOf(rs.getInt("INTERNAL-2_M")));
                        pd_tInt2Pr.setText(String.valueOf(rs.getFloat("INTERNAL-2_P")));
                        pd_tAtdM.setText(String.valueOf(rs.getInt("ATD_M")));
                        pd_tAtdPr.setText(String.valueOf(rs.getFloat("ATD_P")));
                        pd_tAsgM.setText(String.valueOf(rs.getInt("ASG_M")));
                        pd_tAsgPr.setText(String.valueOf(rs.getFloat("ASG_P")));
                        pd_tQzM.setText(String.valueOf(rs.getInt("QZ_M")));
                        pd_tQzPr.setText(String.valueOf(rs.getFloat("QZ_P")));
                        pd_tProjM.setText(String.valueOf(rs.getInt("PROJ_M")));
                        pd_tProjPr.setText(String.valueOf(rs.getFloat("PROJ_P")));
                        pd_tPresM.setText(String.valueOf(rs.getInt("PRES_M")));
                        pd_tPresPr.setText(String.valueOf(rs.getFloat("PRES_P")));

                        sum1 = (rs.getInt("INTERNAL-1_M") * rs.getFloat("INTERNAL-1_P")) + (rs.getInt("INTERNAL-2_M") * rs.getFloat("INTERNAL-2_P"))
                                + (rs.getInt("ATD_M") * rs.getFloat("ATD_P")) + (rs.getInt("ASG_M") * rs.getFloat("ASG_P")) + (rs.getInt("QZ_M") * rs.getFloat("QZ_P"))
                                + (rs.getInt("PROJ_M") * rs.getFloat("PROJ_P")) + (rs.getInt("PRES_M") * rs.getFloat("PRES_P"));
                        valueTOutOf.setText(String.valueOf(sum1));

                    } else if (i == 1) {
                        pd_pInt1M.setText(String.valueOf(rs.getInt("INTERNAL-1_M")));
                        pd_pInt1Pr.setText(String.valueOf(rs.getFloat("INTERNAL-1_P")));
                        pd_pInt2M.setText(String.valueOf(rs.getInt("INTERNAL-2_M")));
                        pd_pInt2Pr.setText(String.valueOf(rs.getFloat("INTERNAL-2_P")));
                        pd_pAtdM.setText(String.valueOf(rs.getInt("ATD_M")));
                        pd_pAtdPr.setText(String.valueOf(rs.getFloat("ATD_P")));
                        pd_pAsgM.setText(String.valueOf(rs.getInt("ASG_M")));
                        pd_pAsgPr.setText(String.valueOf(rs.getFloat("ASG_P")));
                        pd_pQzM.setText(String.valueOf(rs.getInt("QZ_M")));
                        pd_pQzPr.setText(String.valueOf(rs.getFloat("QZ_P")));
                        pd_pProjM.setText(String.valueOf(rs.getInt("PROJ_M")));
                        pd_pProjPr.setText(String.valueOf(rs.getFloat("PROJ_P")));
                        pd_pPresM.setText(String.valueOf(rs.getInt("PRES_M")));
                        pd_pPresPr.setText(String.valueOf(rs.getFloat("PRES_P")));
                        pd_pVivaM.setText(String.valueOf(rs.getInt("VIVA_M")));
                        pd_pVivaPr.setText(String.valueOf(rs.getFloat("VIVA_P")));

                        sum = (rs.getInt("INTERNAL-1_M") * rs.getFloat("INTERNAL-1_P")) + (rs.getInt("INTERNAL-2_M") * rs.getFloat("INTERNAL-2_P"))
                                + (rs.getInt("ATD_M") * rs.getFloat("ATD_P")) + (rs.getInt("ASG_M") * rs.getFloat("ASG_P")) + (rs.getInt("QZ_M") * rs.getFloat("QZ_P"))
                                + (rs.getInt("PROJ_M") * rs.getFloat("PROJ_P")) + (rs.getInt("PRES_M") * rs.getFloat("PRES_P")) + (rs.getInt("VIVA_M") * rs.getFloat("VIVA_P"));
                        valuePOutOf.setText(String.valueOf(sum));
                    }
                }
                i++;
            }
            String sql = "SELECT `DEADLINE` FROM `CSPIT`.`CE_EVALUATOR` WHERE `COLLEGE`=? AND `BRANCH`=? AND `SEM`=? AND `SUB_NAME`=? AND `SUB_CODE`=?";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, pd_college_combo_box.getValue());
            prstmt.setString(2, pd_branch_combo_box.getValue());
            prstmt.setString(3, pd_sem_combo_box.getValue());
            prstmt.setString(4, subName);
            prstmt.setString(5, subCode);
            rs = prstmt.executeQuery();
            while (rs.next()) {
                valueDeadline.setText(String.valueOf(rs.getDate("DEADLINE")));
            }

        }

    }

    public void getMarks(String subCode, String subName) {
        try {
            switch (knowTP(subCode, subName)) {
                case 'b':
                    getTMarks(subCode, subName);
                    getPMarks(subCode, subName);
                    break;
                case 't':
                    getTMarks(subCode, subName);
                    break;
                case 'p':
                    getPMarks(subCode, subName);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getTMarks(String subCode, String subName) {
        try {
            int pid = getId(valueOfId.getText(), valueOfName.getText());
            String sql = "SELECT * FROM `" + ev_college_combo_box.getValue().toString() + "`.`" + ev_sem_combo_box.getValue().toString() + ev_branch_combo_box.getValue().toString() + "_" + subCode + "-T` WHERE `PID`=" + pid;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ev_tInt1M.setText(String.valueOf(rs.getFloat("INT1")));
                ev_tInt2M.setText(String.valueOf(rs.getFloat("INT2")));
                ev_tAtdM.setText(String.valueOf(rs.getFloat("ATD")));
                ev_tAsgM.setText(String.valueOf(rs.getFloat("ASG")));
                ev_tQzM.setText(String.valueOf(rs.getFloat("QZ")));
                ev_tProjM.setText(String.valueOf(rs.getFloat("PROJ")));
                ev_tPresM.setText(String.valueOf(rs.getFloat("PRES")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getPMarks(String subCode, String subName) {
        try {
            String sql = "SELECT * FROM `" + ev_college_combo_box.getValue().toString() + "`.`" + ev_sem_combo_box.getValue().toString() + ev_branch_combo_box.getValue().toString() + "_" + subCode + "-P` WHERE `PID`=" + getId(valueOfId.getText(), valueOfName.getText());
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ev_pInt1M.setText(String.valueOf(rs.getFloat("INT1")));
                ev_pInt2M.setText(String.valueOf(rs.getFloat("INT2")));
                ev_pAtdM.setText(String.valueOf(rs.getFloat("ATD")));
                ev_pAsgM.setText(String.valueOf(rs.getFloat("ASG")));
                ev_pQzM.setText(String.valueOf(rs.getFloat("QZ")));
                ev_pProjM.setText(String.valueOf(rs.getFloat("PROJ")));
                ev_pPresM.setText(String.valueOf(rs.getFloat("PRES")));
                ev_pVivaM.setText(String.valueOf(rs.getFloat("VIVA")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getId(String id, String name) {
        int pid = 0;
        try {
            String sql = "SELECT `PID` FROM `" + ev_college_combo_box.getValue().toString() + "`.`" + ev_sem_combo_box.getValue().toString() + ev_branch_combo_box.getValue().toString() + "_STUDENT` WHERE `SID`='" + valueOfId.getText() + "' AND `SNAME`='" + valueOfName.getText() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Inside getId");
                pid = rs.getInt("PID");
            }
            System.out.println(pid);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pid;
    }

    @FXML
    private void handlePedagogyAction(ActionEvent event) throws SQLException {
        pedagogy_anchor_pane.setDisable(false);
        pedagogy_anchor_pane.setVisible(true);
        evaluate_anchor_pane.setDisable(true);
        evaluate_anchor_pane.setVisible(false);
        report_anchor_pane.setDisable(true);
        report_anchor_pane.setVisible(false);
        choice = 1;
    }

    @FXML
    private void handleEvaluateAction(ActionEvent event) {
        evaluate_anchor_pane.setDisable(false);
        evaluate_anchor_pane.setVisible(true);
        pedagogy_anchor_pane.setDisable(true);
        pedagogy_anchor_pane.setVisible(false);
        report_anchor_pane.setDisable(true);
        report_anchor_pane.setVisible(false);
        choice = 2;
    }

    @FXML
    private void handleReportAction(ActionEvent event) {
        report_anchor_pane.setDisable(false);
        report_anchor_pane.setVisible(true);
        pedagogy_anchor_pane.setDisable(true);
        pedagogy_anchor_pane.setVisible(false);
        evaluate_anchor_pane.setDisable(true);
        evaluate_anchor_pane.setVisible(false);
        choice = 3;
    }

    @FXML
    private void onCollegeSelection(ActionEvent event) {
    }

    @FXML
    private void onBranchSelection(ActionEvent event) {
    }

    @FXML
    private void onTextEnter(ActionEvent event) {
        getPedSum();
    }

    public void getPedSum() {
        if (!pd_plabelAsg.isDisable()) {
            sum = (Float.parseFloat(pd_pInt1M.getText()) * Float.parseFloat(pd_pInt1Pr.getText()))
                    + (Float.parseFloat(pd_pInt2M.getText()) * Float.parseFloat(pd_pInt2Pr.getText()))
                    + (Float.parseFloat(pd_pAtdM.getText()) * Float.parseFloat(pd_pAtdPr.getText()))
                    + (Float.parseFloat(pd_pAsgM.getText()) * Float.parseFloat(pd_pAsgPr.getText()))
                    + (Float.parseFloat(pd_pQzM.getText()) * Float.parseFloat(pd_pQzPr.getText()))
                    + (Float.parseFloat(pd_pProjM.getText()) * Float.parseFloat(pd_pProjPr.getText()))
                    + (Float.parseFloat(pd_pPresM.getText()) * Float.parseFloat(pd_pPresPr.getText()))
                    + (Float.parseFloat(pd_pVivaM.getText()) * Float.parseFloat(pd_pVivaPr.getText()));

            valuePOutOf.setText(String.valueOf(sum));
        }

        if (!pd_tAsgM.isDisable()) {
            sum1 = (Float.parseFloat(pd_tInt1M.getText()) * Float.parseFloat(pd_tInt1Pr.getText()))
                    + (Float.parseFloat(pd_tInt2M.getText()) * Float.parseFloat(pd_tInt2Pr.getText()))
                    + (Float.parseFloat(pd_tAtdM.getText()) * Float.parseFloat(pd_tAtdPr.getText()))
                    + (Float.parseFloat(pd_tAsgM.getText()) * Float.parseFloat(pd_tAsgPr.getText()))
                    + (Float.parseFloat(pd_tQzM.getText()) * Float.parseFloat(pd_tQzPr.getText()))
                    + (Float.parseFloat(pd_tProjM.getText()) * Float.parseFloat(pd_tProjPr.getText()))
                    + (Float.parseFloat(pd_tPresM.getText()) * Float.parseFloat(pd_tPresPr.getText()));

            valueTOutOf.setText(String.valueOf(sum1));
        }

    }

    @FXML
    private void onDone(ActionEvent event) {
        if (!pd_tAsgM.isDisable()) {
            theoryPedInsert();
        }
        if (!pd_pAsgM.isDisable()) {
            practPedInsert();
        }

    }

    public void theoryPedInsert() {
        try {
            getPedSum();
            if (sum1 == 30) {
                String[] t = pd_sub_combo_box.getValue().trim().split(" | ", 3);
                String sql = "UPDATE `CSPIT`.`CE_PEDGY_TH` SET `INTERNAL-1_M`=?, `INTERNAL-1_P`=?, `INTERNAL-2_M`=?, `INTERNAL-2_P`=?, `ATD_M`=?, `ATD_P`=?, `ASG_M`=?, `ASG_P`=?, `QZ_M`=?, `QZ_P`=?, `PROJ_M`=?, `PROJ_P`=?, `PRES_M`=?, `PRES_P`=? WHERE `SUB_NAME`=? AND `SUB_CODE`=? AND `SEM`=?";
                prstmt = con.prepareStatement(sql);
                //prstmt.setInt(1, no);
                prstmt.setFloat(1, Float.parseFloat(pd_tInt1M.getText()));
                prstmt.setFloat(2, Float.parseFloat(pd_tInt1Pr.getText()));
                prstmt.setFloat(3, Float.parseFloat(pd_tInt2M.getText()));
                prstmt.setFloat(4, Float.parseFloat(pd_tInt2Pr.getText()));
                prstmt.setFloat(5, Float.parseFloat(pd_tAtdM.getText()));
                prstmt.setFloat(6, Float.parseFloat(pd_tAtdPr.getText()));
                prstmt.setFloat(7, Float.parseFloat(pd_tAsgM.getText()));
                prstmt.setFloat(8, Float.parseFloat(pd_tAsgPr.getText()));
                prstmt.setFloat(9, Float.parseFloat(pd_tQzM.getText()));
                prstmt.setFloat(10, Float.parseFloat(pd_tQzPr.getText()));
                prstmt.setFloat(11, Float.parseFloat(pd_tProjM.getText()));
                prstmt.setFloat(12, Float.parseFloat(pd_tProjPr.getText()));
                prstmt.setFloat(13, Float.parseFloat(pd_tPresM.getText()));
                prstmt.setFloat(14, Float.parseFloat(pd_tPresPr.getText()));
                prstmt.setString(15, t[2]);
                prstmt.setString(16, t[0]);
                prstmt.setString(17, pd_sem_combo_box.getValue());
                prstmt.executeUpdate();
            } else {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please set pedagogy for 30 marks");
                a.setTitle("Error");
                a.show();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void practPedInsert() {
        try {
            getPedSum();
            if (sum == 30) {
                String[] t = pd_sub_combo_box.getValue().trim().split(" | ", 3);
                String sql = "UPDATE `CSPIT`.`CE_PEDGY_PR` SET `INTERNAL-1_M`=?, `INTERNAL-1_P`=?, `INTERNAL-2_M`=?, `INTERNAL-2_P`=?, `ATD_M`=?, `ATD_P`=?, `ASG_M`=?, `ASG_P`=?, `QZ_M`=?, `QZ_P`=?, `PROJ_M`=?, `PROJ_P`=?, `PRES_M`=?, `PRES_P`=?, `VIVA_M`=?, `VIVA_P`=? WHERE `SUB_NAME`=? AND `SUB_CODE`=? AND `SEM`=?";
                prstmt = con.prepareStatement(sql);
                //prstmt.setInt(1, no);
                prstmt.setFloat(1, Float.parseFloat(pd_pInt1M.getText()));
                prstmt.setFloat(2, Float.parseFloat(pd_pInt1Pr.getText()));
                prstmt.setFloat(3, Float.parseFloat(pd_pInt2M.getText()));
                prstmt.setFloat(4, Float.parseFloat(pd_pInt2Pr.getText()));
                prstmt.setFloat(5, Float.parseFloat(pd_pAtdM.getText()));
                prstmt.setFloat(6, Float.parseFloat(pd_pAtdPr.getText()));
                prstmt.setFloat(7, Float.parseFloat(pd_pAsgM.getText()));
                prstmt.setFloat(8, Float.parseFloat(pd_pAsgPr.getText()));
                prstmt.setFloat(9, Float.parseFloat(pd_pQzM.getText()));
                prstmt.setFloat(10, Float.parseFloat(pd_pQzPr.getText()));
                prstmt.setFloat(11, Float.parseFloat(pd_pProjM.getText()));
                prstmt.setFloat(12, Float.parseFloat(pd_pProjPr.getText()));
                prstmt.setFloat(13, Float.parseFloat(pd_pPresM.getText()));
                prstmt.setFloat(14, Float.parseFloat(pd_pPresPr.getText()));
                prstmt.setFloat(15, Float.parseFloat(pd_pVivaM.getText()));
                prstmt.setFloat(16, Float.parseFloat(pd_pVivaPr.getText()));
                prstmt.setString(17, t[2]);
                prstmt.setString(18, t[0]);
                prstmt.setString(19, pd_sem_combo_box.getValue());
                prstmt.executeUpdate();
            } else {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please set pedagogy for 30 marks");
                a.setTitle("Error");
                a.show();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onReportSelection(ActionEvent event) {
    }

    @FXML
    private void onSemSelection(ActionEvent event) {
        if (choice == 2) {
            try {
                loadStuData();
            } /*catch (SQLException ex) {
                Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
            }*/ /*catch(NullPointerException e){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
        }*/ catch (SQLException ex) {
                Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void onSubjectSelection(ActionEvent event) throws SQLException {
        try {
            switch (choice) {
                case 1:
                    {
                        String[] subSplit = pd_sub_combo_box.getValue().trim().split(" | ", 3);
                        System.out.println(Arrays.toString(subSplit));
                        buildData(subSplit[0], subSplit[2]);
                        if (getPdLockedStatus(subSplit[0], subSplit[2]).equals("U")) {
                            onDone.setDisable(false);
                            switch (knowTP(subSplit[0], subSplit[2])) {
                                case 't':
                                    pd_plabelInt1.setDisable(true);
                                    pd_pInt1M.setDisable(true);
                                    pd_pInt1Pr.setDisable(true);
                                    pd_plabelInt2.setDisable(true);
                                    pd_pInt2M.setDisable(true);
                                    pd_pInt2Pr.setDisable(true);
                                    pd_plabelAtd.setDisable(true);
                                    pd_pAtdM.setDisable(true);
                                    pd_pAtdPr.setDisable(true);
                                    pd_plabelAsg.setDisable(true);
                                    pd_pAsgM.setDisable(true);
                                    pd_pAsgPr.setDisable(true);
                                    pd_plabelQz.setDisable(true);
                                    pd_pQzM.setDisable(true);
                                    pd_pQzPr.setDisable(true);
                                    pd_plabelProj.setDisable(true);
                                    pd_pProjM.setDisable(true);
                                    pd_pProjPr.setDisable(true);
                                    pd_plabelPres.setDisable(true);
                                    pd_pPresM.setDisable(true);
                                    pd_pPresPr.setDisable(true);
                                    pd_plabelViva.setDisable(true);
                                    pd_pVivaM.setDisable(true);
                                    pd_pVivaPr.setDisable(true);
                                    pd_labelPractical.setDisable(true);
                                    labelPOutOf.setDisable(true);
                                    valuePOutOf.setDisable(true);
                                    pd_tlabelInt1.setDisable(false);
                                    pd_tInt1M.setDisable(false);
                                    pd_tInt1Pr.setDisable(false);
                                    pd_tlabelInt2.setDisable(false);
                                    pd_tInt2M.setDisable(false);
                                    pd_tInt2Pr.setDisable(false);
                                    pd_tlabelAtd.setDisable(false);
                                    pd_tAtdM.setDisable(false);
                                    pd_tAtdPr.setDisable(false);
                                    pd_tlabelAsg.setDisable(false);
                                    pd_tAsgM.setDisable(false);
                                    pd_tAsgPr.setDisable(false);
                                    pd_tlabelQz.setDisable(false);
                                    pd_tQzM.setDisable(false);
                                    pd_tQzPr.setDisable(false);
                                    pd_tlabelProj.setDisable(false);
                                    pd_tProjM.setDisable(false);
                                    pd_tProjPr.setDisable(false);
                                    pd_tlabelPres.setDisable(false);
                                    pd_tPresM.setDisable(false);
                                    pd_tPresPr.setDisable(false);
                                    pd_labelTheory.setDisable(false);
                                    labelTOutOf.setDisable(false);
                                    valueTOutOf.setDisable(false);
                                    /*For Evaluate*/
                                    
                                    break;
                                case 'p':
                                    pd_tlabelInt1.setDisable(true);
                                    pd_tInt1M.setDisable(true);
                                    pd_tInt1Pr.setDisable(true);
                                    pd_tlabelInt2.setDisable(true);
                                    pd_tInt2M.setDisable(true);
                                    pd_tInt2Pr.setDisable(true);
                                    pd_tlabelAtd.setDisable(true);
                                    pd_tAtdM.setDisable(true);
                                    pd_tAtdPr.setDisable(true);
                                    pd_tlabelAsg.setDisable(true);
                                    pd_tAsgM.setDisable(true);
                                    pd_tAsgPr.setDisable(true);
                                    pd_tlabelQz.setDisable(true);
                                    pd_tQzM.setDisable(true);
                                    pd_tQzPr.setDisable(true);
                                    pd_tlabelProj.setDisable(true);
                                    pd_tProjM.setDisable(true);
                                    pd_tProjPr.setDisable(true);
                                    pd_tlabelPres.setDisable(true);
                                    pd_tPresM.setDisable(true);
                                    pd_tPresPr.setDisable(true);
                                    pd_labelTheory.setDisable(true);
                                    labelTOutOf.setDisable(true);
                                    valueTOutOf.setDisable(true);
                                    pd_plabelInt1.setDisable(false);
                                    pd_pInt1M.setDisable(false);
                                    pd_pInt1Pr.setDisable(false);
                                    pd_plabelInt2.setDisable(false);
                                    pd_pInt2M.setDisable(false);
                                    pd_pInt2Pr.setDisable(false);
                                    pd_plabelAtd.setDisable(false);
                                    pd_pAtdM.setDisable(false);
                                    pd_pAtdPr.setDisable(false);
                                    pd_plabelAsg.setDisable(false);
                                    pd_pAsgM.setDisable(false);
                                    pd_pAsgPr.setDisable(false);
                                    pd_plabelQz.setDisable(false);
                                    pd_pQzM.setDisable(false);
                                    pd_pQzPr.setDisable(false);
                                    pd_plabelProj.setDisable(false);
                                    pd_pProjM.setDisable(false);
                                    pd_pProjPr.setDisable(false);
                                    pd_plabelPres.setDisable(false);
                                    pd_pPresM.setDisable(false);
                                    pd_pPresPr.setDisable(false);
                                    pd_plabelViva.setDisable(false);
                                    pd_pVivaM.setDisable(false);
                                    pd_pVivaPr.setDisable(false);
                                    pd_labelPractical.setDisable(false);
                                    labelPOutOf.setDisable(false);
                                    valuePOutOf.setDisable(false);
                                    /*For Evaluate*/
                                    
                                    break;
                                case 'b':
                                    pd_tlabelInt1.setDisable(false);
                                    pd_tInt1M.setDisable(false);
                                    pd_tInt1Pr.setDisable(false);
                                    pd_tlabelInt2.setDisable(false);
                                    pd_tInt2M.setDisable(false);
                                    pd_tInt2Pr.setDisable(false);
                                    pd_tlabelAtd.setDisable(false);
                                    pd_tAtdM.setDisable(false);
                                    pd_tAtdPr.setDisable(false);
                                    pd_tlabelAsg.setDisable(false);
                                    pd_tAsgM.setDisable(false);
                                    pd_tAsgPr.setDisable(false);
                                    pd_tlabelQz.setDisable(false);
                                    pd_tQzM.setDisable(false);
                                    pd_tQzPr.setDisable(false);
                                    pd_tlabelProj.setDisable(false);
                                    pd_tProjM.setDisable(false);
                                    pd_tProjPr.setDisable(false);
                                    pd_tlabelPres.setDisable(false);
                                    pd_tPresM.setDisable(false);
                                    pd_tPresPr.setDisable(false);
                                    pd_labelTheory.setDisable(false);
                                    labelTOutOf.setDisable(false);
                                    valueTOutOf.setDisable(false);
                                    pd_plabelInt1.setDisable(false);
                                    pd_pInt1M.setDisable(false);
                                    pd_pInt1Pr.setDisable(false);
                                    pd_plabelInt2.setDisable(false);
                                    pd_pInt2M.setDisable(false);
                                    pd_pInt2Pr.setDisable(false);
                                    pd_plabelAtd.setDisable(false);
                                    pd_pAtdM.setDisable(false);
                                    pd_pAtdPr.setDisable(false);
                                    pd_plabelAsg.setDisable(false);
                                    pd_pAsgM.setDisable(false);
                                    pd_pAsgPr.setDisable(false);
                                    pd_plabelQz.setDisable(false);
                                    pd_pQzM.setDisable(false);
                                    pd_pQzPr.setDisable(false);
                                    pd_plabelProj.setDisable(false);
                                    pd_pProjM.setDisable(false);
                                    pd_pProjPr.setDisable(false);
                                    pd_plabelPres.setDisable(false);
                                    pd_pPresM.setDisable(false);
                                    pd_pPresPr.setDisable(false);
                                    pd_plabelViva.setDisable(false);
                                    pd_pVivaM.setDisable(false);
                                    pd_pVivaPr.setDisable(false);
                                    pd_labelPractical.setDisable(false);
                                    labelPOutOf.setDisable(false);
                                    valuePOutOf.setDisable(false);
                                    break;
                                default:
                                    break;
                            }
                        } else if (getPdLockedStatus(subSplit[0], subSplit[2]).equals("L")) {
                            setPdAccessibility(subSplit[0], subSplit[2]);
                        }       break;
                    }
                case 2:
                    {
                        String[] subSplit = ev_sub_combo_box.getValue().toString().trim().split(" | ", 3);
                        System.out.println(Arrays.toString(subSplit));
                        getMarks(subSplit[0], subSplit[2]);
                        if (getEvLockedStatus(subSplit[0], subSplit[2]).equals("U")) {
                            onSubmit.setDisable(false);
                            onFinalize.setDisable(false);
                            switch (knowTP(subSplit[0], subSplit[2])) {
                                case 't':
                                    ev_plabelInt1.setDisable(true);
                                    ev_pInt1M.setDisable(true);
                                    ev_plabelInt2.setDisable(true);
                                    ev_pInt2M.setDisable(true);
                                    ev_plabelAtd.setDisable(true);
                                    ev_pAtdM.setDisable(true);
                                    ev_plabelAsg.setDisable(true);
                                    ev_pAsgM.setDisable(true);
                                    ev_plabelQz.setDisable(true);
                                    ev_pQzM.setDisable(true);
                                    ev_plabelProj.setDisable(true);
                                    ev_pProjM.setDisable(true);
                                    ev_plabelPres.setDisable(true);
                                    ev_pPresM.setDisable(true);
                                    ev_plabelViva.setDisable(true);
                                    ev_pVivaM.setDisable(true);
                                    ev_labelPractical.setDisable(true);
                                    ev_tlabelInt1.setDisable(false);
                                    ev_tInt1M.setDisable(false);
                                    ev_tlabelInt2.setDisable(false);
                                    ev_tInt2M.setDisable(false);
                                    ev_tlabelAtd.setDisable(false);
                                    ev_tAtdM.setDisable(false);
                                    ev_tlabelAsg.setDisable(false);
                                    ev_tAsgM.setDisable(false);
                                    ev_tlabelQz.setDisable(false);
                                    ev_tQzM.setDisable(false);
                                    ev_tlabelProj.setDisable(false);
                                    ev_tProjM.setDisable(false);
                                    ev_tlabelPres.setDisable(false);
                                    ev_tPresM.setDisable(false);
                                    ev_labelTheory.setDisable(false);
                                    break;
                                case 'p':
                                    ev_tlabelInt1.setDisable(true);
                                    ev_tInt1M.setDisable(true);
                                    ev_tlabelInt2.setDisable(true);
                                    ev_tInt2M.setDisable(true);
                                    ev_tlabelAtd.setDisable(true);
                                    ev_tAtdM.setDisable(true);
                                    ev_tlabelAsg.setDisable(true);
                                    ev_tAsgM.setDisable(true);
                                    ev_tlabelQz.setDisable(true);
                                    ev_tQzM.setDisable(true);
                                    ev_tlabelProj.setDisable(true);
                                    ev_tProjM.setDisable(true);
                                    ev_tlabelPres.setDisable(true);
                                    ev_tPresM.setDisable(true);
                                    ev_labelTheory.setDisable(true);
                                    ev_plabelInt1.setDisable(false);
                                    ev_pInt1M.setDisable(false);
                                    ev_plabelInt2.setDisable(false);
                                    ev_pInt2M.setDisable(false);
                                    ev_plabelAtd.setDisable(false);
                                    ev_pAtdM.setDisable(false);
                                    ev_plabelAsg.setDisable(false);
                                    ev_pAsgM.setDisable(false);
                                    ev_plabelQz.setDisable(false);
                                    ev_pQzM.setDisable(false);
                                    ev_plabelProj.setDisable(false);
                                    ev_pProjM.setDisable(false);
                                    ev_plabelPres.setDisable(false);
                                    ev_pPresM.setDisable(false);
                                    ev_plabelViva.setDisable(false);
                                    ev_pVivaM.setDisable(false);
                                    ev_labelPractical.setDisable(false);
                                    break;
                                case 'b':
                                    ev_tlabelInt1.setDisable(false);
                                    ev_tInt1M.setDisable(false);
                                    ev_tlabelInt2.setDisable(false);
                                    ev_tInt2M.setDisable(false);
                                    ev_tlabelAtd.setDisable(false);
                                    ev_tAtdM.setDisable(false);
                                    ev_tlabelAsg.setDisable(false);
                                    ev_tAsgM.setDisable(false);
                                    ev_tlabelQz.setDisable(false);
                                    ev_tQzM.setDisable(false);
                                    ev_tlabelProj.setDisable(false);
                                    ev_tProjM.setDisable(false);
                                    ev_tlabelPres.setDisable(false);
                                    ev_tPresM.setDisable(false);
                                    ev_labelTheory.setDisable(false);
                                    ev_plabelInt1.setDisable(false);
                                    ev_pInt1M.setDisable(false);
                                    ev_plabelInt2.setDisable(false);
                                    ev_pInt2M.setDisable(false);
                                    ev_plabelAtd.setDisable(false);
                                    ev_pAtdM.setDisable(false);
                                    ev_plabelAsg.setDisable(false);
                                    ev_pAsgM.setDisable(false);
                                    ev_plabelQz.setDisable(false);
                                    ev_pQzM.setDisable(false);
                                    ev_plabelProj.setDisable(false);
                                    ev_pProjM.setDisable(false);
                                    ev_plabelPres.setDisable(false);
                                    ev_pPresM.setDisable(false);
                                    ev_plabelViva.setDisable(false);
                                    ev_pVivaM.setDisable(false);
                                    ev_labelPractical.setDisable(false);
                                    break;
                                default:
                                    break;
                            }
                        } else if (getEvLockedStatus(subSplit[0], subSplit[2]).equals("L")) {
                            setEvAccessibility(subSplit[0], subSplit[2]);
                        }       break;
                    }
                case 3:
                    {
                        String[] subSplit = rpt_sub_combo_box.getValue().toString().trim().split(" | ", 3);
                        switch (knowTP(subSplit[0], subSplit[2])) {
                            case 't':
                                rbPractical.setDisable(true);
                                rbTheory.setDisable(false);
                                break;
                            case 'p':
                                rbPractical.setDisable(false);
                                rbTheory.setDisable(true);
                                break;
                            case 'b':
                                rbPractical.setDisable(false);
                                rbTheory.setDisable(false);
                                break;
                            default:
                                break;
                        }       break;
                    }
                default:
                    break;
            }

        } catch (NullPointerException e) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please select all fields");
            a.setTitle("Error");
            a.show();
        }
    }

    public void loadStuData() throws SQLException {
        try {
            if (ev_college_combo_box.getValue() != null && ev_branch_combo_box.getValue() != null) {
                String sql = "SELECT `SID`, `SNAME` FROM `" + ev_college_combo_box.getValue() + "`.`" + ev_sem_combo_box.getValue() + ev_branch_combo_box.getValue() + "_STUDENT` WHERE `PID`=?";

                prstmt = con.prepareStatement(sql);

                prstmt.setInt(1, id);

                rs = prstmt.executeQuery();

                while (rs.next()) {
                    if (rs.isBeforeFirst()) {
                        pre = false;
                        nxt = true;
                    }
                    if (rs.isAfterLast()) {
                        pre = true;
                        nxt = false;
                    }
                    valueOfName.setText(rs.getString("SNAME"));
                    valueOfId.setText(rs.getString("SID"));
                }
            } else {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*catch(NullPointerException e){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
        }*/
    }

    @FXML
    private void onSearch(ActionEvent event) {
        try {
            int xid = 0;
            if (ev_college_combo_box.getValue() != null && ev_branch_combo_box.getValue() != null) {
                String sql = "SELECT `PID`, `SID`, `SNAME` FROM `" + ev_college_combo_box.getValue() + "`.`" + ev_sem_combo_box.getValue() + ev_branch_combo_box.getValue() + "_STUDENT` WHERE `SID`=?";
                prstmt = con.prepareStatement(sql);
                prstmt.setString(1, searchBox.getText());
                rs = prstmt.executeQuery();
                while (rs.next()) {
                    valueOfName.setText(rs.getString("SNAME"));
                    valueOfId.setText(rs.getString("SID"));
                    xid = rs.getInt("PID");
                }
                if (xid != 0) {
                    String t[] = ev_sub_combo_box.getValue().toString().trim().split(" | ", 3);
                    getMarks(t[0], t[2]);
                } else {
                    a.setAlertType(Alert.AlertType.WARNING);
                    a.setContentText("No data found");
                    a.setTitle("Error");
                    a.show();
                }
            } else {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Please select all fields");
                a.setTitle("Error");
                a.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onSubmit(ActionEvent event) {
        try {
            String t[] = ev_sub_combo_box.getValue().toString().trim().split(" | ", 3);
            System.out.println(t[0] + "\n" + t[2]);
            switch (knowTP(t[0], t[2])) {
                case 'b':
                    updateTheoryMarks(t[0], t[2]);
                    updatePracticalMarks(t[0], t[2]);
                    break;
                case 't':
                    updateTheoryMarks(t[0], t[2]);
                    break;
                case 'p':
                    updatePracticalMarks(t[0], t[2]);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please select all fields");
            a.setTitle("Error");
            a.show();
        }
    }

    @FXML
    void onEvFinalizeKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT:
                onSubmit.requestFocus();
                break;
            case ENTER:
                onFinalize(new ActionEvent());
                break;
            case UP:
                ev_pVivaM.requestFocus();
                ev_pVivaM.selectAll();
                break;
        }
    }

    @FXML
    void onEvSearchKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case DOWN:
                ev_tInt1M.requestFocus();
                ev_tInt1M.selectAll();
                break;
        }
    }

    @FXML
    void onEvSubmitKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                onFinalize.requestFocus();
                break;
            case ENTER:
                onSubmit(new ActionEvent());
                break;
            case UP:
                ev_pVivaM.requestFocus();
                ev_pVivaM.selectAll();
                break;
        }
    }

    @FXML
    void onEvPAsgKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                ev_tAsgM.requestFocus();
                ev_tAsgM.selectAll();
                break;
            case UP:
                ev_pAtdM.requestFocus();
                ev_pAtdM.selectAll();
                break;
            case DOWN:
                ev_pQzM.requestFocus();
                ev_pQzM.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;

        }
    }

    @FXML
    void onEvPAtdKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                ev_tAtdM.requestFocus();
                ev_tAtdM.selectAll();
                break;
            case UP:
                ev_pInt2M.requestFocus();
                ev_pInt2M.selectAll();
                break;
            case DOWN:
                ev_pAsgM.requestFocus();
                ev_pAsgM.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onEvPInt1KeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                ev_tInt1M.requestFocus();
                ev_tInt1M.selectAll();
                break;
            case UP:
                ev_pVivaM.requestFocus();
                ev_pVivaM.selectAll();
                break;
            case DOWN:
                ev_pInt2M.requestFocus();
                ev_pInt2M.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onEvPInt2KeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                ev_tInt2M.requestFocus();
                ev_tInt2M.selectAll();
                break;
            case UP:
                ev_pInt1M.requestFocus();
                ev_pInt1M.selectAll();
                break;
            case DOWN:
                ev_pAtdM.requestFocus();
                ev_pAtdM.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onEvPPresKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                ev_tPresM.requestFocus();
                ev_tPresM.selectAll();
                break;
            case UP:
                ev_pProjM.requestFocus();
                ev_pProjM.selectAll();
                break;
            case DOWN:
                ev_pVivaM.requestFocus();
                ev_pVivaM.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onEvPProjKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                ev_tProjM.requestFocus();
                ev_tProjM.selectAll();
                break;
            case UP:
                ev_pQzM.requestFocus();
                ev_pQzM.selectAll();
                break;
            case DOWN:
                ev_pPresM.requestFocus();
                ev_pPresM.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onEvPQzKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                ev_tQzM.requestFocus();
                ev_tQzM.selectAll();
                break;
            case UP:
                ev_pAsgM.requestFocus();
                ev_pAsgM.selectAll();
                break;
            case DOWN:
                ev_pProjM.requestFocus();
                ev_pProjM.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onEvPVivaKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                ev_tPresM.requestFocus();
                ev_tPresM.selectAll();
                break;
            case UP:
                ev_pPresM.requestFocus();
                ev_pPresM.selectAll();
                break;
            case DOWN:
                ev_pInt1M.requestFocus();
                ev_pInt1M.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onEvTAsgPressed(KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT:
                ev_pAsgM.requestFocus();
                ev_pAsgM.selectAll();
                break;
            case UP:
                ev_tAtdM.requestFocus();
                ev_tAtdM.selectAll();
                break;
            case DOWN:
                ev_tQzM.requestFocus();
                ev_tQzM.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onEvTAtdKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT:
                ev_pAtdM.requestFocus();
                ev_pAtdM.selectAll();
                break;
            case UP:
                ev_tInt2M.requestFocus();
                ev_tInt2M.selectAll();
                break;
            case DOWN:
                ev_tAsgM.requestFocus();
                ev_tAsgM.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onEvTInt1KeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT:
                ev_pInt1M.requestFocus();
                ev_pInt1M.selectAll();
                break;
            case UP:
                ev_tPresM.requestFocus();
                ev_tPresM.selectAll();
                break;
            case DOWN:
                ev_tInt2M.requestFocus();
                ev_tInt2M.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onEvTInt2KeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT:
                ev_pInt2M.requestFocus();
                ev_pInt2M.selectAll();
                break;
            case UP:
                ev_tInt1M.requestFocus();
                ev_tInt1M.selectAll();
                break;
            case DOWN:
                ev_tAtdM.requestFocus();
                ev_tAtdM.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onEvTPresKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT:
                ev_pPresM.requestFocus();
                ev_pPresM.selectAll();
                break;
            case UP:
                ev_tProjM.requestFocus();
                ev_tProjM.selectAll();
                break;
            case DOWN:
                ev_tInt1M.requestFocus();
                ev_tInt1M.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onEvTProjKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT:
                ev_pProjM.requestFocus();
                ev_pProjM.selectAll();
                break;
            case UP:
                ev_tQzM.requestFocus();
                ev_tQzM.selectAll();
                break;
            case DOWN:
                ev_tPresM.requestFocus();
                ev_tPresM.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onEvTQzKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT:
                ev_pQzM.requestFocus();
                ev_pQzM.selectAll();
                break;
            case UP:
                ev_tAsgM.requestFocus();
                ev_tAsgM.selectAll();
                break;
            case DOWN:
                ev_tProjM.requestFocus();
                ev_tProjM.selectAll();
                break;
            case F:
                searchBox.requestFocus();
                searchBox.selectAll();
                break;
            case N:
                onNxtBtn(new ActionEvent());
                break;
            case P:
                onPreBtn(new ActionEvent());
                break;
        }
    }

    @FXML
    void onPdTInt1MKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pInt1Pr.requestFocus();
                pd_pInt1Pr.selectAll();
                break;
            case RIGHT:
                pd_tInt1Pr.requestFocus();
                pd_tInt1Pr.selectAll();
                break;
            case UP:
                pd_tPresM.requestFocus();
                pd_tPresM.selectAll();
                break;
            case DOWN:
                pd_tInt2M.requestFocus();
                pd_tInt2M.selectAll();
                break;
        }
    }

    @FXML
    void onPdTInt2MKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pInt2Pr.requestFocus();
                pd_pInt2Pr.selectAll();
                break;
            case RIGHT:
                pd_tInt2Pr.requestFocus();
                pd_tInt2Pr.selectAll();
                break;
            case UP:
                pd_tInt1M.requestFocus();
                pd_tInt1M.selectAll();
                break;
            case DOWN:
                pd_tAtdM.requestFocus();
                pd_tAtdM.selectAll();
                break;
        }
    }

    @FXML
    void onPdTAtdMKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pAtdPr.requestFocus();
                pd_pAtdPr.selectAll();
                break;
            case RIGHT:
                pd_tAtdPr.requestFocus();
                pd_tAtdPr.selectAll();
                break;
            case UP:
                pd_tInt2M.requestFocus();
                pd_tInt2M.selectAll();
                break;
            case DOWN:
                pd_tAsgM.requestFocus();
                pd_tAsgM.selectAll();
                break;
        }
    }

    @FXML
    void onPdTAsgMKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pAsgPr.requestFocus();
                pd_pAtdPr.selectAll();
                break;
            case RIGHT:
                pd_tAsgPr.requestFocus();
                pd_tAsgPr.selectAll();
                break;
            case UP:
                pd_tAtdM.requestFocus();
                pd_tAtdM.selectAll();
                break;
            case DOWN:
                pd_tQzM.requestFocus();
                pd_tQzM.selectAll();
                break;
        }
    }

    @FXML
    void onPdTQzMKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pQzPr.requestFocus();
                pd_pQzPr.selectAll();
                break;
            case RIGHT:
                pd_tQzPr.requestFocus();
                pd_tQzPr.selectAll();
                break;
            case UP:
                pd_tAsgM.requestFocus();
                pd_tAsgM.selectAll();
                break;
            case DOWN:
                pd_tProjM.requestFocus();
                pd_tProjM.selectAll();
                break;
        }
    }

    @FXML
    void onPdTProjMKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pProjPr.requestFocus();
                pd_pProjPr.selectAll();
                break;
            case RIGHT:
                pd_tProjPr.requestFocus();
                pd_tProjPr.selectAll();
                break;
            case UP:
                pd_tQzM.requestFocus();
                pd_tQzM.selectAll();
                break;
            case DOWN:
                pd_tPresM.requestFocus();
                pd_tPresM.selectAll();
                break;
        }
    }

    @FXML
    void onPdTPresMKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pPresPr.requestFocus();
                pd_pPresPr.selectAll();
                break;
            case RIGHT:
                pd_tPresPr.requestFocus();
                pd_tPresPr.selectAll();
                break;
            case UP:
                pd_tProjM.requestFocus();
                pd_tProjM.selectAll();
                break;
            case DOWN:
                pd_tInt1M.requestFocus();
                pd_tInt1M.selectAll();
                break;
        }
    }

    @FXML
    void onPdTInt1PrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tInt1M.requestFocus();
                pd_tInt1M.selectAll();
                break;
            case RIGHT:
                pd_pInt1M.requestFocus();
                pd_pInt1M.selectAll();
                break;
            case UP:
                pd_tPresPr.requestFocus();
                pd_tPresPr.selectAll();
                break;
            case DOWN:
                pd_tInt2Pr.requestFocus();
                pd_tInt2Pr.selectAll();
                break;
        }
    }

    @FXML
    void onPdTInt2PrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tInt2M.requestFocus();
                pd_tInt2M.selectAll();
                break;
            case RIGHT:
                pd_pInt2M.requestFocus();
                pd_pInt2M.selectAll();
                break;
            case UP:
                pd_tInt1Pr.requestFocus();
                pd_tInt1Pr.selectAll();
                break;
            case DOWN:
                pd_tAtdPr.requestFocus();
                pd_tAtdPr.selectAll();
                break;
        }
    }

    @FXML
    void onPdTAtdPrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tAtdM.requestFocus();
                pd_tAtdM.selectAll();
                break;
            case RIGHT:
                pd_pAtdM.requestFocus();
                pd_pAtdM.selectAll();
                break;
            case UP:
                pd_tInt2Pr.requestFocus();
                pd_tInt2Pr.selectAll();
                break;
            case DOWN:
                pd_tAsgPr.requestFocus();
                pd_tAsgPr.selectAll();
                break;
        }
    }

    @FXML
    void onPdTAsgPrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tAsgM.requestFocus();
                pd_tAtdM.selectAll();
                break;
            case RIGHT:
                pd_pAsgM.requestFocus();
                pd_pAsgM.selectAll();
                break;
            case UP:
                pd_tAtdPr.requestFocus();
                pd_tAtdPr.selectAll();
                break;
            case DOWN:
                pd_tQzPr.requestFocus();
                pd_tQzPr.selectAll();
                break;
        }
    }

    @FXML
    void onPdTQzPrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tQzM.requestFocus();
                pd_tQzM.selectAll();
                break;
            case RIGHT:
                pd_pQzM.requestFocus();
                pd_pQzM.selectAll();
                break;
            case UP:
                pd_tAsgPr.requestFocus();
                pd_tAsgPr.selectAll();
                break;
            case DOWN:
                pd_tProjPr.requestFocus();
                pd_tProjPr.selectAll();
                break;
        }
    }

    @FXML
    void onPdTProjPrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tProjM.requestFocus();
                pd_tProjM.selectAll();
                break;
            case RIGHT:
                pd_pProjM.requestFocus();
                pd_pProjM.selectAll();
                break;
            case UP:
                pd_tQzPr.requestFocus();
                pd_tQzPr.selectAll();
                break;
            case DOWN:
                pd_tPresPr.requestFocus();
                pd_tPresPr.selectAll();
                break;
        }
    }

    @FXML
    void onPdTPresPrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tPresM.requestFocus();
                pd_tPresM.selectAll();
                break;
            case RIGHT:
                pd_pPresM.requestFocus();
                pd_pPresM.selectAll();
                break;
            case UP:
                pd_tProjPr.requestFocus();
                pd_tProjPr.selectAll();
                break;
            case DOWN:
                pd_tInt1Pr.requestFocus();
                pd_tInt1Pr.selectAll();
                break;
        }
    }

    @FXML
    void onPdPInt1MKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tInt1Pr.requestFocus();
                pd_tInt1Pr.selectAll();
                break;
            case RIGHT:
                pd_pInt1Pr.requestFocus();
                pd_pInt1Pr.selectAll();
                break;
            case UP:
                pd_pVivaM.requestFocus();
                pd_pVivaM.selectAll();
                break;
            case DOWN:
                pd_pInt2M.requestFocus();
                pd_pInt2M.selectAll();
                break;
        }
    }

    @FXML
    void onPdPInt2MKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tInt2Pr.requestFocus();
                pd_tInt2Pr.selectAll();
                break;
            case RIGHT:
                pd_pInt2Pr.requestFocus();
                pd_pInt2Pr.selectAll();
                break;
            case UP:
                pd_pInt1M.requestFocus();
                pd_pInt1M.selectAll();
                break;
            case DOWN:
                pd_pAtdM.requestFocus();
                pd_pAtdM.selectAll();
                break;
        }
    }

    @FXML
    void onPdPAtdMKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tAtdPr.requestFocus();
                pd_tAtdPr.selectAll();
                break;
            case RIGHT:
                pd_pAtdPr.requestFocus();
                pd_pAtdPr.selectAll();
                break;
            case UP:
                pd_pInt2M.requestFocus();
                pd_pInt2M.selectAll();
                break;
            case DOWN:
                pd_pAsgM.requestFocus();
                pd_pAsgM.selectAll();
                break;
        }
    }

    @FXML
    void onPdPAsgMKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tAsgPr.requestFocus();
                pd_tAsgPr.selectAll();
                break;
            case RIGHT:
                pd_pAsgPr.requestFocus();
                pd_pAsgPr.selectAll();
                break;
            case UP:
                pd_pAtdM.requestFocus();
                pd_pAtdM.selectAll();
                break;
            case DOWN:
                pd_pQzM.requestFocus();
                pd_pQzM.selectAll();
                break;
        }
    }

    @FXML
    void onPdPQzMKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tQzPr.requestFocus();
                pd_tQzPr.selectAll();
                break;
            case RIGHT:
                pd_pQzPr.requestFocus();
                pd_pQzPr.selectAll();
                break;
            case UP:
                pd_pAsgM.requestFocus();
                pd_pAsgM.selectAll();
                break;
            case DOWN:
                pd_pProjM.requestFocus();
                pd_pProjM.selectAll();
                break;
        }
    }

    @FXML
    void onPdPProjMKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tProjPr.requestFocus();
                pd_tProjPr.selectAll();
                break;
            case RIGHT:
                pd_pProjPr.requestFocus();
                pd_pProjPr.selectAll();
                break;
            case UP:
                pd_pQzM.requestFocus();
                pd_pQzM.selectAll();
                break;
            case DOWN:
                pd_pPresM.requestFocus();
                pd_pPresM.selectAll();
                break;
        }
    }

    @FXML
    void onPdPPresMKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tPresPr.requestFocus();
                pd_tPresPr.selectAll();
                break;
            case RIGHT:
                pd_pPresPr.requestFocus();
                pd_pPresPr.selectAll();
                break;
            case UP:
                pd_pProjM.requestFocus();
                pd_pProjM.selectAll();
                break;
            case DOWN:
                pd_pVivaM.requestFocus();
                pd_pVivaM.selectAll();
                break;
        }
    }

    @FXML
    void onPdPVivaMKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_tPresPr.requestFocus();
                pd_tPresPr.selectAll();
                break;
            case RIGHT:
                pd_pVivaPr.requestFocus();
                pd_pVivaPr.selectAll();
                break;
            case UP:
                pd_pPresM.requestFocus();
                pd_pPresM.selectAll();
                break;
            case DOWN:
                pd_pInt1M.requestFocus();
                pd_pInt1M.selectAll();
                break;
        }
    }

    @FXML
    void onPdPInt1PrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pInt1M.requestFocus();
                pd_pInt1M.selectAll();
                break;
            case RIGHT:
                pd_tInt1M.requestFocus();
                pd_tInt1M.selectAll();
                break;
            case UP:
                pd_pVivaPr.requestFocus();
                pd_pVivaPr.selectAll();
                break;
            case DOWN:
                pd_pInt2Pr.requestFocus();
                pd_pInt2Pr.selectAll();
                break;
        }
    }

    @FXML
    void onPdPInt2PrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pInt2M.requestFocus();
                pd_pInt2M.selectAll();
                break;
            case RIGHT:
                pd_tInt2M.requestFocus();
                pd_tInt2M.selectAll();
                break;
            case UP:
                pd_pInt1Pr.requestFocus();
                pd_pInt1Pr.selectAll();
                break;
            case DOWN:
                pd_pAtdPr.requestFocus();
                pd_pAtdPr.selectAll();
                break;
        }
    }

    @FXML
    void onPdPAtdPrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pAtdM.requestFocus();
                pd_pAtdM.selectAll();
                break;
            case RIGHT:
                pd_tAtdM.requestFocus();
                pd_tAtdM.selectAll();
                break;
            case UP:
                pd_pInt2Pr.requestFocus();
                pd_pInt2Pr.selectAll();
                break;
            case DOWN:
                pd_pAsgPr.requestFocus();
                pd_pAsgPr.selectAll();
                break;
        }
    }

    @FXML
    void onPdPAsgPrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pAsgM.requestFocus();
                pd_pAtdM.selectAll();
                break;
            case RIGHT:
                pd_tAsgM.requestFocus();
                pd_tAsgM.selectAll();
                break;
            case UP:
                pd_pAtdPr.requestFocus();
                pd_pAtdPr.selectAll();
                break;
            case DOWN:
                pd_pQzPr.requestFocus();
                pd_pQzPr.selectAll();
                break;
        }
    }

    @FXML
    void onPdPQzPrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pQzM.requestFocus();
                pd_pQzM.selectAll();
                break;
            case RIGHT:
                pd_tQzM.requestFocus();
                pd_tQzM.selectAll();
                break;
            case UP:
                pd_pAsgPr.requestFocus();
                pd_pAsgPr.selectAll();
                break;
            case DOWN:
                pd_pProjPr.requestFocus();
                pd_pProjPr.selectAll();
                break;
        }
    }

    @FXML
    void onPdPProjPrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pProjM.requestFocus();
                pd_pProjM.selectAll();
                break;
            case RIGHT:
                pd_tProjM.requestFocus();
                pd_tProjM.selectAll();
                break;
            case UP:
                pd_pQzPr.requestFocus();
                pd_pQzPr.selectAll();
                break;
            case DOWN:
                pd_pPresPr.requestFocus();
                pd_pPresPr.selectAll();
                break;
        }
    }

    @FXML
    void onPdPPresPrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pPresM.requestFocus();
                pd_pPresM.selectAll();
                break;
            case RIGHT:
                pd_tPresM.requestFocus();
                pd_tPresM.selectAll();
                break;
            case UP:
                pd_pProjPr.requestFocus();
                pd_pProjPr.selectAll();
                break;
            case DOWN:
                pd_pVivaPr.requestFocus();
                pd_pVivaPr.selectAll();
                break;
        }
    }

    @FXML
    void onPdPVivaPrKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                pd_pVivaM.requestFocus();
                pd_pVivaM.selectAll();
                break;
            case RIGHT:
                pd_tPresM.requestFocus();
                pd_tPresM.selectAll();
                break;
            case UP:
                pd_pPresPr.requestFocus();
                pd_pPresPr.selectAll();
                break;
            case DOWN:
                pd_pInt1Pr.requestFocus();
                pd_pInt1Pr.selectAll();
                break;
        }
    }

    public boolean isMarksPAsPed() {
        try {
            String sql = "SELECT * FROM `CSPIT`.`CE_PEDGY_PR`";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (Float.parseFloat(ev_pInt1M.getText()) <= rs.getFloat("INTERNAL-1_M") && Float.parseFloat(ev_pInt1M.getText()) >= 0 && Float.parseFloat(ev_pInt2M.getText()) <= rs.getFloat("INTERNAL-2_M") && Float.parseFloat(ev_pInt2M.getText()) >= 0 && Float.parseFloat(ev_pAtdM.getText()) <= rs.getFloat("ATD_M") && Float.parseFloat(ev_pAtdM.getText()) >= 0 && Float.parseFloat(ev_pAsgM.getText()) <= rs.getFloat("ASG_M") && Float.parseFloat(ev_pAsgM.getText()) >= 0 && Float.parseFloat(ev_pQzM.getText()) <= rs.getFloat("QZ_M") && Float.parseFloat(ev_pQzM.getText()) >= 0 && Float.parseFloat(ev_pProjM.getText()) <= rs.getFloat("PROJ_M") && Float.parseFloat(ev_pProjM.getText()) >= 0 && Float.parseFloat(ev_pPresM.getText()) <= rs.getFloat("PRES_M") && Float.parseFloat(ev_pPresM.getText()) >= 0 && Float.parseFloat(ev_pVivaM.getText()) <= rs.getFloat("VIVA_M") && Float.parseFloat(ev_pVivaM.getText()) >= 0) {
                    int1PM = Float.parseFloat(ev_pInt1M.getText()) * rs.getFloat("INTERNAL-1_P");
                    int2PM = Float.parseFloat(ev_pInt2M.getText()) * rs.getFloat("INTERNAL-2_P");
                    atdPM = Float.parseFloat(ev_pAtdM.getText()) * rs.getFloat("ATD_P");
                    asgPM = Float.parseFloat(ev_pAsgM.getText()) * rs.getFloat("ASG_P");
                    qzPM = Float.parseFloat(ev_pQzM.getText()) * rs.getFloat("QZ_P");
                    projPM = Float.parseFloat(ev_pProjM.getText()) * rs.getFloat("PROJ_P");
                    presPM = Float.parseFloat(ev_pPresM.getText()) * rs.getFloat("PRES_P");
                    vivaPM = Float.parseFloat(ev_pVivaM.getText()) * rs.getFloat("VIVA_P");
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isMarksTAsPed() {
        try {
            String sql = "SELECT * FROM `CSPIT`.`CE_PEDGY_TH`";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (Float.parseFloat(ev_tInt1M.getText()) <= rs.getFloat("INTERNAL-1_M") && Float.parseFloat(ev_tInt1M.getText()) >= 0 && Float.parseFloat(ev_tInt2M.getText()) <= rs.getFloat("INTERNAL-2_M") && Float.parseFloat(ev_tInt2M.getText()) >= 0 && Float.parseFloat(ev_tAtdM.getText()) <= rs.getFloat("ATD_M") && Float.parseFloat(ev_tAtdM.getText()) >= 0 && Float.parseFloat(ev_tAsgM.getText()) <= rs.getFloat("ASG_M") && Float.parseFloat(ev_tAsgM.getText()) >= 0 && Float.parseFloat(ev_tQzM.getText()) <= rs.getFloat("QZ_M") && Float.parseFloat(ev_tQzM.getText()) >= 0 && Float.parseFloat(ev_tProjM.getText()) <= rs.getFloat("PROJ_M") && Float.parseFloat(ev_tProjM.getText()) >= 0 && Float.parseFloat(ev_tPresM.getText()) <= rs.getFloat("PRES_M") && Float.parseFloat(ev_tPresM.getText()) >= 0) {
                    int1TM = Float.parseFloat(ev_tInt1M.getText()) * rs.getFloat("INTERNAL-1_P");
                    int2TM = Float.parseFloat(ev_tInt2M.getText()) * rs.getFloat("INTERNAL-2_P");
                    atdTM = Float.parseFloat(ev_tAtdM.getText()) * rs.getFloat("ATD_P");
                    asgTM = Float.parseFloat(ev_tAsgM.getText()) * rs.getFloat("ASG_P");
                    qzTM = Float.parseFloat(ev_tQzM.getText()) * rs.getFloat("QZ_P");
                    projTM = Float.parseFloat(ev_tProjM.getText()) * rs.getFloat("PROJ_P");
                    presTM = Float.parseFloat(ev_tPresM.getText()) * rs.getFloat("PRES_P");
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void updateTheoryMarks(String subCode, String subName) {
        try {
            if (isMarksTAsPed()) {
                String sql = "UPDATE `" + ev_college_combo_box.getValue().toString() + "`.`" + ev_sem_combo_box.getValue().toString() + ev_branch_combo_box.getValue().toString() + "_" + subCode + "-T` SET `INT1`=? , `INT2`=? , `ATD`=? , `ASG`=? , `QZ`=? , `PROJ`=? , `PRES`=?  WHERE `PID` = ? ";
                int pid = getId(valueOfId.getText(), valueOfName.getText());
                prstmt = con.prepareStatement(sql);
                prstmt.setFloat(1, int1TM);
                prstmt.setFloat(2, int2TM);
                prstmt.setFloat(3, atdTM);
                prstmt.setFloat(4, asgTM);
                prstmt.setFloat(5, qzTM);
                prstmt.setFloat(6, projTM);
                prstmt.setFloat(7, presTM);
                prstmt.setInt(8, pid);
                prstmt.execute();
            } else {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("Please enter marks as per pedagogy");
                a.setTitle("Error");
                a.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updatePracticalMarks(String subCode, String subName) {
        try {
            if (isMarksPAsPed()) {
                String sql = "UPDATE `" + ev_college_combo_box.getValue().toString() + "`.`" + ev_sem_combo_box.getValue().toString() + ev_branch_combo_box.getValue().toString() + "_" + subCode + "-P` SET `INT1`=? , `INT2`=? , `ATD`=? , `ASG`=? , `QZ`=? , `PROJ`=? , `PRES`=? , `VIVA`=?  WHERE `PID` = ? ";
                int pid = getId(valueOfId.getText(), valueOfName.getText());
                prstmt = con.prepareStatement(sql);
                prstmt.setFloat(1, int1PM);
                prstmt.setFloat(2, int2PM);
                prstmt.setFloat(3, atdPM);
                prstmt.setFloat(4, asgPM);
                prstmt.setFloat(5, qzPM);
                prstmt.setFloat(6, projPM);
                prstmt.setFloat(7, presPM);
                prstmt.setFloat(8, vivaPM);
                prstmt.setInt(9, pid);
                prstmt.execute();
            } else {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("Please enter marks as per pedagogy");
                a.setTitle("Error");
                a.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void onFinalize(ActionEvent event) {
        try {
            String t[] = ev_sub_combo_box.getValue().toString().trim().split(" | ", 3);
            switch (knowTP(t[0], t[2])) {
                case 'b':
                    readySubTReport(t[0], t[2]);
                    readySubPReport(t[0], t[2]);
                    break;
                case 't':
                    readySubTReport(t[0], t[2]);
                    break;
                case 'p':
                    readySubPReport(t[0], t[2]);
                    break;
                default:
                    break;
            }
            blockMarksEntryInDB(t[0], t[2]);
            setEvAccessibility(t[0], t[2]);
            setPdAccessibility(t[0], t[2]);
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please select all fields");
            a.setTitle("Error");
            a.show();
        }
    }

    public void blockMarksEntryInDB(String subCode, String subName) {
        try {
            String sql = "UPDATE `CSPIT`.`CE_EVALUATOR` SET `LOCKED`='L' WHERE `COLLEGE`=? AND `BRANCH`=? AND `SEM`=? AND `SUB_CODE`=? AND `SUB_NAME`=? ";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, ev_college_combo_box.getValue().toString());
            prstmt.setString(2, ev_branch_combo_box.getValue().toString());
            prstmt.setString(3, ev_sem_combo_box.getValue().toString());
            prstmt.setString(4, subCode);
            prstmt.setString(5, subName);
            prstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getEvLockedStatus(String subCode, String subName) {
        try {
            String lockStatus;
            String sql = "SELECT `LOCKED` FROM `CSPIT`.`CE_EVALUATOR` WHERE `COLLEGE`=? AND `BRANCH`=? AND `SEM`=? AND `SUB_CODE`=? AND `SUB_NAME`=? ";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, ev_college_combo_box.getValue().toString());
            prstmt.setString(2, ev_branch_combo_box.getValue().toString());
            prstmt.setString(3, ev_sem_combo_box.getValue().toString());
            prstmt.setString(4, subCode);
            prstmt.setString(5, subName);
            rs = prstmt.executeQuery();
            while (rs.next()) {
                lockStatus = rs.getString("LOCKED");
                return lockStatus;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public String getPdLockedStatus(String subCode, String subName) {
        try {
            String lockStatus;
            String sql = "SELECT `LOCKED` FROM `CSPIT`.`CE_EVALUATOR` WHERE `COLLEGE`=? AND `BRANCH`=? AND `SEM`=? AND `SUB_CODE`=? AND `SUB_NAME`=? ";
            prstmt = con.prepareStatement(sql);
            prstmt.setString(1, pd_college_combo_box.getValue());
            prstmt.setString(2, pd_branch_combo_box.getValue());
            prstmt.setString(3, pd_sem_combo_box.getValue());
            prstmt.setString(4, subCode);
            prstmt.setString(5, subName);
            rs = prstmt.executeQuery();
            while (rs.next()) {
                lockStatus = rs.getString("LOCKED");
                return lockStatus;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void setEvAccessibility(String subCode, String subName) {
        if (getEvLockedStatus(subCode, subName).equals("L")) {
            ev_tlabelInt1.setDisable(true);
            ev_tInt1M.setDisable(true);
            ev_tlabelInt2.setDisable(true);
            ev_tInt2M.setDisable(true);
            ev_tlabelAtd.setDisable(true);
            ev_tAtdM.setDisable(true);
            ev_tlabelAsg.setDisable(true);
            ev_tAsgM.setDisable(true);
            ev_tlabelQz.setDisable(true);
            ev_tQzM.setDisable(true);
            ev_tlabelProj.setDisable(true);
            ev_tProjM.setDisable(true);
            ev_tlabelPres.setDisable(true);
            ev_tPresM.setDisable(true);
            ev_labelTheory.setDisable(true);
            ev_plabelInt1.setDisable(true);
            ev_pInt1M.setDisable(true);
            ev_plabelInt2.setDisable(true);
            ev_pInt2M.setDisable(true);
            ev_plabelAtd.setDisable(true);
            ev_pAtdM.setDisable(true);
            ev_plabelAsg.setDisable(true);
            ev_pAsgM.setDisable(true);
            ev_plabelQz.setDisable(true);
            ev_pQzM.setDisable(true);
            ev_plabelProj.setDisable(true);
            ev_pProjM.setDisable(true);
            ev_plabelPres.setDisable(true);
            ev_pPresM.setDisable(true);
            ev_plabelViva.setDisable(true);
            ev_pVivaM.setDisable(true);
            ev_labelPractical.setDisable(true);
            onSubmit.setDisable(true);
            onFinalize.setDisable(true);
        } else if (getEvLockedStatus(subCode, subName).equals("U")) {
            ev_tlabelInt1.setDisable(false);
            ev_tInt1M.setDisable(false);
            ev_tlabelInt2.setDisable(false);
            ev_tInt2M.setDisable(false);
            ev_tlabelAtd.setDisable(false);
            ev_tAtdM.setDisable(false);
            ev_tlabelAsg.setDisable(false);
            ev_tAsgM.setDisable(false);
            ev_tlabelQz.setDisable(false);
            ev_tQzM.setDisable(false);
            ev_tlabelProj.setDisable(false);
            ev_tProjM.setDisable(false);
            ev_tlabelPres.setDisable(false);
            ev_tPresM.setDisable(false);
            ev_labelTheory.setDisable(false);
            ev_plabelInt1.setDisable(false);
            ev_pInt1M.setDisable(false);
            ev_plabelInt2.setDisable(false);
            ev_pInt2M.setDisable(false);
            ev_plabelAtd.setDisable(false);
            ev_pAtdM.setDisable(false);
            ev_plabelAsg.setDisable(false);
            ev_pAsgM.setDisable(false);
            ev_plabelQz.setDisable(false);
            ev_pQzM.setDisable(false);
            ev_plabelProj.setDisable(false);
            ev_pProjM.setDisable(false);
            ev_plabelPres.setDisable(false);
            ev_pPresM.setDisable(false);
            ev_plabelViva.setDisable(false);
            ev_pVivaM.setDisable(false);
            ev_labelPractical.setDisable(false);
            onSubmit.setDisable(false);
            onFinalize.setDisable(false);
        }
    }

    public void setPdAccessibility(String subCode, String subName) {
        if (getPdLockedStatus(subCode, subName).equals("L")) {
            pd_tlabelInt1.setDisable(true);
            pd_tInt1M.setDisable(true);
            pd_tInt1Pr.setDisable(true);
            pd_tlabelInt2.setDisable(true);
            pd_tInt2M.setDisable(true);
            pd_tInt2Pr.setDisable(true);
            pd_tlabelAtd.setDisable(true);
            pd_tAtdM.setDisable(true);
            pd_tAtdPr.setDisable(true);
            pd_tlabelAsg.setDisable(true);
            pd_tAsgM.setDisable(true);
            pd_tAsgPr.setDisable(true);
            pd_tlabelQz.setDisable(true);
            pd_tQzM.setDisable(true);
            pd_tQzPr.setDisable(true);
            pd_tlabelProj.setDisable(true);
            pd_tProjM.setDisable(true);
            pd_tProjPr.setDisable(true);
            pd_tlabelPres.setDisable(true);
            pd_tPresM.setDisable(true);
            pd_tPresPr.setDisable(true);
            pd_labelTheory.setDisable(true);
            labelTOutOf.setDisable(true);
            valueTOutOf.setDisable(true);
            pd_plabelInt1.setDisable(true);
            pd_pInt1M.setDisable(true);
            pd_pInt1Pr.setDisable(true);
            pd_plabelInt2.setDisable(true);
            pd_pInt2M.setDisable(true);
            pd_pInt2Pr.setDisable(true);
            pd_plabelAtd.setDisable(true);
            pd_pAtdM.setDisable(true);
            pd_pAtdPr.setDisable(true);
            pd_plabelAsg.setDisable(true);
            pd_pAsgM.setDisable(true);
            pd_pAsgPr.setDisable(true);
            pd_plabelQz.setDisable(true);
            pd_pQzM.setDisable(true);
            pd_pQzPr.setDisable(true);
            pd_plabelProj.setDisable(true);
            pd_pProjM.setDisable(true);
            pd_pProjPr.setDisable(true);
            pd_plabelPres.setDisable(true);
            pd_pPresM.setDisable(true);
            pd_pPresPr.setDisable(true);
            pd_plabelViva.setDisable(true);
            pd_pVivaM.setDisable(true);
            pd_pVivaPr.setDisable(true);
            pd_labelPractical.setDisable(true);
            labelPOutOf.setDisable(true);
            valuePOutOf.setDisable(true);
            onDone.setDisable(true);
        } else if (getPdLockedStatus(subCode, subName).equals("U")) {
            pd_tlabelInt1.setDisable(false);
            pd_tInt1M.setDisable(false);
            pd_tInt1Pr.setDisable(false);
            pd_tlabelInt2.setDisable(false);
            pd_tInt2M.setDisable(false);
            pd_tInt2Pr.setDisable(false);
            pd_tlabelAtd.setDisable(false);
            pd_tAtdM.setDisable(false);
            pd_tAtdPr.setDisable(false);
            pd_tlabelAsg.setDisable(false);
            pd_tAsgM.setDisable(false);
            pd_tAsgPr.setDisable(false);
            pd_tlabelQz.setDisable(false);
            pd_tQzM.setDisable(false);
            pd_tQzPr.setDisable(false);
            pd_tlabelProj.setDisable(false);
            pd_tProjM.setDisable(false);
            pd_tProjPr.setDisable(false);
            pd_tlabelPres.setDisable(false);
            pd_tPresM.setDisable(false);
            pd_tPresPr.setDisable(false);
            pd_labelTheory.setDisable(false);
            labelTOutOf.setDisable(false);
            valueTOutOf.setDisable(false);
            pd_plabelInt1.setDisable(false);
            pd_pInt1M.setDisable(false);
            pd_pInt1Pr.setDisable(false);
            pd_plabelInt2.setDisable(false);
            pd_pInt2M.setDisable(false);
            pd_pInt2Pr.setDisable(false);
            pd_plabelAtd.setDisable(false);
            pd_pAtdM.setDisable(false);
            pd_pAtdPr.setDisable(false);
            pd_plabelAsg.setDisable(false);
            pd_pAsgM.setDisable(false);
            pd_pAsgPr.setDisable(false);
            pd_plabelQz.setDisable(false);
            pd_pQzM.setDisable(false);
            pd_pQzPr.setDisable(false);
            pd_plabelProj.setDisable(false);
            pd_pProjM.setDisable(false);
            pd_pProjPr.setDisable(false);
            pd_plabelPres.setDisable(false);
            pd_pPresM.setDisable(false);
            pd_pPresPr.setDisable(false);
            pd_plabelViva.setDisable(false);
            pd_pVivaM.setDisable(false);
            pd_pVivaPr.setDisable(false);
            pd_labelPractical.setDisable(false);
            labelPOutOf.setDisable(false);
            valuePOutOf.setDisable(false);
            onDone.setDisable(false);
        }
    }

    public int getLastPID() {
        try {
            int lastPID = 0;
            String sql = "SELECT `PID` FROM `" + ev_college_combo_box.getValue().toString() + "`.`" + ev_sem_combo_box.getValue().toString() + ev_branch_combo_box.getValue().toString() + "_STUDENT` ORDER BY `PID` DESC LIMIT 1";
            prstmt = con.prepareStatement(sql);
            rs = prstmt.executeQuery();
            while (rs.next()) {
                lastPID = rs.getInt("PID");
            }
            return lastPID;
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void readySubTReport(String subCode, String subName) {
        int lastPID = getLastPID();
        float sumT = 0;
        String sql;
        for (int i = 1; i <= lastPID; i++) {
            try {
                sql = "SELECT * FROM `" + ev_college_combo_box.getValue().toString() + "`.`" + ev_sem_combo_box.getValue().toString() + ev_branch_combo_box.getValue().toString() + "_" + subCode + "-T` WHERE `PID`=?";
                prstmt = con.prepareStatement(sql);
                prstmt.setInt(1, i);
                rs = prstmt.executeQuery();
                while (rs.next()) {
                    sumT = rs.getFloat("INT1") + rs.getFloat("INT2") + rs.getFloat("ATD") + rs.getFloat("ASG") + rs.getFloat("QZ") + rs.getFloat("PROJ") + rs.getFloat("PRES");
                }
                /*INSERT INTO STUDENT*/
                sql = "UPDATE `" + ev_college_combo_box.getValue().toString() + "`.`" + ev_sem_combo_box.getValue().toString() + ev_branch_combo_box.getValue().toString() + "_STUDENT` SET `" + subCode + "-T`=? WHERE `PID`=?";
                prstmt = con.prepareStatement(sql);
                prstmt.setFloat(1, sumT);
                prstmt.setInt(2, i);
                prstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void readySubPReport(String subCode, String subName) {
        int lastPID = getLastPID();
        float sumP = 0;
        String sql;
        for (int i = 1; i <= lastPID; i++) {
            try {
                sql = "SELECT * FROM `" + ev_college_combo_box.getValue().toString() + "`.`" + ev_sem_combo_box.getValue().toString() + ev_branch_combo_box.getValue().toString() + "_" + subCode + "-P` WHERE `PID`=?";
                prstmt = con.prepareStatement(sql);
                prstmt.setInt(1, i);
                rs = prstmt.executeQuery();
                while (rs.next()) {
                    sumP = rs.getFloat("INT1") + rs.getFloat("INT2") + rs.getFloat("ATD") + rs.getFloat("ASG") + rs.getFloat("QZ") + rs.getFloat("PROJ") + rs.getFloat("PRES") + rs.getFloat("VIVA");
                }
                /*INSERT INTO STUDENT*/
                sql = "UPDATE `" + ev_college_combo_box.getValue().toString() + "`.`" + ev_sem_combo_box.getValue().toString() + ev_branch_combo_box.getValue().toString() + "_STUDENT` SET `" + subCode + "-P`=? WHERE `PID`=?";
                prstmt = con.prepareStatement(sql);
                prstmt.setFloat(1, sumP);
                prstmt.setInt(2, i);
                prstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void onNxtBtn(ActionEvent event) {
        try {
            String t[] = ev_sub_combo_box.getValue().toString().trim().split(" | ", 3);
            if (nxt) {
                id++;
                System.out.println(id);
                loadStuData();
                getMarks(t[0], t[2]);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please select all fields");
            a.setTitle("Error");
            a.show();
        }
    }

    @FXML
    private void onPreBtn(ActionEvent event) {
        try {
            String t[] = ev_sub_combo_box.getValue().toString().trim().split(" | ", 3);
            if (pre) {
                id--;
                loadStuData();
                getMarks(t[0], t[2]);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please select all fields");
            a.setTitle("Error");
            a.show();
        }
    }

    @FXML
    private void onTheory(ActionEvent event) {
        try {
            loadReport();
        } catch (NullPointerException e) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please select all fields");
            a.setTitle("Error");
            a.show();
        }
    }

    @FXML
    private void onPractical(ActionEvent event) {
        try {
            loadReport();
        } catch (NullPointerException e) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please select all fields");
            a.setTitle("Error");
            a.show();
        }
    }

    @FXML
    private void onDwnldEX(ActionEvent event) throws IOException, DocumentException {
        try {
            savePDF(4);
        } catch (NullPointerException e) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please select all fields");
            a.setTitle("Error");
            a.show();
        }
    }

    private ObservableList<Student> dataSt;

    public void loadReport() {
        try {
            String t[] = rpt_sub_combo_box.getValue().toString().trim().split(" | ", 3);
            String sql = null;
            dataSt = FXCollections.observableArrayList();
            if (rbTheory.isSelected()) {
                sql = "SELECT `SID`, `SNAME`, `" + t[0] + "-T` FROM `" + rpt_college_combo_box.getValue().toString() + "`.`" + rpt_sem_combo_box.getValue().toString() + rpt_branch_combo_box.getValue().toString() + "_STUDENT`";
            } else if (rbPractical.isSelected()) {
                sql = "SELECT `SID`, `SNAME`, `" + t[0] + "-P` FROM `" + rpt_college_combo_box.getValue().toString() + "`.`" + rpt_sem_combo_box.getValue().toString() + rpt_branch_combo_box.getValue().toString() + "_STUDENT`";
            }
            prstmt = con.prepareStatement(sql);
            rs = prstmt.executeQuery();
            while (rs.next()) {
                if (rbTheory.isSelected()) {
                    dataSt.add(new Student(rs.getInt(t[0] + "-T"), rs.getString("SID"), rs.getString("SNAME")));
                } else if (rbPractical.isSelected()) {
                    dataSt.add(new Student(rs.getInt(t[0] + "-P"), rs.getString("SID"), rs.getString("SNAME")));
                }
                rptTable.setItems(dataSt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void onChangePassword(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = (Parent) FXMLLoader.load(getClass().getResource("ChangePassword.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:C:\\Users\\VASPAR ASPI\\Documents\\NetBeansProjects\\Evaluator\\dist\\Evaluator.png"));
            stage.setTitle("Change Password");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void savePDF(int size) throws IOException, DocumentException {
        try {
            //int row=1;
            String t[] = rpt_sub_combo_box.getValue().toString().trim().split(" | ", 3);
            FileChooser fp = new FileChooser();
            FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter("PDF(*.pdf)", "*.pdf");
            fp.getExtensionFilters().add(extension);
            File fs = fp.showSaveDialog(EvaluatorDriver.copyStage);
            Path path = Paths.get(fs.getAbsolutePath());//"C:\\Users\\VASPAR ASPI\\Desktop\\TRIAL1.xlsx");
            OutputStream os = Files.newOutputStream(path);
            Document doc = new Document();
            PdfWriter.getInstance(doc, os);
            doc.open();
            String sql = null, subCode = null;
            if (rbTheory.isSelected()) {
                subCode = t[0] + "-T";
                sql = "SELECT `SID`, `SNAME`, `" + t[0] + "-T` FROM `" + rpt_college_combo_box.getValue().toString() + "`.`" + rpt_sem_combo_box.getValue().toString() + rpt_branch_combo_box.getValue().toString() + "_STUDENT`";
            } else if (rbPractical.isSelected()) {
                subCode = t[0] + "-P";
                sql = "SELECT `SID`, `SNAME`, `" + t[0] + "-P` FROM `" + rpt_college_combo_box.getValue().toString() + "`.`" + rpt_sem_combo_box.getValue().toString() + rpt_branch_combo_box.getValue().toString() + "_STUDENT`";
            }
            //int size=colName.size();
            PdfPTable tbl = new PdfPTable(size - 1);
            /*float[] widths=new float[size-1];
             widths[0]=500f;
             widths[1]=1500f;
             for(int i=2;i<size-1;i++){
                 widths[i]=500f;
             }
            tbl.setTotalWidth(widths);*/
            PdfPCell cell;
            //Add column header
            cell = new PdfPCell(new Phrase("Id"));
            cell.setFixedHeight(75f);
            tbl.addCell(cell);
            cell = new PdfPCell(new Phrase("Name"));
            cell.setFixedHeight(75f);
            tbl.addCell(cell);
            cell = new PdfPCell(new Phrase(subCode));
            cell.setFixedHeight(75f);
            tbl.addCell(cell);
            tbl.setHeaderRows(1);
            //Add table data
            prstmt = con.prepareStatement(sql);
            rs = prstmt.executeQuery();
            while (rs.next()) {
                tbl.addCell(rs.getString("SID"));
                tbl.addCell(rs.getString("SNAME"));
                tbl.addCell(rs.getString(subCode));
            }
            doc.add(tbl);
            doc.close();
        } catch (DocumentException | IOException | SQLException ex) {
            Logger.getLogger(EvaluatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
