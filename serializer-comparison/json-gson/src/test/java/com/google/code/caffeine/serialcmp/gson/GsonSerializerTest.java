package com.google.code.caffeine.serialcmp.gson;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;

public class GsonSerializerTest
        extends AbstractSerializerTest<GsonDocument>
{

    public GsonSerializerTest()
    {
        super("/basic-fields.json");
    }

    @Override
    protected Serializer<GsonDocument> getSerializer() {
        return new GsonSerializer();
    }

}
