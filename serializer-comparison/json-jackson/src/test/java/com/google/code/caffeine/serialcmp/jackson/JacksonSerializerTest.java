package com.google.code.caffeine.serialcmp.jackson;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;

public class JacksonSerializerTest
        extends AbstractSerializerTest<JacksonDocument>
{

    public JacksonSerializerTest()
    {
        super("/basic-fields.json");
    }

    @Override
    protected Serializer<JacksonDocument> getSerializer() {
        return new JacksonSerializer();
    }

}
