package com.notepad;

import java.util.ArrayList;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	public ModelAndView lista() { //criei um metodo publico pra mostrar contatos de exemplos para usuarios //
		
		ModelAndView modelandview = new ModelAndView("table"); //adicionei que o modelo de visualização vai para pagina table//
		
		modelandview.addObject("notePads", ANOTACOES_EXEMPLOS);//adicionou atributos, o notePads é uma variavel ou chave que vou chamar quando eu quiser que os exemplos de contato aparecem//
		
		return modelandview;

		// retornei um modelo de visualização que no caso é minha constante statica que armazenou valores //
		
	}

	@GetMapping("/table/novo")
        public ModelAndView novo() { 
		
		ModelAndView modelandview = new ModelAndView("table"); 
		
		modelandview.addObject("notePad", new NotePad());
		
		return modelandview;

		// retornei um modelo de visualização que no caso é minha constante statica que armazenou valores //
		
	}
	
	@DeleteMapping("/table/{id}") 
	
	public String remover(@PathVariable String id) {
		
		// Verificando se a lista de ANOTACOES_EXEMPLOS não está vazia
		
		if (!ANOTACOES_EXEMPLOS.isEmpty()) {
			
		// Se a lista não estiver vazia, remove o primeiro elemento
		// Esse método remove sempre o primeiro elemento da lista
		// Se houver critérios específicos, como remover com base no ID,
		// essa lógica pode não ser suficiente e precisa ser ajustada
		
			ANOTACOES_EXEMPLOS.removeIf(notePad -> notePad.getId().equals(id));

	     
		}
		
		// Redireciona para a página "/table" após a remoção (ou não)
		
		return "redirect:/table";
		
	}
	@PostMapping ("/table")
	public String cadastrarContato (NotePad notePad) {
		String id = UUID.randomUUID().toString();
		
		notePad.setId(id);
		
		ANOTACOES_EXEMPLOS.add(notePad);
		
		return "redirect:/table";
	}
	@GetMapping("/table/{id}/editar")
	public ModelAndView editar (@PathVariable String id) {
		
       ModelAndView modelandview = new ModelAndView("table"); 
		
       NotePad notepad = proucurarContato(id); // invocando o metodo proucurarContato com indentificador de id
       
		modelandview.addObject("notePad", notepad); // passo a variavel da classe contato, e essa variavel vai ser ultilizada para ser preenchida, vai ser prenchida pois ela recebeu um valor de um metodo
		
		return modelandview;
		
	}
//	proximo passo é proucurar pelo id na lista quem vai ser editado, vamos proucurar pelo indentificador
	
	
//	private NotePad proucurarContato (String id) { // jeito 1
//		for ( int i = 0 ; i < ANOTACOES_EXEMPLOS.size(); i++ ) { // criando um for pra iterar até o ultimo elemento do arraylist, que no caso vai iterar até a elemento que eu selecionar
//			
//			NotePad notepad = ANOTACOES_EXEMPLOS.get(i); // criando uma variavel chamada notepad da classe NotePad que vai receber o valor de acordo com a opção de cadastro que eu selecionei 
//			
//			if(notepad.getId().equals(id)) {// se o identificador desse notepad for igual ao que está sendo passado no parametro no caso id, vai retornar o contato
//				return notepad;
//			}
//		}
//		//caso todos os retornos retornem falsos, vai retornar o null ao inveis de notepad
//		return null;
//	}
//	
	private NotePad proucurarContato (String id) { //jeito 2
		
		Integer indice = proucurarIndiceContato(id); // adiciono uma variavel indice com o valor do metodo que responde o indice do contato
	
		if (indice != null) {
			NotePad notepad = ANOTACOES_EXEMPLOS.get(indice);
			return notepad;
		}
		return null;
		}
	
	@PutMapping("/table/{id}")
	private String atualizar (NotePad notepad) {
		
		Integer indice = proucurarIndiceContato(notepad.getId());
		
		NotePad notepadDeContatoAntigo = ANOTACOES_EXEMPLOS.get(indice); // decrando uma variavel com o contato velho, e pegando o contato so com o indice da lista
		
		ANOTACOES_EXEMPLOS.remove(notepadDeContatoAntigo);// removo o contato antigo
		
		ANOTACOES_EXEMPLOS.add(indice, notepadDeContatoAntigo);// adiciono o novo atualizado. e quando ele atualizar ele vai permanecer como ele tava, pois to passando o indice no parametro que corresponde a posição atual dele, estou fazendo com que quando eu atualizar o contato, ele fique em uma posição que ele ja estava 
		
		return "redirec:/table";
	}
	private Integer proucurarIndiceContato (String id) {
		for (int i = 0; i < ANOTACOES_EXEMPLOS.size();i++) {
			
			NotePad notepad = ANOTACOES_EXEMPLOS.get(i);
			
		
		if (notepad.getId().equals(id)) {
			return i; // ao inveis de retornar o contato selecionado, vai retornar o indice dele
		}	
		
		}
		return null;
	}
}