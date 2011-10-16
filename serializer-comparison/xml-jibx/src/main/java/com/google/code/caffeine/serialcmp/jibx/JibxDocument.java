package com.google.code.caffeine.serialcmp.jibx;

import java.util.Locale;

public class JibxDocument {

    public int getIntp() {
        return intp;
    }

    public void setIntp(int intp) {
        this.intp = intp;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Locale getLocale() {
        return locale;
    }


    private int intp;
    private Integer integer;
    private String string;
    private Locale locale;

}
