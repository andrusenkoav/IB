package ru.ibank.model.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserCreateResponse {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
