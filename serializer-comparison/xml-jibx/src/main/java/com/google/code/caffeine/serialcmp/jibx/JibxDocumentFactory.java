package com.google.code.caffeine.serialcmp.jibx;

import com.google.code.caffeine.serialcmp.model.Bean;
import com.google.code.caffeine.serialcmp.model.Document;
import com.google.code.caffeine.serialcmp.model.DocumentFactory;

public class JibxDocumentFactory implements DocumentFactory {
    @Override
    public Document createDocument() {
        return new JibxDocument();
    }

    @Override
    public Bean createBean() {
        return new JibxBean();
    }
}
