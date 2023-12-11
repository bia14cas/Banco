package ifpa.banco.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ifpa.banco.modelo.ContaBancaria;
import ifpa.banco.modelo.ContaBancariaVersionada;
import ifpa.banco.repositorio.ContaRepo;
import ifpa.banco.repositorio.ContaVersionadaRepo;

@RestController
public class ContaBancariaControlador {

	@Autowired
	private ContaRepo contasRepo;
	
	@Autowired
	private ContaVersionadaRepo contasVersionadasRepo;

	@GetMapping("/listagem/contas")
	public List<ContaBancaria> listarContas() {
		return contasRepo.findAll();
	}
	
	@GetMapping("/listagem/contas/versionadas")
	public List<ContaBancariaVersionada> listarContasVersionadas() {
		return contasVersionadasRepo.findAll();
	}

	@PutMapping("/depositar/conta/{id}")
	public String depositar(@PathVariable Long id, @RequestParam float valor) {
		ContaBancaria conta = contasRepo.findById(id).orElse(null);
		conta.setSaldo(conta.getSaldo() + valor);
		contasRepo.save(conta);
		return "Deposito realizado!";
	}
	
	@PutMapping("/depositar/conta/versionada/{id}")
	public String depositarContaVersionada(@PathVariable Long id, @RequestParam float valor) {
		ContaBancariaVersionada conta = contasVersionadasRepo.findById(id).orElse(null);
		conta.setSaldo(conta.getSaldo() + valor);
		contasVersionadasRepo.save(conta);
		return "Deposito realizado!";
	}

	@PutMapping("/retirar/conta/{id}")
	public String retirar(@PathVariable Long id, @RequestParam float valor) {
		ContaBancaria conta = contasRepo.findById(id).orElse(null);
		if (conta.getSaldo() >= valor) {
			conta.setSaldo(conta.getSaldo() - valor);
			contasRepo.save(conta);
			return "Retirada realizada!";
		} else {
			return "Saldo insuficiente para realizar a retirada.";
		}
	}
	
	@PutMapping("/retirar/conta/versionada/{id}")
	public String retirarContaVersionada(@PathVariable Long id, @RequestParam float valor) {
		ContaBancariaVersionada conta = contasVersionadasRepo.findById(id).orElse(null);
		if (conta.getSaldo() >= valor) {
			conta.setSaldo(conta.getSaldo() - valor);
			contasVersionadasRepo.save(conta);
			return "Retirada realizada!";
		} else {
			return "Saldo insuficiente para realizar a retirada.";
		}
	}

}

