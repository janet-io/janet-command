package io.janet.command.test;


import io.janet.Command;
import io.reactivex.functions.Function;

public abstract class Contract {

    private Function<Command, Boolean> predicate;

    Object result;
    Exception exception;

    Contract(Function<Command, Boolean> predicate) {
        this.predicate = predicate;
    }

    public static <K extends Command> FilterContract<K> of(Function<Command, Boolean> predicate) {
        return new FilterContract<K>(predicate);
    }

    public static <K extends Command> FilterContract<K> of(Class<K> clazz) {
        return of(new ClassPredicate((Class<Command>) clazz));
    }

    public boolean check(Command command) {
        try {
            return predicate.apply(command);
        } catch (Exception e) {
            return false;
        }
    }

}
