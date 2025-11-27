package pe.edu.cibertec.tareas_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TareasApiApplicationTests {


    @BeforeEach
    void setUp() {
        System.out.println("Iniciando TareasApiApplicationTests");
    }

	@Test
	void contextLoads() {
        System.out.println("Ejecutando tests contextloads...");
	}

}
