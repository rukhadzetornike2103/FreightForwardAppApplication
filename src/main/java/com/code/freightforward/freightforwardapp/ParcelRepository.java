package com.code.freightforward.freightforwardapp;
//this is the librarian protecting our tables

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {
    //we just passed in the table/class name, and it's ID type we will be searching for in that SPECIFIC table
    List<Parcel> findByContainerIsNull();//this is to find parcel's which do not have containers
}
