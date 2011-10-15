package com.google.code.caffeine.serialcmp.jaxb;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;
import com.google.code.caffeine.serialcmp.model.DocumentFactory;

public class JaxbSerializerTest extends AbstractSerializerTest {

    @Override
    protected Serializer getSerializer() {
        return new JaxbSerializer();
    }

    @Override
    protected DocumentFactory getDocumentFactory() {
        return new JaxbDocumentFactory();
    }

}
