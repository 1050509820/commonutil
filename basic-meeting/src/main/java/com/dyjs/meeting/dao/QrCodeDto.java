package com.dyjs.meeting.dao;

import java.io.Serializable;

public class QrCodeDto implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qrcode.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qrcode.openid
     *
     * @mbggenerated
     */
    private String openid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qrcode.imgurl
     *
     * @mbggenerated
     */
    private String imgurl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qrcode.tel
     *
     * @mbggenerated
     */
    private String tel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qrcode.isDel
     *
     * @mbggenerated
     */
    private String isdel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table qrcode
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qrcode.id
     *
     * @return the value of qrcode.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qrcode.id
     *
     * @param id the value for qrcode.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qrcode.openid
     *
     * @return the value of qrcode.openid
     *
     * @mbggenerated
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qrcode.openid
     *
     * @param openid the value for qrcode.openid
     *
     * @mbggenerated
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qrcode.imgurl
     *
     * @return the value of qrcode.imgurl
     *
     * @mbggenerated
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qrcode.imgurl
     *
     * @param imgurl the value for qrcode.imgurl
     *
     * @mbggenerated
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qrcode.tel
     *
     * @return the value of qrcode.tel
     *
     * @mbggenerated
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qrcode.tel
     *
     * @param tel the value for qrcode.tel
     *
     * @mbggenerated
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qrcode.isDel
     *
     * @return the value of qrcode.isDel
     *
     * @mbggenerated
     */
    public String getIsdel() {
        return isdel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qrcode.isDel
     *
     * @param isdel the value for qrcode.isDel
     *
     * @mbggenerated
     */
    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table qrcode
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", openid=").append(openid);
        sb.append(", imgurl=").append(imgurl);
        sb.append(", tel=").append(tel);
        sb.append(", isdel=").append(isdel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}