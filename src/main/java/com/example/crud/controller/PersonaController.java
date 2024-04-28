package com.example.crud.controller;

import com.example.crud.entity.Persona;
import com.example.crud.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/personas")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping
    public Iterable<Persona> list() {
        return personaRepository.findAll();
    }

    // devuelve una persona
    @GetMapping("/{id}")
    public Persona getPersona(@PathVariable Integer id) {
        return personaRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "persona with id " + id + " does not exists"
        ));
    }

    // guarda las personas
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public Persona create(@RequestBody Persona persona) {
        return personaRepository.save(persona);
    }

    @PutMapping("/{id}/update")
    public Persona update(@PathVariable Integer id,
                          @RequestBody Persona form) {

        Persona personaFromDb = personaRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "persona con el id:" + id + " no existe"
        ));

        personaFromDb.setName(form.getName());
        personaFromDb.setUsername(form.getUsername());
        personaFromDb.setEmail(form.getEmail());
        personaFromDb.setBirthday(form.getBirthday());
        personaFromDb.setAge(form.getAge());

        return personaRepository.save(personaFromDb);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable Integer id) {

        Persona persona = personaRepository.findById(id).orElseThrow( () -> new IllegalStateException(
                "persona con el id: " + id + " no existe"
        ));

        personaRepository.delete(persona);
    }

}
