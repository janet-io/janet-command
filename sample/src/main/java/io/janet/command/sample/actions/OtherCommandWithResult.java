package io.janet.command.sample.actions;


import io.janet.Command;
import io.janet.command.annotations.CommandAction;

@CommandAction
public class OtherCommandWithResult extends Command<String> {

    @Override
    protected void run(CommandCallback<String> callback) throws Throwable {
        callback.onSuccess("Result");
    }
}
