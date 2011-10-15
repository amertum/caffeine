package com.google.code.caffeine.serialcmp.simple;

import com.google.code.caffeine.serialcmp.model.Bean;
import com.google.code.caffeine.serialcmp.model.Document;
import com.google.code.caffeine.serialcmp.model.EnumValue;
import org.simpleframework.xml.*;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.*;

@Root(name = "document")
public class SimpleDocument implements Document {

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
        this.list = list;
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

    @Element(name = "int-p", required = false)
    private int intp;
    @Element(required = false)
    private Integer integer;
    @Transient
    private Object object;
    @Element(name = "string", required = false)
    private String string;
    @Element(name = "string-required")
    private String stringRequired;
    @Element(required = false)
    private Date date;
    @Element(required = false)
    private Locale locale;
    @Element(required = false)
    @Convert(UUIDConverter.class)
    private UUID uuid;
    @Element(required = false)
    private Currency currency;
    @Element(required = false)
    private EnumValue enumValue;

    @Element(name = "bean", required = false)
    private Bean bean;

    @ElementList(required = false, type = SimpleBean.class)
    private Iterable<Bean> iterable;
    @ElementList(required = false, type = SimpleBean.class, inline = false, entry = "collection-element")
    private Collection<Bean> collection;
    @ElementList(required = false, type = SimpleBean.class, entry = "element")
    private List<Bean> list;
    @ElementList(required = false, type = SimpleBean.class)
    private Set<Bean> set;
    @ElementMap(required = false, keyType = String.class, valueType = SimpleBean.class, attribute = true)
    private Map<String, Bean> map;

    public static class UUIDConverter implements Converter<UUID> {

        public UUID read(InputNode node) throws Exception {
            return UUID.fromString(node.getValue());
        }

        public void write(OutputNode node, UUID uuid) {
            node.setValue(uuid.toString());
        }
    }

}
