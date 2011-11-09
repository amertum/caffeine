package com.google.code.caffeine.serialcmp.gson;

import com.google.code.caffeine.serialcmp.Serializer;
import com.thoughtworks.xstream.XStream;

import java.io.StringReader;
import java.io.StringWriter;

public class XstreamSerializer implements Serializer<XstreamDocument> {

    public XstreamSerializer()
    {
        this.stream = new XStream();
        this.stream.processAnnotations(XstreamDocument.class);
    }

    @Override
    public String serialize(XstreamDocument document) {
        try {
            StringWriter stringWriter = new StringWriter();
            this.stream.toXML(document, stringWriter);

            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XstreamDocument deserialize(String data) {
        try {
            XstreamDocument document = (XstreamDocument) this.stream.fromXML(new StringReader(data));

            return document;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final XStream stream;

}
