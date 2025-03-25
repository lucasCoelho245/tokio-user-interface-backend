package br.com.tokiomarine.seguradora.repository;

import br.com.tokiomarine.seguradora.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByClientId(Long clientId);
}
