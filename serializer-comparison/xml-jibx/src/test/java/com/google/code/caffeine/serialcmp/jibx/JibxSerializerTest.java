package com.google.code.caffeine.serialcmp.jibx;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;
import com.google.code.caffeine.serialcmp.model.DocumentFactory;

public class JibxSerializerTest extends AbstractSerializerTest {

    @Override
    protected Serializer getSerializer() {
        return new JibxSerializer();
    }

    @Override
    protected DocumentFactory getDocumentFactory() {
        return new JibxDocumentFactory();
    }

}
