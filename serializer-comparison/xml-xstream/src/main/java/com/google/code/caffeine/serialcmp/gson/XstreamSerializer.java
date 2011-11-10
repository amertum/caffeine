package com.google.code.caffeine.serialcmp.gson;

import com.google.code.caffeine.serialcmp.Serializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class XstreamSerializer
        implements Serializer<XstreamDocument>
{

    public XstreamSerializer()
    {
        this.stream = new XStream() {

            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next)
            {
                return new MapperWrapper(next) {

                    @Override
                    public boolean shouldSerializeMember(
                            Class definedIn,
                            String fieldName)
                    {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };

        this.stream.processAnnotations(XstreamDocument.class);
    }

    @Override
    public String serialize(XstreamDocument document)
    {
        try {
            StringWriter stringWriter = new StringWriter();
            this.stream.toXML(document, stringWriter);

            return stringWriter.toString();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public XstreamDocument deserialize(String data)
    {
        try {
            XstreamDocument document = (XstreamDocument) this.stream.fromXML(new StringReader(data));

            return document;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final XStream stream;

    public static class CustomMapConverter
            extends AbstractCollectionConverter
    {

        public CustomMapConverter(Mapper mapper)
        {
            super(mapper);
        }

        public boolean canConvert(Class type)
        {
            return type.equals(HashMap.class) || type.equals(Hashtable.class) ||
                   type.getName().equals("java.util.LinkedHashMap") ||
                   // Used by java.awt.Font in JDK 6
                   type.getName().equals("sun.font.AttributeMap");
        }

        public void marshal(
                Object source,
                HierarchicalStreamWriter writer,
                MarshallingContext context)
        {
            Map map = (Map) source;
            for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry entry = (Map.Entry) iterator.next();
                writer.startNode(entry.getKey().toString());
                writer.setValue(entry.getValue().toString());
                writer.endNode();
            }
        }

        public Object unmarshal(
                HierarchicalStreamReader reader,
                UnmarshallingContext context)
        {
            Map map = (Map) createCollection(context.getRequiredType());
            populateMap(reader, context, map);
            return map;
        }

        protected void populateMap(
                HierarchicalStreamReader reader,
                UnmarshallingContext context,
                Map map)
        {
            populateMap(reader, context, map, map);
        }

        protected void populateMap(
                HierarchicalStreamReader reader,
                UnmarshallingContext context,
                Map map,
                Map target)
        {
            while (reader.hasMoreChildren()) {
                reader.moveDown();
                putCurrentEntryIntoMap(reader, context, map, target);
                reader.moveUp();
            }
        }

        protected void putCurrentEntryIntoMap(
                HierarchicalStreamReader reader,
                UnmarshallingContext context,
                Map map,
                Map target)
        {
            reader.moveDown();
            Object key = readItem(reader, context, map);
            reader.moveUp();

            reader.moveDown();
            Object value = readItem(reader, context, map);
            reader.moveUp();

            target.put(key, value);
        }

    }

}
