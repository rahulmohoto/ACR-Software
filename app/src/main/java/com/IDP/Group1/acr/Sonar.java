package com.IDP.Group1.acr;

public class Sonar {
    private int sonar_mostleft, sonar_left, sonar_mid, sonar_right, sonar_mostright;
    public Sonar(){

    }

    public Sonar(int sonar_mostleft, int sonar_left, int sonar_mid, int sonar_right, int sonar_mostright){
        this.sonar_mostleft=sonar_mostleft;
        this.sonar_left=sonar_left;
        this.sonar_mid=sonar_mid;
        this.sonar_right=sonar_right;
        this.sonar_mostright=sonar_mostright;
    }

    public void setSonar_mostleft(int sonar_mostleft) {
        this.sonar_mostleft = sonar_mostleft;
    }

    public void setSonar_left(int sonar_left) {
        this.sonar_left = sonar_left;
    }

    public void setSonar_mid(int sonar_mid) {
        this.sonar_mid = sonar_mid;
    }

    public void setSonar_right(int sonar_right) {
        this.sonar_right = sonar_right;
    }

    public void setSonar_mostright(int sonar_mostright) {
        this.sonar_mostright = sonar_mostright;
    }

    public int getSonar_mostleft() {
        return sonar_mostleft;
    }

    public int getSonar_left() {
        return sonar_left;
    }

    public int getSonar_mid() {
        return sonar_mid;
    }

    public int getSonar_right() {
        return sonar_right;
    }

    public int getSonar_mostright() {
        return sonar_mostright;
    }
}

