package com.pesiik.shoplist.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Parcelable, Comparable<Product>{

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("description")
    private String description;
    @JsonProperty("count")
    private int count;

    @JsonCreator
    public Product(@JsonProperty("id") int id,
                   @JsonProperty("name") String name,
                   @JsonProperty("price") Double price,
                   @JsonProperty("description") String description,
                   @JsonProperty("count") int count) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.count = count;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
        description = in.readString();
        count = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(price);
        }
        dest.writeString(description);
        dest.writeInt(count);
    }


    @Override
    public int compareTo(@NonNull Product o) {
        if(id > o.getId())
            return 1;
        else if(id<o.getId())
            return -1;
        else
            return 0;
    }
}
