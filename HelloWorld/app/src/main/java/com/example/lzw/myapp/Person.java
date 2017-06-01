package com.example.lzw.myapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LZW on 2016/09/18.
 */
public class Person implements Parcelable {
    private int age;
    private String name;
    public static final Parcelable.Creator<Person> CREATOR=new Parcelable.Creator<Person>()
    {
        public Person createFromParcel(Parcel in)
        {
            return new Person(in);
        }
        public Person[] newArray(int size)
        {
            return new Person[size];
        }
    };

    public Person(){}

    private Person(Parcel in)
    {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(age);
        parcel.writeString(name);
    }

    public void readFromParcel(Parcel in)
    {
        age=in.readInt();
        name=in.readString();
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age=age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name=name;
    }
}
