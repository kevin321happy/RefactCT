package com.wh.jxd.com.refactorqm.model;

import java.util.List;

/**
 * 功能：课程评价列表类
 * Created by Administrator on 2016/9/6.
 */
public class CommentInfo extends BaseModel{
  // 评价内容
  private String evaluation;
  // 评论日期
  private String create_date;
  // 打分
  private String rate;
  // 学习进度
  private String process;
  // 用户照片
  private String head_image;
  // 用户名
  private String username;
  private String created_at;//发布时间
  private String is_show;//是否显示
  private String member_id;
  private String class_id;
  private String evaluation_id;//评论的id
  private List<String> evalution_reply;


  public List<String> getEvalution_reply() {
    return evalution_reply;
  }

  public void setEvalution_reply(List<String> evalution_reply) {
    this.evalution_reply = evalution_reply;
  }

  public String getEvaluation_id() {
    return evaluation_id;
  }

  public void setEvaluation_id(String evaluation_id) {
    this.evaluation_id = evaluation_id;
  }

  public String getEvaluation() {
    return evaluation;
  }

  public void setEvaluation(String evaluation) {
    this.evaluation = evaluation;
  }

  public String getCreate_date() {
    return create_date;
  }

  public void setCreate_date(String create_date) {
    this.create_date = create_date;
  }

  public String getRate() {
    return rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  public String getProcess() {
    return process;
  }

  public void setProcess(String process) {
    this.process = process;
  }

  public String getHead_image() {
    return head_image;
  }

  public void setHead_image(String head_image) {
    this.head_image = head_image;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getIs_show() {
    return is_show;
  }

  public void setIs_show(String is_show) {
    this.is_show = is_show;
  }

  public String getMember_id() {
    return member_id;
  }

  public void setMember_id(String member_id) {
    this.member_id = member_id;
  }

  public String getClass_id() {
    return class_id;
  }

  public void setClass_id(String class_id) {
    this.class_id = class_id;
  }
}
