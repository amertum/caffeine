package com.google.code.caffeine.serialcmp.jaxb;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;

public class JaxbSerializerTest extends AbstractSerializerTest {

    @Override
    protected Serializer getSerializer() {
        return new JaxbSerializer();
    }

}
