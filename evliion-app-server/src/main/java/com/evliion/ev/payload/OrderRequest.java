package com.evliion.ev.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class OrderRequest {

    @NotNull
    private List<Integer> ooid;

    @NotBlank
    private String mobile;


    private String usermessage;


    public List<Integer> getOoid() {
        return ooid;
    }

    public void setOoid(List<Integer> ooid) {
        this.ooid = ooid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsermessage() {
        return usermessage;
    }

    public void setUsermessage(String usermessage) {
        this.usermessage = usermessage;
    }
}
