package com.dyzhsw.cardcontrol.util;

import com.dyzhsw.cardcontrol.dto.StationAdd;
import com.dyzhsw.cardcontrol.dto.StationTime;
import com.dyzhsw.cardcontrol.dto.SwitchPump;
import com.dyzhsw.cardcontrol.dto.TelemetryState;
import com.sun.corba.se.spi.ior.ObjectKey;

public class StringReplaceUtils {

	/**
	 * 字符串替换
	 * @param original 原字符串
	 * @param rep 需要替换字符串
	 * @param tar 替换成目标字符串
	 * @return
	 */
	public static String replace(String original,String rep,String tar) {
		if(null!=original) {
			return original.replaceAll(rep, tar);
		}
		return "";
	}

    /**
     * 去除字符串前面的0
     * @param str
     * @return
     */
	public static String del0(String str){
		str= str.replaceAll("^(0+)","");
        if(str.length()==0){
            return "0";
        }
        return str;
	}

	/**
	 * 十六进制转bit
	 * @param hexString
	 * @return
	 */
	public static String hexString2binaryString(String hexString)
	{
		if (hexString == null || hexString.length() % 2 != 0)
			return null;
		String bString = "", tmp;
		for (int i = 0; i < hexString.length(); i++)
		{
			tmp = "0000"
					+ Integer.toBinaryString(Integer.parseInt(hexString
					.substring(i, i + 1), 16));
			bString += tmp.substring(tmp.length() - 4);
		}
		return bString;
	}
	public static Object getStationTime(Object object){
		if(object instanceof StationTime){
			StationTime stationTime =(StationTime)object;
			if("".equals(stationTime.getStatus())||stationTime.getStatus()==null){
				return stationTime;
			}else{
				String str = StringReplaceUtils.hexString2binaryString(stationTime.getStatus());
				str = new StringBuilder(str).reverse().toString();
				String resolve = stationTime.getResolve();
				TelemetryState telemetryState = new TelemetryState();
				for(int i=0;i<str.length();i++){
					if(i==0){
						telemetryState.setChargeState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==1){
						telemetryState.setVoltageState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==2){
						telemetryState.setWaterTransfiniteState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==3){
						telemetryState.setFlowTransfiniteState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==4){
						telemetryState.setWaterQualityTransfiniteState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==5){
						telemetryState.setFlowMeterState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==6){
						telemetryState.setWaterLevelMeterState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==7){
						telemetryState.setTerminalBoxDoor(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==8){
						telemetryState.setMemoryState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==9){
						telemetryState.setiCCardFunction(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==10){
						telemetryState.setWaterPumpState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==11){
						telemetryState.setRestWaterState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==12){
						telemetryState.setElectricState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==13){
						telemetryState.setThreePhaseState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==14){
						telemetryState.setPipelineState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==15){
						telemetryState.setMachineState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==16){
						telemetryState.setBalance(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==17){
						telemetryState.setLeakageWaterState(Integer.parseInt(str.substring(i,i+1)));
					}
				}
				stationTime.setTelemetryState(telemetryState);
				stationTime.setResolve(resolve+"" +telemetryState.toString());
				return stationTime;
			}
		}else if(object instanceof StationAdd){
			StationAdd stationTime = (StationAdd)object;
			if("".equals(stationTime.getStatus())||stationTime.getStatus()==null){
				return stationTime;
			}else{
				String str = StringReplaceUtils.hexString2binaryString(stationTime.getStatus());
				str = new StringBuilder(str).reverse().toString();
				String resolve = stationTime.getResolve();
				TelemetryState telemetryState = new TelemetryState();
				for(int i=0;i<str.length();i++){
					if(i==0){
						telemetryState.setChargeState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==1){
						telemetryState.setVoltageState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==2){
						telemetryState.setWaterTransfiniteState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==3){
						telemetryState.setFlowTransfiniteState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==4){
						telemetryState.setWaterQualityTransfiniteState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==5){
						telemetryState.setFlowMeterState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==6){
						telemetryState.setWaterLevelMeterState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==7){
						telemetryState.setTerminalBoxDoor(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==8){
						telemetryState.setMemoryState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==9){
						telemetryState.setiCCardFunction(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==10){
						telemetryState.setWaterPumpState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==11){
						telemetryState.setRestWaterState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==12){
						telemetryState.setElectricState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==13){
						telemetryState.setThreePhaseState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==14){
						telemetryState.setPipelineState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==15){
						telemetryState.setMachineState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==16){
						telemetryState.setBalance(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==17){
						telemetryState.setLeakageWaterState(Integer.parseInt(str.substring(i,i+1)));
					}
				}
				stationTime.setTelemetryState(telemetryState);
				stationTime.setResolve(resolve+"" +telemetryState.toString());
				return stationTime;
			}
		}else if(object instanceof SwitchPump){
			SwitchPump stationTime = (SwitchPump)object;
			if("".equals(stationTime.getStatus())||stationTime.getStatus()==null){
				return stationTime;
			}else{
				String str = StringReplaceUtils.hexString2binaryString(stationTime.getStatus());
				str = new StringBuilder(str).reverse().toString();
				String resolve = stationTime.getResolve();
				TelemetryState telemetryState = new TelemetryState();
				for(int i=0;i<str.length();i++){
					if(i==0){
						telemetryState.setChargeState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==1){
						telemetryState.setVoltageState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==2){
						telemetryState.setWaterTransfiniteState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==3){
						telemetryState.setFlowTransfiniteState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==4){
						telemetryState.setWaterQualityTransfiniteState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==5){
						telemetryState.setFlowMeterState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==6){
						telemetryState.setWaterLevelMeterState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==7){
						telemetryState.setTerminalBoxDoor(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==8){
						telemetryState.setMemoryState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==9){
						telemetryState.setiCCardFunction(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==10){
						telemetryState.setWaterPumpState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==11){
						telemetryState.setRestWaterState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==12){
						telemetryState.setElectricState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==13){
						telemetryState.setThreePhaseState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==14){
						telemetryState.setPipelineState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==15){
						telemetryState.setMachineState(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==16){
						telemetryState.setBalance(Integer.parseInt(str.substring(i,i+1)));
					}else if(i==17){
						telemetryState.setLeakageWaterState(Integer.parseInt(str.substring(i,i+1)));
					}
				}
				stationTime.setTelemetryState(telemetryState);
				stationTime.setResolve(resolve+"" +telemetryState.toString());
				return stationTime;
			}
		}else{
			return  object;
		}

	}


}
