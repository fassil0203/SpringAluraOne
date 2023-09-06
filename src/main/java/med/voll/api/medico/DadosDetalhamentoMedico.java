package med.voll.api.medico;

import med.voll.api.Endereco.endereco;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade) {

    public DadosDetalhamentoMedico(Medico medico){

        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEspecialidade());

    }
        }

