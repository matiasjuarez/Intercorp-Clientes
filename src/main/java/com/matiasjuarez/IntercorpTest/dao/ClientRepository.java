package com.matiasjuarez.IntercorpTest.dao;

import com.matiasjuarez.IntercorpTest.model.client.ClienteEntity;
import com.matiasjuarez.IntercorpTest.model.kpicliente.KpiClienteDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClienteEntity, Long> {
    @Query("SELECT AVG(c.edad) as promedioEdad, STDEV(c.edad) as desviacionEstandarEdad from cliente c")
    KpiClienteDTO calculateClientsKPI();
}
