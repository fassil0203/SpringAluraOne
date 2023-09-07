package med.voll.api.domain.medico;

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

