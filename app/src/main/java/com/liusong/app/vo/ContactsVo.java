package com.liusong.app.vo;

import java.util.List;

/**
 * Created by liu song on 2017/5/16.
 */

public class ContactsVo {
    private int id;
    private String name;
    private List<Phone> phones;

    public ContactsVo(int id, String name, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.phones = phones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "ContactsVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phones=" + phones.toString() +
                '}';
    }

    public static class Phone{
        private String typeName;
        private String number;

        public Phone(String typeName, String number) {
            this.typeName = typeName;
            this.number = number;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "{" +
                    "typeName='" + typeName + '\'' +
                    ", number='" + number + '\'' +
                    '}';
        }
    }
}
