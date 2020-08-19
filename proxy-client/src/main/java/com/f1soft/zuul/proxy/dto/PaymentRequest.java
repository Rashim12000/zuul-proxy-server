package com.f1soft.zuul.proxy.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rashim Dhaubanjar
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String name;
    private String amount;
    private boolean savePayment;
    private String subscriptionType;
    private String notes;

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", savePayment=" + savePayment +
                ", subscriptionType='" + subscriptionType + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}