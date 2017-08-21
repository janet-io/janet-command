package io.janet.command.test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.janet.ActionHolder;
import io.janet.ActionService;
import io.janet.ActionServiceWrapper;
import io.janet.Command;
import io.janet.JanetException;

public class MockCommandActionService extends ActionServiceWrapper {
    private final List<Contract> contracts;

    private MockCommandActionService(ActionService actionService, List<Contract> contracts) {
        super(actionService);
        this.contracts = contracts;
    }

    @Override
    protected <A> boolean onInterceptSend(ActionHolder<A> holder) throws JanetException {
        for (Contract contract : contracts) {
            Command command = (Command) holder.action();
            if (contract.check(command)) {
                Exception e = contract.exception;
                if (e != null) throw new JanetException(e);
                else {
                    command.setResult(contract.result);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected <A> void onInterceptCancel(ActionHolder<A> holder) {
    }

    @Override
    protected <A> void onInterceptStart(ActionHolder<A> holder) {
    }

    @Override
    protected <A> void onInterceptProgress(ActionHolder<A> holder, int progress) {
    }

    @Override
    protected <A> void onInterceptSuccess(ActionHolder<A> holder) {
    }

    @Override
    protected <A> boolean onInterceptFail(ActionHolder<A> holder, JanetException e) {
        return false;
    }

    public final static class Builder {
        private final List<Contract> contracts = new CopyOnWriteArrayList<Contract>();
        private ActionService actionService;

        public Builder addContract(Contract contract) {
            contracts.add(contract);
            return this;
        }

        public Builder actionService(ActionService actionService) {
            this.actionService = actionService;
            return this;
        }

        public MockCommandActionService build() {
            return new MockCommandActionService(actionService, contracts);
        }
    }


}
