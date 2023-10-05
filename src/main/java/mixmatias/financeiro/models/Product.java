package mixmatias.financeiro.models;


import lombok.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@Setter
public class Product {
    private int code;
    private String description;
    private String type;
    private double value;

}
