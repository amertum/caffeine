package com.google.code.caffeine.serialcmp.gson;

import com.google.code.caffeine.serialcmp.AbstractDocument;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;
import java.util.Locale;

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

    public List<Element> getElements()
    {
        return elements;
    }

    public void setElements(final List<Element> elements)
    {
        this.elements = elements;
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
    private List<Element> elements;

    @XStreamAlias("element")
    public static class Element {
        
        public String getId()
        {
            return id;
        }

        public void setId(final String id)
        {
            this.id = id;
        }

        public String getText()
        {
            return text;
        }

        public void setText(final String text)
        {
            this.text = text;
        }

        @XStreamAlias("id")
        @XStreamAsAttribute
        private String id;
        @XStreamAlias("text")
        private String text;
        
    }

}
