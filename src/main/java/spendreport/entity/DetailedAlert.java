package spendreport.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailedAlert {
    private long id; //accountID?
    private long timestamp;
    private double amount;
    // introducing zipcode for every alert to add extra information about the fraud
    private String zipCode;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            DetailedAlert alert = (DetailedAlert) o;
            return this.id == alert.id && this.timestamp == alert.timestamp && Double.compare(alert.amount, this.amount) == 0 && Objects.equals(this.zipCode, alert.zipCode);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.timestamp, this.zipCode, this.amount});
    }

    public String toString() {
        return "Alert{id=" + this.id + ", timestamp=" + this.timestamp + ",zipCode=" + this.zipCode + ", amount=" + this.amount + '}';
    }
}
