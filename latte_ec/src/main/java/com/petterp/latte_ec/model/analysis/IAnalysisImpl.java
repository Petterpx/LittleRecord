package com.petterp.latte_ec.model.analysis;

/**
 * @author by petterp
 * @date 2019-08-10
 */
public class IAnalysisImpl implements IAnalysisModel {
    private String year;
    private String month;

    @Override
    public String getTitleRes() {
        return year + month;
    }

    @Override
    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public void setYear(String year) {
        this.year = year;
    }
}
