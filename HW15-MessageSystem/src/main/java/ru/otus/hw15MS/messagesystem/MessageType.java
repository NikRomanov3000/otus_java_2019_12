package ru.otus.hw15MS.messagesystem;

public enum MessageType {
    USER_DATA("UserData"),//Get User data by Id
    ALL_USERS_DATA("AllUsersData"),// Get All Users
    ADD_USER("AddUserData"); //Post User Data


    private final String value;

    MessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
