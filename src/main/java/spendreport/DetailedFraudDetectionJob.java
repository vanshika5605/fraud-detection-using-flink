package spendreport;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import spendreport.entity.DetailedAlert;
import spendreport.entity.DetailedTransaction;
import spendreport.source.DetailedTransactionSource;

public class DetailedFraudDetectionJob {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<DetailedTransaction> transactions = env
                .addSource(new DetailedTransactionSource())
                .name("detailed-transactions");

        DataStream<DetailedAlert> alerts = transactions
                .keyBy(DetailedTransaction::getAccountId)
                .process(new DetailedFraudDetector())
                .name("detailed-fraud-detector");

        alerts
                .addSink(new DetailedAlertSink())
                .name("send-alerts");

        env.execute("Detailed Fraud Detection");
    }
}
