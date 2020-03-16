package com.MyJsonSerializer;
import com.MyJsonSerializer.ClassForTest.User;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Gson gson = new Gson();
        User user = new User();
        NikSON nikson = new NikSON();

        String gsonResult = gson.toJson(user);
        String myResult = nikson.toJson(user);
        System.out.println("Gson result:   " + gsonResult);
        System.out.println("myJson result: "+ myResult);
        System.out.println("Test status: "+ gsonResult.equals(myResult));

    }
}
