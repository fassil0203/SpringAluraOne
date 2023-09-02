package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.MedicoRepository;
import med.voll.api.controller.medico.DadosListagemMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<DadosListagemMedico> listar(){
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }
}
