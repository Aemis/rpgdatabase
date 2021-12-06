/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.converter;

import br.com.lacetecnologia.rpgdatabase.enumerados.ParametroNome;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Leticia Sena
 */
@Converter(autoApply= true)
public class ParametroNomeConverter implements AttributeConverter<ParametroNome, String> {

    @Override
    public String convertToDatabaseColumn(ParametroNome attribute) {
        if(attribute== null) return null;
        else return attribute.getValor();
    }

    @Override
    public ParametroNome convertToEntityAttribute(String dbData) {
        if(dbData == null) return null;
        else return ParametroNome.getParametro(dbData.trim());
    }
    
    
}
