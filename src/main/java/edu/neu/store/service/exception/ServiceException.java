package edu.neu.store.service.exception;

/**
 * 業務異常，是當前項目中所有業務異常的基類
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 5631578415492734367L;

	public ServiceException() {
		super();
	}

	public ServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ServiceException(String arg0) {
		super(arg0);
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
	}

}
