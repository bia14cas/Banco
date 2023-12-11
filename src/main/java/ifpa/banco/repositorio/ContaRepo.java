package ifpa.banco.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import ifpa.banco.modelo.ContaBancaria;

public interface ContaRepo extends JpaRepository<ContaBancaria, Long>{

}
