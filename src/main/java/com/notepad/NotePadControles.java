package com.notepad;

import java.util.ArrayList;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotePadControles {

	private static final ArrayList<NotePad> ANOTACOES_EXEMPLOS = new ArrayList<>();

	static {

		ANOTACOES_EXEMPLOS.add(new NotePad("Ana", "Souza",
				"Olá, este é o meu primeiro bloco de notas! Estou testando como as notas são exibidas neste site. Parece simples e fácil de usar.",
				"1"));
		ANOTACOES_EXEMPLOS.add(new NotePad("Vinicíus", "Silva",
				"Testando mais uma vez! Parece que posso adicionar várias notas e elas serão exibidas de maneira organizada. Ótimo para manter minhas ideias anotadas.",
				"2"));
		ANOTACOES_EXEMPLOS.add(new NotePad("Lucas", "Melo",
				"Esta é uma nota de exemplo para mostrar como o bloco de notas pode ser usado para diferentes fins. Estou gostando do design simples e limpo.",
				"3"));
		ANOTACOES_EXEMPLOS.add(new NotePad("Mateus", "Gonçalves",
				"Estou adicionando mais uma nota para ver como elas são empilhadas. Parece que tudo está funcionando perfeitamente. Ótima ferramenta!",
				"4"));
		ANOTACOES_EXEMPLOS.add(new NotePad("Rogério", "Brito",
				"Praticar exercícios físicos pelo menos 3 vezes por semana: corrida, yoga ou musculação. Manter uma alimentação saudável e equilibrada. #VidaSaudável",
				"5"));

	}

	@GetMapping("/")
	public String index() {

		return "index";

	}

	@GetMapping("/table")

	public ModelAndView lista() {

		ModelAndView modelandview = new ModelAndView("table");

		modelandview.addObject("notePads", ANOTACOES_EXEMPLOS);

		return modelandview;

	}

	@GetMapping("/table/novo")
	public ModelAndView novo() {

		ModelAndView modelandview = new ModelAndView("table");

		modelandview.addObject("notePad", new NotePad());

		return modelandview;

	}

	@DeleteMapping("/table/{id}")

	public String remover(@PathVariable String id) {

		if (!ANOTACOES_EXEMPLOS.isEmpty()) {

			ANOTACOES_EXEMPLOS.removeIf(notePad -> notePad.getId().equals(id));

		}

		return "redirect:/table";

	}

	@PostMapping("/table")
	public String cadastrarContato(NotePad notePad) {

		String id = UUID.randomUUID().toString();

		notePad.setId(id);

		ANOTACOES_EXEMPLOS.add(notePad);

		return "redirect:/table";

	}

}