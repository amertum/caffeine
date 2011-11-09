package com.google.code.caffeine.serialcmp.jibx;

import com.google.code.caffeine.serialcmp.Serializer;
import com.google.common.base.Throwables;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

import java.io.StringReader;
import java.io.StringWriter;

public class JibxSerializer implements Serializer<JibxDocument> {

    public JibxSerializer()
    {
        try {
            this.bindingFactory = BindingDirectory.getFactory(JibxDocument.class);
            this.marshallingContext = this.bindingFactory.createMarshallingContext();
            this.unmarshallingContext = this.bindingFactory.createUnmarshallingContext();
        }
        catch (JiBXException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public String serialize(JibxDocument document) {
        try {
            this.marshallingContext.setIndent(4);

            StringWriter stringWriter = new StringWriter();
            this.marshallingContext.marshalDocument(document, "UTF-8", null, stringWriter);

            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JibxDocument deserialize(String data) {
        try {

            JibxDocument document = (JibxDocument) this.unmarshallingContext.unmarshalDocument(new StringReader(data));

            return document;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final IBindingFactory bindingFactory;
    private final IMarshallingContext marshallingContext;
    private final IUnmarshallingContext unmarshallingContext;

}
