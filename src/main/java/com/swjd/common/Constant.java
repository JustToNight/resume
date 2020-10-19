package com.swjd.common;

/**
 * @author Climb.Xu
 * @date 2020/10/18 17:01
 */
public class Constant {


    /**
     * 公司状态枚举
     */
    public enum CompanyStatus {
        CREATE(0, "新建"),
        START(1, "正在招聘");
        private final int code;
        private final String msg;

        CompanyStatus(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     *
     */
    public enum ResumeStatus {
        AUDITBYC(0, "讲师审核中"),
        AUDITBYB(1, "就业老师审核中"),
        PASS(2, "审核通过"),
        BACKTO(3, "打回");
        private final int code;
        private final String msg;

        ResumeStatus(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

}
