package com.google.code.caffeine.serialcmp.xstream;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;

public class XstreamSerializerTest extends AbstractSerializerTest<XstreamDocument> {

    @Override
    protected Serializer<XstreamDocument> getSerializer() {
        return new XstreamSerializer();
    }

}
