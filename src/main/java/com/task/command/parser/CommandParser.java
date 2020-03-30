package com.task.command.parser;

import com.task.command.AbstractCommand;
import com.task.command.impl.DeleteAllCommand;
import com.task.command.impl.InsertCommand;
import com.task.command.impl.ShowAllCommand;
import com.task.command.parser.exception.CommandNotFoundException;
import com.task.dao.service.SUserService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command parser
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandParser {
    // pattern matching only non white characters
    private static final Pattern ONLY_NON_WHITE_CHARS = Pattern.compile("\\S*");

    // Singleton
    private static CommandParser INSTANCE;

    @NonNull
    private SUserService sUserService;

    /**
     * Get instance of CommandParser
     * @param sUserService user service processing commands
     * @return instance of {@link CommandParser}
     */
    public static CommandParser getInstance(SUserService sUserService) {
        if (INSTANCE == null) {
            INSTANCE = new CommandParser(sUserService);
        }

        return INSTANCE;
    }

    /**
     * Get command for que from provided string
     * @param command string with command
     * @return {@link AbstractCommand} command for queuing
     */
    public AbstractCommand parseCommand(String command) {

        // get command part
        Matcher matcher = ONLY_NON_WHITE_CHARS.matcher(command);

        String commandName = StringUtils.EMPTY;
        if (matcher.find()) {
            commandName = matcher.group();
        }

        // get command based on provided string
        return getCommand(commandName, command);
    }

    private AbstractCommand getCommand(String commandName, String command) {
        AbstractCommand foundCommand;
        CommandEnum chosenCommand = CommandEnum.getByCommandName(commandName);

        // create command according to found enum
        switch (chosenCommand) {
            case DELETE:
                foundCommand = new DeleteAllCommand(sUserService);
                break;
            case INSERT:
                foundCommand = InsertCommand.of(command, sUserService);
                break;
            case SHOW_ALL:
                foundCommand = new ShowAllCommand(sUserService);
                break;
            default:
                throw new CommandNotFoundException();
        }

        return foundCommand;
    }
}
