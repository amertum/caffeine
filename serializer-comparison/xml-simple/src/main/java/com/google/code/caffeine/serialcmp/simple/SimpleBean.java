package com.google.code.caffeine.serialcmp.simple;

import com.google.code.caffeine.serialcmp.model.Bean;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "bean")
public class SimpleBean implements Bean {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Attribute(name = "name")
    private String name;

}
