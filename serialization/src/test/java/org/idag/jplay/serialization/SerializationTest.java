package org.idag.jplay.serialization;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationTest {
    private static class NotSerializable {
        private final int number;

        public NotSerializable(final int n) {
            number = n;
        }

        public int getNumber() {
            return number;
        }
    }

    private static class SomeSerializableClass implements Serializable {
        private final String id;
        private final NotSerializable notSerializable;

        public SomeSerializableClass(final String s) {
            id = s;
            notSerializable = new NotSerializable(s.length());
        }

        public String getId() {
            return id;
        }
    }

    @Test(expected = NotSerializableException.class)
    public void testUnserializableField() throws Exception {
        final SomeSerializableClass s = new SomeSerializableClass("test");

        final ByteArrayOutputStream binOut = new ByteArrayOutputStream();
        try (final ObjectOutputStream objOut = new ObjectOutputStream(binOut)) {
            objOut.writeObject(s);
        }
    }
}
