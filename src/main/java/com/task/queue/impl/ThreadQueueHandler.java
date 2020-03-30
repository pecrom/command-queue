package com.task.queue.impl;

import com.task.command.AbstractCommand;
import com.task.queue.QueueHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Queue;

/**
 * Implementation of {@link QueueHandler}
 */
@RequiredArgsConstructor
public class ThreadQueueHandler implements QueueHandler {

    // flag that the queue should be active
    private boolean active = true;

    // queue for storing commands
    @NonNull
    private Queue<AbstractCommand> commandQueue;

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void publish(AbstractCommand command) {
        commandQueue.add(command);
        notify();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized AbstractCommand retrieve() {
        while (commandQueue.isEmpty() && active) {
            try {
                wait();
            } catch (InterruptedException e) {
                active = false;
            }
        }

        // get command from queue
        return commandQueue.poll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNotEmpty() {
        return !commandQueue.isEmpty();
    }
}
