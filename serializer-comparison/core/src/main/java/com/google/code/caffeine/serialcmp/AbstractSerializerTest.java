package com.google.code.caffeine.serialcmp;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.javasimon.Simon;
import org.javasimon.SimonManager;
import org.javasimon.Split;
import org.javasimon.Stopwatch;
import org.javasimon.utils.AbstractDataCollector;
import org.javasimon.utils.GoogleChartGenerator;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

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
        String stackStr = this.getClass().getSimpleName().substring(0, index); // + "." + stack.getMethodName().substring(4);

        final Serializer<DocumentType> serializer = this.getSerializer();

        //List<Stopwatch> stopwatchs = Lists.newArrayList();
        for (int step = 0; step < MAXS.length; step++) {

            Stopwatch stopwatch = SimonManager.getStopwatch(stackStr + "-" + MAXS[step]);
            if (step != 0) {
                //if (step == MAXS.length - 1) {
                stopwatchs.add(stopwatch);
            }

            for (int i = 0; i < MAXS[step]; i++) {
                Split split = stopwatch.start();

                final DocumentType object = serializer.deserialize(this.createData(this.getResourceName()));
                if (object instanceof AbstractDocument) {
                    final AbstractDocument document = (AbstractDocument) object;

                    assertEquals(Locale.FRENCH, document.getLocale());
                    assertEquals(5, document.getIntp());
                    assertEquals(Integer.valueOf(10), document.getInteger());
                    assertEquals("string", document.getString());
                }
                final String xml = serializer.serialize(object);
                split.stop();

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

    @AfterClass
    public static void barChart()
    {
        final AbstractDataCollector collector = new AbstractDataCollector(Iterables.toArray(stopwatchs, Simon.class)) {
            public double obtainValue(Simon simon)
            {
                return ((Stopwatch) simon).getMean();
            }
        };
        collector.collect();

        String chartUrl = GoogleChartGenerator.barChart(collector, "basic fields mean", 1000000, "ms");
        System.out.println("chartUrl: " +
                           chartUrl.replace("chs=600x300&cht=bvg&chbh=32,10,60&chco=4d89f9,c6d9fd&chxt=x,x,y", "chs=1000x200&cht=bvg&chbh=20,10,55&chco=4D89D9|8D89D9|BD89D9&chxt=y,y,x"));
        System.out.println("chartUrl: " +
                           chartUrl.replace("chs=600x300&cht=bvg&chbh=32,10,60&chco=4d89f9,c6d9fd&chxt=x,x,y", "chs=300x450&cht=bhg&chbh=10,5,10&chco=4D89D9|8D89D9|BD89D9&chxt=y,y,x"));
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

    private static final int[] MAXS = {1, 10, 100, 1000};

}
