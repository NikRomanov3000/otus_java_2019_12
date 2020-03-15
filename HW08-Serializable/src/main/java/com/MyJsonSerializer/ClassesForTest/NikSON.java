package com.MyJsonSerializer.ClassesForTest;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


public class NikSON {
    public String toJson(Object obj) throws IllegalAccessException {
        return obj == null ? "Empty object" : this.createJSON(obj).toString();
    }

    public JsonObject createJSON(Object obj) throws IllegalAccessException {
        Field[] classFields = obj.getClass().getDeclaredFields();
        var result = Json.createObjectBuilder();

        for (Field field : classFields) {
            field.setAccessible(true);
            if (field.get(obj) != null) {
                if (field.getType().isPrimitive()) { // for Primitive
                    //нужна проверка на числа и булен
                    result.add(field.getName(), String.valueOf(field.get(obj)));
                }

                if (field.getType().isArray()) { //for Array
                    var jsonArray = Json.createArrayBuilder();
                    int length = Array.getLength(field.get(obj));
                    Object[] array = new Object[length];
                    for (int i = 0; i < length; i++) {
                        array[i] = Array.get(field.get(obj), i);
                        //--System.out.println(array[i].getClass());
                        //jsonArray.add(String.valueOf(array[i]));
                        //add(field.getName(), createJSON(field.get(obj)));
                        jsonArray.add(createJSON(field.get(obj)));
                    }
                    result.add(field.getName(), jsonArray.build());
                }

                if (Iterable.class.isAssignableFrom(field.getType())) { // for Collection
                    var jsonCollections = Json.createArrayBuilder((Collection<?>) field.get(obj));
                    result.add(field.getName(), jsonCollections.build());
                }
                if (!field.getType().isPrimitive() && !Iterable.class.isAssignableFrom(field.getType()) && !field.getType().isArray()) {
                    if (String.class.isAssignableFrom(field.getType())) {
                        result.add(field.getName(), field.get(obj).toString());
                    } else {
                        result.add(field.getName(), createJSON(field.get(obj)));
                    }
                }

            }

        }
        return result.build();
    }
}




