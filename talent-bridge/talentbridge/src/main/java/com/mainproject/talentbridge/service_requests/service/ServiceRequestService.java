package com.mainproject.talentbridge.service_requests.service;

import java.util.List;

import com.mainproject.talentbridge.service_requests.model.ServiceRequest;

public interface ServiceRequestService {

    ServiceRequest createServiceRequest(final ServiceRequest serviceRequest);

    ServiceRequest updateServiceRequest(final ServiceRequest serviceRequest);

    ServiceRequest getServiceRequest(final long serviceRequestId);

    List<ServiceRequest> getClientServiceRequests(final long clientId);

    List<ServiceRequest> getFreelancerServiceRequests(final long freelancerId);

    List<ServiceRequest> search(final String searchTerm);

    List<ServiceRequest> findAll();

}
