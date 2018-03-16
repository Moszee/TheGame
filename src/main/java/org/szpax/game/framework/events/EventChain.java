package org.szpax.game.framework.events;

import org.szpax.game.framework.api.EventBuilder;
import org.szpax.game.framework.model.Realm;
import org.szpax.game.framework.api.EventExecutor;
import org.szpax.game.framework.executors.SaturatingEventExecutor;
import org.szpax.game.framework.executors.SingleEventExecutor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class EventChain {

    private final List<EventExecutor> events;

    private EventChain(List<EventExecutor> events) {
        this.events = new LinkedList<>(events);
    }

    public void play(Realm realm) {
        events.stream()
                .filter(event -> event.canExecuteIn(realm))
                .forEach(event -> event.executeIn(realm));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final List<EventExecutor> events = new ArrayList<>();
        private final List<EventBuilder> singleEventBuilders = new ArrayList<>();
        private final List<EventBuilder> saturatingEventBuilders = new ArrayList<>();

        public Builder addSaturatingEvent(EventBuilder kingdomEvent) {
            saturatingEventBuilders.add(kingdomEvent);
            return this;
        }

        public Builder addEvent(EventBuilder kingdomEvent) {
            singleEventBuilders.add(kingdomEvent);
            return this;
        }

        public EventChain build() {
            List<SaturatingEventExecutor> saturatingExecutors = saturatingEventBuilders
                    .stream()
                    .map(it -> new SaturatingEventExecutor(it.build()))
                    .collect(toList());

            List<SingleEventExecutor> singleExecutors = singleEventBuilders
                    .stream()
                    .map(it -> new SingleEventExecutor(it.build()))
                    .collect(toList());

            events.addAll(singleExecutors);
            events.addAll(saturatingExecutors);

            return new EventChain(events);
        }
    }
}
