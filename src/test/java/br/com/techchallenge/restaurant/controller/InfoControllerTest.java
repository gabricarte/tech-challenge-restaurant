package br.com.techchallenge.restaurant.controller;

import br.com.techchallenge.restaurant.domain.dto.InfoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfoControllerTest {

    @Mock
    private BuildProperties buildProperties;

    @InjectMocks
    private InfoController controller;

    @Test
    void testFind_Success() {
        when(buildProperties.getVersion()).thenReturn("1.0.0");

        ResponseEntity<InfoDTO> response = controller.find();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getVersion()).isEqualTo("1.0.0");
    }
}

