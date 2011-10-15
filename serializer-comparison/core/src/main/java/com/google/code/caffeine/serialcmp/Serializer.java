package com.google.code.caffeine.serialcmp;

import com.google.code.caffeine.serialcmp.model.Document;

public interface Serializer {

    String serialize(Document document);

    Document deserialize(String xml);

}
