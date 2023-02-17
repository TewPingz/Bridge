package net.pixteria.bridge.future;

import java.util.Objects;
import java.util.UUID;

public abstract class Event {

    private UUID id;

    private String instanceId;

    private String target;

    void init(final UUID id, final String instanceId, final String target) {
        this.id = id;
        this.instanceId = instanceId;
        this.target = target;
    }

    String instanceId() {
        return this.instanceId;
    }

    String target() {
        return this.target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final var event = (Event) o;
        return Objects.equals(this.id, event.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
