package com.mx.profuturo.bolsa.service.common;

import com.mx.profuturo.bolsa.model.service.vacancies.dto.PersonaProfuturoDTO;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.PersonaProfuturoVO;

public interface OpenHrService {

    PersonaProfuturoVO searchProfuturoPersonByEmail(PersonaProfuturoDTO dto);
}
