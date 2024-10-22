package spendreport.source;

import spendreport.entity.DetailedTransaction;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

class DetailedTransactionIterator implements Iterator<DetailedTransaction>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Timestamp INITIAL_TIMESTAMP = Timestamp.valueOf("2019-01-01 00:00:00");
    private static final long SIX_MINUTES = 360000L;
    private final boolean bounded;
    private int index = 0;
    private long timestamp;
    private final Random random = new Random();
    private static final String[] ZIP_CODES = {"01003", "02115", "78712"};

    private static List<DetailedTransaction> data = Arrays.asList(
            new DetailedTransaction(1L, 0L, 188.23, null),
            new DetailedTransaction(2L, 0L, 374.79, null),
            new DetailedTransaction(3L, 0L, 112.15, null),
            new DetailedTransaction(4L, 0L, 478.75, null),
            new DetailedTransaction(5L, 0L, 208.85, null)
            // Add more transactions as necessary
    );

    public static DetailedTransactionIterator bounded() {
        return new DetailedTransactionIterator(true);
    }

    public static DetailedTransactionIterator unbounded() {
        return new DetailedTransactionIterator(false);
    }

    private DetailedTransactionIterator(boolean bounded) {
        this.bounded = bounded;
        this.timestamp = INITIAL_TIMESTAMP.getTime();
    }

    @Override
    public boolean hasNext() {
        if (this.index < data.size()) {
            return true;
        } else if (!this.bounded) {
            this.index = 0;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public DetailedTransaction next() {
        DetailedTransaction transaction = data.get(this.index++);
        transaction.setTimestamp(this.timestamp);
        transaction.setZipCode(randomZipCode());
        this.timestamp += SIX_MINUTES;
        return transaction;
    }

    private String randomZipCode() {
        return ZIP_CODES[random.nextInt(ZIP_CODES.length)];
    }
}