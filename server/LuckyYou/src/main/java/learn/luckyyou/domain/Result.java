package learn.luckyyou.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Result <T>{
    private final ArrayList<String> messages = new ArrayList<>();
    private ResultType type = ResultType.SUCCESS;
    private T payload;

    public ResultType getType() {
        return type;
    }

    public boolean isSuccess() {
        return type == ResultType.SUCCESS;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public void addMessage(String message, ResultType type) {
        messages.add(message);
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Result<?> result)) return false;
        return Objects.equals(messages, result.messages) && type == result.type && Objects.equals(payload, result.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, type, payload);
    }
}
