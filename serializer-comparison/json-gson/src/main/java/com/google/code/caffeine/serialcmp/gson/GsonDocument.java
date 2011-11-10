package com.google.code.caffeine.serialcmp.gson;

import com.google.code.caffeine.serialcmp.AbstractDocument;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GsonDocument
        extends AbstractDocument
{

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

    public List<GsonElement> getElements()
    {
        return this.elements;
    }

    public void setElements(final List<GsonElement> elements)
    {
        this.elements = elements;
    }

    public Map<String, String> getMap()
    {
        return this.map;
    }

    public void setMap(final Map<String, String> map)
    {
        this.map = map;
    }

    @SerializedName("int-p")
    private int intp;
    @SerializedName("integer")
    private Integer integer;
    @SerializedName("string")
    private String string;
    @SerializedName("locale")
    private Locale locale;
    @SerializedName("myList")
    private List<GsonElement> elements;
    @SerializedName("myMap")
    private Map<String, String> map;

}
