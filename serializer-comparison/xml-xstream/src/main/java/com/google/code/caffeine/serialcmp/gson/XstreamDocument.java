package com.google.code.caffeine.serialcmp.gson;

import com.google.code.caffeine.serialcmp.AbstractDocument;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@XStreamAlias("document")
public class XstreamDocument
        extends AbstractDocument
{

    public int getIntp()
    {
        return intp;
    }

    public void setIntp(int intp)
    {
        this.intp = intp;
    }

    public Integer getInteger()
    {
        return integer;
    }

    public void setInteger(Integer integer)
    {
        this.integer = integer;
    }

    public String getString()
    {
        return string;
    }

    public void setString(String string)
    {
        this.string = string;
    }

    public Locale getLocale()
    {
        return locale;
    }

    public void setLocale(Locale locale)
    {
        this.locale = locale;
    }

    public List<XStreamElement> getElements()
    {
        return elements;
    }

    public void setElements(final List<XStreamElement> elements)
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

    @XStreamAlias("int-p")
    private int intp;
    @XStreamAlias("integer")
    private Integer integer;
    @XStreamAlias("string")
    private String string;
    @XStreamAlias("locale")
    @XStreamAsAttribute
    private Locale locale;
    @XStreamAlias("myList")
    @XStreamConverter(CollectionConverter.class)
    private List<XStreamElement> elements;
    @XStreamAlias("myMap")
    // TODO @XStreamConverter(XstreamSerializer.CustomMapConverter.class)
    @XStreamOmitField
    private Map<String, String> map;

}
