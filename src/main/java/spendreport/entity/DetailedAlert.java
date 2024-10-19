package spendreport.entity;

import org.apache.flink.walkthrough.common.entity.Alert;

public class DetailedAlert {
    private Alert alert;
    private long timestamp;
    private String zipCode;
    private double amount;
}
