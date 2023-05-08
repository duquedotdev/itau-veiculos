package br.com.itau.infrastructure.api;


import br.com.itau.infrastructure.user.models.CreateUserRequest;
import br.com.itau.infrastructure.user.models.UpdateUserRequest;
import br.com.itau.infrastructure.user.models.UserListResponse;
import br.com.itau.infrastructure.user.models.UserResponse;
import br.com.itau.pagination.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "users")
@Tag(name = "Users")
public interface UserAPI {
  
  // ====================== CREATE ======================
  @PostMapping(
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @Operation(summary = "Create a new user")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Created successfully"),
    @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
  })
  ResponseEntity<?> createUser(@RequestBody CreateUserRequest input);


  // ====================== LIST ======================

  @GetMapping
  @Operation(summary = "List all groups paginated")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Listed successfully"),
    @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
  })
  Pagination<UserListResponse> listUsers(
    @RequestParam(name = "search", required = false, defaultValue = "") final String search,
    @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
    @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
    @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
    @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
  );
  
  
  // ====================== GET ======================

  @GetMapping(
    value = "{id}",
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @Operation(summary = "Get a user by it's identifier")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Group retrieved successfully"),
    @ApiResponse(responseCode = "404", description = "Group was not found"),
    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
  })
  UserResponse getById(@PathVariable(name = "id") String id);
 
  // ====================== UPDATE ======================

  @PutMapping(
    value = "{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @Operation(summary = "Update a user by it's identifier")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Group updated successfully"),
    @ApiResponse(responseCode = "404", description = "Group was not found"),
    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
  })
  Object updateById(@PathVariable(name = "id") String id, @RequestBody UpdateUserRequest input);

  
  // ====================== DELETE ======================

  @DeleteMapping(value = "{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Delete a group by it's identifier")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Group deleted successfully"),
    @ApiResponse(responseCode = "404", description = "Group was not found"),
    @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
  })
  void deleteById(@PathVariable(name = "id") String id);

}
