package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Entity.Usuario;
import com.example.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listar(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    @Transactional
    @GetMapping("/{id}")
        public Usuario ver(@PathVariable Long id){
            return usuarioRepository.findById(id).orElse(null);
        }

        @Transactional
        @PutMapping("/{id}")
        public void editar(@PathVariable Long id, @RequestBody Usuario usuario){
            usuario.setId(id);
            usuarioRepository.saveAndFlush(usuario);
        }

        @Transactional
        @PostMapping()
        public void salvar(@RequestBody Usuario usuario){
            usuarioRepository.save(usuario);
        }

        @Transactional
        @DeleteMapping("/{id}")
        public void deletar(@PathVariable Long id){
            usuarioRepository.deleteById(id);
        }
}

