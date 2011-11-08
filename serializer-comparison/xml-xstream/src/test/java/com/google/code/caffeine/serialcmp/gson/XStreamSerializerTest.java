package com.google.code.caffeine.serialcmp.gson;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;

public class XStreamSerializerTest
        extends AbstractSerializerTest<XstreamDocument>
{

    public XStreamSerializerTest()
    {
        super("/basic-fields.xml");
    }

    @Override
    protected Serializer<XstreamDocument> getSerializer() {
        return new XstreamSerializer();
    }

}
