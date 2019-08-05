package com.komanov.blockingqueue.jmh;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public enum QueueType {
    ArrayBlockingQueue {
        @Override
        public BlockingQueue<String> create(int capacity) {
            return new ArrayBlockingQueue<>(capacity);
        }
    },
    FairArrayBlockingQueue {
        @Override
        public BlockingQueue<String> create(int capacity) {
            return new ArrayBlockingQueue<>(capacity, true);
        }
    },
    LinkedBlockingDeque {
        @Override
        public BlockingQueue<String> create(int capacity) {
            return new LinkedBlockingDeque<>(capacity);
        }
    },
    LinkedBlockingQueue {
        @Override
        public BlockingQueue<String> create(int capacity) {
            return new LinkedBlockingQueue<>(capacity);
        }
    },
    ;

    public abstract BlockingQueue<String> create(int capacity);
}
