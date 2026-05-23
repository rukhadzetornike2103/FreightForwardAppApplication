package com.code.freightforward.freightforwardapp;
//this is the librarian protecting our tables

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {
}
