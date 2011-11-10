package com.google.code.caffeine.serialcmp.jibx;

import com.google.code.caffeine.serialcmp.AbstractDocument;

import java.util.List;
import java.util.Locale;

public class JibxDocument extends AbstractDocument {

    public int getIntp() {
        return this.intp;
    }

    public void setIntp(int intp) {
        this.intp = intp;
    }

    public Integer getInteger() {
        return this.integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public String getString() {
        return this.string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<JibxElement> getElements()
    {
        return this.elements;
    }

    public void setElements(final List<JibxElement> elements)
    {
        this.elements = elements;
    }

    private int intp;
    private Integer integer;
    private String string;
    private Locale locale;
    private List<JibxElement> elements;

}
