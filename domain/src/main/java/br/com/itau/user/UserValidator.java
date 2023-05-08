package br.com.itau.user;

import br.com.itau.validation.Error;
import br.com.itau.validation.ValidationHandler;
import br.com.itau.validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator extends Validator {
  public static final int NAME_MAX_LENGTH = 255;
  public static final int NAME_MIN_LENGTH = 3;
  public static final String NO_SYMBOLS = "^[a-zA-Z0-9 ]*$";
  public static final String RFC_5322 = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
  public static final String PROVIDERS = "^([a-zA-Z0-9._%+-]+@(gmail|hotmail|outlook|yahoo)\\.(com|com\\.br|net))$" ;
  private final User user;

  public UserValidator(final User anUser, final ValidationHandler aHandler) {
    super(aHandler);
    this.user = anUser;
  }

  @Override
  public void validate() {
    checkNameConstraints();
    checkEmailConstrains();
    checkBirthdateConstraints();
    checkAddressConstraints();
    checkSkillsConstraints();
  }

  private void checkNameConstraints() {
    final var name = this.user.getName();
    if (name == null) {
      this.validationHandler().append(new Error("'name' should not be null"));
      return;
    }

    if (name.isBlank()) {
      this.validationHandler().append(new Error("'name' should not be empty"));
      return;
    }

    final int length = name.trim().length();
    if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
      this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
    }
    
    final Pattern pattern = Pattern.compile(NO_SYMBOLS);
    final Matcher matcher = pattern.matcher(name);
    
    if (!matcher.matches()) {
      this.validationHandler().append(new Error("'name' should not contain symbols"));
    }
    
  }
  
  private void checkEmailConstrains() {
    final var email = this.user.getEmail();

    if (email == null) {
      this.validationHandler().append(new Error("'email' should not be null"));
      return;
    }

    if (email.isBlank()) {
      this.validationHandler().append(new Error("'email' should not be empty"));
      return;
    }

    //Valida se o email está no formato correto via RFC 5322
    final Pattern emailPattern = Pattern.compile(RFC_5322);
    final Matcher matcher = emailPattern.matcher(email);
    
    if (!matcher.matches()) {
      this.validationHandler().append(new Error("'email' format should be 'aaa@bbb.com'"));
    }
    
    //Valida se o email é de um dos provedores permitidos
    final Pattern providersPattern = Pattern.compile(PROVIDERS);
    final Matcher providersMatcher = providersPattern.matcher(email);
    
    if (!providersMatcher.matches()) {
      this.validationHandler().append(new Error("'email' should be from gmail, hotmail, outlook or yahoo"));
    }
    
  }
  
  
  private void checkBirthdateConstraints() {
    final var birthdate = this.user.getBirthdate();
    if (birthdate == null) {
      this.validationHandler().append(new Error("'birthdate' should not be null"));
      return;
    }

    LocalDate now = LocalDate.now();   

    int age = Period.between(birthdate, now).getYears();
    
    if (age < 18) {
      this.validationHandler().append(new Error("'birthdate' should be hire then 18 years"));
    }
    
  }
  
  private void checkAddressConstraints() {
    final var address = this.user.getAddress();
    if (address == null) {
      this.validationHandler().append(new Error("'address' should not be null"));
      return;
    }

    if (address.isBlank()) {
      this.validationHandler().append(new Error("'address' should not be empty"));
      return;
    }

    final int length = address.trim().length();
    if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
      this.validationHandler().append(new Error("'address' must be between 3 and 255 characters"));
    }
    
    final Pattern pattern = Pattern.compile(NO_SYMBOLS);
    final Matcher matcher = pattern.matcher(address);
    
    if (!matcher.matches()) {
      this.validationHandler().append(new Error("'address' should not contain symbols"));
    }
    
  }
  
  private void checkSkillsConstraints(){
    final var skills = this.user.getSkills();
    if (skills == null) {
      this.validationHandler().append(new Error("'skills' should not be null"));
      return;
    }
    
    if (skills.stream().anyMatch(skill -> skill.isBlank())) {
      this.validationHandler().append(new Error("'skills' should not be empty"));
      return;
    }
    
    if (skills.stream().anyMatch(skill -> skill.length() > NAME_MAX_LENGTH || skill.length() < NAME_MIN_LENGTH)) {
      this.validationHandler().append(new Error("'skills' must be between 3 and 255 characters"));
    }
    
    final Pattern pattern = Pattern.compile(NO_SYMBOLS);
    
    if (skills.stream().anyMatch(skill -> {
        final Matcher matcher = pattern.matcher(skill);
        return !matcher.matches();
      })) {
        this.validationHandler().append(new Error("'skills' should not contain symbols"));
      }
    
    
  }
  
  

}
