package com.google.code.caffeine.serialcmp.jaxb;

import com.google.code.caffeine.serialcmp.model.Bean;

public class JaxbBean implements Bean {

    public JaxbBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
