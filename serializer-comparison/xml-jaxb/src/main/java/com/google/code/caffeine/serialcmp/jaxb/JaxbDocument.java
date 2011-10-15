package com.google.code.caffeine.serialcmp.jaxb;

import com.google.code.caffeine.serialcmp.model.Bean;
import com.google.code.caffeine.serialcmp.model.Document;
import com.google.code.caffeine.serialcmp.model.EnumValue;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

@XmlRootElement(name = "document")
public class JaxbDocument implements Document {

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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String getStringRequired() {
        return stringRequired;
    }

    @Override
    public void setStringRequired(String stringRequired) {
        this.stringRequired = stringRequired;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public EnumValue getEnumValue() {
        return enumValue;
    }

    public void setEnumValue(EnumValue enumValue) {
        this.enumValue = enumValue;
    }

    public Bean getBean() {
        return bean;
    }

    public void setBean(Bean bean) {
        this.bean = bean;
    }

    public Iterable<Bean> getIterable() {
        return iterable;
    }

    public void setIterable(Iterable<Bean> iterable) {
        this.iterable = iterable;
    }

    public Collection<Bean> getCollection() {
        return collection;
    }

    public void setCollection(Collection<Bean> collection) {
        this.collection = collection;
    }

    public List<Bean> getList() {
        return list;
    }

    public void setList(List<Bean> list) {
        this.list = new ArrayList<Bean>(list);
    }

    public Set<Bean> getSet() {
        return set;
    }

    public void setSet(Set<Bean> set) {
        this.set = set;
    }

    public Map<String, Bean> getMap() {
        return map;
    }

    public void setMap(Map<String, Bean> map) {
        this.map = map;
    }

    @XmlElement(name = "intp")
    private int intp;
    @XmlTransient
    private Integer integer;
    @XmlTransient
    private Object object;
    @XmlTransient
    private String string;

    private String stringRequired;
    @XmlTransient
    private Date date;
    @XmlTransient
    private Locale locale;
    @XmlTransient
    private UUID uuid;
    @XmlTransient
    private Currency currency;
    @XmlTransient
    private EnumValue enumValue;

    @XmlTransient
    private Bean bean;

    @XmlTransient
    private Iterable<Bean> iterable;
    @XmlTransient
    private Collection<Bean> collection;
    @XmlElement(name = "list")
    private ArrayList<Bean> list;
    @XmlTransient
    private Set<Bean> set;
    @XmlTransient
    private Map<String, Bean> map;

}
