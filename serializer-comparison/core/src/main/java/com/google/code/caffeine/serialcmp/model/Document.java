package com.google.code.caffeine.serialcmp.model;

import java.util.*;

public interface Document {

    int getIntp();

    void setIntp(int intp);

    Integer getInteger();

    void setInteger(Integer integer);

    Object getObject();

    void setObject(Object object);

    String getString();

    void setString(String string);

    String getStringRequired();

    void setStringRequired(String stringRequired);

    Date getDate();

    void setDate(Date date);

    Locale getLocale();

    void setLocale(Locale locale);

    UUID getUuid();

    void setUuid(UUID uuid);

    Currency getCurrency();

    void setCurrency(Currency currency);

    EnumValue getEnumValue();

    void setEnumValue(EnumValue enumValue);

    Bean getBean();

    void setBean(Bean bean);

    Iterable<Bean> getIterable();

    void setIterable(Iterable<Bean> iterable);

    Collection<Bean> getCollection();

    void setCollection(Collection<Bean> collection);

    List<Bean> getList();

    void setList(List<Bean> list);

    Set<Bean> getSet();

    void setSet(Set<Bean> set);

    Map<String, Bean> getMap();

    void setMap(Map<String, Bean> map);

}
