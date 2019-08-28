package com.petterp.latte_ec.main.analysis;

/**
 * @author by petterp
 * @date 2019-08-10
 */
public class AnalyMessages {
    private int mode;
    private String year = "";
    private String month = "";


    public int getMode() {
        return mode;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
