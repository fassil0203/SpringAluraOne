package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.MedicoRepository;
import med.voll.api.controller.medico.DadosListagemMedico;
import med.voll.api.medico.DadosAtualizacaoMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));

    }
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size=10,sort={"nome"})Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

   @PutMapping
   @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico =repository.getReferenceById(dados.id());
        medico.atualizarinformacoes(dados);

    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico =repository.getReferenceById(id);
        medico.excluir();

    }
}
