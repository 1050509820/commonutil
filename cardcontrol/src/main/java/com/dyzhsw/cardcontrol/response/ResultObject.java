package com.dyzhsw.cardcontrol.response;

import java.util.HashMap;
import java.util.Map;
/**
 * 封装返回json对象
 */

public class ResultObject {

	/** 返回码 */
	private String stateCode;
	/** 提示信息 */
	private String message;

	/** 返回的数据 */
	private Map<Object, Object> object = new HashMap<Object, Object>();

	public String getStateCode() {
		return stateCode;
	}

	/**
	 * 添加返回码
	 * 
	 * @param stateCode
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 添加返回提示信息
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public Map<Object, Object> getObject() {
		return object;
	}

	/**
	 * 添加返回数据
	 * 
	 * @param key
	 * @param value
	 */
	public void setObject(Object key, Object value) {
		this.object.put(key, value);
	}

	/**
	 * 可以连接追加调用此方法，添加返回数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public ResultObject add(Object key, Object value) {
		this.object.put(key, value);
		return this;
	}

	/**
	 * 返回执行成功
	 * 
	 * @return
	 */
	public static ResultObject success() {
		ResultObject result = new ResultObject();
		result.setStateCode(ResultCodeEnum.SUCCESS.code);
		result.setMessage("请求成功");
		return result;
	}

	/**
	 * 返回执行失败
	 * 
	 * @return
	 */
	public static ResultObject fail() {
		ResultObject result = new ResultObject();
		result.setStateCode(ResultCodeEnum.CONTENTNOTFOUND.code);
		result.setMessage("请求失败");
		return result;
	}

	/**
	 * 自定义返回结果
	 * 
	 * @param stateCode
	 *            返回码
	 * @param message
	 *            返回提示信息
	 * @return
	 */
	public static ResultObject ret(String stateCode, String message) {
		ResultObject result = new ResultObject();
		result.setStateCode(stateCode);
		result.setMessage(message);
		return result;
	}

	/**
	 * 请求成功，添加返回数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static ResultObject success(Object key, Object value) {
		return ResultObject.success().add(key, value);
	}

	/**
	 * 返回成功
	 * 
	 * @param message
	 *            自定义成功提示信息
	 * @return
	 */
	public static ResultObject success(String message) {
		return ResultObject.ret(ResultCodeEnum.SUCCESS.code, message);
	}

	/**
	 * 返回失败
	 * 
	 * @param message
	 *            自定义失败原因
	 * @return
	 */
	public static ResultObject fail(String failCode, String message) {
		return ResultObject.ret(failCode, message);
	}

	/**
	 * 返回失败，自定义返回数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static ResultObject fail(Object key, Object value) {
		return ResultObject.fail().add(key, value);
	}

}
