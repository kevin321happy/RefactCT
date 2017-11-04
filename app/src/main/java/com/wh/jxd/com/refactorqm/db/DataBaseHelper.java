package com.wh.jxd.com.refactorqm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kevin321vip on 2017/11/2.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    //表名
    private static final String DB_NAME = "QMCT";
    //字段名
    public static final String TABLE_NAME_COURSE_CHAPTERS = "course_chapters";
    public static final String TABLE_NAME_RECENT_STUDY = "recent_study";
    public static final String ID = "id";
    public static final String UID = "uid";
    public static final String SECTIONID = "sectionId";
    public static final String SECTION_NAME = "sectionname";
    public static final String SECTION_URL = "sectionUrl";
    public static final String STATE = "state";
    public static final String LOCAL_PATH = "localpath";
    public static final String COURSEID = "courseid";
    public static final String CHAPTERID = "chapterId";
    public static final String CHAPTERNAME = "chapterName";
    public static final String FILETYPE = "fileType";
    public static final String FILESIZE = "fileSize";
    public static final String PROGRESS = "progress";
    public static final String COURSE_ID = "course_id";
    public static final String COURSE_NAME = "course_name";
    public static final String LAST_STUDY_TIME = "last_Study_Time";//最近一次的学习时间
    public static final String IS_TASK = "is_Task";//是否是任务课程
    public static final String COURSE_IMA = "course_Ima";//课程图片

    //数据库版本
    private static final int version = 1;
    //创建章节表的语句
    private static final String sql_course_chapters = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME_COURSE_CHAPTERS + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UID + " TEXT, "
            + SECTIONID + " TEXT, "
            + SECTION_NAME + " TEXT, "
            + SECTION_URL + " TEXT, "
            + STATE + " TEXT, "
            + LOCAL_PATH + " TEXT, "
            + COURSEID + " TEXT, "
            + CHAPTERID + " TEXT, "
            + CHAPTERNAME + " TEXT, "
            + FILETYPE + " TEXT, "
            + FILESIZE + " TEXT, "
            + PROGRESS + " TEXT)";
    //创建最近学习记录表
    private static final String sql_recent_study = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME_RECENT_STUDY + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COURSE_ID + " TEXT, "
            + COURSE_NAME + " TEXT, "
            + COURSE_IMA + " TEXT, "
            + LAST_STUDY_TIME + " TEXT, "
            + IS_TASK + " TEXT)";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL(sql_course_chapters);
        db.execSQL(sql_recent_study);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = oldVersion; i <= newVersion; i++) {
            updateTableToVersion(db, i);
        }

    }

    /**
     * 执行更新表操作
     *
     * @param db
     * @param i
     */
    private void updateTableToVersion(SQLiteDatabase db, int i) {
        switch (i) {

        }
    }
}
