package com.psychoyogi.User.Service.controller;


import com.psychoyogi.User.Service.dto.SearchResponse;
import com.psychoyogi.User.Service.dto.UserDto;
import com.psychoyogi.User.Service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "APIs for managing user data")
@Validated
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    @Operation(summary = "Search users", description = "Free-text search on firstName, lastName, and SSN")
    @ApiResponse(responseCode = "200", description = "Search completed successfully")
    public ResponseEntity<SearchResponse<UserDto>> searchUsers(
            @Parameter(description = "Search query string")
            @RequestParam
            @NotBlank(message = "Query cannot be blank")
            @Size(min = 1, max = 100, message = "Query must be between 1 and 100 characters")
            String query) {

        SearchResponse<UserDto> response = userService.searchUsers(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a user by their unique identifier")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserDto> getUserById(
            @Parameter(description = "User ID")
            @PathVariable Long id) {

        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get user by email", description = "Retrieve a user by their email address")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserDto> getUserByEmail(
            @Parameter(description = "User email address")
            @PathVariable
            @Email(message = "Invalid email format")
            String email) {

        UserDto user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}