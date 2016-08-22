/**
 * FileName:     DaoException.java
 * CreationTime: 2010-3-29
 * Author:       qiujy
 * EMail:        qjyong@gmail.com
 * Site:         http://www.tjitcast.com
 * CopyRight: 2010-2012 All Recieves.
 */
package com.mini.server.dao;

/**
 * Dao异常
 * @author qiujy
 */
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 3650611476284189863L;

	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}
