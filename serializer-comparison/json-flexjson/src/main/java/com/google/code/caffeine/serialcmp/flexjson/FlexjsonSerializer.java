package com.google.code.caffeine.serialcmp.flexjson;

import com.google.code.caffeine.serialcmp.Serializer;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.ObjectBinder;
import flexjson.ObjectFactory;
import flexjson.transformer.AbstractTransformer;
import org.apache.commons.lang.LocaleUtils;

import java.lang.reflect.Type;
import java.util.Locale;

public class FlexjsonSerializer
        implements Serializer<FlexjsonDocument>
{

    @Override
    public String serialize(FlexjsonDocument document)
    {
        try {
            return this.serializer.exclude("*.class").transform(new LocaleTransformer(), Locale.class)
                                  .serialize(document);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FlexjsonDocument deserialize(String data)
    {
        try {
            return this.deserializer.use("locale", new LocaleObjectFactory()).deserialize(data, FlexjsonDocument.class);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final JSONSerializer serializer = new JSONSerializer();
    private final JSONDeserializer<FlexjsonDocument> deserializer = new JSONDeserializer<FlexjsonDocument>();

    public static class LocaleTransformer
            extends AbstractTransformer
    {

        @Override
        public void transform(
                final Object object)
        {
            final Locale locale = (Locale) object;
            this.getContext().write(locale.toString());
        }

    }

    public static class LocaleObjectFactory
            implements ObjectFactory
    {

        public Object instantiate(
                final ObjectBinder context,
                final Object value,
                final Type targetType,
                final Class targetClass)
        {
            return LocaleUtils.toLocale(value.toString());
        }

    }

}
