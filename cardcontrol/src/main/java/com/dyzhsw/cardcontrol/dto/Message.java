package com.dyzhsw.cardcontrol.dto;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.functioncode
     *
     * @mbggenerated
     */
    private String functioncode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.telemetryAddress
     *
     * @mbggenerated
     */
    private String telemetryaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.flag
     *
     * @mbggenerated
     */
    private String flag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.message
     *
     * @mbggenerated
     */
    private String message;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.centerAddress
     *
     * @mbggenerated
     */
    private String centeraddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.resolve
     *
     * @mbggenerated
     */
    private String resolve;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column message.sendtime
     *
     * @mbggenerated
     */
    private Date sendtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table message
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.id
     *
     * @return the value of message.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.id
     *
     * @param id the value for message.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.functioncode
     *
     * @return the value of message.functioncode
     *
     * @mbggenerated
     */
    public String getFunctioncode() {
        return functioncode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.functioncode
     *
     * @param functioncode the value for message.functioncode
     *
     * @mbggenerated
     */
    public void setFunctioncode(String functioncode) {
        this.functioncode = functioncode == null ? null : functioncode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.telemetryAddress
     *
     * @return the value of message.telemetryAddress
     *
     * @mbggenerated
     */
    public String getTelemetryaddress() {
        return telemetryaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.telemetryAddress
     *
     * @param telemetryaddress the value for message.telemetryAddress
     *
     * @mbggenerated
     */
    public void setTelemetryaddress(String telemetryaddress) {
        this.telemetryaddress = telemetryaddress == null ? null : telemetryaddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.flag
     *
     * @return the value of message.flag
     *
     * @mbggenerated
     */
    public String getFlag() {
        return flag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.flag
     *
     * @param flag the value for message.flag
     *
     * @mbggenerated
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.message
     *
     * @return the value of message.message
     *
     * @mbggenerated
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.message
     *
     * @param message the value for message.message
     *
     * @mbggenerated
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.createtime
     *
     * @return the value of message.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.createtime
     *
     * @param createtime the value for message.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.centerAddress
     *
     * @return the value of message.centerAddress
     *
     * @mbggenerated
     */
    public String getCenteraddress() {
        return centeraddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.centerAddress
     *
     * @param centeraddress the value for message.centerAddress
     *
     * @mbggenerated
     */
    public void setCenteraddress(String centeraddress) {
        this.centeraddress = centeraddress == null ? null : centeraddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.password
     *
     * @return the value of message.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.password
     *
     * @param password the value for message.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.resolve
     *
     * @return the value of message.resolve
     *
     * @mbggenerated
     */
    public String getResolve() {
        return resolve;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.resolve
     *
     * @param resolve the value for message.resolve
     *
     * @mbggenerated
     */
    public void setResolve(String resolve) {
        this.resolve = resolve == null ? null : resolve.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column message.sendtime
     *
     * @return the value of message.sendtime
     *
     * @mbggenerated
     */
    public Date getSendtime() {
        return sendtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column message.sendtime
     *
     * @param sendtime the value for message.sendtime
     *
     * @mbggenerated
     */
    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message
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
        sb.append(", functioncode=").append(functioncode);
        sb.append(", telemetryaddress=").append(telemetryaddress);
        sb.append(", flag=").append(flag);
        sb.append(", message=").append(message);
        sb.append(", createtime=").append(createtime);
        sb.append(", centeraddress=").append(centeraddress);
        sb.append(", password=").append(password);
        sb.append(", resolve=").append(resolve);
        sb.append(", sendtime=").append(sendtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}