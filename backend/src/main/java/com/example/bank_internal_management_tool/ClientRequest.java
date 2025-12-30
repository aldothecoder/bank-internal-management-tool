package com.example.bank_internal_management_tool;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ClientRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Java Persistence API auto-increment feature
    private Long id;

    private String clientName;
    private String requestType;
    private String status;                          //Pending, Approved, Denied
    private Boolean complianceVerified = false;     //Default = false
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    //Constructors
    public ClientRequest(){}

    public ClientRequest(String clientName, String requestType, String status){
        this.clientName = clientName;
        this.requestType = requestType;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getRequestType() { return requestType; }
    public void setRequestType(String requestType) { this.requestType = requestType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Boolean getComplianceVerified() { return complianceVerified; }
    public void setComplianceVerified(Boolean complianceVerified) { this.complianceVerified = complianceVerified; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

}
