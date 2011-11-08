package com.google.code.caffeine.serialcmp.xmlprotostuff;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;
import com.google.code.caffeine.serialcmp.xmlprotostuff.proto.XmlProtostuffDocument;

public class XmlProtostuffSerializerTest
        extends AbstractSerializerTest<XmlProtostuffDocument>
{

    public XmlProtostuffSerializerTest()
    {
        super("/basic-fields.xml");
    }

    @Override
    protected Serializer<XmlProtostuffDocument> getSerializer() {
        return new XmlProtostuffSerializer();
    }

}
