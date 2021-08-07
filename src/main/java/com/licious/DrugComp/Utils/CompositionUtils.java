package com.licious.DrugComp.Utils;

import com.licious.DrugComp.dto.IngredientInfo;

import java.util.Collections;
import java.util.List;

public class CompositionUtils {

    public static String getCompositionName(List<IngredientInfo> ingredientInfoList) {
        String compositionName = "";
        for(int i = 0; i < ingredientInfoList.size(); i++) {
            if(i != ingredientInfoList.size() - 1) {
                compositionName += ingredientInfoList.get(i).getName() + " (" +
                        ingredientInfoList.get(i).getStrength() +
                        ingredientInfoList.get(i).getUnit() + ")" + " + ";
            }
            else {
                compositionName += ingredientInfoList.get(i).getName() + " (" +
                        ingredientInfoList.get(i).getStrength() +
                        ingredientInfoList.get(i).getUnit() + ")";
            }
        }
        return compositionName;
    }

    public static String getMoleculeName(List<String> ingredientName){
        String moleculeName = "";
        for(int i = 0; i < ingredientName.size(); i++) {
            if(i != ingredientName.size() - 1)
                moleculeName += ingredientName.get(i) + " + ";
            else
                moleculeName += ingredientName.get(i);
        }
        return  moleculeName;
    }
}
