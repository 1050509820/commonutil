package com.dyzhsw.cardcontrol.dto;

public class TelemetryState {

    //交流电充电状态
    private Integer chargeState;

    // 蓄电池电压状态
    private Integer voltageState;

    // 水位超限报警状态
    private Integer waterTransfiniteState;

    // 流量超限报警状态
    private Integer flowTransfiniteState;

    // 水质超限报警状态
    private Integer waterQualityTransfiniteState;

    // 流量仪表状态
    private Integer flowMeterState;

    // 水位仪表状态
    private Integer waterLevelMeterState;

    // 终端箱门状态
    private Integer terminalBoxDoor;

    // 存储器状态
    private Integer memoryState;

    // IC 卡功能有效
    private Integer iCCardFunction;

    // 水泵工作状态
    private Integer waterPumpState;

    // 剩余水量报警
    private Integer restWaterState;

    // 电能表状态
    private Integer electricState;

    // 三相电缺相
    private Integer threePhaseState;

    //管道压力状态
    private Integer pipelineState;

    // 机井开采量状态
    private Integer machineState;

    // 用户剩余金额
    private Integer balance;

    // 漏水报警
    private Integer leakageWaterState;



    public Integer getChargeState() {
        return chargeState;
    }

    public void setChargeState(Integer chargeState) {
        this.chargeState = chargeState;
    }

    public Integer getVoltageState() {
        return voltageState;
    }

    public void setVoltageState(Integer voltageState) {
        this.voltageState = voltageState;
    }

    public Integer getWaterTransfiniteState() {
        return waterTransfiniteState;
    }

    public void setWaterTransfiniteState(Integer waterTransfiniteState) {
        this.waterTransfiniteState = waterTransfiniteState;
    }

    public Integer getFlowTransfiniteState() {
        return flowTransfiniteState;
    }

    public void setFlowTransfiniteState(Integer flowTransfiniteState) {
        this.flowTransfiniteState = flowTransfiniteState;
    }

    public Integer getWaterQualityTransfiniteState() {
        return waterQualityTransfiniteState;
    }

    public void setWaterQualityTransfiniteState(Integer waterQualityTransfiniteState) {
        this.waterQualityTransfiniteState = waterQualityTransfiniteState;
    }

    public Integer getFlowMeterState() {
        return flowMeterState;
    }

    public void setFlowMeterState(Integer flowMeterState) {
        this.flowMeterState = flowMeterState;
    }

    public Integer getWaterLevelMeterState() {
        return waterLevelMeterState;
    }

    public void setWaterLevelMeterState(Integer waterLevelMeterState) {
        this.waterLevelMeterState = waterLevelMeterState;
    }

    public Integer getTerminalBoxDoor() {
        return terminalBoxDoor;
    }

    public void setTerminalBoxDoor(Integer terminalBoxDoor) {
        this.terminalBoxDoor = terminalBoxDoor;
    }

    public Integer getMemoryState() {
        return memoryState;
    }

    public void setMemoryState(Integer memoryState) {
        this.memoryState = memoryState;
    }

    public Integer getiCCardFunction() {
        return iCCardFunction;
    }

    public void setiCCardFunction(Integer iCCardFunction) {
        this.iCCardFunction = iCCardFunction;
    }

    public Integer getWaterPumpState() {
        return waterPumpState;
    }

    public void setWaterPumpState(Integer waterPumpState) {
        this.waterPumpState = waterPumpState;
    }

    public Integer getRestWaterState() {
        return restWaterState;
    }

    public void setRestWaterState(Integer restWaterState) {
        this.restWaterState = restWaterState;
    }

    public Integer getElectricState() {
        return electricState;
    }

    public void setElectricState(Integer electricState) {
        this.electricState = electricState;
    }

    public Integer getThreePhaseState() {
        return threePhaseState;
    }

    public void setThreePhaseState(Integer threePhaseState) {
        this.threePhaseState = threePhaseState;
    }

    public Integer getPipelineState() {
        return pipelineState;
    }

    public void setPipelineState(Integer pipelineState) {
        this.pipelineState = pipelineState;
    }

    public Integer getMachineState() {
        return machineState;
    }

    public void setMachineState(Integer machineState) {
        this.machineState = machineState;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getLeakageWaterState() {
        return leakageWaterState;
    }

    public void setLeakageWaterState(Integer leakageWaterState) {
        this.leakageWaterState = leakageWaterState;
    }

    @Override
    public String toString() {
        return
                '{'+"交流电充电状态:" + chargeState +
                ", 蓄电池电压状态:" + voltageState +
                ", 水位超限报警状态:" + waterTransfiniteState +
                ", 流量超限报警状态:" + flowTransfiniteState +
                ", 水质超限报警状态:" + waterQualityTransfiniteState +
                ", 流量仪表状态:" + flowMeterState +
                ", 水位仪表状态:" + waterLevelMeterState +
                ", 终端箱门状态:" + terminalBoxDoor +
                ", 存储器状态:" + memoryState +
                ", IC 卡功能有效:" + iCCardFunction +
                ", 水泵工作状态:" + waterPumpState +
                ", 剩余水量报警:" + restWaterState +
                ", 电能表状态:" + electricState +
                ", 三相电缺相:" + threePhaseState +
                ", 管道压力状态:" + pipelineState +
                ", 机井开采量状态:" + machineState +
                ", 用户剩余金额:" + balance +
                ", 漏水报警:" + leakageWaterState+'}'
               ;
    }
}
