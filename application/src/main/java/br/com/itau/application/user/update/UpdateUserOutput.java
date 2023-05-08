package br.com.itau.application.user.update;

import br.com.itau.user.User;

public record UpdateUserOutput(String id) {
  public static UpdateUserOutput from(final String id) {
    return new UpdateUserOutput(id);
  }
  
  public static UpdateUserOutput from(final User user) {
    return new UpdateUserOutput(user.getId().getValue());
  }
}
