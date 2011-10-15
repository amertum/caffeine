package com.google.code.caffeine.serialcmp.jaxb;

import com.google.code.caffeine.serialcmp.model.*;

public class JaxbDocumentFactory implements DocumentFactory {
    @Override
    public Document createDocument() {
        return new JaxbDocument();
    }

    @Override
    public Bean createBean() {
        return new JaxbBean();
    }
}
