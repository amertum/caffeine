package com.google.code.caffeine.serialcmp.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

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

    public ArrayList<JaxbElement> getElements()
    {
        return this.elements;
    }

    public void setElements(final ArrayList<JaxbElement> elements)
    {
        this.elements = elements;
    }

    public HashMap<String, String> getMap()
    {
        return this.map;
    }

    public void setMap(final HashMap<String, String> map)
    {
        this.map = map;
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
    @XmlElementWrapper(name = "myList")
    @XmlElement(name = "element")
    private ArrayList<JaxbElement> elements;
    @XmlTransient
    private HashMap<String, String> map;

}
