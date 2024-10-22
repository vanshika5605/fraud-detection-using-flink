package spendreport.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class DetailedTransaction {
    private long accountId;
    private long timestamp;
    private double amount;
    private String zipCode;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            DetailedTransaction that = (DetailedTransaction) o;
            return this.accountId == that.accountId && this.timestamp == that.timestamp && Double.compare(that.amount, this.amount) == 0 && Objects.equals(this.zipCode, that.zipCode);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.accountId, this.timestamp, this.amount, this.zipCode});
    }

    public String toString() {
        return "DetailedTransaction{accountId=" + this.accountId + ", timestamp=" + this.timestamp + ", amount=" + this.amount + ",zipCode=" + this.zipCode +'}';
    }
}
