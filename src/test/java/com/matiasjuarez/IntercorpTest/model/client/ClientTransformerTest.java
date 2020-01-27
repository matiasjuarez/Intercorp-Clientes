package com.matiasjuarez.IntercorpTest.model.client;

import com.matiasjuarez.IntercorpTest.exceptions.ErrorMessages;
import com.matiasjuarez.IntercorpTest.exceptions.ValidationException;
import com.matiasjuarez.IntercorpTest.service.DateHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTransformerTest {
    private static ClientTransformer transformer;
    private static DateHandler dateHandler;
    private static ClienteHelper clienteHelper;
    private static ErrorMessages errorMessages;

    @BeforeAll
    public static void setup() {
        dateHandler = new DateHandler();
        clienteHelper = new ClienteHelper(dateHandler);
        errorMessages = new ErrorMessages();
        transformer = new ClientTransformer(dateHandler, clienteHelper, errorMessages);
    }

    @Test
    public void testDtoToEntityTransformation() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNombre("matias");
        dto.setApellido("juarez");
        dto.setFechaNacimientoString("12/04/1991");

        ClienteEntity entity = transformer.convertToEntity(dto);

        assertEquals("matias", entity.getNombre());
        assertEquals("juarez", entity.getApellido());
        assertEquals(LocalDate.of(1991, Month.APRIL, 12), entity.getFechaNacimiento());

        // Not so sure if this assertion is a good idea, because it will start failing after 12/04/2020
        assertEquals(28, entity.getEdad());
    }

    @Test
    public void testDtoToEntityTransformation_noName_expectException() {
        ClienteDTO dto = new ClienteDTO();
        dto.setApellido("juarez");
        dto.setFechaNacimientoString("12/04/1991");

        try {
            transformer.convertToEntity(dto);
            fail("Expected exception");
        } catch (ValidationException ve) {
            assertEquals(errorMessages.getClienteNameRequired(), ve.getMessage());
        }
    }

    @Test
    public void testDtoToEntityTransformation_noLastname_expectException() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNombre("matias");
        dto.setFechaNacimientoString("12/04/1991");

        try {
            transformer.convertToEntity(dto);
            fail("Expected exception");
        } catch (ValidationException ve) {
            assertEquals(errorMessages.getClienteLastnameRequired(), ve.getMessage());
        }
    }

    @Test
    public void testDtoToEntityTransformation_noBirthday_expectException() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNombre("matias");
        dto.setApellido("juarez");

        try {
            transformer.convertToEntity(dto);
            fail("Expected exception");
        } catch (ValidationException ve) {
            assertEquals(errorMessages.getClienteBirthdayRequired(), ve.getMessage());
        }
    }

    @Test
    public void testDtoToEntityTransformation_tooMuchBirthdayInformation_expectException() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNombre("matias");
        dto.setApellido("juarez");
        dto.setFechaNacimientoString("12/04/1991");
        dto.setFechaNacimiento(12345L);

        try {
            transformer.convertToEntity(dto);
            fail("Expected exception");
        } catch (ValidationException ve) {
            assertEquals(errorMessages.getClienteAmbiguousBirthdayInformation(), ve.getMessage());
        }
    }
}
