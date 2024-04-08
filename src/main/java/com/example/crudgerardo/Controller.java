package com.example.crudgerardo;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudgerardo.model.role;
import com.example.crudgerardo.model.usuaruio;
import com.example.crudgerardo.repo.roleRepo;
import com.example.crudgerardo.repo.usuariosRepo;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired 
    usuariosRepo usuariosRepo;

    @Autowired 
    roleRepo roleRepo;

    public Controller(com.example.crudgerardo.repo.usuariosRepo usuariosRepo,
            com.example.crudgerardo.repo.roleRepo roleRepo) {
        this.usuariosRepo = usuariosRepo;
        this.roleRepo = roleRepo;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<usuaruio>>getAllUsuarios(@RequestParam(required= false) String nombre){
        try {
            List<usuaruio> usuaruiosList = new ArrayList<>();
            
            if (nombre == null){

                usuariosRepo.findAll().forEach(usuaruiosList::add);

            }else{

                usuariosRepo.findByNombreContaining(nombre).forEach(usuaruiosList::add);

            }     
            
            if (usuaruiosList.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.OK);
                                
            }
            return new ResponseEntity<>(usuaruiosList, HttpStatus.NO_CONTENT);
            
        } catch (Exception e) {
            

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }  

    @GetMapping("/roles")
    public ResponseEntity<List<role>> getAllRoles(@RequestParam(required = false) String nombre){
        try {
            List<role> rolesList = new ArrayList<>();

            if (nombre==null) {
                roleRepo.findAll().forEach(rolesList::add);
            }else{
                roleRepo.findByNombreContaining(nombre).forEach(rolesList::add);
            }
            if (rolesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);                
            }
            return new ResponseEntity<>(rolesList, HttpStatus.OK);
        } catch (Exception e) {
            

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<usuaruio> getUsuariosByID(@PathVariable int id){
        Optional<usuaruio> usuaruiosData = usuariosRepo.findById(id);
        if (usuaruiosData.isPresent()) {
            return new ResponseEntity<>(usuaruiosData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<role> getRolesByID(@PathVariable int id){
        Optional<role> roleData = roleRepo.findById(id);
        if (roleData.isPresent()) {
            return new ResponseEntity<>(roleData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }

    @PostMapping("/usuarios")
    public ResponseEntity<usuaruio> postCrearUsuarios(@RequestBody usuaruio usuaruio){
  
        usuaruio newUsuario = usuariosRepo.save(new usuaruio(
            usuaruio.getNombre(),
            usuaruio.getA_paterno(),
            usuaruio.getA_materno(),
            usuaruio.getId_role()));
        return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);

    }
    
    @PostMapping("/roles")
    public ResponseEntity<role> postcrearRol(@RequestBody role role){
        role newRol= roleRepo.save(new role(role.getNombre()));
        return new ResponseEntity<>(newRol, HttpStatus.CREATED);

    }

    @DeleteMapping("/usuarios/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUsuarios(@PathVariable("id") int id){
        usuariosRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/roles/delete/{id}")
    public ResponseEntity<HttpStatus> deleteRol(@PathVariable("id") int id){
        roleRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/usuarios/edit/{id}")
    public ResponseEntity<usuaruio> putUsuarios(@PathVariable("id") int id, @RequestBody usuaruio usuaruio){
        Optional<usuaruio> tusuarioOptional = usuariosRepo.findById(id);
        if (tusuarioOptional.isPresent()) {
            usuaruio updateusuario = tusuarioOptional.get();
            if(usuaruio.getNombre() != null) 
                updateusuario.setNombre(usuaruio.getNombre()) ;
            if(usuaruio.getA_paterno() != null) 
                updateusuario.setA_paterno(usuaruio.getA_paterno());
            if(usuaruio.getA_materno() != null) 
                updateusuario.setA_materno(usuaruio.getA_materno());
            if(usuaruio.getId_role() != 0) 
                updateusuario.setId_role(usuaruio.getId_role());
            return new ResponseEntity<>(usuariosRepo.save(updateusuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
    @PutMapping("/roles/edit/{id}")
    public  ResponseEntity<role> putRoles(@PathVariable("id") int id, @RequestBody role role){
        Optional<role> roleOptional = roleRepo.findById(id);

        if (roleOptional.isPresent()) {
        role updateRole = roleOptional.get();
            if (role.getNombre() != null) 
                updateRole.setNombre(role.getNombre());
            return new ResponseEntity<>(roleRepo.save(updateRole), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
