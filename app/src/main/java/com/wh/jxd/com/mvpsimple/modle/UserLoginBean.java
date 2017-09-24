package com.wh.jxd.com.mvpsimple.modle;

/**
 * Created by kevin321vip on 2017/8/28.
 */

public class UserLoginBean {

    /**
     * status : 1
     * info : success
     * data : {"id":"5534","userid":"5534","user_name":"肖文祥","company_id":"59","truename":"裘马讲师","file_id":"http://v1.52qmct.com/uploadima/2017/5534575201708112258.jpg","status":"1","phone_verified":"0","phone":"15623374595","ry_token":"CZwcF3taZe/fE4/APqPGrv872zmacUYS3Ez8NNTA69UG1BBCwMFvnYnA4+JlgHdwQLMPKG6Oawl4g7kqBOLpJw==","mail":"","email_verified":"0","created_at":"2017-07-24 09:59:47","last_login":"2017-08-28 21:46:07","is_teacher":"1","qmct_token":"bf8c0fc68b8a2a51","bean_points":"0","points":"","total_points":"6","readstatus":"0"}
     * suc : y
     * is_login : 1
     */

    private String status;
    private String info;
    private UserInfo data;
    private String suc;
    private String is_login;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

    public void setSuc(String suc) {
        this.suc = suc;
    }

    public void setIs_login(String is_login) {
        this.is_login = is_login;
    }

    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public UserInfo getData() {
        return data;
    }

    public String getSuc() {
        return suc;
    }

    public String getIs_login() {
        return is_login;
    }

    public static class UserInfo {
        /**
         * id : 5534
         * userid : 5534
         * user_name : 肖文祥
         * company_id : 59
         * truename : 裘马讲师
         * file_id : http://v1.52qmct.com/uploadima/2017/5534575201708112258.jpg
         * status : 1
         * phone_verified : 0
         * phone : 15623374595
         * ry_token : CZwcF3taZe/fE4/APqPGrv872zmacUYS3Ez8NNTA69UG1BBCwMFvnYnA4+JlgHdwQLMPKG6Oawl4g7kqBOLpJw==
         * mail :
         * email_verified : 0
         * created_at : 2017-07-24 09:59:47
         * last_login : 2017-08-28 21:46:07
         * is_teacher : 1
         * qmct_token : bf8c0fc68b8a2a51
         * bean_points : 0
         * points :
         * total_points : 6
         * readstatus : 0
         */

        private String id;
        private String userid;
        private String user_name;
        private String company_id;
        private String truename;
        private String file_id;
        private String status;
        private String phone_verified;
        private String phone;
        private String ry_token;
        private String mail;
        private String email_verified;
        private String created_at;
        private String last_login;
        private String is_teacher;
        private String qmct_token;
        private String bean_points;
        private String points;
        private String total_points;
        private String readstatus;

        public void setId(String id) {
            this.id = id;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public void setFile_id(String file_id) {
            this.file_id = file_id;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setPhone_verified(String phone_verified) {
            this.phone_verified = phone_verified;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setRy_token(String ry_token) {
            this.ry_token = ry_token;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public void setEmail_verified(String email_verified) {
            this.email_verified = email_verified;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setLast_login(String last_login) {
            this.last_login = last_login;
        }

        public void setIs_teacher(String is_teacher) {
            this.is_teacher = is_teacher;
        }

        public void setQmct_token(String qmct_token) {
            this.qmct_token = qmct_token;
        }

        public void setBean_points(String bean_points) {
            this.bean_points = bean_points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public void setTotal_points(String total_points) {
            this.total_points = total_points;
        }

        public void setReadstatus(String readstatus) {
            this.readstatus = readstatus;
        }

        public String getId() {
            return id;
        }

        public String getUserid() {
            return userid;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getCompany_id() {
            return company_id;
        }

        public String getTruename() {
            return truename;
        }

        public String getFile_id() {
            return file_id;
        }

        public String getStatus() {
            return status;
        }

        public String getPhone_verified() {
            return phone_verified;
        }

        public String getPhone() {
            return phone;
        }

        public String getRy_token() {
            return ry_token;
        }

        public String getMail() {
            return mail;
        }

        public String getEmail_verified() {
            return email_verified;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getLast_login() {
            return last_login;
        }

        public String getIs_teacher() {
            return is_teacher;
        }

        public String getQmct_token() {
            return qmct_token;
        }

        public String getBean_points() {
            return bean_points;
        }

        public String getPoints() {
            return points;
        }

        public String getTotal_points() {
            return total_points;
        }

        public String getReadstatus() {
            return readstatus;
        }
    }
}
