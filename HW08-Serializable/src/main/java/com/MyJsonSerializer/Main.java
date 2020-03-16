package com.MyJsonSerializer;
import com.MyJsonSerializer.ClassForTest.SimpleUser;
import com.MyJsonSerializer.ClassForTest.User;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Gson gson = new Gson();
        User user = new User();
        NikSON nikson = new NikSON();

        String gsonResult = gson.toJson(user);
        String myResult = nikson.toJson(user);
        System.out.println(gsonResult);
        System.out.println(myResult);
        System.out.println("Test status: "+ gsonResult.equals(myResult));

    }
}
