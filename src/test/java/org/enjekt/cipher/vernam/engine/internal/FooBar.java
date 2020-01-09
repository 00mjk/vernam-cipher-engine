package org.enjekt.cipher.vernam.engine.internal;

import java.io.Serializable;

public class FooBar implements Serializable {
	private String name;
	private Integer rank;
	private Long serialNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Long getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}
	@Override
	public String toString() {
		return "FooBar [name=" + name + ", rank=" + rank + ", serialNumber=" + serialNumber + "]";
	}
	

}
