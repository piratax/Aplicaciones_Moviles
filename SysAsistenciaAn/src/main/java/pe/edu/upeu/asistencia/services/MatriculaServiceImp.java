package pe.edu.upeu.asistencia.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;
import pe.edu.upeu.asistencia.models.Matricula;
import pe.edu.upeu.asistencia.repositories.FacultadRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class MatriculaServiceImp implements MatriculaService {

    @Autowired
    private MatriculaRepository entidadRepo;

    @Override
    public Matricula save(Matricula entidad) {
        return entidadRepo.save(entidad);
    }

    @Override
    public List<Matricula> findAll() {
        return entidadRepo.findAll();
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Matricula entidadx = entidadRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matricula not exist with id :" + id));
        entidadRepo.delete(entidadx);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Matricula geEntidadById(Long id) {
        Matricula findEntidad = entidadRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Matricula not exist with id :" + id));
        return findEntidad;
    }

    @Override
    public Matricula update(Matricula entidad, Long id) {
        Facultad entidadx = entidadRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periodo not exist with id :" + id));
        entidadx.setNombrefac(entidad.getNombrefac());
        entidadx.setEstado(entidad.getEstado());
        entidadx.setIniciales(entidad.getIniciales());
        return entidadRepo.save(entidadx);
    }

}
