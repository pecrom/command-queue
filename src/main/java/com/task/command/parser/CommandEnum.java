package com.task.command.parser;

import com.task.command.parser.exception.CommandNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommandEnum {
    /**
     * Delete all
     */
    DELETE("ZmazVsetko"),

    /**
     * Insert new
     */
    INSERT("Vloz"),

    /**
     * Show all
     */
    SHOW_ALL("Vypis");

    // command name
    private String commandName;

    /**
     * Get command by its name
     * @param name name of command
     * @return {@link CommandEnum} command
     */
    public static CommandEnum getByCommandName(String name) {

        // find command by its name, otherwise throw exception
        for (CommandEnum command : values()) {
            if (command.getCommandName().equals(name)) {
                return command;
            }
        }

        throw new CommandNotFoundException();
    }
}
