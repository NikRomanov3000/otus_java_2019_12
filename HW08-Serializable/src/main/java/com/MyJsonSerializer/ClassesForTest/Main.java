package com.MyJsonSerializer.ClassesForTest;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Gson gson = new Gson();
        User user = new User();
        int testInt = 100;
        NikSON nikson = new NikSON();
        SimpleUser simpleUser = new SimpleUser("Fox", 22);
        List<String> hobbyList = new ArrayList<>(Arrays.asList("Board games", "Metal music", "YouTube"));
        Set<String> stackOfInterest = new HashSet<>(Arrays.asList("Java", "Spring boot", "Hibernate", "Docker", "Postgres"));


        Field[] fields = user.getClass().getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            if(field.get(user)!=null) {
              //  System.out.println(field.getName() +" "+ field.getType() + " " + Iterable.class.isAssignableFrom(field.getType()));
            }
        }
      //  System.out.println(user.getClass().getField("hobbyList").getName());

        String gsonResult = gson.toJson(user);
        String myResult = nikson.toJson(user);
        System.out.println(gsonResult);
        System.out.println(myResult);

    }
}
