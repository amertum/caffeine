package com.google.code.caffeine.serialcmp.simple;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;
import com.google.code.caffeine.serialcmp.model.DocumentFactory;

public class SimpleSerializerTest extends AbstractSerializerTest {

    @Override
    protected Serializer getSerializer() {
        return new SimpleSerializer();
    }

    @Override
    protected DocumentFactory getDocumentFactory() {
        return new SimpleDocumentFactory();
    }

}
