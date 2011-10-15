package com.google.code.caffeine.serialcmp;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.google.common.io.Resources;

import java.io.IOException;

public class XmlFactory {

    public static String createNull() {
        try {
            return Resources.toString(Resources.getResource(XmlFactory.class, "/null.xml"), Charsets.UTF_8);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static String createEmpty() {
        try {
            return Resources.toString(Resources.getResource(XmlFactory.class, "/empty.xml"), Charsets.UTF_8);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static String createOne() {
        try {
            return Resources.toString(Resources.getResource(XmlFactory.class, "/one.xml"), Charsets.UTF_8);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static String createMany() {
        try {
            return Resources.toString(Resources.getResource(XmlFactory.class, "/many.xml"), Charsets.UTF_8);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

}
