package com.google.code.caffeine.serialcmp;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.apache.commons.lang.LocaleUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.javasimon.Simon;
import org.javasimon.SimonManager;
import org.javasimon.Split;
import org.javasimon.Stopwatch;
import org.javasimon.utils.AbstractDataCollector;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static com.google.common.collect.Iterables.getLast;
import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Ordering.from;
import static com.google.common.collect.Ordering.natural;
import static com.google.common.io.Resources.getResource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public abstract class AbstractSerializerTest<DocumentType> {

    protected AbstractSerializerTest(final String resourceName)
    {
        this.resourceName = resourceName;
    }

    protected abstract Serializer<DocumentType> getSerializer();

    @Test
    public void testBasicFields()
            throws Exception
    {
        StackTraceElement stack = Thread.currentThread().getStackTrace()[1];
        int index = this.getClass().getSimpleName().indexOf("SerializerTest");
        String stackStr = this.getClass().getSimpleName()
                              .substring(0, index); // + "." + stack.getMethodName().substring(4);

        final Serializer<DocumentType> serializer = this.getSerializer();

        //List<Stopwatch> stopwatchs = Lists.newArrayList();
        for (int step = 0; step < MAXS.length; step++) {

            Stopwatch stopwatch = SimonManager.getStopwatch(stackStr + "-" + MAXS[step]);
            if (step != 0) {
                //if (step == MAXS.length - 1) {
                stopwatchs.add(stopwatch);
            }

            for (int i = 0; i < MAXS[step]; i++) {
                final Map<String, String> dataMap = randomData();
                final String dataXml = makeXml(dataMap);
                final String dataJson = makeJson(dataMap, "int-p");

                final Map<String, String> strategy = ImmutableMap.<String, String>builder().put("Simple", dataXml)
                                                                 .put("Jaxb", dataXml).put("XStream", dataXml)
                                                                 .put("Jibx", dataXml).put("Gson", dataJson)
                                                                 .put("Jackson", dataJson).build();
                final String data = strategy.get(stackStr);
                //final String data = this.createData(this.getResourceName());

                Split split = stopwatch.start();

                final DocumentType object = serializer.deserialize(data);
                final String xml = serializer.serialize(object);
                split.stop();

                if (object instanceof AbstractDocument) {
                    final AbstractDocument document = (AbstractDocument) object;

                    assertEquals(LocaleUtils.toLocale(dataMap.get("locale")), document.getLocale());
                    assertEquals(Integer.parseInt(dataMap.get("intp")), document.getIntp());
                    assertEquals(Integer.decode(dataMap.get("integer")), document.getInteger());
                    assertEquals(dataMap.get("string"), document.getString());
                }

                if (MAXS[step] == 1) {
                    System.out.println(xml);
                }
            }

            System.out.println("Result: " + stopwatch);
        }
    }

    private String getResourceName()
    {
        return this.resourceName;
    }

    private String resourceName;

    private static final List<Stopwatch> stopwatchs = Lists.newArrayList();

    private static Map<String, String> randomData()
    {
        final String intp = RandomStringUtils.random(5, "123456789");
        final String integer = RandomStringUtils.random(5, "123456789");
        final String string = RandomStringUtils.randomAlphanumeric(10);
        final Locale[] locales = new Locale[]{Locale.FRENCH, Locale.ENGLISH, Locale.CHINESE, Locale.GERMAN,
                                              Locale.ITALIAN, Locale.KOREAN, Locale.JAPANESE};
        final String locale = locales[RandomUtils.nextInt(locales.length)].toString();

        return ImmutableMap.of("intp", intp, "integer", integer, "string", string, "locale", locale);
    }

    private static String makeXml(
            final Map<String, String> data)
    {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><document locale=\"" + data.get("locale") + "\"><int-p>" +
               data.get("intp") + "</int-p><integer>" + data.get("integer") + "</integer><string>" +
               data.get("string") + "</string></document>";
    }

    private static String makeJson(
            final Map<String, String> data,
            final String intpName)
    {
        return "{\"locale\":\"" + data.get("locale") + "\",\"int-p\":" + data.get("intp") + ",\"integer\":" +
               data.get("integer") + ",\"string\":\"" + data.get("string") + "\"}";
    }

    @AfterClass
    public static void barChart()
    {
//        final Simon[] simons = toArray(from(new ByNameSimonComparator(false)).compound(new ByNameSimonComparator(true))
//                                               .sortedCopy(stopwatchs), Simon.class);
//        final Simon[] simons = toArray(from(new ByNameSimonComparator(true)).compound(new ByNameSimonComparator(false))
//                                               .sortedCopy(stopwatchs), Simon.class);
        final Simon[] simons = toArray(from(new ByNameSimonComparator(false)).compound(new ByMeanSimonComparator())
                                               .sortedCopy(stopwatchs), Simon.class);

        final AbstractDataCollector collector = new AbstractDataCollector(simons) {
            public double obtainValue(Simon simon)
            {
                return ((Stopwatch) simon).getMean();
            }
        };
        collector.collect();

        String chartUrl = GoogleChartGenerator
                .barChart(collector, "Comparaison librairies serialisation XML JSON", 1000, "us");
        System.out.println("chartUrl: " + chartUrl);
    }

    @Test
    @Ignore
    public void testRequiredOptionalTransient()
            throws Exception
    {
        fail();
    }

    @Test
    @Ignore
    public void testDate()
            throws Exception
    {
        fail();
    }

    @Test
    @Ignore
    public void testLocale()
            throws Exception
    {
        fail();
    }

    @Test
    @Ignore
    public void testHierarchyBeans()
            throws Exception
    {
        fail();
    }

    @Test
    @Ignore
    public void testList()
            throws Exception
    {
        fail();
    }

    @Test
    @Ignore
    public void testMap()
            throws Exception
    {
        fail();
    }

    @Test
    @Ignore
    public void testConstructor()
            throws Exception
    {
        fail();
    }

    @Test
    @Ignore
    public void testCopiedFields()
            throws Exception
    {
        fail();
    }

    public String createData(final String resourceName)
    {
        try {
            return Resources.toString(getResource(this.getClass(), resourceName), Charsets.UTF_8);
        }
        catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static Date date()
    {
        return new Date(2011, 9, 15, 17, 53, 15);
    }

    public static final UUID _UUID = UUID.fromString("338b6eb4-d697-4245-94d8-cb2ba2a27a18");

    private static final int[] MAXS = {1, 1000, 100000};

    private static class ByNameSimonComparator
            implements Comparator<Stopwatch>
    {

        public ByNameSimonComparator(
                final boolean byType)
        {
            this.byType = byType;
        }

        @Override
        public int compare(
                final Stopwatch o1,
                final Stopwatch o2)
        {
            final Iterable<String> s1 = Splitter.on("-").split(o1.getName());
            final Iterable<String> s2 = Splitter.on("-").split(o2.getName());

            final int compare;
            if (this.byType) {
                compare = natural().compare(s1.iterator().next(), s2.iterator().next());
            }
            else {
                compare = natural().compare(getLast(s1), getLast(s2));
            }

            return compare;
        }

        private final boolean byType;

    }

    private static class ByMeanSimonComparator
            implements Comparator<Stopwatch>
    {

        @Override
        public int compare(
                final Stopwatch o1,
                final Stopwatch o2)
        {
            return natural().compare(o1.getMean(), o2.getMean());
        }

    }

}
