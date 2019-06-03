package com.kraynov.calculator.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;

public class TaskSerializer<T> {

    private JAXBContext context;
    private Marshaller marshaller;

    public TaskSerializer(Class<T> clazz) {
        try {
            context = JAXBContext.newInstance(clazz);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void writeToStream(T task, OutputStream stream) {
        try {
            marshaller.marshal(task, stream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
