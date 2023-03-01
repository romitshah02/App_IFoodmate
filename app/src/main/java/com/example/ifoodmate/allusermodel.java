package com.example.ifoodmate;

public class allusermodel {

    String u_no,u_name,p_no;

    public allusermodel(String u_no, String u_name, String p_no) {
        this.u_no = u_no;
        this.u_name = u_name;
        this.p_no = p_no;
    }

    public String getU_no() {
        return u_no;
    }

    public String getU_name() {
        return u_name;
    }

    public String getP_no() {
        return p_no;
    }
}
