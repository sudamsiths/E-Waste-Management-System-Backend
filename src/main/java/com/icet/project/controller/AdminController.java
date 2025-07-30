package com.icet.project.controller;


import com.icet.project.model.dto.UsersDTO;
import com.icet.project.model.entity.LoginRequest;
import com.icet.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    final UserService userService;

    @GetMapping("/getAllAdmin")
    public List<UsersDTO> getAllUsers(UsersDTO usersDTO){
        return userService.getAllUsers(usersDTO);
    }

    @PostMapping("/addAdmin")
    public void addUsers(@RequestBody UsersDTO usersDTO ){
        userService.addUsers(usersDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String result = userService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(result);
    }
    @PutMapping("/update/admin/{id}")
    public void updateUser(@PathVariable ("id") Long id, @RequestBody UsersDTO usersDTO) {
        System.out.println("Updating user with ID: " + id + " with data: " + usersDTO);
        userService.updateUser(id,usersDTO);
    }

    @GetMapping("/search/admin/by-name/{fullName}")
    public List<UsersDTO>searchUsers(@PathVariable ("fullName") String fullName){
        System.out.println("Searching for users with full name: " + fullName);
        return userService.searchUsers(fullName);
    }

    @GetMapping("/search/admin/by-role/{role}")
    public List<UsersDTO> searchUsersByRole(@PathVariable("role") String role) {
        return userService.searchUsersByRole(role);
    }
    @GetMapping("/tickets/pending")
    public List<GarbageTicketDTO> viewPendingTickets() {
        return garbageTicketService.getPendingTickets();
    }

    @PostMapping("/assign-ticket")
    public ResponseEntity<String> assignTicket(@RequestParam Long ticketId, @RequestParam Long agentId) {
        garbageTicketService.assignAgent(ticketId, agentId);
        return ResponseEntity.ok("Agent assigned successfully");
    }


}
