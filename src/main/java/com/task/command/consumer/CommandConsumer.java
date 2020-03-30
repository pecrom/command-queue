package com.task.command.consumer;

import com.task.command.AbstractCommand;
import com.task.queue.QueueHandler;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Command consumer
 */
@RequiredArgsConstructor
public class CommandConsumer extends Thread {

    // queue to get the commands from
    @NonNull
    private QueueHandler queue;

    // flag if the commands should be consumed from queue
    @Setter
    private boolean keepConsuming = true;

    // flag that the command is being processed right now
    @Getter
    private boolean isBeingProcessed = false;

    /**
     * Start consuming from queue
     */
    public void startConsuming() {
        this.start();
    }

    @Override
    public void run() {

        AbstractCommand command;

        // keep processing commands
        do {
            command = queue.retrieve();
            processCommand(command);
        } while (keepConsuming);

    }

    // process command
    private void processCommand(AbstractCommand command) {
        if (command != null) {
            isBeingProcessed = true;
            command.process();
            isBeingProcessed = false;
        }
    }

}
