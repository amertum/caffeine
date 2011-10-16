package com.google.code.caffeine.serialcmp.jaxb;

import org.apache.commons.lang.LocaleUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Locale;

public class LocaleXmlAdapter extends XmlAdapter<String, Locale> {

    @Override
    public Locale unmarshal(String value) throws Exception {
        return LocaleUtils.toLocale(value);
    }

    @Override
    public String marshal(Locale locale) throws Exception {
        return locale.toString();
    }
}
