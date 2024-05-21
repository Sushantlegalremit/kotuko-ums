package com.kotuko.usermanagementsystem.user.controller;

import com.kotuko.usermanagementsystem.config.KotukoException;
import com.kotuko.usermanagementsystem.config.UmsErrorCodes;
import com.kotuko.usermanagementsystem.user.dto.request.UserRequest;
import com.kotuko.usermanagementsystem.user.dto.response.UserResponse;
import com.kotuko.usermanagementsystem.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(responseCode = "403", description = UmsErrorCodes.ACCESS_DENIED),
            @ApiResponse(responseCode = "409", description = UmsErrorCodes.USER_ALREADY_EXIST),
            @ApiResponse(responseCode = "400", description = UmsErrorCodes.REQUEST_VALIDATION_FAILED),
            @ApiResponse(responseCode = "500", description = UmsErrorCodes.INTERNAL_SERVER_ERROR)})
    @Operation(summary = "User registration ", description = "User Register in ums")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) throws Exception {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "403", description = UmsErrorCodes.ACCESS_DENIED),
            @ApiResponse(responseCode = "400", description = UmsErrorCodes.REQUEST_VALIDATION_FAILED),
            @ApiResponse(responseCode = "500", description = UmsErrorCodes.INTERNAL_SERVER_ERROR)})
    @Operation(summary = "Get User by user name  ")
    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) throws KotukoException {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "403", description = UmsErrorCodes.ACCESS_DENIED),
            @ApiResponse(responseCode = "400", description = UmsErrorCodes.REQUEST_VALIDATION_FAILED),
            @ApiResponse(responseCode = "500", description = UmsErrorCodes.INTERNAL_SERVER_ERROR)})
    @Operation(summary = "Get All User by first name ")
    @GetMapping(value = "/first-name/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponse>> getUserByFirstName(@PathVariable String firstName,@RequestParam(value = "size",defaultValue = "10") int size,
                                                                 @RequestParam(value = "page", defaultValue = "0") int page) throws KotukoException {
        return ResponseEntity.ok(userService.getUsersByFirstName(firstName,page,size));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "403", description = UmsErrorCodes.ACCESS_DENIED),
            @ApiResponse(responseCode = "400", description = UmsErrorCodes.REQUEST_VALIDATION_FAILED),
            @ApiResponse(responseCode = "500", description = UmsErrorCodes.INTERNAL_SERVER_ERROR)})
    @Operation(summary = "Get All User by last name ")
    @GetMapping(value = "/last-name/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponse>> getUserByLastName(@PathVariable String lastName,@RequestParam(value = "size",defaultValue = "10") int size,
                                                                @RequestParam(value = "page", defaultValue = "0") int page) throws KotukoException {
        return ResponseEntity.ok(userService.getUsersByLastName(lastName,page,size));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "403", description = UmsErrorCodes.ACCESS_DENIED),
            @ApiResponse(responseCode = "400", description = UmsErrorCodes.REQUEST_VALIDATION_FAILED),
            @ApiResponse(responseCode = "500", description = UmsErrorCodes.INTERNAL_SERVER_ERROR)})
    @Operation(summary = "Get All User by email ")
    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponse>> getUserByEmail(@PathVariable String email,@RequestParam(value = "size",defaultValue = "10") int size,
                                                             @RequestParam(value = "page", defaultValue = "0") int page) throws KotukoException {
        return ResponseEntity.ok(userService.getUsersByEmail(email,page,size));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "403", description = UmsErrorCodes.ACCESS_DENIED),
            @ApiResponse(responseCode = "409", description = UmsErrorCodes.USER_ALREADY_EXIST),
            @ApiResponse(responseCode = "404", description = UmsErrorCodes.USER_DOES_NOT_EXIST),
            @ApiResponse(responseCode = "400", description = UmsErrorCodes.REQUEST_VALIDATION_FAILED),
            @ApiResponse(responseCode = "500", description = UmsErrorCodes.INTERNAL_SERVER_ERROR)})
    @Operation(summary = "Update User by user id ")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserRequest request) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "403", description = UmsErrorCodes.ACCESS_DENIED),
            @ApiResponse(responseCode = "404", description = UmsErrorCodes.USER_DOES_NOT_EXIST),
            @ApiResponse(responseCode = "400", description = UmsErrorCodes.REQUEST_VALIDATION_FAILED),
            @ApiResponse(responseCode = "500", description = UmsErrorCodes.INTERNAL_SERVER_ERROR)})
    @Operation(summary = "Delete User by user id ")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) throws KotukoException {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
