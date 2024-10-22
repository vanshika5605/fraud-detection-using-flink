package spendreport.entity;

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

    public DetailedTransaction(long accountId, long timestamp, double amount, String zipCode) {
        this.transaction = new Transaction(accountId, timestamp, amount);
        this.zipCode = zipCode;
    }

    public long getTimestamp() {
        return transaction.getTimestamp();
    }

    public void setTimestamp(long timestamp) {
        this.transaction.setTimestamp(timestamp);
    }

    public double getAmount() {
        return transaction.getAmount();
    }

    public void setAmount(double amount) {
        this.transaction.setAmount(amount);
    }

    public long getAccountId() {
        return transaction.getAccountId();
    }

    public void setAccountId(long accountId) {
        this.transaction.setAccountId(accountId);
    }
}
