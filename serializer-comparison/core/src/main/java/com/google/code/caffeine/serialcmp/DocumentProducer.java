package com.google.code.caffeine.serialcmp;

import com.google.code.caffeine.serialcmp.model.*;

import java.util.*;

public class DocumentProducer {

    public static Document createNull(DocumentFactory documentFactory) {
        final Document document = documentFactory.createDocument();

        document.setIntp(0);
        document.setInteger(null);
        document.setObject(null);
        document.setString(null);
        document.setStringRequired("string-required");
        document.setDate(null);
        document.setLocale(null);
        document.setUuid(null);
        document.setCurrency(null);
        document.setEnumValue(null);

        document.setBean(null);

        document.setIterable(null);
        document.setCollection(null);
        document.setList(null);
        document.setSet(null);
        document.setMap(null);

        return document;
    }

    public static Document createEmpty(DocumentFactory documentFactory) {
        final Document document = documentFactory.createDocument();

        document.setIntp(0);
        document.setInteger(0);
        document.setObject(new Object());
        document.setString("");
        document.setStringRequired("string-required");
        document.setDate(new Date());
        document.setLocale(Locale.ROOT);
        document.setUuid(UUID.randomUUID());
        document.setCurrency(Currency.getInstance(Locale.FRANCE));
        document.setEnumValue(EnumValue.VALUE_A);

        final Bean bean1 = documentFactory.createBean();
        bean1.setName("name1");
        document.setBean(bean1);

        document.setIterable(new ArrayList());
        document.setCollection(new ArrayList());
        document.setList(new ArrayList());
        document.setSet(new HashSet<Bean>(new ArrayList()));
        document.setMap(new HashMap<String, Bean>());

        return document;
    }

    public static Document createOne(DocumentFactory documentFactory) {
        final Document document = documentFactory.createDocument();

        document.setIntp(5);
        document.setInteger(Integer.valueOf(10));
        document.setObject(new Object());
        document.setString("string");
        document.setStringRequired("string-required");
        document.setDate(new Date());
        document.setLocale(Locale.FRENCH);
        document.setUuid(UUID.randomUUID());
        document.setCurrency(Currency.getInstance(Locale.FRANCE));
        document.setEnumValue(EnumValue.VALUE_A);

        final Bean bean1 = documentFactory.createBean();
        bean1.setName("name1");
        final Bean bean2 = documentFactory.createBean();
        bean2.setName("name2");
        document.setBean(bean1);

        document.setIterable(new ArrayList(Arrays.asList(bean1)));
        document.setCollection(new ArrayList(Arrays.asList(bean1)));
        document.setList(new ArrayList(Arrays.asList(bean1)));
        document.setSet(new HashSet<Bean>(new ArrayList(Arrays.asList(bean1))));
        final HashMap<String, Bean> mapOne = new HashMap<String, Bean>();
        mapOne.put("key1", bean1);
        document.setMap(mapOne);

        return document;
    }

    public static Document createMany(DocumentFactory documentFactory) {
        final Document document = documentFactory.createDocument();

        document.setIntp(5);
        document.setInteger(Integer.valueOf(10));
        document.setObject(new Object());
        document.setString("string");
        document.setStringRequired("string-required");
        document.setDate(new Date());
        document.setLocale(Locale.FRENCH);
        document.setUuid(UUID.randomUUID());
        document.setCurrency(Currency.getInstance(Locale.FRANCE));
        document.setEnumValue(EnumValue.VALUE_A);

        final Bean bean1 = documentFactory.createBean();
        bean1.setName("name1");
        final Bean bean2 = documentFactory.createBean();
        bean2.setName("name2");
        document.setBean(bean1);

        document.setIterable(new ArrayList(Arrays.asList(bean1, bean2)));
        document.setCollection(new ArrayList(Arrays.asList(bean1, bean2)));
        document.setList(new ArrayList(Arrays.asList(bean1, bean2)));
        document.setSet(new HashSet<Bean>(new ArrayList(Arrays.asList(bean1, bean2))));
        final HashMap<String, Bean> mapMany = new HashMap<String, Bean>();
        mapMany.put("key1", bean1);
        mapMany.put("key2", bean2);
        document.setMap(mapMany);

        return document;
    }

}
