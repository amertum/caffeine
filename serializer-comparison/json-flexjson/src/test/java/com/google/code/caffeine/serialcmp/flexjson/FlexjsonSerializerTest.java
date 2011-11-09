package com.google.code.caffeine.serialcmp.flexjson;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;

public class FlexjsonSerializerTest
        extends AbstractSerializerTest<FlexjsonDocument>
{

    public FlexjsonSerializerTest()
    {
        super("/basic-fields-nodash.json");
    }

    @Override
    protected Serializer<FlexjsonDocument> getSerializer() {
        return new FlexjsonSerializer();
    }

}
