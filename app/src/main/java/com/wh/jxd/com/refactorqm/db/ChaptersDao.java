package com.wh.jxd.com.refactorqm.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceActivity;

import com.socks.library.KLog;
import com.wh.jxd.com.refactorqm.AppcationEx;
import com.wh.jxd.com.refactorqm.model.SectionInfo;
import com.wh.jxd.com.refactorqm.utils.PreferenceUtils;

import java.util.ArrayList;

/**
 * Created by kevin321vip on 2017/11/2.
 * 操作课程章节的Dao
 */

public class ChaptersDao {

    private final DataBaseHelper mHelper;
    private SQLiteDatabase mDb;

    public ChaptersDao() {
        mHelper = new DataBaseHelper(AppcationEx.getInstance());
    }

    /**
     * 往数据库中插入一条数据
     */
    public synchronized boolean insertOneChapter(SectionInfo sectionInfo) {
        mDb = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.CHAPTERID, sectionInfo.getClass_chapter_id());
        values.put(DataBaseHelper.UID, PreferenceUtils.getUserId());
        values.put(DataBaseHelper.CHAPTERNAME, sectionInfo.getChapter_name());
        values.put(DataBaseHelper.COURSEID, sectionInfo.getClass_id());
        values.put(DataBaseHelper.COURSEID, sectionInfo.getClass_id());
        values.put(DataBaseHelper.SECTION_URL, sectionInfo.getUrl());
        values.put(DataBaseHelper.SECTIONID, sectionInfo.getSession_id());
        values.put(DataBaseHelper.FILESIZE, sectionInfo.getFile_size());
        values.put(DataBaseHelper.FILETYPE, sectionInfo.getFile_type());
        values.put(DataBaseHelper.LOCAL_PATH, sectionInfo.getLocal_path());
        values.put(DataBaseHelper.PROGRESS, sectionInfo.getProcess());
        long insert = mDb.insert(DataBaseHelper.TABLE_NAME_COURSE_CHAPTERS, null, values);
        mDb.close();
        KLog.i("插入数据成功：" + sectionInfo.toString());
        return insert != (-1);
    }

    /**
     * 删除一条数据
     */
    public synchronized boolean deleteOneChapter(String chapterid) {
        mDb = mHelper.getWritableDatabase();
        int delete = mDb.delete(DataBaseHelper.TABLE_NAME_COURSE_CHAPTERS, DataBaseHelper.CHAPTERID + "=?", new String[]{chapterid});
        mDb.close();
        return delete > 0;
    }

    /**
     * 查询一个课程下面的全部的章节
     */
    public synchronized ArrayList<SectionInfo> queryAllSectionByCourseID(String courseid) {
        mDb = mHelper.getWritableDatabase();
        Cursor cursor = mDb.query(DataBaseHelper.TABLE_NAME_COURSE_CHAPTERS, null, DataBaseHelper.COURSEID + "=?", new String[]{courseid}, null, null, null);
        ArrayList<SectionInfo> sectionInfos = new ArrayList<>();
        SectionInfo sectionInfo;
        while (cursor.moveToNext()) {
            String sectionid = cursor.getString(cursor.getColumnIndex(DataBaseHelper.SECTIONID));
            String setction_name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.SECTION_NAME));
            String courseId = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COURSEID));
            String chaptername = cursor.getString(cursor.getColumnIndex(DataBaseHelper.CHAPTERNAME));
            String chapterid = cursor.getString(cursor.getColumnIndex(DataBaseHelper.CHAPTERID));
            String filesize = cursor.getString(cursor.getColumnIndex(DataBaseHelper.FILESIZE));
            String filetype = cursor.getString(cursor.getColumnIndex(DataBaseHelper.FILETYPE));
            String local_Path = cursor.getString(cursor.getColumnIndex(DataBaseHelper.LOCAL_PATH));
            String progress = cursor.getString(cursor.getColumnIndex(DataBaseHelper.PROGRESS));

            SectionInfo info = new SectionInfo();
            info.setChapter_name(chaptername);
            info.setClass_chapter_id(chapterid);
            info.setClass_id(courseId);
            info.setSession_id(sectionid);
            info.setFile_size(filesize);
            info.setFile_type(filetype);
            info.setLocal_path(local_Path);
            info.setProcess(progress);

            sectionInfos.add(info);
        }
        cursor.close();
        mDb.close();
        return sectionInfos;
    }
}
