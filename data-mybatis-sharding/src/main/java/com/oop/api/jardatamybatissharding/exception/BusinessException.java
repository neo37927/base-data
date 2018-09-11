package com.oop.api.jardatamybatissharding.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 业务逻辑异常
 * 
 * @ClassName: BusinessException
 * @Description:TODO
 * @author: zhangk
 * @date: 2015年11月3日 下午3:04:25
 *
 */
public class BusinessException extends RuntimeException {

	public static final Logger log = LoggerFactory.getLogger(BusinessException.class);

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	protected String exceptionKey;

	public BusinessException() {
        if(this.getLocalizedMessage()!=null)
		    log.error(this.getLocalizedMessage());
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Exception ex, String message) {
		super(message, ex);
	}

	public BusinessException(Exception ex) {
		super(ex);
		exceptionKey = "SYSTEM_ERROR";
	}

	public BusinessException(DataAccessException ex) {
		setExceptionKey(ex.getExceptionKey());
	}

	public String getExceptionKey() {
		if (StringUtils.isEmpty(exceptionKey))
			exceptionKey = "SYSTEM_ERROR";
		return exceptionKey;
	}

	protected void setExceptionKey(String exceptionKey) {
		this.exceptionKey = exceptionKey;
	}

}
