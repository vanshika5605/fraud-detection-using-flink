package spendreport;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import spendreport.entity.DetailedAlert;
import spendreport.entity.DetailedTransaction;
import spendreport.sink.DetailedAlertSink;
import spendreport.source.DetailedTransactionSource;

public class DetailedFraudDetectionJob {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //modified the datastream to use detailed transactions through a detailed transaction source
        DataStream<DetailedTransaction> transactions = env
                .addSource(new DetailedTransactionSource())
                .name("detailed-transactions");

        //modified datastream to generate detailed alerts as per the new DetailedFraudDetector class
        DataStream<DetailedAlert> alerts = transactions
                .keyBy(DetailedTransaction::getAccountId)
                .process(new DetailedFraudDetector())
                .name("detailed-fraud-detector");

        //uses a new DetailedAlertSink to collect alerts
        alerts
                .addSink(new DetailedAlertSink())
                .name("send-alerts");

        env.execute("Detailed Fraud Detection");
    }
}
