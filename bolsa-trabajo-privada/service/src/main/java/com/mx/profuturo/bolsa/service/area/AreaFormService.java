package com.mx.profuturo.bolsa.service.area;

import java.util.ArrayList;
import java.util.List;

import com.mx.profuturo.bolsa.model.service.areasinteres.dto.*;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;

public interface AreaFormService {

	BasicDTO createInterestArea(AreaInteresDTO areaDto) throws GenericStatusException;
	boolean updateInterestArea(AreaInteresDTO areaDto) throws GenericStatusException;

	AreaInteresDTO getInterestArea(int areaDto) throws GenericStatusException;
	BasicDTO createSubtArea(SubAreaInteresDTO subDto) throws GenericStatusException;
	Boolean updateSubArea(SubAreaInteresDTO subDto) throws GenericStatusException;
	Boolean deleteSubArea(int idsubDto) throws GenericStatusException;
	List<AreaInteresDTO> getInterestAreas() throws GenericStatusException;
	UpdateInterestSubareaResponseBean updateInterestSubarea(String descripcion, int idArea, int idSubArea, String nombre) throws GenericStatusException;
	boolean dropInterestaArea(DropInterestAreaRequestBean idArea) throws GenericStatusException;
	boolean dropInterestSubarea(DropInterestSubareaRequestBean id) throws GenericStatusException;


}
