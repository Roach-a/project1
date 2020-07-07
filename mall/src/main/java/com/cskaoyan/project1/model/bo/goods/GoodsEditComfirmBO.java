package com.cskaoyan.project1.model.bo.goods;

import com.cskaoyan.project1.model.Goods;

import java.util.List;

public class GoodsEditComfirmBO {
    private Integer id;
    private String name;
    private Integer typeId;
    private String img;
    private String desc;
    private List<GoodsEditComfirmSpecBO> specList;

    public Integer getId() {
        return id;
    }
    public Goods toGoods(Double[] priceAndStocknum) {
        Goods goods = new Goods(id,img,name,priceAndStocknum[0],typeId,priceAndStocknum[1].intValue(),desc);
        return goods;
    }
    public void setId(Integer id) {
        this.id = id;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<GoodsEditComfirmSpecBO> getSpecList() {
        return specList;
    }

    public void setSpecList(List<GoodsEditComfirmSpecBO> specList) {
        this.specList = specList;
    }
}
