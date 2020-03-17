package com.MyJsonSerializer.ClassForTest;

import java.util.*;

/*
Class for checking DIYgson;
 */
public class User {
    private String name = "Nikita";
    private String someNULL = null;
    private int age = 20;
    private byte someByte = 9;
    private short someShort = 512;
    double someDouble = 931924.124;
    public float someFloat = 5.3f;
    private long someLong = 9L;
    private boolean checkJava = true;
    private char favoriteLetter = 'R';
    private SimpleUser simpleUser = new SimpleUser("Alex", 19);
    private List<String> hobbyList = new ArrayList<>(Arrays.asList("Board games", "Metal music", "YouTube"));
    private Set<String> stackOfInterest = new HashSet<>(Arrays.asList("Java", "Spring boot", "Hibernate", "Docker", "Postgres"));
    private int[] someIntArray = {9, 4, 136, 12, 16};
    private SimpleUser[] someObjArray = {new SimpleUser("Mike", 27), new SimpleUser("Sergey", 25)};
}
