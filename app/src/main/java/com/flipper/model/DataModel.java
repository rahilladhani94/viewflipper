package com.flipper.model;

public class DataModel {

    String status;
    String bgcolor;
    String textcolor;


    public DataModel(String status, String bgcolor, String textcolor) {
        this.status=status;
        this.bgcolor=bgcolor;
        this.textcolor=textcolor;




    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(String textcolor) {
        this.textcolor = textcolor;
    }
}