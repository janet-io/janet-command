package io.janet.command.sample.actions;


import io.janet.Command;
import io.janet.command.annotations.CommandAction;

@CommandAction
public class CommandWithResult extends Command<String> {

    @Override
    protected void run(CommandCallback<String> callback) throws Throwable {
        callback.onSuccess("Result");
    }
}
