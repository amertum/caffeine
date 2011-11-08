package com.google.code.caffeine.serialcmp.xmlprotostuff;

import com.dyuproject.protostuff.XmlIOUtil;
import com.google.code.caffeine.serialcmp.Serializer;
import com.google.code.caffeine.serialcmp.xmlprotostuff.proto.XmlProtostuffDocument;
import com.google.common.base.Charsets;

import java.io.StringWriter;

public class XmlProtostuffSerializer
        implements Serializer<XmlProtostuffDocument> {

    @Override
    public String serialize(XmlProtostuffDocument document) {
        try {
            StringWriter writer = new StringWriter();
            XmlIOUtil.writeTo(writer, document, XmlProtostuffDocument.getSchema());

            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XmlProtostuffDocument deserialize(String data) {
        try {
            XmlProtostuffDocument document = XmlProtostuffDocument.getDefaultInstance();
            XmlIOUtil.mergeFrom(data.getBytes(Charsets.UTF_8), document, XmlProtostuffDocument.getSchema());
            
            return document;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
