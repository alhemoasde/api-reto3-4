package co.edu.unab.api.services;

import java.time.LocalDate;
import java.util.*;

import co.edu.unab.api.repositories.AddressRepository;
import co.edu.unab.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unab.api.models.ClienteModel;
import co.edu.unab.api.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AddressRepository addressRepository;

    public ArrayList<ClienteModel> obtenerClientes(){
        return (ArrayList<ClienteModel>) clienteRepository.findAll();
    }

    public ClienteModel guardarCliente(ClienteModel cliente){
        cliente.setNombre(cliente.getNombre().toUpperCase());
        cliente.setApellido(cliente.getApellido().toUpperCase());
        cliente.setfRegistro(LocalDate.now());
        this.addressRepository.save(cliente.getAddress());
        this.productRepository.saveAll(cliente.getProductos());
        return clienteRepository.save(cliente);
    }

    public boolean eliminarCliente(String id){

        if (clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
            return true;
        }else{
            return false;
        }      
                             
    }      
    public Optional<ClienteModel> obtenerClientePorId(String id){
        return clienteRepository.findById(id);
    }

    public List<ClienteModel> obtenerClientesByNombre(String nombre){
        return clienteRepository.findAllByNombre(nombre.toUpperCase());
    }

    public List<ClienteModel> obtenerClientesByNombreAndApellido(String nombre, String apellido){
        return clienteRepository.findAllByNombreAndApellido(nombre.toUpperCase(),apellido.toUpperCase());
    }

    public ArrayList<Map> obtenerClientesByCiudad(String ciudad){
        ArrayList<Map> result = new ArrayList<>();
        List<ClienteModel> clientes = clienteRepository.findAllByAddress_Ciudad(ciudad);
        for (ClienteModel cli: clientes ) {
            Map<String,String> data = new LinkedHashMap<>();
            data.put("Nombre",cli.getNombre());
            data.put("Apellido",cli.getApellido());
            result.add(data);
        }
        return result;
    }

    public List<ClienteModel> obtenerClientesPuntosMayores(Long puntos){
        return clienteRepository.findAllByPuntosGreaterThanEqual(puntos);
    }

    public List<ClienteModel> obtenerClientesPuntosMenores(Long puntos){
        return clienteRepository.findAllByPuntosLessThanEqual(puntos);
    }

}