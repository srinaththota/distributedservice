package com.assignment.messageservice.model;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Message implements Serializable{

	private static final long serialVersionUID = -295422703255886286L;
	
	@PrimaryKey
	private int id;
	private String value;
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", value=" + value + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Message(int id, String value) {
		super();
	
		this.id = id;
		this.value = value;
	}
	public Message() {
		super();
	}
	@Override
	public int hashCode() {
		return id+value.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		Message msgObj=(Message)obj;
		if(this.id == msgObj.id && this.value.equals(msgObj.value))
		{
			return true;
		}
		return false;
	}
	
	
}
