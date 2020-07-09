package com.cskaoyan.project1.model.vo.backstage;

import com.cskaoyan.project1.model.Goods;

public class GoodsSelfEditVO {
    private Integer id;
    private String img;
    private String name;
    private Double unitPrice;
    private Integer typeId;
    private String desc;

    public GoodsSelfEditVO() {
    }
    public GoodsSelfEditVO(Goods goods){
        id = goods.getId();
        img = goods.getImg();
        name = goods.getName();
        desc = goods.getDesc();
        typeId = goods.getTypeId();
        unitPrice = goods.getPrice();

    }

    public Integer getId() {
        return id;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
