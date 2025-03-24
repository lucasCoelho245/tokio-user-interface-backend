package br.com.tokiomarine.seguradora.repository;

import br.com.tokiomarine.seguradora.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
