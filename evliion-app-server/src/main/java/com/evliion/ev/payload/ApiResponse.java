package com.evliion.ev.payload;

/**
 * 
 */
public class ApiResponse {
    private Boolean success;
    private String message;
    private Long id;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(Boolean success, String message, Long id) {
		super();
		this.success = success;
		this.message = message;
		this.id = id;
	}

	public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
