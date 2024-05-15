package br.com.fabios.web.repository;

import br.com.fabios.web.models.CalculoJurosCompostos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CalculoJurosCompostosRepository extends CrudRepository<CalculoJurosCompostos, Integer> {
    @Query(value = "select CASE WHEN count(1) > 0 then 'true' else 'false' END from CalculoJurosCompostos where id = :id", nativeQuery = true)
    public boolean exist(int id);

}
