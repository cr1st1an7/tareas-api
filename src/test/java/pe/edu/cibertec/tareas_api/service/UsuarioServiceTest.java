package pe.edu.cibertec.tareas_api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.cibertec.tareas_api.model.Usuario;
import pe.edu.cibertec.tareas_api.repository.UsuarioRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void listarTodos() {
        Usuario u = new Usuario();
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(u));

        var lista = usuarioService.listarTodos();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_Exitoso() {
        Usuario u = new Usuario();
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(u));

        var result = usuarioService.buscarPorId(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void crear_Exitoso() {
        Usuario u = new Usuario();
        u.setEmail("test@example.com");

        when(usuarioRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(usuarioRepository.save(u)).thenReturn(u);

        var result = usuarioService.crear(u);

        assertNotNull(result);
    }
}