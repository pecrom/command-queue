package com.task;

import com.task.command.AbstractCommand;
import com.task.command.consumer.CommandConsumer;
import com.task.command.parser.CommandParser;
import com.task.dao.repository.SUsersRepository;
import com.task.dao.repository.impl.SUsersRepositoryImpl;
import com.task.dao.service.SUserService;
import com.task.dao.service.impl.SUserServiceImpl;
import com.task.queue.QueueHandler;
import com.task.queue.impl.ThreadQueueHandler;
import com.task.util.HibernateUtil;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {
        String[] commands = {"Vloz (1, \"a1\", \"Dusan\")",
                            "Vloz (2, \"a2\", \"Fero\")",
                            "Vypis",
                            "ZmazVsetko",
                            "Vypis"
        };

        // initialise
        SUsersRepository sUsersRepository = new SUsersRepositoryImpl();
        SUserService sUserService = new SUserServiceImpl(sUsersRepository);

        CommandParser parser = CommandParser.getInstance(sUserService);

        QueueHandler queue = new ThreadQueueHandler(new LinkedBlockingQueue<>());
        CommandConsumer consumer = new CommandConsumer(queue);
        consumer.startConsuming();

        AbstractCommand parsedCommand;

        // read commands and queue them
        for (String command : commands) {
            parsedCommand = parseCommand(command, parser);

            if (parsedCommand != null) {
                queue.publish(parsedCommand);
            }
        }

        waitForCommandProcessing(queue, consumer);
        finish(consumer);
    }

    // finish everything
    public static void finish(CommandConsumer commandConsumer) {
        HibernateUtil.getSessionFactory().getCurrentSession().close();
        HibernateUtil.getSessionFactory().close();
        commandConsumer.interrupt();
        commandConsumer.setKeepConsuming(false);
    }

    // wait until all commands are finished
    public static void waitForCommandProcessing(QueueHandler queueHandler, CommandConsumer commandConsumer) {
        while(queueHandler.isNotEmpty() || commandConsumer.isBeingProcessed()) {
            // left blank
        }
    }

    // parse command
    public static AbstractCommand parseCommand(String command, CommandParser parser) {
        AbstractCommand parsedCommand = null;

        try {
            parsedCommand = parser.parseCommand(command);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return parsedCommand;
    }
}
