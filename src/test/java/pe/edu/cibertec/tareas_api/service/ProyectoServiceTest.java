package pe.edu.cibertec.tareas_api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.cibertec.tareas_api.model.Proyecto;
import pe.edu.cibertec.tareas_api.model.Usuario;
import pe.edu.cibertec.tareas_api.repository.ProyectoRepository;
import pe.edu.cibertec.tareas_api.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProyectoServiceTest {

    @Mock
    private ProyectoRepository proyectoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ProyectoService proyectoService;

    @Test
    void listarTodos() {
        Proyecto p = new Proyecto();
        when(proyectoRepository.findAll()).thenReturn(Arrays.asList(p));

        var lista = proyectoService.listarTodos();

        assertNotNull(lista);
        assertEquals(1, lista.size());
    }

    @Test
    void buscarPorId_Exitoso() {
        Proyecto p = new Proyecto();
        p.setNombre("Proyecto X");
        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(p));

        var result = proyectoService.buscarPorId(1L);

        assertEquals("Proyecto X", result.get().getNombre());
    }

    @Test
    void crear_Exitoso() {
        Usuario u = new Usuario();
        u.setId(1L);
        u.setActivo(true);

        Proyecto p = new Proyecto();
        p.setUsuario(u);
        p.setFechaInicio(LocalDate.now());
        p.setFechaFin(LocalDate.now().plusDays(1));

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(u));
        when(proyectoRepository.save(p)).thenReturn(p);

        var result = proyectoService.crear(p);

        assertNotNull(result);
    }

    @Test
    void crear_FechaInvalida() {
        Usuario u = new Usuario();
        u.setId(1L);
        u.setActivo(true);

        Proyecto p = new Proyecto();
        p.setUsuario(u);
        p.setFechaInicio(LocalDate.now());
        p.setFechaFin(LocalDate.now().minusDays(1));

        assertThrows(RuntimeException.class, () -> proyectoService.crear(p));
    }
}