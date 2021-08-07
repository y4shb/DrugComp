package com.licious.DrugComp.dto.request;

import com.licious.DrugComp.dto.IngredientInfo;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompositionDetailsRequest {
    private List<IngredientInfo> ingredients;
    private Boolean rx_required;
}
