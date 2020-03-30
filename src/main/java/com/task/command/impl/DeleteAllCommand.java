package com.task.command.impl;

import com.task.command.AbstractCommand;
import com.task.dao.service.SUserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Delete all users command
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class DeleteAllCommand extends AbstractCommand {

    @NonNull
    private SUserService sUserService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void process() {
        sUserService.deleteAll();
    }
}
