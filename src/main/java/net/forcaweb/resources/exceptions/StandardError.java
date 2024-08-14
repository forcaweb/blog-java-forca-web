package net.forcaweb.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Instant timeStamp;
	private Integer status;
	private String message;
	private String path;
	private String error;
	
	public StandardError() {}

	public StandardError(Instant timeStamp, Integer status, String message, String path, String error) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.message = message;
		this.path = path;
		this.error = error;
	}

	public Instant getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Instant timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
