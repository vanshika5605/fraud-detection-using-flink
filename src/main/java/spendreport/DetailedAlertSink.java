package spendreport;

import org.apache.flink.annotation.PublicEvolving;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.walkthrough.common.entity.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spendreport.entity.DetailedAlert;

@PublicEvolving
public class DetailedAlertSink implements SinkFunction<DetailedAlert> {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(org.apache.flink.walkthrough.common.sink.AlertSink.class);

    public DetailedAlertSink() {
    }

    public void invoke(Alert value, SinkFunction.Context context) {
        LOG.info(value.toString());
    }
}
