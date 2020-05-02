package edu.neu.store.controller.exception;

/**
 * 上傳的文件超出了限制的異常
 */
public class FileSizeOutOfLimitException 
	extends FileUploadException {

	private static final long serialVersionUID = -8449983418301878109L;

	public FileSizeOutOfLimitException() {
		super();
	}

	public FileSizeOutOfLimitException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileSizeOutOfLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileSizeOutOfLimitException(String message) {
		super(message);
	}

	public FileSizeOutOfLimitException(Throwable cause) {
		super(cause);
	}

}
