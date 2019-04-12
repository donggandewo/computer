package com.zzh.entity;

import java.util.List;

public class Category {
    private int categoryId;
    private String categoryName;
    private int parentsId;
    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getParentsId() {
        return parentsId;
    }

    public void setParentsId(int parentsId) {
        this.parentsId = parentsId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", parentsId=" + parentsId +
                '}';
    }
}
