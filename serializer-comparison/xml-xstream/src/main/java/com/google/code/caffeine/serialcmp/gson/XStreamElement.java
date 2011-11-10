package com.google.code.caffeine.serialcmp.gson;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("element")
public class XStreamElement {

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
