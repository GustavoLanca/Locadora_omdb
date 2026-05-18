package com.example.locadora.filme;

import java.time.Year;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
 
 
@Service
public class FilmeService {
	@Autowired
	private FilmeRepository filmeRepository;
	
	public List<Filme> getAllFilme() {
		return filmeRepository.findAll(Sort.by("titulo").ascending());
	}
	public Filme getFilmeById(Long id) {
		return filmeRepository.getReferenceById(id);
	}
	public List<Filme> findAllById(List<Long> filmesIds) {
		return filmeRepository.findAllById(filmesIds);
	}
 
	public List<Filme> buscarLancamentosDoAno() {
        int anoAtual = Year.now().getValue();
       return filmeRepository.findByDataLancamento(anoAtual);
    }
	
	public String save(Filme filme) {
			filmeRepository.save(filme);
			String mensagem = filme.getId() != null ?
					"Filme '" + filme.getTitulo() + "' atualizado com sucesso!" :
						"Filme '" + filme.getTitulo() + "' criado com sucesso!";
			return mensagem;	
	}
	
	public String save(DadosCadastroFilme dados) {
		Filme filme = new Filme();
		filme.setTitulo(dados.titulo());
		filme.setAno(dados.ano());
		filme.setClassificacao(dados.classificacao());
		filme.setDataLancamento(dados.dataLancamento());
		filme.setDuracao(dados.duracao());
		filme.setGenero(dados.genero());
		filme.setNomeDiretor(dados.nomeDiretor());
		filme.setResumo(dados.resumo());
		filme.setPais(dados.pais());
		filme.setLingua(dados.lingua());
		filmeRepository.save(filme);
		String mensagem = filme.getId() != null ?
				"Filme '" + filme.getTitulo() + "' atualizado com sucesso!" :
					"Filme '" + filme.getTitulo() + "' criado com sucesso!";
		return mensagem;
	}
	
	public String deleteFilme(Long id) {
		filmeRepository.deleteById(id);
		return "O filme " + id + " foi apagado!";
	}
	
//	
//	public List<Filme> buscarFilmesDestaque() {
//        // Implementar lógica específica para filmes em destaque
//        // Exemplo: 5 filmes mais recentes ou mais avaliados
//        return filmeRepository.findTop5ByOrderByDataLancamentoDesc();
//    }
}