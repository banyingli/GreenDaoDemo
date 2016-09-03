package com.example;
import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class GreenDaoGeneratorV2 {
    public static void main(String[] args) throws Exception {
        // ����һ������Schema����
        // ��������ֱ��?��ݿ�汾�����Զ���ɴ���İ�·����
        Schema schema = new Schema(2, "cn.banyingli.greendaodemo.db");

        // ���ʵ��
        addConcert(schema);

        // ��V2�����?
        addSongs(schema);

        // ������ǽ�ʹ�� DAOGenerator ��� generateAll() �����Զ���ɴ��룬�˴�����Ҫ����Լ������������Ŀ¼����֮ǰ������ java-gen)��
        new DaoGenerator().generateAll(schema, "greendaogenerator/src-gen-v2");
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
        note.addIntProperty("concert_no").autoincrement(); // �ݳ����������
        note.addStringProperty("title").notNull(); // �ݳ���Ʋ�Ϊ��
        note.addStringProperty("venue"); // �ݳ�����

        // ��V2�����ֶΡ�
        note.addDateProperty("date"); // �ݳ�ʱ��
    }

    /**
     * ��ӱ�Songs
     * @param schema
     */
    private static void addSongs(Schema schema) {
        // ���һ����Ϊ��Songs���ı�
        Entity note = schema.addEntity("Songs");

        // Ϊ������ֶ�
        note.addIdProperty();
        note.addIntProperty("concert_no"); // ���Ӧ���ݳ����
        note.addStringProperty("title").notNull(); // ������Ʋ�Ϊ��
    }
}
