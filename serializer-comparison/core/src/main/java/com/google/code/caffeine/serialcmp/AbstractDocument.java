package com.google.code.caffeine.serialcmp;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Locale;

public abstract class AbstractDocument {

    public abstract int getIntp();

    public abstract void setIntp(int intp);

    public abstract Integer getInteger();

    public abstract void setInteger(Integer integer);

    public abstract String getString();

    public abstract void setString(String string);

    public abstract Locale getLocale();

    public abstract void setLocale(Locale locale);

}
