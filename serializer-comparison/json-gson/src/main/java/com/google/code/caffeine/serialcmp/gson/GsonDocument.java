package com.google.code.caffeine.serialcmp.gson;

import com.google.code.caffeine.serialcmp.AbstractDocument;
import com.google.gson.annotations.SerializedName;

import java.util.Locale;

public class GsonDocument
        extends AbstractDocument
{

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

    @SerializedName("int-p")
    private int intp;
    @SerializedName("integer")
    private Integer integer;
    @SerializedName("string")
    private String string;
    @SerializedName("locale")
    private Locale locale;

}
