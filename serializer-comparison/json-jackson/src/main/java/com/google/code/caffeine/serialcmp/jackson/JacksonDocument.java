package com.google.code.caffeine.serialcmp.jackson;

import com.google.code.caffeine.serialcmp.AbstractDocument;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
import java.util.Locale;
import java.util.Map;

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

    public List<JacksonElement> getElements()
    {
        return this.elements;
    }

    public void setElements(final List<JacksonElement> elements)
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

    @JsonProperty("int-p")
    private int intp;
    @JsonProperty("integer")
    private Integer integer;
    @JsonProperty("string")
    private String string;
    @JsonProperty("locale")
    private Locale locale;
    @JsonProperty("myList")
    private List<JacksonElement> elements;
    @JsonProperty("myMap")
    private Map<String, String> map;

}
