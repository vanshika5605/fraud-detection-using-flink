package spendreport.source;

import org.apache.flink.annotation.Public;
import org.apache.flink.streaming.api.functions.source.FromIteratorFunction;
import spendreport.entity.DetailedTransaction;

import java.io.Serializable;
import java.util.Iterator;

@Public
public class DetailedTransactionSource extends FromIteratorFunction<DetailedTransaction> {
    private static final long serialVersionUID = 1L;

    //modified transaction source to use a new iterator which implements the logic for random zip code addition to transactions
    public DetailedTransactionSource() {
        super(new RateLimitedIterator<>(DetailedTransactionIterator.unbounded()));
    }

    private static class RateLimitedIterator<T> implements Iterator<T>, Serializable {
        private static final long serialVersionUID = 1L;
        private final Iterator<T> inner;

        private RateLimitedIterator(Iterator<T> inner) {
            this.inner = inner;
        }

        public boolean hasNext() {
            return this.inner.hasNext();
        }

        public T next() {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException var2) {
                InterruptedException e = var2;
                throw new RuntimeException(e);
            }

            return this.inner.next();
        }
    }
}