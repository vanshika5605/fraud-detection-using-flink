package spendreport.sink;

import org.apache.flink.annotation.PublicEvolving;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spendreport.entity.DetailedAlert;

@PublicEvolving
public class DetailedAlertSink implements SinkFunction<DetailedAlert> {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(DetailedAlertSink.class);

    public DetailedAlertSink() {
    }

    public void invoke(DetailedAlert value, SinkFunction.Context context) {
        LOG.info(value.toString());
    }
}
