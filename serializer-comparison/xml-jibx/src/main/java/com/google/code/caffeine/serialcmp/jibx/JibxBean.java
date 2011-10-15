package com.google.code.caffeine.serialcmp.jibx;

import com.google.code.caffeine.serialcmp.model.Bean;

public class JibxBean implements Bean {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
