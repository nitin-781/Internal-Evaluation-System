/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

/**
 *
 * @author VASPAR
 */
public class Report {
   public String id,name;
   public float marks,int1,int2,atd,asg,qz,proj,pres,viva;

    public Report(String id, String name, float marks, float int1, float int2, float atd, float asg, float qz, float proj, float pres, float viva) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.int1 = int1;
        this.int2 = int2;
        this.atd = atd;
        this.asg = asg;
        this.qz = qz;
        this.proj = proj;
        this.pres = pres;
        this.viva = viva;
    }

    public Report(String id, String name, float marks, float int1, float int2, float atd, float asg, float qz, float proj, float pres) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.int1 = int1;
        this.int2 = int2;
        this.atd = atd;
        this.asg = asg;
        this.qz = qz;
        this.proj = proj;
        this.pres = pres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    public float getInt1() {
        return int1;
    }

    public void setInt1(float int1) {
        this.int1 = int1;
    }

    public float getInt2() {
        return int2;
    }

    public void setInt2(float int2) {
        this.int2 = int2;
    }

    public float getAtd() {
        return atd;
    }

    public void setAtd(float atd) {
        this.atd = atd;
    }

    public float getAsg() {
        return asg;
    }

    public void setAsg(float asg) {
        this.asg = asg;
    }

    public float getQz() {
        return qz;
    }

    public void setQz(float qz) {
        this.qz = qz;
    }

    public float getProj() {
        return proj;
    }

    public void setProj(float proj) {
        this.proj = proj;
    }

    public float getPres() {
        return pres;
    }

    public void setPres(float pres) {
        this.pres = pres;
    }

    public float getViva() {
        return viva;
    }

    public void setViva(float viva) {
        this.viva = viva;
    }

    public Report() {
    }

    
}
