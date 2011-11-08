package com.google.code.caffeine.serialcmp.simple;

import com.google.code.caffeine.serialcmp.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.io.StringReader;
import java.io.StringWriter;

public class SimpleSerializer implements Serializer<SimpleDocument> {

    @Override
    public String serialize(SimpleDocument document) {
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
    public SimpleDocument deserialize(String data) {
        try {
            Strategy strategy = new AnnotationStrategy();
            Persister serializer = new Persister(strategy);

            SimpleDocument document = serializer.read(SimpleDocument.class, new StringReader(data));

            return document;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
