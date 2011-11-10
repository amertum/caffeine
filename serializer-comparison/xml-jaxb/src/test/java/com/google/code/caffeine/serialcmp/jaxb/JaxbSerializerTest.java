package com.google.code.caffeine.serialcmp.jaxb;

import com.google.code.caffeine.serialcmp.AbstractSerializerTest;
import com.google.code.caffeine.serialcmp.Serializer;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class JaxbSerializerTest
        extends AbstractSerializerTest
{

    public JaxbSerializerTest()
    {
        super("/basic-fields.xml");
    }

    @Override
    protected Serializer getSerializer() {
        return new JaxbSerializer();
    }

    @Test
    public void testSerialization() {
        final JaxbDocument document = new JaxbDocument();
        document.setIntp(5);
        document.setInteger(10);
        document.setString("string");
        document.setLocale(Locale.ENGLISH);

        JaxbElement element = new JaxbElement();
        element.setId("id");
        element.setText("text");

        document.setElements(new ArrayList(Arrays.asList(element)));
        document.setMap(new HashMap(ImmutableMap.of("key1", "value1")));

        System.out.println(this.getSerializer().serialize(document));
    }

}
