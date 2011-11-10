package com.google.code.caffeine.serialcmp.gson;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class XStreamSerializerTest
        extends AbstractSerializerTest<XstreamDocument>
{

    public XStreamSerializerTest()
    {
        super("/basic-fields.xml");
    }

    @Override
    protected Serializer<XstreamDocument> getSerializer() {
        return new XstreamSerializer();
    }

    @Test
    public void testSerialization() {
        final XstreamDocument document = new XstreamDocument();
        document.setIntp(5);
        document.setInteger(10);
        document.setString("string");
        document.setLocale(Locale.ENGLISH);

        XStreamElement element = new XStreamElement();
        element.setId("id");
        element.setText("text");

        document.setElements(new ArrayList(Arrays.asList(element)));
        document.setMap(new HashMap(ImmutableMap.of("key1", "value1")));

        System.out.println(this.getSerializer().serialize(document));
    }

}
