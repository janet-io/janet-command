package io.janet.command.test;


import io.janet.Command;
import io.reactivex.functions.Function;

public class FilterContract<T extends Command> extends BaseContract {

    private BaseContract contract;

    FilterContract(Function<Command, Boolean> predicate) {
        super(predicate);
    }

    FilterContract(Function<Command, Boolean> predicate, BaseContract contract) {
        super(predicate);
        this.contract = contract;
    }

    public FilterContract filter(Function<T, Boolean> filterFunction) {
        return new FilterContract(filterFunction, this);
    }

    @Override public boolean check(Command command) {
        boolean result = true;
        if (contract != null) {
            result = contract.check(command);
        }
        if (result) {
            result = super.check(command);
        }
        return result;
    }
}
