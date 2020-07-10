package com.example.fabricfox.Model;

public class SellerRequest
{

    private String name, phone, image, pid, pname, description, pcolor, pextra, date, time, requestState;

    public SellerRequest()
    {

    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SellerRequest(String name, String phone, String image, String pid, String pname, String description, String pcolor, String pextra, String date, String time, String requestState) {
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.pid = pid;
        this.pname = pname;
        this.description = description;
        this.pcolor = pcolor;
        this.pextra = pextra;
        this.date = date;
        this.time = time;
        this.requestState = requestState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPcolor() {
        return pcolor;
    }

    public void setPcolor(String pcolor) {
        this.pcolor = pcolor;
    }

    public String getPextra() {
        return pextra;
    }

    public void setPextra(String pextra) {
        this.pextra = pextra;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRequestState() {
        return requestState;
    }

    public void setRequestState(String requestState) {
        this.requestState = requestState;
    }
}
