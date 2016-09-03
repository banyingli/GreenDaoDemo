package com.example;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class GreenDaoGeneratorV1 {
    public static void main(String[] args) throws Exception {
        // ����һ������Schema����
        // ���������ֱ�������ݿ�汾�����Զ����ɴ���İ�·����
        Schema schema = new Schema(1, "cn.banyingli.greendaodemo.db");

        // ���ʵ��
        addConcert(schema);

        // ʹ�� DAOGenerator ��� generateAll() �����Զ����ɴ��룬�˴���Ҫ�����Լ�������������Ŀ¼
        new DaoGenerator().generateAll(schema, "greendaogenerator/src-gen-v1");
    }

    /**
     * ��ӱ�Concert
     * @param schema
     */
    private static void addConcert(Schema schema) {
        // ���һ����Ϊ��Concert���ı�
        Entity note = schema.addEntity("Concert");

        // Ϊ������ֶ�
        note.addIdProperty();
        note.addStringProperty("title").notNull(); // �ݳ����Ʋ�Ϊ��
        note.addStringProperty("venue").notNull(); // �ݳ�����
    }
}
