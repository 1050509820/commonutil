package com.dyjs.meeting.dao;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderInfoDto extends BaseRowModel implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.id
     *
     * @mbggenerated
     */
    private Date startTime;
    private Date endTime;

    @ExcelProperty(index=9,value = "入住酒店")
    private String hotel;

    @ExcelProperty(index=10,value = "就餐信息")
    private String dining;

    @ExcelProperty(index=11,value = "座位信息")
    private String seat;

    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getDining() {
        return dining;
    }

    public void setDining(String dining) {
        this.dining = dining;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    @ExcelProperty(index=8,value = "会议对接人")
    private String dock;

    private  String isCheck;


    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getDock() {
        return dock;
    }

    public void setDock(String dock) {
        this.dock = dock;
    }

    public Date getMoveOutTime() {
        return moveOutTime;
    }

    public void setMoveOutTime(Date moveOutTime) {
        this.moveOutTime = moveOutTime;
    }

    @ExcelProperty(index=6,format = "yyyy/MM/dd HH:mm:ss")
    private Date moveOutTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.username
     *
     * @mbggenerated
     */
    @ExcelProperty(index = 0,value="姓名")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.tel
     *
     * @mbggenerated
     */
    @ExcelProperty(index=1,value = "电话号码")
    private String tel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.unit
     *
     * @mbggenerated
     */
    @ExcelProperty(index=2,value = "单位")
    private String unit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.position
     *
     * @mbggenerated
     */
    @ExcelProperty(index=3,value = "职位")
    private String position;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.accommodation
     *
     * @mbggenerated
     */
    @ExcelProperty(index=4,value = "是否住宿(1：是 0：否)")
    private Integer accommodation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.move_into_time
     *
     * @mbggenerated
     */
    @ExcelProperty(index=5,format = "yyyy/MM/dd HH:mm:ss")
    private Date moveIntoTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.room_type
     *
     * @mbggenerated
     */
    @ExcelProperty(index=7,value = "房间类型")
    private String roomType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.orederTime
     *
     * @mbggenerated
     */
    private Date ordertime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.isValid
     *
     * @mbggenerated
     */
    private String isvalid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_info
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.id
     *
     * @return the value of order_info.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.id
     *
     * @param id the value for order_info.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.username
     *
     * @return the value of order_info.username
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.username
     *
     * @param username the value for order_info.username
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.tel
     *
     * @return the value of order_info.tel
     *
     * @mbggenerated
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.tel
     *
     * @param tel the value for order_info.tel
     *
     * @mbggenerated
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.unit
     *
     * @return the value of order_info.unit
     *
     * @mbggenerated
     */
    public String getUnit() {
        return unit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.unit
     *
     * @param unit the value for order_info.unit
     *
     * @mbggenerated
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.position
     *
     * @return the value of order_info.position
     *
     * @mbggenerated
     */
    public String getPosition() {
        return position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.position
     *
     * @param position the value for order_info.position
     *
     * @mbggenerated
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.accommodation
     *
     * @return the value of order_info.accommodation
     *
     * @mbggenerated
     */
    public Integer getAccommodation() {
        return accommodation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.accommodation
     *
     * @param accommodation the value for order_info.accommodation
     *
     * @mbggenerated
     */
    public void setAccommodation(Integer accommodation) {
        this.accommodation = accommodation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.move_into_time
     *
     * @return the value of order_info.move_into_time
     *
     * @mbggenerated
     */
    public Date getMoveIntoTime() {
        return moveIntoTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.move_into_time
     *
     * @param moveIntoTime the value for order_info.move_into_time
     *
     * @mbggenerated
     */
    public void setMoveIntoTime(Date moveIntoTime) {
        this.moveIntoTime = moveIntoTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.room_type
     *
     * @return the value of order_info.room_type
     *
     * @mbggenerated
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.room_type
     *
     * @param roomType the value for order_info.room_type
     *
     * @mbggenerated
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.orederTime
     *
     * @return the value of order_info.orederTime
     *
     * @mbggenerated
     */
    public Date getOrdertime() {
        return ordertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.orederTime
     *
     * @param ordertime the value for order_info.orederTime
     *
     * @mbggenerated
     */
    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.isValid
     *
     * @return the value of order_info.isValid
     *
     * @mbggenerated
     */
    public String getIsvalid() {
        return isvalid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.isValid
     *
     * @param isvalid the value for order_info.isValid
     *
     * @mbggenerated
     */
    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_info
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
        sb.append(", username=").append(username);
        sb.append(", tel=").append(tel);
        sb.append(", unit=").append(unit);
        sb.append(", position=").append(position);
        sb.append(", accommodation=").append(accommodation);
        sb.append(", moveIntoTime=").append(moveIntoTime);
        sb.append(", roomType=").append(roomType);
        sb.append(", ordertime=").append(ordertime);
        sb.append(", isvalid=").append(isvalid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}