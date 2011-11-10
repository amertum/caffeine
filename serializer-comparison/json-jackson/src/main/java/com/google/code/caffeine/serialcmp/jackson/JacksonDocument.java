package com.google.code.caffeine.serialcmp.jackson;

import com.google.code.caffeine.serialcmp.AbstractDocument;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Locale;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JacksonDocument
        extends AbstractDocument
{

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

    @JsonProperty("int-p")
    private int intp;
    @JsonProperty("integer")
    private Integer integer;
    @JsonProperty("string")
    private String string;
    @JsonProperty("locale")
    private Locale locale;

}
