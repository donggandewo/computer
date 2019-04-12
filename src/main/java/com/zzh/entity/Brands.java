package com.zzh.entity;

public class Brands {
    private int brandsId;
    private String brandsName;
    private String pic;

    public int getBrandsId() {
        return brandsId;
    }

    public void setBrandsId(int brandsId) {
        this.brandsId = brandsId;
    }

    public String getBrandsName() {
        return brandsName;
    }

    public void setBrandsName(String brandsName) {
        this.brandsName = brandsName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Brands{" +
                "brandsId=" + brandsId +
                ", brandsName='" + brandsName + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
