package com.MyJsonSerializer.ClassForTest;

import java.util.*;
/*
Class for checking DIYgson;
 */
public class User {
    String name = "Nikita";
    String someNULL = null;
    int age = 20;
    byte someByte = 9;
    short someShort = 512;
    double someDouble = 931924.124;
    float someFloat = 5.2f;
    long someLong = 9L;
    boolean checkJava = true;
    char favoriteLetter = 'R';
    SimpleUser simpleUser = new SimpleUser("Alex", 19);
    List<String> hobbyList = new ArrayList<>(Arrays.asList("Board games", "Metal music", "YouTube"));
    Set<String> stackOfInterest = new HashSet<>(Arrays.asList("Java", "Spring boot", "Hibernate", "Docker", "Postgres"));
    int[] someIntArray = {9, 4, 136, 12, 16};
    SimpleUser[] someObjArray = {new SimpleUser("Mike", 27), new SimpleUser("Sergey", 25)};
}
