package com.mx.profuturo.bolsa.service.vacantes;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.hiringform.vo.DatosFormularioVO;
import com.mx.profuturo.bolsa.model.service.vacancies.base.VacanteDatosBasicos;
import com.mx.profuturo.bolsa.model.service.vacancies.dto.*;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.*;
import com.mx.profuturo.bolsa.model.vo.common.BasicCatalogVO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.EstrategiaVO;
import com.mx.profuturo.bolsa.model.vo.vacancy.PublicacionVO;
import com.mx.profuturo.bolsa.model.vo.vacancy.VacanteVO;
import com.mx.profuturo.bolsa.model.service.vacancies.dto.CrearVacanteDTO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.InterviewException;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.SuccessCreateResponseBean;
import com.mx.profuturo.bolsa.model.service.candidates.dto.SuccessGetSeatsResponseBean;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public interface VacancyService {
    ResumenVacantesVO getAllVacancies(String role,String idUsuario,int paginaActual)throws GenericStatusException;
    IsObserverNeeded getRecruitentType(BuscarDatosVacanteDTO request, String idRole)throws GenericStatusException;
    InformacionVacanteVO getVacancyInfo(int idVacante)throws GenericStatusException;
    ResumenVacantesVO getMastaerVacanciesByUser(RequestBean genericRequestBean)throws GenericStatusException;
    ArrayList<CatalogoVO> getMexicanStates()throws GenericStatusException;
    DetalleVacanteCerradaVO getClosedVacancyDetail(Integer idVacante)throws GenericStatusException;
    ResumenVacanteMaestraVO getMasterVacancySummary(int idVacante,String idUsuario, String role)throws GenericStatusException;
    ListaPublicacionesVO getPublications(ObtenerPublicacionesDTO obtenerPublicacionesDTO)throws GenericStatusException;
    TablaPostuladosVO getCandidates(BuscarDatosVacanteDTO request,String role, Integer paginaActual)throws GenericStatusException;
    ArrayList<SugerenciaCandidatoVO> getCandidateSuggestion(Integer idVacante, Integer idSede, Integer idPublicacion) throws GenericStatusException;
    List<VacanteDatosBasicos> searchVacanciesByName(BusquedaDTO busquedaDTO)throws GenericStatusException;
    EstrategiaVacanteMaestraVO getMasterVacancyStrategy(int idPublicacion)throws GenericStatusException;
    ArrayList<CatalogoVO> getPositionLevels()throws GenericStatusException;
    VacanteVO getVacancyById(BasicDTO basicDTO);
    boolean startProcess(IniciarProcesoDTO iniciarProcesoDTO)throws InterviewException, GenericStatusException;
    boolean discardFromProcess(IniciarProcesoDTO iniciarProcesoDTO)throws GenericStatusException;
    boolean editVacancySpots(EditarPlazasVacanteMaestraDTO editarPlazas)throws GenericStatusException;
    InformacionPublicacionMaestra getMasterVacancyInfo(Integer idPublicacion)throws GenericStatusException;
    VacanteMaestraVO getMasterVacancy(Integer idVacante) throws GenericStatusException;
    BasicDTO updateVacancy(CrearVacanteDTO vacanteDTO) throws GenericStatusException;
    BasicDTO saveMasterVacancyPublication(GuardarPublicacionDTO publicacion, String idRole)throws GenericStatusException;
    BasicDTO saveVacancy(CrearVacanteDTO data) throws GenericStatusException;
    EstrategiaVO getStrategyById(int idPublicacion)throws GenericStatusException;
    boolean cancelVacancy(Integer dto, String idUsuario)throws GenericStatusException;
    ArrayList<ContenidoCodigoBolsaVO> generateContent(Integer idPublicacion) throws GenericStatusException;
    CatalogoVO getStrategyForm(BasicDTO dto)throws GenericStatusException;
    BasicDTO saveStrategy(GuardarEstrategiaCorpDTO estrategiaVO) throws GenericStatusException;
    BasicDTO saveMasterVacancyStrategy(InformacionEstrategiaVacanteMaestraDTO estrategiaVacanteMaestra)throws GenericStatusException;
    BasicDTO createStrategy(EstrategiaVO estrategiaVO) throws GenericStatusException;
    LinkedList<PersonaProfuturoVO> searchCollaborator(String idUsuario, String email, Integer idRama) throws GenericStatusException;
    LinkedList<PersonaProfuturoVO> searchObserver(String idUsuario) throws GenericStatusException;
    LinkedList<CatalogoVO> getDepartmentsByRegional(int idRama) throws GenericStatusException;
    LinkedList<CatalogoVO> getDivisions(int idRama) throws GenericStatusException;
    LinkedList<CatalogoVO> getRegionsByBranch(int idRama) throws GenericStatusException;
    LinkedList<CatalogoVO> getOffice(int idRama, int idGerencia) throws GenericStatusException;


    boolean assignPublication(AsignarPublicacionDTO publicacionDTO) throws GenericStatusException;
    // SuccessCreateResponseBean getVacantMaster(VacanteDTO vacanteDTO) throws GenericStatusException;
    //SuccessCreateResponseBean saveVacantMaster(Object data) throws GenericStatusException;
    // BasicDTO createSeat(AbrirPlazasDTO data) throws GenericStatusException;
    //  SuccessGetSeatsResponseBean getSeats(ObtenerPlazasAbiertasPorSedeDTO data) throws GenericStatusException;

    ArrayList<BasicCatalogVO> getJobBoard(int idVacante)throws GenericStatusException;
    ArrayList<BasicCatalogVO> getExternalJobBoardBranch(int idRama)throws GenericStatusException;
    GetVacancyResponse getVacancy(int idVacante)throws GenericStatusException;
    DetalleVacanteVO vacancyDetail(BuscarDatosVacanteDTO request, String idRole)throws GenericStatusException;
    boolean changeStateVacancy(EstadoVacanteDTO request) throws GenericStatusException;
	LinkedList<CatalogoVO> getDivisions(int idRama, Boolean validation) throws GenericStatusException;

}
