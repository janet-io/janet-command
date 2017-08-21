package io.janet.command.sample;

import java.util.concurrent.TimeUnit;

import io.janet.ActionPipe;
import io.janet.CommandActionService;
import io.janet.Janet;
import io.janet.command.sample.actions.ThreadSleepCommand;
import io.janet.command.sample.actions.WrongCommand;
import io.janet.helper.ActionStateSubscriber;
import io.reactivex.Observable;

public class CommandSample {

    public static void main(String... args) throws Throwable {
        Janet janet = new Janet.Builder()
                .addService(new CommandActionService())
                .build();

        ActionPipe<ThreadSleepCommand> actionPipe = janet.createPipe(ThreadSleepCommand.class);

        actionPipe.observe()
                .subscribe(new ActionStateSubscriber<ThreadSleepCommand>()
                        .onProgress((action, progress) -> System.out.println(progress))
                        .onSuccess(threadSleepAction -> System.out.println(threadSleepAction.getResult()))
                        .onFail((threadSleepAction1, throwable) -> throwable.printStackTrace()));

        ThreadSleepCommand action = new ThreadSleepCommand();

        Observable.fromCallable(() -> "test")
                .delay(ThreadSleepCommand.DURATION - 1000, TimeUnit.MILLISECONDS)
                .subscribe(o -> actionPipe.cancel(action));

        actionPipe.send(action);

        janet.createPipe(WrongCommand.class).send(new WrongCommand());
    }
}
