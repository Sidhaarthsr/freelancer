package com.mainproject.talentbridge.service_requests.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mainproject.talentbridge.service_requests.model.ServiceRequest;
import com.mainproject.talentbridge.service_requests.service.ServiceRequestService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/service-request")
@Slf4j
public class ServiceRequestController {
    @Autowired
    private ServiceRequestService serviceRequestService;

    @PostMapping("")
    public ServiceRequest create(@RequestBody final ServiceRequest serviceRequest) {
        log.info("Creating service request");
        return serviceRequestService.createServiceRequest(serviceRequest);
    }

    @PutMapping("")
    public ServiceRequest updateServiceRequest(@RequestBody ServiceRequest serviceRequest) {
        return serviceRequestService.updateServiceRequest(serviceRequest);
    }

    @GetMapping("/id/{id}")
    public ServiceRequest getServiceRequest(@PathVariable("id") final Long serviceRequestId) {
        return serviceRequestService.getServiceRequest(serviceRequestId);
    }

    @GetMapping("/client-id/{id}")
    public List<ServiceRequest> getClientServiceRequests(@PathVariable("id") final Long clientId) {
        return serviceRequestService.getClientServiceRequests(clientId);
    }

    @GetMapping("/freelancer-id/{id}")
    public List<ServiceRequest> getFreelancerServiceRequests(@PathVariable("id") final Long freelancerId) {
        return serviceRequestService.getFreelancerServiceRequests(freelancerId);
    }

    @GetMapping("/search")
    public List<ServiceRequest> search(@RequestParam(name = "searchTerm") final String searchTerm) {
        if (searchTerm == null || "".equals(searchTerm)) {
            return serviceRequestService.findAll();
        } else {
            return serviceRequestService.search(searchTerm);
        }

    }
}
