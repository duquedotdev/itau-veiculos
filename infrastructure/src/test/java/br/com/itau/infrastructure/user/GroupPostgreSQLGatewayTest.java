package br.com.itau.infrastructure.user;


import br.com.itau.infrastructure.PostgreSQLGatewayTest;
import br.com.itau.infrastructure.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@PostgreSQLGatewayTest
public class GroupPostgreSQLGatewayTest {
  
  @Autowired
  private UserPostgreSQLGateway gateway;
  
  @Autowired
  private UserRepository repository;
  
  
//  @Test  
//  public void testInjectedDependencies() {
//    Assertions.assertNotNull(gateway);
//    Assertions.assertNotNull(repository);
//  }
  
}
