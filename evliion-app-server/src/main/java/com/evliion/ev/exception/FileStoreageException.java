package com.evliion.ev.exception;

public class FileStoreageException extends RuntimeException {
	public FileStoreageException(String message) {
		super(message);
	}
	public FileStoreageException(String message, Throwable cause) {
		super(message, cause);
	}

}
