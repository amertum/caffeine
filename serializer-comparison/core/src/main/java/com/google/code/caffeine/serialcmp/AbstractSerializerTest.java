package com.google.code.caffeine.serialcmp;

import com.google.code.caffeine.serialcmp.model.Document;
import com.google.code.caffeine.serialcmp.model.DocumentFactory;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public abstract class AbstractSerializerTest {

    protected abstract Serializer getSerializer();

    protected abstract DocumentFactory getDocumentFactory();

    @Test
    public void testSerializeNull() throws Exception {
        final String xml = getSerializer().serialize(DocumentProducer.createNull(getDocumentFactory()));
        System.out.println(xml);
    }

    @Test
    public void testDeserializeNull() throws Exception {
        final Document document = getSerializer().deserialize(XmlFactory.createNull());
        assertEquals(0, document.getIntp());
        assertEquals(null, document.getString());
        assertEquals("string-required", document.getStringRequired());
    }


    @Test
    public void testSerializeEmpty() throws Exception {
        final String xml = getSerializer().serialize(DocumentProducer.createEmpty(getDocumentFactory()));
        System.out.println(xml);
    }

    @Test
    public void testDeserializeEmpty() throws Exception {
        final Document document = getSerializer().deserialize(XmlFactory.createEmpty());
        assertEquals(0, document.getIntp());
        assertEquals(null, document.getString());
        assertEquals("string-required", document.getStringRequired());
    }


    @Test
    public void testSerializeOne() throws Exception {
        final String xml = getSerializer().serialize(DocumentProducer.createOne(getDocumentFactory()));
        System.out.println(xml);
    }

    @Test
    public void testDeserializeOne() throws Exception {
        final Document document = getSerializer().deserialize(XmlFactory.createOne());
        assertEquals(5, document.getIntp());
        assertEquals("string", document.getString());
        assertEquals("string-required", document.getStringRequired());
    }

    @Test
    public void testSerializeMany() throws Exception {
        final String xml = getSerializer().serialize(DocumentProducer.createMany(getDocumentFactory()));
        System.out.println(xml);
    }

    @Test
    public void testDeserializeMany() throws Exception {
        final Document document = getSerializer().deserialize(XmlFactory.createMany());
        assertEquals(5, document.getIntp());
        assertEquals("string", document.getString());
        assertEquals("string-required", document.getStringRequired());
    }


}
