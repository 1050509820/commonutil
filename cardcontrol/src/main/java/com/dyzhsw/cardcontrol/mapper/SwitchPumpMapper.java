package com.dyzhsw.cardcontrol.mapper;

import com.dyzhsw.cardcontrol.dto.SwitchPump;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SwitchPumpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table telemetry_switch_pump
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table telemetry_switch_pump
     *
     * @mbggenerated
     */
    int insert(SwitchPump record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table telemetry_switch_pump
     *
     * @mbggenerated
     */
    int insertSelective(SwitchPump record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table telemetry_switch_pump
     *
     * @mbggenerated
     */
    SwitchPump selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table telemetry_switch_pump
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SwitchPump record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table telemetry_switch_pump
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SwitchPump record);

    List<SwitchPump> selectSwitchPump(@Param("fuctionCode") String fuctionCode,@Param("telemetryAddress") String telemetryAddress,@Param("centerAdress") String centerAdress,@Param("startTime") String startTime,@Param("endTime") String endTime);

    List<SwitchPump> selectAll(@Param("fuctionCode") String fuctionCode,@Param("telemetryAddress") String telemetryAddress,@Param("startTime") String startTime,@Param("endTime") String endTime);

}