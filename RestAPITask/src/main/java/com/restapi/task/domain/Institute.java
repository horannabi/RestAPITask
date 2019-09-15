package com.restapi.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ToString
@Entity
@Table(name="INSTITUTE")
@EqualsAndHashCode(of="instituteCode")
@NoArgsConstructor
@AllArgsConstructor
public class Institute {
	
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_institute_code")
	//@SequenceGenerator(name="seq_institute_code", sequenceName="SEQ_INSTITUTE_CODE", allocationSize = 1, initialValue =1)
	@Id
	private String instituteCode;
	
	private String instituteName;
}
