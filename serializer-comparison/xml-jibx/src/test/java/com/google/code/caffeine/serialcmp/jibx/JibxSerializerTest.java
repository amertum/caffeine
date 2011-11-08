package com.google.code.caffeine.serialcmp.jibx;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;

public class JibxSerializerTest
        extends AbstractSerializerTest
{

    public JibxSerializerTest()
    {
        super("/basic-fields.xml");
    }

    @Override
    protected Serializer getSerializer() {
        return new JibxSerializer();
    }

}
