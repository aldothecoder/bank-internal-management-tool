package com.example.bank_internal_management_tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/requests")
public class ClientRequestController {

    @Autowired
    private ClientRequestRepository repository;

    @GetMapping
    public List<ClientRequest> getAllRequests(){
        return repository.findAll();
    }

    //Optional meaning it may or may not return something depending on if it find it
    //Get ClientRequest by id
    @GetMapping("/{id}")
    public Optional<ClientRequest> getRequestById(@PathVariable Long id){
        return repository.findById(id);
    }

    //Create a new request
    @PostMapping
    public ClientRequest createRequest(@RequestBody ClientRequest request){
        request.setStatus("Pending");       //default status
        return repository.save(request);
    }

    //Update an existing request
    @PutMapping("/{id}")
    public ClientRequest updateRequest(@PathVariable Long id, @RequestBody ClientRequest updatedRequest){
        return repository.findById(id).map(request -> {
            request.setClientName(updatedRequest.getClientName());
            request.setRequestType(updatedRequest.getRequestType());
            request.setStatus(updatedRequest.getStatus());
            request.setComplianceVerified(updatedRequest.getComplianceVerified());
            return repository.save(request);
        }).orElseGet(() -> {
            updatedRequest.setId(id);
            return repository.save(updatedRequest);
        });
    }

    // Approve or deny request (status update)
    @PatchMapping("/{id}/status")
    public ClientRequest updateStatus(@PathVariable Long id, @RequestParam String status){
        return repository.findById(id).map(request -> {
            request.setStatus(status);
            return repository.save(request);
        }).orElseThrow(() -> new RuntimeException("Request not found"));
    }

    //Mark compliance verified
    @PatchMapping("/{id}/compliance")
    public ClientRequest markCompliance(@PathVariable Long id, @RequestParam Boolean complianceVerified){
        return repository.findById(id).map(request -> {
            request.setComplianceVerified(complianceVerified);
            return repository.save(request);
        }).orElseThrow(() -> new RuntimeException("Request not found"));
    }

    //Delete a request
    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id){
        repository.deleteById(id);
    }
}
