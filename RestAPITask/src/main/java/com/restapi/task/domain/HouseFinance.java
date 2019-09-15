package com.restapi.task.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="institute")
@Entity
@Table(name="HOUSE_FINANCE")
@EqualsAndHashCode(of="hfNo")
@NoArgsConstructor
@AllArgsConstructor
public class HouseFinance {

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_house_finance_hf_no")
	//@SequenceGenerator(name="seq_house_finance_hf_no", sequenceName="SEQ_HOUSE_FINANCE_HF_NO", allocationSize = 1, initialValue =10000)
	private int hfNo;
	private int year;
	private int month;
	private int amount;
	
	@ManyToOne
	@JoinColumn(name="instituteCode")
	private Institute institute;

}
