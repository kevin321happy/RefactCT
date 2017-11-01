package com.wh.jxd.com.refactorqm.model;
/**
 * description: 课程章下面的每个小节
 * autour: Kevin
 * company:锦绣氘(武汉)科技有限公司
 * date: 2017/6/6 16:13 
 * update: 2017/6/6
 * version: 1.21
 * 站在峰顶 看世界
 * 落在谷底 思人生
*/
public  class SectionInfo extends BaseModel{

  private String chapter_name;
  private String class_chapter_id;
  private String class_id;
  private String company_id;
  private String file_size;
  private String file_type;
  private String id;
  private String process;
  private String session_id;
  private String studiedTime;
  private String study_time;
  private String url;
  private String is_playing;//是否正在播放,"1"表示正在播放,"0",表示非播放状态
  private String is_latest;//是否是最近一次看的章節
  private String watch_history_time;//之前的觀看時長

  public String getIs_latest() {
    return is_latest;
  }

  public void setIs_latest(String is_latest) {
    this.is_latest = is_latest;
  }

  public String getWatch_history_time() {
    return watch_history_time;
  }

  public void setWatch_history_time(String watch_history_time) {
    this.watch_history_time = watch_history_time;
  }

  public String getIs_playing() {
    return is_playing;
  }

  public void setIs_playing(String is_playing) {
    this.is_playing = is_playing;
  }

  public String getChapter_name() {
    return chapter_name;
  }

  public void setChapter_name(String chapter_name) {
    this.chapter_name = chapter_name;
  }

  public String getClass_chapter_id() {
    return class_chapter_id;
  }

  public void setClass_chapter_id(String class_chapter_id) {
    this.class_chapter_id = class_chapter_id;
  }
  public String getClass_id() {
    return class_id;
  }

  public void setClass_id(String class_id) {
    this.class_id = class_id;
  }

  public String getCompany_id() {
    return company_id;
  }

  public void setCompany_id(String company_id) {
    this.company_id = company_id;
  }

  public String getFile_size() {
    return file_size;
  }

  public void setFile_size(String file_size) {
    this.file_size = file_size;
  }

  public String getFile_type() {
    return file_type;
  }

  public void setFile_type(String file_type) {
    this.file_type = file_type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProcess() {
    return process;
  }

  public void setProcess(String process) {
    this.process = process;
  }

  public String getSession_id() {
    return session_id;
  }

  public void setSession_id(String session_id) {
    this.session_id = session_id;
  }

  public String getStudiedTime() {
    return studiedTime;
  }

  public void setStudiedTime(String studiedTime) {
    this.studiedTime = studiedTime;
  }

  public String getStudy_time() {
    return study_time;
  }

  public void setStudy_time(String study_time) {
    this.study_time = study_time;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
