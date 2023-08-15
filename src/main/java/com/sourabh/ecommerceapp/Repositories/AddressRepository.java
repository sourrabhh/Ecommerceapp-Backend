package com.sourabh.ecommerceapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourabh.ecommerceapp.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>
{
    
}
