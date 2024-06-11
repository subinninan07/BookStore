package com.bookstore.entity;

import lombok.Data;

@Data
public class OrderProductQuantity {

	private Integer productId;
	private Integer quantity;

	public OrderProductQuantity() {
		super();
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
