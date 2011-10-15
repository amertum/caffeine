package com.google.code.caffeine.serialcmp.simple;

import com.google.code.caffeine.serialcmp.model.Bean;
import com.google.code.caffeine.serialcmp.model.Document;
import com.google.code.caffeine.serialcmp.model.DocumentFactory;

public class SimpleDocumentFactory implements DocumentFactory {
    @Override
    public Document createDocument() {
        return new SimpleDocument();
    }

    @Override
    public Bean createBean() {
        return new SimpleBean();
    }
}
