package com.evliion.ev.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import com.evliion.ev.model.audit.DateAudit;

@Entity
@Table(name="card", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"number"
		})
})
public class Card extends DateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 40)
	private String name;

	@NotBlank
	@Size(min = 16, max = 16)
	private String number;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;


	@NotNull
	@Min(1)
	@Max(12)
	private int expMonth;


	@NotNull
	private int expYear;

	public Card() {

	}
	public Card(Long id, @NotBlank @Size(max = 40) String name, @NotBlank @Size(min = 16, max = 16) String number,
			@NotBlank @Min(1) @Max(12) int expMonth, @NotBlank int expYear) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.expMonth = expMonth;
		this.expYear = expYear;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getExpMonth() {
		return expMonth;
	}
	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}
	public int getExpYear() {
		return expYear;
	}
	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
