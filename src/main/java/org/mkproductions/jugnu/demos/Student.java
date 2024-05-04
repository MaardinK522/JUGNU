package org.mkproductions.jugnu.demos;

import org.mkproductions.jugnu.entities.ClassifyAbleClass;
import org.mkproductions.jugnu.entities.ClassifyAbleConstructor;
import org.mkproductions.jugnu.entities.ClassifyAbleField;
import org.mkproductions.jugnu.entities.ClassifyAbleMethod;

@ClassifyAbleClass
public class Student {
    @ClassifyAbleField
    String name;
    int age;
    String gender;

    @ClassifyAbleConstructor
    public Student(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Student() {
        this.name = "";
        this.age = 0;
        this.gender = "";
    }

    public String getName() {
        return name;
    }

    @ClassifyAbleMethod
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @ClassifyAbleMethod
    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    @ClassifyAbleMethod
    public void setGender(String gender) {
        this.gender = gender;
    }
}
