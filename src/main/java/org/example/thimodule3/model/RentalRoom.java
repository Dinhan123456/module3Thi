package org.example.thimodule3.model;

public class RentalRoom {
    private int id;
    private String tenantName;
    private String phoneNumber;
    private String startDate;
    private int paymentMethodId;
    private String paymentMethodName;
    private String note;

    public RentalRoom() {}
    public RentalRoom(int id, String tenantName, String phoneNumber, String startDate, int paymentMethodId, String paymentMethodName, String note) {
        this.id = id;
        this.tenantName = tenantName;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.paymentMethodId = paymentMethodId;
        this.paymentMethodName = paymentMethodName;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}