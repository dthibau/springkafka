package org.formation.service;

import org.formation.domain.Coursier;
import org.formation.domain.CoursierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class CoursierService {


    public void moveCoursier(long id, double lat, double lon) {

       
    }

}
