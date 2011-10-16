package com.google.code.caffeine.serialcmp.xstream;

import com.google.code.caffeine.serialcmp.Serializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.StringReader;
import java.io.StringWriter;

public class XstreamSerializer implements Serializer<XstreamDocument> {

    @Override
    public String serialize(XstreamDocument document) {
        try {
            XStream stream = new XStream();
            stream.processAnnotations(XstreamDocument.class);

            StringWriter stringWriter = new StringWriter();
            stream.toXML(document, stringWriter);

            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XstreamDocument deserialize(String xml) {
        try {
            XStream stream = new XStream(); // TODO try other parser
            stream.processAnnotations(XstreamDocument.class);

            XstreamDocument document = (XstreamDocument) stream.fromXML(new StringReader(xml));

            return document;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
