package com.google.code.caffeine.serialcmp.jackson;

import com.google.code.caffeine.serialcmp.Serializer;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.StringWriter;

public class JacksonSerializer
        implements Serializer<JacksonDocument> {

    @Override
    public String serialize(JacksonDocument document) {
        try {
            final StringWriter writer = new StringWriter();
            this.mapper.writeValue(writer, document);

            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JacksonDocument deserialize(String data) {
        try {
            final JacksonDocument document = this.mapper.readValue(data, JacksonDocument.class);
            
            return document;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final ObjectMapper mapper = new ObjectMapper();

}
