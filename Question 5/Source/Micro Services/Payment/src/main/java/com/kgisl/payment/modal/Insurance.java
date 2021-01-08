package com.kgisl.payment.modal;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ins_insurance")
@Getter
@Setter
public class Insurance {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ins_id")
	private Integer insId;
	
	@Column(name = "ins_holder_first_name")
    private String insHolderFirstName;
	
	@Column(name = "ins_holder_last_name")
    private String insHolderLastName;
	
	@Column(name = "ins_phone_number")
    private String insPhoneNumber;
	
	@Column(name = "ins_email")
    private String insEmailId;
	
	@Column(name = "ins_premium_amount")
    private BigDecimal insPremium;
	
	@Column(name = "ins_gender")
	private String insGender;
	
	@Column(name = "ins_paid")
	private Character paid;
	
	@Column(name = "ins_paid_date")
	private Date paidDate;
}
