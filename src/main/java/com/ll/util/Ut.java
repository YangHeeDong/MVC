package com.ll.util;

import java.lang.reflect.InvocationTargetException;

public class Ut {
    public static class str {

        public static String decapitalize(String string) {
            if (string == null || string.length() == 0) {
                return string;
            }

            char c[] = string.toCharArray();
            c[0] = Character.toLowerCase(c[0]);

            return new String(c);
        }

        public static String beforeFrom(String str, String fromStr, int matchCount) {
            StringBuilder sb = new StringBuilder();

            String[] bits = str.split(fromStr);

            for (int i = 0; i < matchCount; i++) {
                sb.append(bits[i]);
                if (i + 1 < matchCount) {
                    sb.append(fromStr);
                }
            }

            return sb.toString();
        }

        public static String beforeFrom(String str, String fromStr) {
            StringBuilder sb = new StringBuilder();

            String[] bits = str.split(fromStr);

            for (int i = 0; i < bits.length; i++) {
                sb.append(bits[i]);
            }

            return sb.toString();
        }
    }

    public static class cls {

        public static <T> T newObj(Class<T> cls, T defaultValue) {
            try {
                return cls.getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                return defaultValue;
            } catch (IllegalAccessException e) {
                return defaultValue;
            } catch (InvocationTargetException e) {
                return defaultValue;
            } catch (NoSuchMethodException e) {
                return defaultValue;
            }
        }
    }

}
