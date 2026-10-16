package payout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryNotifier implements Notifier {
    private final List<String> messages = new ArrayList<>();

    @Override
    public void send(String message) {
        messages.add(Objects.requireNonNull(message, "Message must not be null"));
    }

    public List<String> getMessages() {
        return List.copyOf(messages);
    }
}
