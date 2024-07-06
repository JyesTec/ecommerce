package com.wipro.ecommerce.dto;

import java.time.LocalDate;

import com.wipro.ecommerce.entity.Order;
import com.wipro.ecommerce.entity.Product;
import com.wipro.ecommerce.entity.Seller;


public class OrderItemDTO {
	
	    private int orderItemId;

	   
	    private Product product;


	    private Order order;

	
	    private Seller seller;

	    private int quantity;

	    private double price;
	    
	    private String status;

	    private String statusDescription;
	    
	    private LocalDate deliveryDate;

		@Override
		public String toString() {
			return "OrderItemDTO [orderItemId=" + orderItemId + ", product=" + product + ", order=" + order
					+ ", seller=" + seller + ", quantity=" + quantity + ", price=" + price + ", status=" + status
					+ ", statusDescription=" + statusDescription + ", deliveryDate=" + deliveryDate + "]";
		}

		public int getOrderItemId() {
			return orderItemId;
		}

		public void setOrderItemId(int orderItemId) {
			this.orderItemId = orderItemId;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		public Seller getSeller() {
			return seller;
		}

		public void setSeller(Seller seller) {
			this.seller = seller;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getStatusDescription() {
			return statusDescription;
		}

		public void setStatusDescription(String statusDescription) {
			this.statusDescription = statusDescription;
		}

		public LocalDate getDeliveryDate() {
			return deliveryDate;
		}

		public void setDeliveryDate(LocalDate deliveryDate) {
			this.deliveryDate = deliveryDate;
		}

		public OrderItemDTO(int orderItemId, Product product, Order order, Seller seller, int quantity, double price,
				String status, String statusDescription, LocalDate deliveryDate) {
			super();
			this.orderItemId = orderItemId;
			this.product = product;
			this.order = order;
			this.seller = seller;
			this.quantity = quantity;
			this.price = price;
			this.status = status;
			this.statusDescription = statusDescription;
			this.deliveryDate = deliveryDate;
		}

		public OrderItemDTO() {
			super();
		}
	    
	    
	    

}
