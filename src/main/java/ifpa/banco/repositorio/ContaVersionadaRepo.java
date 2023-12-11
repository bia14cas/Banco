package ifpa.banco.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import ifpa.banco.modelo.ContaBancariaVersionada;

public interface ContaVersionadaRepo extends JpaRepository<ContaBancariaVersionada, Long>{

}
