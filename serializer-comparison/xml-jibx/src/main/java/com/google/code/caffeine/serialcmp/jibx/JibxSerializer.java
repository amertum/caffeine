package com.google.code.caffeine.serialcmp.jibx;

import com.google.code.caffeine.serialcmp.Serializer;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;

import java.io.StringReader;
import java.io.StringWriter;

public class JibxSerializer implements Serializer<JibxDocument> {

    @Override
    public String serialize(JibxDocument document) {
        try {
            IBindingFactory bindingFactory = BindingDirectory.getFactory(JibxDocument.class);
            IMarshallingContext context = bindingFactory.createMarshallingContext();
            context.setIndent(4);

            StringWriter stringWriter = new StringWriter();
            context.marshalDocument(document, "UTF-8", null, stringWriter);

            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JibxDocument deserialize(String xml) {
        try {
            IBindingFactory bindingFactory = BindingDirectory.getFactory(JibxDocument.class);
            IUnmarshallingContext context = bindingFactory.createUnmarshallingContext();

            JibxDocument document = (JibxDocument) context.unmarshalDocument(new StringReader(xml));

            return document;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
