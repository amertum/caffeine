package com.google.code.caffeine.serialcmp.gson;

import com.google.code.caffeine.serialcmp.Serializer;
import com.google.gson.Gson;

public class GsonSerializer
        implements Serializer<GsonDocument> {

    @Override
    public String serialize(GsonDocument document) {
        try {
            return new Gson().toJson(document);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GsonDocument deserialize(String data) {
        try {
            final GsonDocument document = new Gson().fromJson(data, GsonDocument.class);

            return document;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
