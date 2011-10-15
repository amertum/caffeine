package com.google.code.caffeine.serialcmp.simple;

import com.google.code.caffeine.serialcmp.Serializer;
import com.google.code.caffeine.serialcmp.model.Document;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class SimpleSerializer implements Serializer {

    @Override
    public String serialize(Document document) {
        try {
            Strategy strategy = new AnnotationStrategy();
            Persister serializer = new Persister(strategy);

            StringWriter stringWriter = new StringWriter();
            serializer.write(document, stringWriter);

            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Document deserialize(String xml) {
        try {
            Strategy strategy = new AnnotationStrategy();
            Persister serializer = new Persister(strategy);

            Document document = serializer.read(SimpleDocument.class, new StringReader(xml));

            return document;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
