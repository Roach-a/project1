package com.cskaoyan.project1.dao;

import com.alibaba.druid.util.StringUtils;
import com.cskaoyan.project1.model.Goods;
import com.cskaoyan.project1.model.Spec;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.Message;
import com.cskaoyan.project1.model.vo.TypeGoodsVO;
import com.cskaoyan.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
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

    //利用goodId查询good信息
    @Override
    public Goods getGoodsInfo(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Goods good = null;
        try {
            good = runner.query("select * from goods where id = ?"
            ,new BeanHandler<Goods>(Goods.class),id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return good;
    }

    //利用goodId获取此good的所有规格
    @Override
    public List<Spec> getSpecList(int goodId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Spec> specList = null;
        try {
            specList = runner.query("select * from spec where goodsId = ?"
            ,new BeanListHandler<Spec>(Spec.class),goodId);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return specList;
    }

    @Override
    public Double[] updateSpecs(List<Spec> specList) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        //0位price，1位stockNum
        Double[] res = new Double[2];
        res[0] = Double.valueOf(Integer.MAX_VALUE);
        res[1] = Double.valueOf(Integer.MIN_VALUE);
        for (Spec spec:specList) {
            res[0] = Math.min(res[0],spec.getUnitPrice());
            res[1] = Math.max(res[1],spec.getStockNum());
            try {
                runner.update("update spec set specName=?,stockNum=?,unitPrice=? where id = ?"
                ,spec.getSpecName(),spec.getStockNum(),spec.getUnitPrice(),spec.getId());
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @Override
    public void updateGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        int row = 0;
        try {
            row = runner.update("update goods set img=?,name=?,price=?,typeId=?,stockNum=?,`desc`=? where id = ?"
            ,goods.getImg(),goods.getName(),goods.getPrice(),goods.getTypeId(),goods.getStockNum()
            ,goods.getDesc(),goods.getId());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addSpec(Spec spec) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        if (searchSpecBySpecName(spec) != null)return false;
        String sql = "insert into spec values (null,?,?,?,?)";
        try {
            runner.update(sql,spec.getSpecName(),spec.getStockNum(),spec.getUnitPrice(),spec.getGoodsId());
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private Spec searchSpecBySpecName(Spec spec) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Spec res = null;
        try {
            res = runner.query("select * from spec where specName = ?"
            ,new BeanHandler<Spec>(Spec.class),spec.getSpecName());
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void deleteSpec(Spec spec) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from spec where goodsId = ? and specName =?",spec.getGoodsId(),spec.getSpecName());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGoods(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try{
            //删除商品和规格
            runner.update("delete from goods where id = ?",id);
            runner.update("delete from spec where goodsId = ?",id);
            //删除评论
            runner.update("delete from comment where goodsId = ?",id);
            //删除订单（！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！）
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> noReplyMsg(){
        List<Message> messageList = null;
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            messageList = runner.query("select * from comment where state = 1",new BeanListHandler<Message>(Message.class));
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }

    @Override
    public List<Message> repliedMsg() {
        List<Message> messageList = null;
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            messageList = runner.query("select * from comment where state = 0",new BeanListHandler<Message>(Message.class));
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;

    }

    @Override
    public void reply(Message message) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try{
            runner.update("update comment set replyContent=?,state = 0 where id = ?",message.getReplyContent(),message.getId());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> getGoodsIdByType(Integer typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Integer> goodsIdList = null;
        try{
            goodsIdList = runner.query("select id from goods where typeId = ?",new ColumnListHandler<Integer>("id"),typeId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return goodsIdList;
    }

    @Override
    public void deleteType(Integer typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try{
            runner.update("delete from type where id = ?",typeId);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
