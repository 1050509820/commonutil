package com.dyzhsw.cardcontrol.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class StationModify implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.id
     *
     * @mbggenerated
     */
    private String id;

    private String resolve;

    public String getResolve() {
        return resolve;
    }

    public void setResolve(String resolve) {
        this.resolve = resolve;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.telemetryAddress
     *
     * @mbggenerated
     */
    private String telemetryaddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.centerAdress
     *
     * @mbggenerated
     */
    private String centeradress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.serialNumber
     *
     * @mbggenerated
     */
    private String serialnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.fuctionCode
     *
     * @mbggenerated
     */
    private String fuctioncode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.timeIdentifer
     *
     * @mbggenerated
     */
    private String timeidentifer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.sendTime
     *
     * @mbggenerated
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date sendtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.createTime
     *
     * @mbggenerated
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.heartBeat
     *
     * @mbggenerated
     */
    private String heartbeat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.waterLimit
     *
     * @mbggenerated
     */
    private String waterlimit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.balanceLimit
     *
     * @mbggenerated
     */
    private String balancelimit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.waterPrice
     *
     * @mbggenerated
     */
    private String waterprice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.powerPrice
     *
     * @mbggenerated
     */
    private String powerprice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.flowmeterProtocol
     *
     * @mbggenerated
     */
    private String flowmeterprotocol;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.colletCycle
     *
     * @mbggenerated
     */
    private String colletcycle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.energyProtocol
     *
     * @mbggenerated
     */
    private String energyprotocol;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.energyCycle
     *
     * @mbggenerated
     */
    private String energycycle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column telemetry_station_modify.billingMethod
     *
     * @mbggenerated
     */
    private String billingmethod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table telemetry_station_modify
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.id
     *
     * @return the value of telemetry_station_modify.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.id
     *
     * @param id the value for telemetry_station_modify.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.telemetryAddress
     *
     * @return the value of telemetry_station_modify.telemetryAddress
     *
     * @mbggenerated
     */
    public String getTelemetryaddress() {
        return telemetryaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.telemetryAddress
     *
     * @param telemetryaddress the value for telemetry_station_modify.telemetryAddress
     *
     * @mbggenerated
     */
    public void setTelemetryaddress(String telemetryaddress) {
        this.telemetryaddress = telemetryaddress == null ? null : telemetryaddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.centerAdress
     *
     * @return the value of telemetry_station_modify.centerAdress
     *
     * @mbggenerated
     */
    public String getCenteradress() {
        return centeradress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.centerAdress
     *
     * @param centeradress the value for telemetry_station_modify.centerAdress
     *
     * @mbggenerated
     */
    public void setCenteradress(String centeradress) {
        this.centeradress = centeradress == null ? null : centeradress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.password
     *
     * @return the value of telemetry_station_modify.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.password
     *
     * @param password the value for telemetry_station_modify.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.serialNumber
     *
     * @return the value of telemetry_station_modify.serialNumber
     *
     * @mbggenerated
     */
    public String getSerialnumber() {
        return serialnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.serialNumber
     *
     * @param serialnumber the value for telemetry_station_modify.serialNumber
     *
     * @mbggenerated
     */
    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.fuctionCode
     *
     * @return the value of telemetry_station_modify.fuctionCode
     *
     * @mbggenerated
     */
    public String getFuctioncode() {
        return fuctioncode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.fuctionCode
     *
     * @param fuctioncode the value for telemetry_station_modify.fuctionCode
     *
     * @mbggenerated
     */
    public void setFuctioncode(String fuctioncode) {
        this.fuctioncode = fuctioncode == null ? null : fuctioncode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.timeIdentifer
     *
     * @return the value of telemetry_station_modify.timeIdentifer
     *
     * @mbggenerated
     */
    public String getTimeidentifer() {
        return timeidentifer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.timeIdentifer
     *
     * @param timeidentifer the value for telemetry_station_modify.timeIdentifer
     *
     * @mbggenerated
     */
    public void setTimeidentifer(String timeidentifer) {
        this.timeidentifer = timeidentifer == null ? null : timeidentifer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.sendTime
     *
     * @return the value of telemetry_station_modify.sendTime
     *
     * @mbggenerated
     */
    public Date getSendtime() {
        return sendtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.sendTime
     *
     * @param sendtime the value for telemetry_station_modify.sendTime
     *
     * @mbggenerated
     */
    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.createTime
     *
     * @return the value of telemetry_station_modify.createTime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.createTime
     *
     * @param createtime the value for telemetry_station_modify.createTime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.heartBeat
     *
     * @return the value of telemetry_station_modify.heartBeat
     *
     * @mbggenerated
     */
    public String getHeartbeat() {
        return heartbeat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.heartBeat
     *
     * @param heartbeat the value for telemetry_station_modify.heartBeat
     *
     * @mbggenerated
     */
    public void setHeartbeat(String heartbeat) {
        this.heartbeat = heartbeat == null ? null : heartbeat.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.waterLimit
     *
     * @return the value of telemetry_station_modify.waterLimit
     *
     * @mbggenerated
     */
    public String getWaterlimit() {
        return waterlimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.waterLimit
     *
     * @param waterlimit the value for telemetry_station_modify.waterLimit
     *
     * @mbggenerated
     */
    public void setWaterlimit(String waterlimit) {
        this.waterlimit = waterlimit == null ? null : waterlimit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.balanceLimit
     *
     * @return the value of telemetry_station_modify.balanceLimit
     *
     * @mbggenerated
     */
    public String getBalancelimit() {
        return balancelimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.balanceLimit
     *
     * @param balancelimit the value for telemetry_station_modify.balanceLimit
     *
     * @mbggenerated
     */
    public void setBalancelimit(String balancelimit) {
        this.balancelimit = balancelimit == null ? null : balancelimit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.waterPrice
     *
     * @return the value of telemetry_station_modify.waterPrice
     *
     * @mbggenerated
     */
    public String getWaterprice() {
        return waterprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.waterPrice
     *
     * @param waterprice the value for telemetry_station_modify.waterPrice
     *
     * @mbggenerated
     */
    public void setWaterprice(String waterprice) {
        this.waterprice = waterprice == null ? null : waterprice.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.powerPrice
     *
     * @return the value of telemetry_station_modify.powerPrice
     *
     * @mbggenerated
     */
    public String getPowerprice() {
        return powerprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.powerPrice
     *
     * @param powerprice the value for telemetry_station_modify.powerPrice
     *
     * @mbggenerated
     */
    public void setPowerprice(String powerprice) {
        this.powerprice = powerprice == null ? null : powerprice.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.flowmeterProtocol
     *
     * @return the value of telemetry_station_modify.flowmeterProtocol
     *
     * @mbggenerated
     */
    public String getFlowmeterprotocol() {
        return flowmeterprotocol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.flowmeterProtocol
     *
     * @param flowmeterprotocol the value for telemetry_station_modify.flowmeterProtocol
     *
     * @mbggenerated
     */
    public void setFlowmeterprotocol(String flowmeterprotocol) {
        this.flowmeterprotocol = flowmeterprotocol == null ? null : flowmeterprotocol.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.colletCycle
     *
     * @return the value of telemetry_station_modify.colletCycle
     *
     * @mbggenerated
     */
    public String getColletcycle() {
        return colletcycle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.colletCycle
     *
     * @param colletcycle the value for telemetry_station_modify.colletCycle
     *
     * @mbggenerated
     */
    public void setColletcycle(String colletcycle) {
        this.colletcycle = colletcycle == null ? null : colletcycle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.energyProtocol
     *
     * @return the value of telemetry_station_modify.energyProtocol
     *
     * @mbggenerated
     */
    public String getEnergyprotocol() {
        return energyprotocol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.energyProtocol
     *
     * @param energyprotocol the value for telemetry_station_modify.energyProtocol
     *
     * @mbggenerated
     */
    public void setEnergyprotocol(String energyprotocol) {
        this.energyprotocol = energyprotocol == null ? null : energyprotocol.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.energyCycle
     *
     * @return the value of telemetry_station_modify.energyCycle
     *
     * @mbggenerated
     */
    public String getEnergycycle() {
        return energycycle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.energyCycle
     *
     * @param energycycle the value for telemetry_station_modify.energyCycle
     *
     * @mbggenerated
     */
    public void setEnergycycle(String energycycle) {
        this.energycycle = energycycle == null ? null : energycycle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column telemetry_station_modify.billingMethod
     *
     * @return the value of telemetry_station_modify.billingMethod
     *
     * @mbggenerated
     */
    public String getBillingmethod() {
        return billingmethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column telemetry_station_modify.billingMethod
     *
     * @param billingmethod the value for telemetry_station_modify.billingMethod
     *
     * @mbggenerated
     */
    public void setBillingmethod(String billingmethod) {
        this.billingmethod = billingmethod == null ? null : billingmethod.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table telemetry_station_modify
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
        sb.append(", telemetryaddress=").append(telemetryaddress);
        sb.append(", centeradress=").append(centeradress);
        sb.append(", password=").append(password);
        sb.append(", serialnumber=").append(serialnumber);
        sb.append(", fuctioncode=").append(fuctioncode);
        sb.append(", timeidentifer=").append(timeidentifer);
        sb.append(", sendtime=").append(sendtime);
        sb.append(", createtime=").append(createtime);
        sb.append(", heartbeat=").append(heartbeat);
        sb.append(", waterlimit=").append(waterlimit);
        sb.append(", balancelimit=").append(balancelimit);
        sb.append(", waterprice=").append(waterprice);
        sb.append(", powerprice=").append(powerprice);
        sb.append(", flowmeterprotocol=").append(flowmeterprotocol);
        sb.append(", colletcycle=").append(colletcycle);
        sb.append(", energyprotocol=").append(energyprotocol);
        sb.append(", energycycle=").append(energycycle);
        sb.append(", billingmethod=").append(billingmethod);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}