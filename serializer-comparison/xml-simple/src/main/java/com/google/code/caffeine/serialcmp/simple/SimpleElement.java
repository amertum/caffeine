package com.google.code.caffeine.serialcmp.simple;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class SimpleElement {

    public String getId()
    {
        return this.id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public String getText()
    {
        return this.text;
    }

    public void setText(final String text)
    {
        this.text = text;
    }

    @Attribute(name = "id")
    private String id;
    @Element(name="text")
    private String text;

}
