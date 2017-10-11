package com.wh.jxd.com.refactorqm.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/30.
 * 课程
 */
public class CourseInfo extends BaseModel implements Serializable  {


  private int courseClickNum;



  private String courseId;

  private String courseImage;

  private String courseInfo;

  private String courseName;

  private String coursePrice;
  private String courseIntro;

  private int studyNum;

  private String teacherName;

  private String tip;

  private String typeName;
  //课程完成结果
  private String result;
  // 好评率
  private String welltoken;
  // 试卷ID
  private String paperid;
  private String teacherid;
  private String collection;
  private String riokin;
  private String integral;//积分
  private String pricetype;
  private String company_id;
  //学习进度
  private String study_process;
  //首页课程名
  private String class_name;
  //首页课程图片
  private String class_pic;

  public String getClass_name() {
    return class_name;
  }

  public void setClass_name(String class_name) {
    this.class_name = class_name;
  }

  public String getClass_pic() {
    return class_pic;
  }

  public void setClass_pic(String class_pic) {
    this.class_pic = class_pic;
  }

  public String getStudy_process() {
    return study_process;
  }

  public void setStudy_process(String study_process) {
    this.study_process = study_process;
  }

  public String getCompany_id() {
    return company_id;
  }

  public void setCompany_id(String company_id) {
    this.company_id = company_id;
  }

  public String getTeacherid() {
    return teacherid;
  }

  public void setTeacherid(String teacherid) {
    this.teacherid = teacherid;
  }

  public String getCollection() {
    return collection;
  }

  public void setCollection(String collection) {
    this.collection = collection;
  }

  public String getRiokin() {
    return riokin;
  }

  public void setRiokin(String riokin) {
    this.riokin = riokin;
  }

  public String getIntegral() {
    return integral;
  }

  public void setIntegral(String integral) {
    this.integral = integral;
  }

  public String getPricetype() {
    return pricetype;
  }

  public void setPricetype(String pricetype) {
    this.pricetype = pricetype;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public void setCourseClickNum(int courseClickNum) {
    this.courseClickNum = courseClickNum;
  }

  public int getCourseClickNum() {
    return this.courseClickNum;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public String getCourseId() {
    return this.courseId;
  }

  public void setCourseImage(String courseImage) {
    this.courseImage = courseImage;
  }

  public String getCourseImage() {
    return this.courseImage;
  }

  public void setCourseInfo(String courseInfo) {
    this.courseInfo = courseInfo;
  }

  public String getCourseInfo() {
    return this.courseInfo;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public String getCourseName() {
    return this.courseName;
  }

  public void setCoursePrice(String coursePrice) {
    this.coursePrice = coursePrice;
  }

  public String getCoursePrice() {
    return this.coursePrice;
  }

  public void setStudyNum(int studyNum) {
    this.studyNum = studyNum;
  }

  public int getStudyNum() {
    return this.studyNum;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public String getTeacherName() {
    return this.teacherName;
  }

  public void setTip(String tip) {
    this.tip = tip;
  }

  public String getTip() {
    return this.tip;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return this.typeName;
  }

  public String getWelltoken() {
    return welltoken;
  }

  public void setWelltoken(String welltoken) {
    this.welltoken = welltoken;
  }

  public String getPaperid() {
    return paperid;
  }

  public void setPaperid(String paperid) {
    this.paperid = paperid;
  }

  public String getCourseIntro() {
    return courseIntro;
  }

  public void setCourseIntro(String courseIntro) {
    this.courseIntro = courseIntro;
  }

  @Override
  public String toString() {
    return "CourseInfo{" +
        "courseClickNum=" + courseClickNum +
        ", courseId='" + courseId + '\'' +
        ", courseImage='" + courseImage + '\'' +
        ", courseInfo='" + courseInfo + '\'' +
        ", courseName='" + courseName + '\'' +
        ", coursePrice='" + coursePrice + '\'' +
        ", courseIntro='" + courseIntro + '\'' +
        ", studyNum=" + studyNum +
        ", teacherName='" + teacherName + '\'' +
        ", tip='" + tip + '\'' +
        ", typeName='" + typeName + '\'' +
        ", result='" + result + '\'' +
        ", welltoken='" + welltoken + '\'' +
        ", paperid='" + paperid + '\'' +
        '}';
  }
}
