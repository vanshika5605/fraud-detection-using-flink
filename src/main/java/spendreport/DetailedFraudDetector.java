package spendreport;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import spendreport.entity.DetailedAlert;
import spendreport.entity.DetailedTransaction;

/**
 * Fraud detector based on transaction amounts and zip codes.
 * Triggers an alert if a small transaction is followed by a large transaction
 * with the same zip code within a short period of time.
 */
public class DetailedFraudDetector extends KeyedProcessFunction<Long, DetailedTransaction, DetailedAlert> {

    private static final double SMALL_AMOUNT = 1.00;
    private static final double LARGE_AMOUNT = 500.00;
    private static final long ONE_MINUTE = 60 * 1000; // 1 minute window

    // State to hold information about the last small transaction amount for each account
    //  declares a private, transient field that stores the last small transaction for each account in the form of a 
    // ValueState object, where the state will contain a DetailedTransaction.
    private transient ValueState<DetailedTransaction> lastSmallTransactionState;

    @Override
    // initialize the state that will be used to store information about small transactions.
    public void open(org.apache.flink.configuration.Configuration parameters) throws Exception {
        ValueStateDescriptor<DetailedTransaction> descriptor = new ValueStateDescriptor<>(
            "last-small-transaction", // State name
            DetailedTransaction.class // State type
        );
        lastSmallTransactionState = getRuntimeContext().getState(descriptor);
    }

    @Override
    public void processElement(
            DetailedTransaction transaction,
            Context context,
            Collector<DetailedAlert> collector) throws Exception {

        // Get the previous small transaction from state
        DetailedTransaction lastSmallTransaction = lastSmallTransactionState.value();

        if (lastSmallTransaction != null) {
            // Check if the current transaction is large and within 1 minute
            boolean isLargeTransaction = transaction.getAmount() >= LARGE_AMOUNT;
            boolean withinTimeWindow = (transaction.getTimestamp() - lastSmallTransaction.getTimestamp()) < ONE_MINUTE;
            boolean sameZipCode = transaction.getZipCode().equals(lastSmallTransaction.getZipCode());

            if (isLargeTransaction && withinTimeWindow && sameZipCode) {
                // Fraud detected: create and emit an alert
                DetailedAlert alert = new DetailedAlert(
                    transaction.getAccountId(),
                    transaction.getTimestamp(),
                    transaction.getZipCode(),
                    transaction.getAmount()
                );
                collector.collect(alert);
                // Clear the state after alerting
                lastSmallTransactionState.clear();
                return;
            }
        }

        // Check if the current transaction is small
        if (transaction.getAmount() <= SMALL_AMOUNT) {
            // Update the state with this small transaction
            lastSmallTransactionState.update(transaction);
        } else {
            // Clear state if it's not a small transaction, to reset the process
            lastSmallTransactionState.clear();
        }
    }
}
