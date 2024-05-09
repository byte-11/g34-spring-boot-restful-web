package uz.pdp.g34springbootinit.dto.web;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    private String errorMessage;
    private Object body;
    private Integer status;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponseDTO(String errorMessage, Integer status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
