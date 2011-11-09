package com.google.code.caffeine.serialcmp.jaxb;

import com.google.code.caffeine.serialcmp.Serializer;
import com.google.common.base.Throwables;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JaxbSerializer implements Serializer<JaxbDocument> {

    public JaxbSerializer()
    {
        try {
            this.context = JAXBContext.newInstance(JaxbDocument.class);

            this.marshaller = this.context.createMarshaller();
            this.marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            this.unmarshaller = this.context.createUnmarshaller();
        }
        catch (JAXBException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public String serialize(JaxbDocument document) {
        try {
            StringWriter stringWriter = new StringWriter();
            this.marshaller.marshal(document, stringWriter);

            return stringWriter.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JaxbDocument deserialize(String data) {
        try {
            JaxbDocument document = (JaxbDocument) this.unmarshaller.unmarshal(new StringReader(data));

            return document;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private final JAXBContext context;
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

}
