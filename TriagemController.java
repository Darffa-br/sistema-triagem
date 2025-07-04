package com.triagem.controller;

import com.triagem.model.Medico;
import com.triagem.model.Paciente;
import com.triagem.service.TriagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class TriagemController {

    private final TriagemService service;

    public TriagemController(TriagemService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Sistema está no ar");
    }

    @PostMapping("/triagem")
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(service.salvarPaciente(paciente));
    }

    @PostMapping("/medicos")
    public ResponseEntity<Medico> cadastrarMedico(@RequestBody Medico medico) {
        return ResponseEntity.ok(service.salvarMedico(medico));
    }

    @PutMapping("/medicos/{id}/plantao")
    public ResponseEntity<?> atualizarPlantao(@PathVariable Long id, @RequestParam boolean emPlantao) {
        return service.atualizarPlantao(id, emPlantao)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pacientes/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) {
        return service.buscarPacientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/atendimento/proximo")
    public ResponseEntity<?> proximoAtendimento() {
        Optional<Paciente> paciente = service.proximoAtendimento();
        if (paciente.isPresent()) {
            return ResponseEntity.ok(paciente.get());
        } else {
            return ResponseEntity.status(404).body("Nenhum paciente ou médico disponível");
        }
    }
}