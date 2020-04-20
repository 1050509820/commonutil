package com.basic.common.equipment.entity;

import java.io.Serializable;

/**
 * 封装返回json对象
 * 
 * @author 常康
 *
 */

public class Result<T> implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer stateCode;

	private String message;

	private T object;

	


	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 添加返回提示信息
	 * 
	 * @param msg
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	/**
	 * 返回执行成功
	 * 
	 * @return
	 */
	public static Result<Object> success() {
		Result<Object> result = new Result<Object>();
		result.setStateCode(ResultEnum.SUCCESS.getStateCode());
		result.setMessage(ResultEnum.SUCCESS.getMessage());
		return result;
	}

	/**
	 * 返回执行失败
	 * 
	 * @return
	 */
	public static Result<Object> fail() {
		Result<Object> result = new Result<Object>();
		result.setStateCode(ResultEnum.ERROR.getStateCode());
		result.setMessage(ResultEnum.ERROR.getMessage());
		return result;
	}

	/**
	 * 自定义返回结果
	 * 
	 * @param code
	 *            返回码
	 * @param msg
	 *            返回提示信息
	 * @return
	 */
	public static Result<Object> freedom(Integer code, String msg) {
		Result<Object> result = new Result<Object>();
		result.setStateCode(code);
		result.setMessage(msg);
		return result;
	}

	/**
	 * 请求成功，添加返回数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static <T> Result<T> success(T object) {
		Result<T> result = new Result<T>();
		result.setStateCode(ResultEnum.SUCCESS.getStateCode());
		result.setMessage(ResultEnum.SUCCESS.getMessage());
		result.setObject(object);
		return result;
	}

	/**
	 * 返回成功
	 * 
	 * @param msg
	 *            自定义成功提示信息
	 * @return
	 */
	public static Result<Object> success(String msg) {
		return Result.freedom(ResultEnum.SUCCESS.getStateCode(), msg);
	}

	/**
	 * 返回失败
	 * 
	 * @param msg
	 *            自定义失败原因
	 * @return
	 */
	public static Result<Object> fail(String msg) {
		return Result.freedom(ResultEnum.ERROR.getStateCode(), msg);
	}

	/**
	 * 返回失败，自定义返回数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static <T> Result<T> fail(T object) {
		Result<T> result = new Result<T>();
		result.setStateCode(ResultEnum.ERROR.getStateCode());
		result.setMessage(ResultEnum.ERROR.getMessage());
		result.setObject(object);
		return result;
	}
}
