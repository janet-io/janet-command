package io.janet.command.test;


import io.janet.Command;
import io.reactivex.functions.Function;

class ClassPredicate implements Function<Command, Boolean> {
    private Class<Command> clazz;

    ClassPredicate(Class<Command> clazz) {
        this.clazz = clazz;
    }

    @Override public Boolean apply(Command command) throws Exception {
        return clazz == command.getClass();
    }
}
