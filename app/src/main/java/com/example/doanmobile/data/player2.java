package com.example.doanmobile.data;

public class player2 {
    private String name;
    private int diem;

    public player2(String name, int diem) {
        this.name = name;
        this.diem = diem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiem() {
        return diem;
    }

    public void setdiem(int diem) {
        this.diem = diem;
    }

    @Override
    public String toString()
    {
        return name + "," + diem;
    }

    public String toString1()
    {
        return name + " - " + diem +" điểm";
    }

}