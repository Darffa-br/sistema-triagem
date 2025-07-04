package com.triagem.service;

import com.triagem.model.Medico;
import com.triagem.model.Paciente;
import com.triagem.repository.MedicoRepository;
import com.triagem.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TriagemService {
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;

    public TriagemService(PacienteRepository pacienteRepo, MedicoRepository medicoRepo) {
        this.pacienteRepo = pacienteRepo;
        this.medicoRepo = medicoRepo;
    }

    public Paciente salvarPaciente(Paciente paciente) {
        return pacienteRepo.save(paciente);
    }

    public Medico salvarMedico(Medico medico) {
        return medicoRepo.save(medico);
    }

    public Optional<Paciente> buscarPacientePorId(Long id) {
        return pacienteRepo.findById(id);
    }

    public Optional<Medico> atualizarPlantao(Long id, boolean emPlantao) {
        return medicoRepo.findById(id).map(m -> {
            m.setEmPlantao(emPlantao);
            return medicoRepo.save(m);
        });
    }

    public Optional<Paciente> proximoAtendimento() {
        List<Medico> medicos = medicoRepo.findByEmPlantaoTrue();
        if (medicos.isEmpty()) return Optional.empty();

        return pacienteRepo.findAll().stream()
            .sorted(Comparator
                .comparing((Paciente p) -> p.getPrioridade().ordinal())
                .thenComparing(p -> p.getGravidade().ordinal()))
            .findFirst();
    }
}