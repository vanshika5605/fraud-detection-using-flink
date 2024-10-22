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
            new DetailedTransaction(5L, 0L, 208.85, null),
            new DetailedTransaction(1L, 0L, 379.64, null),
            new DetailedTransaction(2L, 0L, 351.44, null),
            new DetailedTransaction(3L, 0L, 320.75, null),
            new DetailedTransaction(4L, 0L, 259.42, null),
            new DetailedTransaction(5L, 0L, 273.44, null),
            new DetailedTransaction(1L, 0L, 267.25, null),
            new DetailedTransaction(2L, 0L, 397.15, null),
            new DetailedTransaction(3L, 0L, 0.219, null),
            new DetailedTransaction(4L, 0L, 231.94, null),
            new DetailedTransaction(5L, 0L, 384.73, null),
            new DetailedTransaction(1L, 0L, 419.62, null),
            new DetailedTransaction(2L, 0L, 412.91, null),
            new DetailedTransaction(3L, 0L, 0.77, null),
            new DetailedTransaction(4L, 0L, 22.1, null),
            new DetailedTransaction(5L, 0L, 377.54, null),
            new DetailedTransaction(1L, 0L, 375.44, null),
            new DetailedTransaction(2L, 0L, 230.18, null),
            new DetailedTransaction(3L, 0L, 0.8, null),
            new DetailedTransaction(4L, 0L, 350.89, null),
            new DetailedTransaction(5L, 0L, 127.55, null),
            new DetailedTransaction(1L, 0L, 483.91, null),
            new DetailedTransaction(2L, 0L, 228.22, null),
            new DetailedTransaction(3L, 0L, 871.15, null),
            new DetailedTransaction(4L, 0L, 64.19, null),
            new DetailedTransaction(5L, 0L, 79.43, null),
            new DetailedTransaction(1L, 0L, 56.12, null),
            new DetailedTransaction(2L, 0L, 256.48, null),
            new DetailedTransaction(3L, 0L, 148.16, null),
            new DetailedTransaction(4L, 0L, 199.95, null),
            new DetailedTransaction(5L, 0L, 252.37, null),
            new DetailedTransaction(1L, 0L, 274.73, null),
            new DetailedTransaction(2L, 0L, 473.54, null),
            new DetailedTransaction(3L, 0L, 119.92, null),
            new DetailedTransaction(4L, 0L, 323.59, null),
            new DetailedTransaction(5L, 0L, 353.16, null),
            new DetailedTransaction(1L, 0L, 211.9, null),
            new DetailedTransaction(2L, 0L, 280.93, null),
            new DetailedTransaction(3L, 0L, 347.89, null),
            new DetailedTransaction(4L, 0L, 459.86, null),
            new DetailedTransaction(5L, 0L, 82.31, null),
            new DetailedTransaction(1L, 0L, 373.26, null),
            new DetailedTransaction(2L, 0L, 479.83, null),
            new DetailedTransaction(3L, 0L, 454.25, null),
            new DetailedTransaction(4L, 0L, 83.64, null),
            new DetailedTransaction(5L, 0L, 292.44, null)
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

    public DetailedTransaction next() {
        DetailedTransaction transaction = (DetailedTransaction) data.get(this.index++);
        transaction.setTimestamp(this.timestamp);
        transaction.setZipCode(randomZipCode());
        this.timestamp += SIX_MINUTES;
        return transaction;
    }

    private String randomZipCode() {
        return ZIP_CODES[random.nextInt(ZIP_CODES.length)];
    }
}