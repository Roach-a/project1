package com.cskaoyan.project1.dao;

import com.alibaba.druid.util.StringUtils;
import com.cskaoyan.project1.model.Goods;
import com.cskaoyan.project1.model.Spec;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.TypeBO;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {

    @Override
    public List<Type> getType() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Type> typeList = null;
        try {
            typeList = runner.query("select * from type",new BeanListHandler<Type>(Type.class));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return typeList;
    }
    //通过名字获取goodsType
    private Type getTypeByName(String name) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Type type = null;
        try {
            type = runner.query("select * from type where name = ?", new BeanHandler<Type>(Type.class), name);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    @Override
    public List<TypeGoodsVO> getGoodsByType(String typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        int id = StringUtils.stringToInteger(typeId);
        String sql = "select id,img,name,price,typeId,stockNum from goods where typeId = ?";
        List<TypeGoodsVO> typeGoodsVOList = null;
        try {
            typeGoodsVOList = runner.query(sql,new BeanListHandler<TypeGoodsVO>(TypeGoodsVO.class),id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return typeGoodsVOList;
    }

    @Override
    public void addGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "insert into goods values(null,?,?,?,?,?,?)";
        try{
            runner.update(sql,goods.getImg(),goods.getName(),goods.getPrice(),goods.getTypeId(),goods.getStockNum(),goods.getDesc());

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int lastInsertId() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Integer id = null;
        try{
            BigInteger query = runner.query("select last_insert_id()",new ScalarHandler<>());
            id = query.intValue();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void addSpecs(List<Spec> specs) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "insert into spec values (null,?,?,?,?)";
        for (Spec spec:specs) {
            try{
                runner.update(sql,spec.getSpecName(),spec.getStockNum()
                ,spec.getUnitPrice(),spec.getGoodsId());
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean addGoodsType(Type type) {
        if (getTypeByName(type.getName()) != null)return false;
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into type values (null,?)",type.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
