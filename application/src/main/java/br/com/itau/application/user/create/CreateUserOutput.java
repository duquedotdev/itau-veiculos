package br.com.itau.application.user.create;

import br.com.itau.user.User;

public record CreateUserOutput (String id){
  public static CreateUserOutput from(final String id) {
    return new CreateUserOutput(id);
  }
  public static CreateUserOutput from(final User group) {
    return new CreateUserOutput(group.getId().getValue());
  }
}
