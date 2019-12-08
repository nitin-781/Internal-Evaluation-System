/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.time.LocalDate;

/**
 *
 * @author VASPAR
 */
public class Evaluator {
    public String sem,name,email,college,branch,subCode,subName,tp;
    public int no;

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }
    public LocalDate dt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public LocalDate getDt() {
        return dt;
    }

    public void setDt(LocalDate dt) {
        this.dt = dt;
    }

    public Evaluator(int no,String sem,String name, String email, String college, String branch, String subCode, String subName, String tp, LocalDate dt) {
        this.name = name;
        this.email = email;
        this.college = college;
        this.branch = branch;
        this.sem=sem;
        this.subCode = subCode;
        this.subName = subName;
        this.no = no;
        this.tp = tp;
        this.dt = dt;
    }

    public Evaluator() {
    }

    
}
