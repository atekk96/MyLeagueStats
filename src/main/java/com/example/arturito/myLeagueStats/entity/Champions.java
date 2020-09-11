package com.example.arturito.myLeagueStats.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="champions")
public class Champions {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="value")
	private int value;
	
	@Column(name="label")
	private String label;
	
	public Champions() {
		
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
