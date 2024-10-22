package spendreport.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.flink.walkthrough.common.entity.Alert;
import org.apache.flink.walkthrough.common.entity.Transaction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailedAlert {
    private long id; //accountID?
    private long timestamp;
    private String zipCode;
    private double amount;
}
