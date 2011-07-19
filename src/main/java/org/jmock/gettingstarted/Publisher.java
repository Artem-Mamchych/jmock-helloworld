package org.jmock.gettingstarted;

public class Publisher {
    private Subscriber subscriber;

    public void add(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void publish(String message) {
        subscriber.receive(message);
    }
}
