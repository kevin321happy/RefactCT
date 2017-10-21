package com.wh.jxd.com.refactorqm.model;

/**
 * Created by kevin321vip on 2017/10/20.
 */

public class CategoryInfo extends BaseModel{
    private String category_image;//分类图标的路径
    private String category_name;//匪类条目的名字
    private String class_category_id;//课程分类的id

    @Override
    public String toString() {
        return "CategoryInfo{" +
                "category_image='" + category_image + '\'' +
                ", category_name='" + category_name + '\'' +
                ", class_category_id='" + class_category_id + '\'' +
                '}';
    }

    public CategoryInfo() {
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getClass_category_id() {
        return class_category_id;
    }

    public void setClass_category_id(String class_category_id) {
        this.class_category_id = class_category_id;
    }
}
