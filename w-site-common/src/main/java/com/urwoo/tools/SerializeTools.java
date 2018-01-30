package com.urwoo.tools;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class SerializeTools {

    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (Exception e) {
            log.error("serialize error!", e);
        } finally {
            close(os);
            close(bos);
        }
        return rv;
    }


    public static Object deserialize(byte[] in) {
        return deserialize(in, Object.class);
    }

    public static <T> T deserialize(byte[] bytes, Class<T> clazz) {
        Object obj = null;
        BufferedInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (bytes != null && bytes.length > 0) {
                bis = new BufferedInputStream(new ByteArrayInputStream(bytes));
                is = new ObjectInputStream(bis);
                obj = is.readObject();
            }
        } catch (Exception e) {
            log.error("serialize error!", e);
        } finally {
            close(is);
            close(bis);
        }
        return (T) obj;
    }

    private static void close(Closeable closeable) {
        if (closeable != null)
            try {
                closeable.close();
            } catch (IOException e) {
                log.error("close stream error!", e);
            }
    }
}
