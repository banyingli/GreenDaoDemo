package com.example;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class GreenDaoGeneratorV1 {
    public static void main(String[] args) throws Exception {
        // 创建一个用于Schema对象。
        // 两个参数分别代表：数据库版本号与自动生成代码的包路径。
        Schema schema = new Schema(1, "cn.banyingli.greendaodemo.db");

        // 添加实体
        addConcert(schema);

        // 使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处需要根据自己的情况更改输出目录
        new DaoGenerator().generateAll(schema, "greendaogenerator/src-gen-v1");
    }

    /**
     * 添加表Concert
     * @param schema
     */
    private static void addConcert(Schema schema) {
        // 添加一张名为【Concert】的表
        Entity note = schema.addEntity("Concert");

        // 为表添加字段
        note.addIdProperty();
        note.addStringProperty("title").notNull(); // 演出名称不为空
        note.addStringProperty("venue").notNull(); // 演出场馆
    }
}
