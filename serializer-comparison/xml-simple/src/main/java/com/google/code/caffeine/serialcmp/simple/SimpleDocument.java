package com.google.code.caffeine.serialcmp.simple;

import com.google.code.caffeine.serialcmp.AbstractDocument;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.Locale;
import java.util.UUID;

@Root(name = "document")
public class SimpleDocument extends AbstractDocument {

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

    @Element(name = "int-p")
    protected int intp;
    @Element(name = "integer")
    protected Integer integer;
    @Element(name = "string")
    protected String string;
    @Attribute(name = "locale")
    protected Locale locale;


    public static class UUIDConverter implements Converter<UUID> {

        public UUID read(InputNode node) throws Exception {
            return UUID.fromString(node.getValue());
        }

        public void write(OutputNode node, UUID uuid) {
            node.setValue(uuid.toString());
        }
    }

}
