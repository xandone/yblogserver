package com.xandone.yblog.pojo;

/**
 * @author ：xandone
 * created on  ：2020/9/21 11:56
 * description：
 */
public class YearArtData {
    private String year;
    private int codeCount;
    private int essayCount;

    public YearArtData(String year, int codeCount, int essayCount) {
        this.year = year;
        this.codeCount = codeCount;
        this.essayCount = essayCount;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCodeCount() {
        return codeCount;
    }

    public void setCodeCount(int codeCount) {
        this.codeCount = codeCount;
    }

    public int getEssayCount() {
        return essayCount;
    }

    public void setEssayCount(int essayCount) {
        this.essayCount = essayCount;
    }
}
