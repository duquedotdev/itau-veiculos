package br.com.itau.exceptions;

import br.com.itau.validation.handler.Notification;

public class NotificationException extends DomainException {

  public NotificationException(final String aMessage, final Notification notification) {
    super(aMessage, notification.getErrors());
  }
}
