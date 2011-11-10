package com.google.code.caffeine.serialcmp.jackson;

import org.codehaus.jackson.annotate.JsonProperty;

public class JacksonElement {

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

    @JsonProperty("id")
    private String id;
    @JsonProperty("text")
    private String text;

}
