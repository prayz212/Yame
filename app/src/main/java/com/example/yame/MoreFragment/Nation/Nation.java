package com.example.yame.MoreFragment.Nation;

public class Nation {

    private String name;
    private boolean check;

    public Nation(String name) {
        this.name = name;
        this.check = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Nation{" +
                "name='" + name + '\'' +
                ", check=" + check +
                '}';
    }
}
