package com.example.springmongodb.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Person {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
   private String id;

    public Person(String id, String firstName, String secondName, String accountNumber) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.accountNumber=accountNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
        private String firstName;

        private String secondName;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Indexed
        private String accountNumber;
}
