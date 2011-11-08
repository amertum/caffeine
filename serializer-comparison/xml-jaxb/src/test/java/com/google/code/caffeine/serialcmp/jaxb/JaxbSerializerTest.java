package com.google.code.caffeine.serialcmp.jaxb;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;

public class JaxbSerializerTest
        extends AbstractSerializerTest
{

    public JaxbSerializerTest()
    {
        super("/basic-fields.xml");
    }

    @Override
    protected Serializer getSerializer() {
        return new JaxbSerializer();
    }

}
