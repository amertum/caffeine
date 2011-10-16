package com.google.code.caffeine.serialcmp;

public interface Serializer<Type> {

    String serialize(Type document);

    Type deserialize(String xml);

}
