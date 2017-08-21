package io.janet.command.sample.actions;


import io.janet.Command;
import io.janet.command.annotations.CommandAction;

@CommandAction
public class CommandWithType extends Command<String> {

    private Type type;

    public CommandWithType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    protected void run(CommandCallback<String> callback) throws Throwable {
        callback.onSuccess("Result");
    }

    public enum Type {
        FOO, BAR
    }
}
