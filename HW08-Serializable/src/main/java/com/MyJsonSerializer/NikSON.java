package com.MyJsonSerializer;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;


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
                    addPrimitive(field, obj, result);
                }

                if (field.getType().isArray()) { //for Array
                    var jsonArray = Json.createArrayBuilder();
                    int length = Array.getLength(field.get(obj));
                    Object[] array = new Object[length];

                    for (int i = 0; i < length; i++) {
                        array[i] = Array.get(field.get(obj), i);
                        if (checkPrimitiveClass(array[i].getClass())) {
                            jsonArray = addPrimitiveToArray(array[i], jsonArray);
                        } else {
                            if (String.class.isAssignableFrom(field.getType())) {
                                result.add(field.getName(), field.get(array[i]).toString());
                            } else {
                                jsonArray.add(createJSON(array[i]));
                            }
                        }
                    }
                    result.add(field.getName(), jsonArray.build());
                }

                if (Iterable.class.isAssignableFrom(field.getType())) { // for Collection
                    var jsonCollections = Json.createArrayBuilder((Collection<?>) field.get(obj));
                    result.add(field.getName(), jsonCollections.build());
                }
                //for Objects
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

    private void addPrimitive(Field field, Object object, JsonObjectBuilder result) throws IllegalAccessException {
        if (byte.class.isAssignableFrom(field.getType())
                || short.class.isAssignableFrom(field.getType())
                || int.class.isAssignableFrom(field.getType())) {
            result.add(field.getName(), field.getInt(object));
        }
        if (long.class.isAssignableFrom(field.getType())) {
            result.add(field.getName(), field.getLong(object));
        }
        if (boolean.class.isAssignableFrom(field.getType())) {
            result.add(field.getName(), field.getBoolean(object));
        }
        if (float.class.isAssignableFrom(field.getType())) {
            result.add(field.getName(), Float.parseFloat(String.valueOf(field.get(object))));
        }
        if (double.class.isAssignableFrom(field.getType())) {
            result.add(field.getName(), field.getDouble(object));
        }
        if (char.class.isAssignableFrom(field.getType())) {
            result.add(field.getName(),String.valueOf(field.get(object)));
        }
    }

    private JsonArrayBuilder addPrimitiveToArray(Object object, JsonArrayBuilder result) {
        if (Byte.class.isAssignableFrom(object.getClass())
                || Short.class.isAssignableFrom(object.getClass())
                || Integer.class.isAssignableFrom(object.getClass())) {
            result.add((int) object);
        }
        if (Long.class.isAssignableFrom(object.getClass())) {
            result.add((long) object);
        }
        if (Boolean.class.isAssignableFrom(object.getClass())) {
            result.add((boolean) object);
        }
        if (Float.class.isAssignableFrom(object.getClass())) {
            double res = (float) object;
            result.add(res);
        }
        if (Double.class.isAssignableFrom(object.getClass())) {
            result.add((double) object);
        }
        if (Character.class.isAssignableFrom(object.getClass())) {
            result.add(String.valueOf(object));
        }
        return result;
    }

    private boolean checkPrimitiveClass(Object object) {
        if (Integer.class.isAssignableFrom((Class<?>) object)
                || Short.class.isAssignableFrom((Class<?>) object)
                || Float.class.isAssignableFrom((Class<?>) object)
                || Byte.class.isAssignableFrom((Class<?>) object)
                || Double.class.isAssignableFrom((Class<?>) object)
                || Long.class.isAssignableFrom((Class<?>) object)
                || Character.class.isAssignableFrom((Class<?>) object)
                || Boolean.class.isAssignableFrom((Class<?>) object)) {
            return true;
        } else return false;
    }
}





