package pe.edu.cibertec.tareas_api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.cibertec.tareas_api.model.Proyecto;
import pe.edu.cibertec.tareas_api.model.Tarea;
import pe.edu.cibertec.tareas_api.repository.ProyectoRepository;
import pe.edu.cibertec.tareas_api.repository.TareaRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TareaServiceTest {

    @Mock
    private TareaRepository tareaRepository;

    @Mock
    private ProyectoRepository proyectoRepository;

    @InjectMocks
    private TareaService tareaService;

    @Test
    void listarTodos() {
        Tarea t = new Tarea();
        when(tareaRepository.findAll()).thenReturn(Arrays.asList(t));

        var lista = tareaService.listarTodos();

        assertNotNull(lista);
        assertEquals(1, lista.size());
    }

    @Test
    void crear_Exitoso() {
        Proyecto p = new Proyecto();
        p.setId(1L);
        p.setActivo(true);

        Tarea t = new Tarea();
        t.setEstado("PENDIENTE");
        t.setPrioridad("ALTA");
        t.setProyecto(p);

        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(p));
        when(tareaRepository.save(t)).thenReturn(t);

        var result = tareaService.crear(t);

        assertNotNull(result);
    }

    @Test
    void crear_EstadoInvalido() {
        Tarea t = new Tarea();
        t.setEstado("ESTADO_INVALIDO");

        assertThrows(RuntimeException.class, () -> tareaService.crear(t));
        verify(tareaRepository, never()).save(any());
    }
}