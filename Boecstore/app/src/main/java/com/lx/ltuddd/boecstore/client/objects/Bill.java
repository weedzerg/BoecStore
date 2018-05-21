package com.lx.ltuddd.boecstore.client.objects;

import java.util.Date;

/**
 * Created by DaiPhongPC on 5/21/2018.
 */

public class Bill {
    private String id;
    private Customer customer;
    private Staff staff;
    private Order order;
    private Date datePay;
    private String payment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getDatePay() {
        return datePay;
    }

    public void setDatePay(Date datePay) {
        this.datePay = datePay;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public double totalPay() {
        double result = 0;
        for (Cart c : order.getLsCart()) {
            result += c.totalPrice();
        }
        return result;
    }

}
