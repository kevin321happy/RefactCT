package com.wh.jxd.com.refactorqm.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.AppcationEx;
import com.wh.jxd.com.refactorqm.model.RecentStudyEntity;
import com.wh.jxd.com.refactorqm.utils.DataCleanManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin321vip on 2017/11/2.
 * 操作最近学习
 */

public class RecentStudyDao {

    private final DataBaseHelper mHelper;
    private SQLiteDatabase mDb;

    public RecentStudyDao() {
        mHelper = new DataBaseHelper(AppcationEx.getInstance());
    }

    /**
     * 增加一条数据
     */
    public boolean insertOneRecentStudy(RecentStudyEntity recentStudyEntity) {
        mDb = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.COURSE_ID, recentStudyEntity.getCourse_Id());
        values.put(DataBaseHelper.COURSE_IMA, recentStudyEntity.getCourse_Ima());
        values.put(DataBaseHelper.LAST_STUDY_TIME, recentStudyEntity.getLast_Study_Time());
        values.put(DataBaseHelper.IS_TASK, recentStudyEntity.getIs_Task());
        values.put(DataBaseHelper.COURSE_NAME, recentStudyEntity.getCourse_name());
        long insert = mDb.insert(DataBaseHelper.TABLE_NAME_RECENT_STUDY, null, values);
        mDb.close();
        if (insert!=-1){
            KLog.i("数据插入成功："+recentStudyEntity.toString());
        }else {
            KLog.i("数据插入失败：");
        }
        return insert != (-1);
    }
    /**
     * 查询最近学习数据
     */
    public List<RecentStudyEntity> queryRecentStudy() {
        mDb = mHelper.getWritableDatabase();
//        Cursor cursor = mDb.rawQuery("select * from " + DataBaseHelper.TABLE_NAME_RECENT_STUDY, null);
        Cursor cursor = mDb.query(DataBaseHelper.TABLE_NAME_RECENT_STUDY,null,null,null,null,null,null);
        ArrayList<RecentStudyEntity> studyEntities = new ArrayList<>();
        while (cursor.moveToNext()) {
            RecentStudyEntity entity = new RecentStudyEntity();
            String course_id = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COURSE_ID));
            String course_name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COURSE_NAME));
            String course_ima = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COURSE_IMA));
            String last_study_time = cursor.getString(cursor.getColumnIndex(DataBaseHelper.LAST_STUDY_TIME));
            String is_task = cursor.getString(cursor.getColumnIndex(DataBaseHelper.IS_TASK));

            entity.setCourse_Id(course_id);
            entity.setCourse_name(course_name);
            entity.setCourse_Ima(course_ima);
            entity.setIs_Task(is_task);
            entity.setLast_Study_Time(last_study_time);

            studyEntities.add(entity);
        }
        cursor.close();
        mDb.close();
        return studyEntities;
    }
}
