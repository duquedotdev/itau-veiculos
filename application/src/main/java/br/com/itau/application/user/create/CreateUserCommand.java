package br.com.itau.application.user.create;

import java.time.LocalDate;
import java.util.List;

public record CreateUserCommand (
  String name,  
  String email,  
  LocalDate birthdate,
  String address,
  List<String> skills,
  Boolean isActive

){
  public static CreateUserCommand with(final String name,
                                       final String email,
                                       final LocalDate birthdate,
                                       final String address,
                                       final List<String> skills,
                                       final Boolean isActive
                                       )                                      
    {
    return new CreateUserCommand(name, email, birthdate, address, skills, isActive);
  }
}
