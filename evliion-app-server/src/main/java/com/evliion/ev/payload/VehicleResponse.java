package com.evliion.ev.payload;
import javax.validation.constraints.NotNull;

public class VehicleResponse extends VehicleRequest {
    private boolean success;
    
    private Long id;

    private UserSummary createdBy;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserSummary getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserSummary createdBy) {
		this.createdBy = createdBy;
	}
}

