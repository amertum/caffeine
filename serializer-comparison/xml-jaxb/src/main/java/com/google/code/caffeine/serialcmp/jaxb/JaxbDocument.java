package com.google.code.caffeine.serialcmp.jaxb;

import com.google.code.caffeine.serialcmp.AbstractDocument;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.*;

@XmlRootElement(name = "document")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType
public class JaxbDocument {

    public JaxbDocument() {
    }

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

    @XmlElement(name = "intp")
    private int intp;
    @XmlElement(name = "integer")
    private Integer integer;
    @XmlElement(name = "string")
    private String string;
    @XmlAttribute(name = "locale")
    @XmlJavaTypeAdapter(value = LocaleXmlAdapter.class, type = Locale.class)
    private Locale locale;

}
