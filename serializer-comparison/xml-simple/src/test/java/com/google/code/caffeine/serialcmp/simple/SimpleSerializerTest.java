package com.google.code.caffeine.serialcmp.simple;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;

public class SimpleSerializerTest
        extends AbstractSerializerTest<SimpleDocument>
{

    public SimpleSerializerTest()
    {
        super("/basic-fields.xml");
    }

    @Override
    protected Serializer<SimpleDocument> getSerializer() {
        return new SimpleSerializer();
    }

}
