package com.google.code.caffeine.serialcmp.xstream;

import com.google.code.caffeine.serialcmp.AbstractDocument;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.Locale;

@XStreamAlias("document")
public class XstreamDocument extends AbstractDocument {

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

    @XStreamAlias("int-p")
    private int intp;
    @XStreamAlias("integer")
    private Integer integer;
    @XStreamAlias("string")
    private String string;
    @XStreamAlias("locale")
    @XStreamAsAttribute
    private Locale locale;

}
