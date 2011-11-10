package com.google.code.caffeine.serialcmp.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "element")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbElement {

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

    @XmlAttribute(name = "id")
    private String id;
    @XmlElement(name = "text")
    private String text;

}
