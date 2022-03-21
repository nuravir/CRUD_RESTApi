package com.pb.demo.exceptions;

public class ResourceNotValidException extends RuntimeException {

	private static final long serialVersionUID = 2L;
	public ResourceNotValidException(String msg) {
    super(msg);
	  }
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}
