package mvc.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.model.Tarefa;
import mvc.model.TarefaDAO;


@Controller
public class TarefasController {

	@RequestMapping("/")
	public String execute() {
		System.out.println("Lógica do MVC");
		return "info";
	}

	@RequestMapping("criaTarefa")
	public String form() {
		return "formTarefa";
	}

	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) {
		if (result.hasErrors()) {
			return "formTarefa";
		}
		TarefaDAO dao = new TarefaDAO();
//		dao.adicionaTarefa(tarefa);
		dao.adicionaDescricao(tarefa);
		return "adicionada";
	}

	@RequestMapping("listaTarefas")
	public String lista(Model model) {
		TarefaDAO dao = new TarefaDAO();
		model.addAttribute("tarefas", dao.getLista());
		return "lista";
	}

	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) {
		TarefaDAO dao = new TarefaDAO();
		dao.remove(tarefa);
		return "redirect:listaTarefas";
	}

	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model) {
		TarefaDAO dao = new TarefaDAO();
		model.addAttribute("tarefa", dao.buscaPorId(id));
		return "mostra";
	}

	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		TarefaDAO dao = new TarefaDAO();
		dao.altera(tarefa);
		return "redirect:listaTarefas";
	}

}
