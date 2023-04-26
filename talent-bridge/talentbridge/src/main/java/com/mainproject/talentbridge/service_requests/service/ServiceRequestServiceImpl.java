package com.mainproject.talentbridge.service_requests.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mainproject.talentbridge.service_requests.model.ServiceRequest;
import com.mainproject.talentbridge.service_requests.repository.ServiceRequestRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceRequestServiceImpl implements ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Override
    public ServiceRequest createServiceRequest(final ServiceRequest serviceRequest) {
        log.info("Creating service request in repository : {}", serviceRequest.getId());
        return serviceRequestRepository.save(serviceRequest);
    }

    @Override
    public ServiceRequest updateServiceRequest(final ServiceRequest serviceRequest) {
        log.info("Updating service request in repository : {}", serviceRequest.getId());

        return serviceRequestRepository.update(serviceRequest);
    }

    @Override
    public ServiceRequest getServiceRequest(long serviceRequestId) {
        log.info("Fetching service request from repository : {}", serviceRequestId);
        return serviceRequestRepository.findById(serviceRequestId).orElse(null);
    }

    @Override
    public List<ServiceRequest> getClientServiceRequests(long clientId) {
        log.info("Fetching service requests for client : {}" + clientId);
        return serviceRequestRepository.findByClientId(clientId);
    }

    @Override
    public List<ServiceRequest> getFreelancerServiceRequests(long freelancerId) {
        log.info("Fetching service requests for freelancer : {}", freelancerId);
        return serviceRequestRepository.findByAssignee(freelancerId);
    }

    @Override
    public List<ServiceRequest> search(String searchTerm) {
        return serviceRequestRepository.searchByField(searchTerm);
    }

    @Override
    public List<ServiceRequest> findAll() {
        return serviceRequestRepository.findAll();
    }
}
