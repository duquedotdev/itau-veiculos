package br.com.itau.events;

@FunctionalInterface
public interface DomainEventPublisher {
  void publishEvent(DomainEvent event);
}