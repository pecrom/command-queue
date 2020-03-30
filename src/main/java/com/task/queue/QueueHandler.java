package com.task.queue;

import com.task.command.AbstractCommand;

/**
 * Interface for queue
 */
public interface QueueHandler {

    /**
     * Publish message to queue
     * @param command {@link AbstractCommand} command to be published
     */
    void publish(AbstractCommand command);

    /**
     * Get command from queue
     * @return {@link AbstractCommand} retrieve command from queue
     */
    AbstractCommand retrieve();

    /**
     * Check if queue is not empty
     * @return true / false
     */
    boolean isNotEmpty();

}
