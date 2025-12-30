package com.example.bank_internal_management_tool;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepo automatically supplies these
//	save() → create or update a request
//	findById() → get a request by ID
//	findAll() → get all requests
//	deleteById() → remove a request
@Repository
public interface ClientRequestRepository extends JpaRepository<ClientRequest, Long> {

}
