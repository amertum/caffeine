package com.google.code.caffeine.serialcmp.jibx;

import com.google.code.caffeine.serialcmp.AbstractDocument;

import java.util.Locale;

public class JibxDocument extends AbstractDocument {

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

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    private int intp;
    private Integer integer;
    private String string;
    private Locale locale;

}
