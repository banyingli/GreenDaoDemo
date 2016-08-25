package com.example;
import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGeneratorV1 {
    public static void main(String[] args) throws Exception {
        // 创建一个用于Schema对象。
        // 两个参数分别代表：数据库版本号与自动生成代码的包路径。
        Schema schema = new Schema(1, "cn.banyingli.greendaodemo.db");

        // 添加实体
        addConcert(schema);

        // 最后我们将使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处你需要根据自己的情况更改输出目录（既之前创建的 java-gen)。
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
        note.addLongProperty("concert_no").primaryKey().autoincrement(); // 演出编号自增长
        note.addStringProperty("title").notNull(); // 演出名称不为空
        note.addStringProperty("venue"); // 演出场馆
    }
}
