package com.wh.jxd.com.refactorqm.model;

import java.io.Serializable;
import java.util.List;

/**
 * description: 课程的章信息
 * autour: Kevin
 * company:锦绣氘(武汉)科技有限公司
 * date: 2017/6/6 16:12 
 * update: 2017/6/6
 * version: 1.21
 * 站在峰顶 看世界
 * 落在谷底 思人生
*/
public class ChapterInfo extends BaseModel implements Serializable {
    /**
     * classChapterList : [{"chapter_name":"111","class_chapter_id":"1","class_id":"2","company_id":"8","file_size":"100","file_type":"3","id":"1","process":"0.00","session_id":"","studiedTime":"","study_time":"40","url":""},{"chapter_name":"333","class_chapter_id":"2","class_id":"2","company_id":"8","file_size":"100","file_type":"3","id":"2","process":"0.00","session_id":"","studiedTime":"","study_time":"30","url":""}]
     * class_id : 2
     * class_name : 测试课程2
     * class_session_id : 1
     * company_id : 8
     * id : 1
     * seesion_name : 这是个章节
     */
    private String class_id;
    private String class_name;
    private String class_session_id;
    private String company_id;
    private String id;
    private String session_name;
    private List<SectionInfo> classChapterList;

    public String getClass_id() {
      return class_id;
    }

    public void setClass_id(String class_id) {
      this.class_id = class_id;
    }

    public String getClass_name() {
      return class_name;
    }

    public void setClass_name(String class_name) {
      this.class_name = class_name;
    }

    public String getClass_session_id() {
      return class_session_id;
    }

    public void setClass_session_id(String class_session_id) {
      this.class_session_id = class_session_id;
    }

    public String getCompany_id() {
      return company_id;
    }

    public void setCompany_id(String company_id) {
      this.company_id = company_id;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

  public String getSession_name() {
    return session_name;
  }

  public void setSession_name(String session_name) {
    this.session_name = session_name;
  }

  public List<SectionInfo> getChapterList() {
      return classChapterList;
    }

    public void setChapterList(List<SectionInfo> ChapterList) {
      this.classChapterList = classChapterList;
    }

  @Override
  public String toString() {
    return "ChapterInfo{" +
        "class_id='" + class_id + '\'' +
        ", class_name='" + class_name + '\'' +
        ", class_session_id='" + class_session_id + '\'' +
        ", company_id='" + company_id + '\'' +
        ", id='" + id + '\'' +
        ", seesion_name='" + session_name + '\'' +
        ", classChapterList=" + classChapterList +
        '}';
  }
}
