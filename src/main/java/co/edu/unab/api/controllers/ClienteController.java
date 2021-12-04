package co.edu.unab.api.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unab.api.models.ClienteModel;
import co.edu.unab.api.services.ClienteService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping()
    public ArrayList<ClienteModel> obtenerClientes(){
        return  clienteService.obtenerClientes();
    }

    @PostMapping()
    public ClienteModel guardarCliente(@RequestBody ClienteModel cliente){
        
        return clienteService.guardarCliente(cliente);
    }
    
    @DeleteMapping(path = "/{id}")
    public String eliminarClientePorId(@PathVariable("id") String id){
        boolean resultadoEliminar=this.clienteService.eliminarCliente(id);
        if (resultadoEliminar){
            return "Se eliminó el usuario con id: "+id;
        }else{
            return "No se pudo eliminar el usuario con el id: "+id;
        }
    }
    
    @GetMapping(path = "/{id}")
    public Optional<ClienteModel> obtenerClientePorId(@PathVariable("id") String id){
        return this.clienteService.obtenerClientePorId(id);
    }

    @GetMapping(value = "/nombre",params = "nom")
    public ResponseEntity<Map<String, List<ClienteModel>>> buscarByNombre(@RequestParam(required = true) String nom) {
        Map<String, List<ClienteModel>> respuesta = new HashMap<>();
        respuesta.put("clientes", this.clienteService.obtenerClientesByNombre(nom));
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping(value = "/nombreApellido",params = {"nom","ape"})
    public ResponseEntity<Map<String, List<ClienteModel>>> buscarByNombreApellido(@RequestParam(required = true) String nom, @RequestParam(required = true) String ape) {
        System.out.println("nom = " + nom + ", ape = " + ape);
        Map<String, List<ClienteModel>> respuesta = new HashMap<>();
        respuesta.put("clientes", this.clienteService.obtenerClientesByNombreAndApellido(nom,ape));
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping(value = "/ciudad",params ="ciu")
    public ResponseEntity<Object> buscarByCiudad(@RequestParam(required = true) String ciu) {
        return ResponseEntity.ok(this.clienteService.obtenerClientesByCiudad(ciu));
    }

    @GetMapping(value="/mayor",params = "puntoMayor")
    public ResponseEntity<Map<String, List<ClienteModel>>> buscarPuntosMayores(@RequestParam(required = true) Long puntoMayor) {
        Map<String, List<ClienteModel>> respuesta = new HashMap<>();
        respuesta.put("clientes", this.clienteService.obtenerClientesPuntosMayores(puntoMayor));
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping(value="/menor",params = "puntoMenor")
    public ResponseEntity<Map<String, List<ClienteModel>>> buscarPuntosMenores(@RequestParam(required = true) Long puntoMenor) {
        Map<String, List<ClienteModel>> respuesta = new HashMap<>();
        respuesta.put("clientes", this.clienteService.obtenerClientesPuntosMenores(puntoMenor));
        return ResponseEntity.ok(respuesta);
    }

  
}