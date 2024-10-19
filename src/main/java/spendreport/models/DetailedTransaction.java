package spendreport.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.flink.walkthrough.common.entity.Transaction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailedTransaction {
    private Transaction transaction;
    private String zipCode;
}
