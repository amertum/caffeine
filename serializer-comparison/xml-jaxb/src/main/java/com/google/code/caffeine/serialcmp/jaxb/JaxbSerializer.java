package com.google.code.caffeine.serialcmp.jaxb;

import com.google.code.caffeine.serialcmp.Serializer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JaxbSerializer implements Serializer<JaxbDocument> {

    @Override
    public String serialize(JaxbDocument document) {
        try {
            JAXBContext context = JAXBContext.newInstance(JaxbDocument.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(document, stringWriter);

            return stringWriter.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JaxbDocument deserialize(String data) {
        try {
            JAXBContext context = JAXBContext.newInstance(JaxbDocument.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JaxbDocument document = (JaxbDocument) unmarshaller.unmarshal(new StringReader(data));

            return document;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
