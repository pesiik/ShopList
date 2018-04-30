package com.pesiik.shoplist.Model;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Parcelable{

    @JsonProperty("id")
    private final UUID id;

    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("description")
    private String description;
    @JsonProperty("count")
    private int count;

    public Product(){
        id = UUID.randomUUID();
    }

    @JsonCreator
    public Product(UUID id, String name,Double price, String description, int count) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.count = count;
    }

    protected Product(Parcel in) {
        long mostSigBits = in.readLong();
        long leastSigBits = in.readLong();
        UUID uuid = new UUID(mostSigBits, leastSigBits);
        id = uuid;
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

    public UUID getId() {
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
        dest.writeLong(id.getMostSignificantBits());
        dest.writeLong(id.getLeastSignificantBits());
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


}
