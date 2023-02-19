package kalven.springframework.kalvenjms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * @Project kalven-jms
 * @Author kalvens on 2/19/23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldMessage implements Serializable {
    static final long serialVersionUID = 5558080800279388387L;
    private UUID id;
    private String message;
}
