package com.dyzhsw.cardcontrol.util;

public class equipment_base {
    private String key;   //通道key
    private String centerAdress; //中心站地址1字节
    private String telemetryAddress; //遥测站地址
    private String password;  //密码
    private String centerAdress4; //中心站地址4字节
    private String channel1;  //中心站1主信道及地址标识符
    private String channel2;//中心站2主信道及地址标识符
    private String channel3;//中心站3主信道及地址标识符
    private String channel4;    //中心站4主信道及地址标识符
    private String workStyle;  //工作方式标识符
    private String colletKey;//采集要素
    private String timeIdentifer;//定时时间标识符
    private String heartBeat;//心跳间隔
    private String waterLimit;//年机井用水量上限标识符
    private String balanceLimit;//用水户余额报警下限标识符
    private String waterPrice;//水价标识符
    private String powerPrice;//电价标识符
    private String flowmeterProtocol;//流量计协议标识符
    private String colletCycle;//流量计采集周期标识符
    private String energyProtocol;//电能表协议标识符
    private String energyCycle;//电能表采集周期标识符
    private String billingMethod; //计费方式
    private String pumpIsopen;//水泵开关控制标识符

    public String getBillingMethod() {
        return billingMethod;
    }

    public void setBillingMethod(String billingMethod) {
        this.billingMethod = billingMethod;
    }

    public String getPumpIsopen() {
        return pumpIsopen;
    }

    public void setPumpIsopen(String pumpIsopen) {
        this.pumpIsopen = pumpIsopen;
    }

    public String getTimeIdentifer() {
        return timeIdentifer;
    }

    public void setTimeIdentifer(String timeIdentifer) {
        this.timeIdentifer = timeIdentifer;
    }

    public String getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(String heartBeat) {
        this.heartBeat = heartBeat;
    }

    public String getWaterLimit() {
        return waterLimit;
    }

    public void setWaterLimit(String waterLimit) {
        this.waterLimit = waterLimit;
    }

    public String getBalanceLimit() {
        return balanceLimit;
    }

    public void setBalanceLimit(String balanceLimit) {
        this.balanceLimit = balanceLimit;
    }

    public String getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(String waterPrice) {
        this.waterPrice = waterPrice;
    }

    public String getPowerPrice() {
        return powerPrice;
    }

    public void setPowerPrice(String powerPrice) {
        this.powerPrice = powerPrice;
    }

    public String getFlowmeterProtocol() {
        return flowmeterProtocol;
    }

    public void setFlowmeterProtocol(String flowmeterProtocol) {
        this.flowmeterProtocol = flowmeterProtocol;
    }

    public String getColletCycle() {
        return colletCycle;
    }

    public void setColletCycle(String colletCycle) {
        this.colletCycle = colletCycle;
    }

    public String getEnergyProtocol() {
        return energyProtocol;
    }

    public void setEnergyProtocol(String energyProtocol) {
        this.energyProtocol = energyProtocol;
    }

    public String getEnergyCycle() {
        return energyCycle;
    }

    public void setEnergyCycle(String energyCycle) {
        this.energyCycle = energyCycle;
    }

    public String getCenterAdress4() {
        return centerAdress4;
    }

    public void setCenterAdress4(String centerAdress4) {
        this.centerAdress4 = centerAdress4;
    }

    public String getChannel1() {
        return channel1;
    }

    public void setChannel1(String channel1) {
        this.channel1 = channel1;
    }

    public String getChannel2() {
        return channel2;
    }

    public void setChannel2(String channel2) {
        this.channel2 = channel2;
    }

    public String getChannel3() {
        return channel3;
    }

    public void setChannel3(String channel3) {
        this.channel3 = channel3;
    }

    public String getChannel4() {
        return channel4;
    }

    public void setChannel4(String channel4) {
        this.channel4 = channel4;
    }

    public String getWorkStyle() {
        return workStyle;
    }

    public void setWorkStyle(String workStyle) {
        this.workStyle = workStyle;
    }

    public String getColletKey() {
        return colletKey;
    }

    public void setColletKey(String colletKey) {
        this.colletKey = colletKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCenterAdress() {
        return centerAdress;
    }

    public void setCenterAdress(String centerAdress) {
        this.centerAdress = centerAdress;
    }

    public String getTelemetryAddress() {
        return telemetryAddress;
    }

    public void setTelemetryAddress(String telemetryAddress) {
        this.telemetryAddress = telemetryAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "equipment_base{" +
                "key='" + key + '\'' +
                ", centerAdress='" + centerAdress + '\'' +
                ", telemetryAddress='" + telemetryAddress + '\'' +
                ", password='" + password + '\'' +
                ", centerAdress4='" + centerAdress4 + '\'' +
                ", channel1='" + channel1 + '\'' +
                ", channel2='" + channel2 + '\'' +
                ", channel3='" + channel3 + '\'' +
                ", channel4='" + channel4 + '\'' +
                ", workStyle='" + workStyle + '\'' +
                ", colletKey='" + colletKey + '\'' +
                ", timeIdentifer='" + timeIdentifer + '\'' +
                ", heartBeat='" + heartBeat + '\'' +
                ", waterLimit='" + waterLimit + '\'' +
                ", balanceLimit='" + balanceLimit + '\'' +
                ", waterPrice='" + waterPrice + '\'' +
                ", powerPrice='" + powerPrice + '\'' +
                ", flowmeterProtocol='" + flowmeterProtocol + '\'' +
                ", colletCycle='" + colletCycle + '\'' +
                ", energyProtocol='" + energyProtocol + '\'' +
                ", energyCycle='" + energyCycle + '\'' +
                ", billingMethod='" + billingMethod + '\'' +
                ", pumpIsopen='" + pumpIsopen + '\'' +
                '}';
    }
}
