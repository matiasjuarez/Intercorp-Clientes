package com.matiasjuarez.IntercorpTest.dao;

import com.matiasjuarez.IntercorpTest.model.client.ClienteEntity;
import com.matiasjuarez.IntercorpTest.model.kpicliente.KpiClienteDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClienteEntity, Long> {
    @Query("SELECT new com.matiasjuarez.IntercorpTest.model.kpicliente.KpiClienteDTO(AVG(c.edad), sqrt((sum(c.edad*c.edad)/count(c.edad)) - (avg(c.edad) * avg(c.edad)))) from ClienteEntity c")
    KpiClienteDTO calculateClientsKPI();
}
