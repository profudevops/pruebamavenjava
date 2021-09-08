package com.mx.profuturo.bolsa.web.controller.corporate.vacante;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.mx.profuturo.bolsa.configuration.TokenService;
import com.mx.profuturo.bolsa.model.service.vacancies.base.Vacante;
import com.mx.profuturo.bolsa.model.service.vacancies.dto.*;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.*;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.EstrategiaVO;
import com.mx.profuturo.bolsa.model.vo.common.BasicCatalogVO;
import com.mx.profuturo.bolsa.model.vo.common.RegionVO;
import com.mx.profuturo.bolsa.model.vo.common.SucursalVO;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.ResumenAplicacionVO;
import com.mx.profuturo.bolsa.model.vo.vacancy.ResumenPublicacionVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.SuccessCreateResponseBean;
import com.mx.profuturo.bolsa.model.service.candidates.dto.SuccessGetSeatsResponseBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.vo.vacancy.PublicacionVO;
import com.mx.profuturo.bolsa.model.vo.vacancy.VacanteVO;
import com.mx.profuturo.bolsa.service.vacantes.VacancyService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.exception.custom.InterviewException;
import com.mx.profuturo.bolsa.web.controller.common.BaseController;

@CrossOrigin
@RequestMapping(value="vacantes")
@Controller("vacanciesController")
@Scope("request")
public class VacanciesControllerImpl extends BaseController{

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private TokenService tokenService;

    public
    @ResponseBody
    @RequestMapping(value="obtener-todo", method = RequestMethod.GET)
    EntityResponseBean<ResumenVacantesVO> getAll(
            @RequestHeader("Authorization")String token,
            @RequestParam(value="paginaActual") int paginaActual)throws GenericStatusException{

        String roleFromToken  = tokenService.getUserRoleFromToken(this.getCleanToken(token));
        String userId= tokenService.getUserIdFromToken(this.getCleanToken(token));

        ResumenVacantesVO entity = vacancyService.getAllVacancies(roleFromToken,userId,paginaActual);
        return this.buildEntitySuccessResponse(entity);
    }


    public
    @ResponseBody
    @RequestMapping(value="obtener-publicaciones", method = RequestMethod.POST)
    EntityResponseBean<ListaPublicacionesVO> getublications(
            @RequestHeader("Authorization") String token,
            @RequestBody ObtenerPublicacionesDTO obtenerPublicacionesDTO)throws GenericStatusException{

        obtenerPublicacionesDTO.setIdUsuario(this.tokenService.getUserIdFromToken(getCleanToken(token)));
        obtenerPublicacionesDTO.setRole(this.tokenService.getUserRoleFromToken(getCleanToken(token)));

        ListaPublicacionesVO entity = vacancyService.getPublications(obtenerPublicacionesDTO);
        return this.buildEntitySuccessResponse(entity);
    }


    public @ResponseBody
    @RequestMapping(value="obtener-resumen-vacante-maestra", method = RequestMethod.GET)
    EntityResponseBean<ResumenVacanteMaestraVO> getMasterVacancySummary(
            @RequestParam(value="idVacante") int idVacante,
            @RequestHeader("Authorization") String token) throws GenericStatusException {

        ResumenVacanteMaestraVO resumen = new ResumenVacanteMaestraVO();

        resumen = vacancyService.getMasterVacancySummary(
                idVacante,
                this.tokenService.getUserIdFromToken(getCleanToken(token)),
                this.tokenService.getUserRoleFromToken(getCleanToken(token)));

        return this.buildEntitySuccessResponse(resumen);

    }



    public @ResponseBody
    @RequestMapping(value="guardar-vacante", method = RequestMethod.POST)
    EntityResponseBean<BasicDTO> crearVacante(
            @RequestHeader("Authorization") String token
            ,@RequestBody CrearVacanteDTO vacante) throws GenericStatusException{
        BasicDTO responseService = null;

        vacante.setVacanteMaestra(false);
        vacante.setIdUsuario(tokenService.getUserIdFromToken(getCleanToken(token)));
        responseService = vacancyService.saveVacancy(vacante);

        return this.buildEntitySuccessResponse(responseService);

    }

    public @ResponseBody
    @RequestMapping(value="guardar-vacante-maestra", method = RequestMethod.POST)
    EntityResponseBean<BasicDTO> crearVacanteMaestra(@RequestBody CrearVacanteDTO vacante) throws GenericStatusException{
        BasicDTO responseService = null;

        vacante.setVacanteMaestra(true);
        responseService = vacancyService.saveVacancy(vacante);

        return this.buildEntitySuccessResponse(responseService);


    }


    public @ResponseBody
    @RequestMapping(value="guardar-publicacion-vacante-maestra", method = RequestMethod.POST)
    EntityResponseBean<BasicDTO> guardarPublicacionVacanteMaestra(
            @RequestHeader("Authorization") String token,
            @RequestBody GuardarPublicacionDTO publicacion) throws GenericStatusException{
        BasicDTO responseService = new BasicDTO();

        String idRole = tokenService.getUserRoleFromToken(this.getCleanToken(token));

        publicacion.setIdUsuario(tokenService.getUserIdFromToken(this.getCleanToken(token)));
        responseService=vacancyService.saveMasterVacancyPublication(publicacion,idRole);


        return this.buildEntitySuccessResponse(responseService);

    }


    public @ResponseBody
    @RequestMapping(value="guardar-estrategia", method = RequestMethod.POST)
    EntityResponseBean<EstrategiaVO> guardarEstrategia(
            @RequestHeader("Authorization")String token,
            @RequestBody GuardarEstrategiaCorpDTO estrategia) throws GenericStatusException{
        BasicDTO responseService = null;
        estrategia.setIdPublicacion(estrategia.getIdVacante());

        estrategia.setReclutador(tokenService.getUserIdFromToken(this.getCleanToken(token)));

        responseService=vacancyService.saveStrategy(estrategia);


        return this.buildEntitySuccessResponse(responseService);

    }


    @ResponseBody
    @RequestMapping(value="obtener-vacante", method = RequestMethod.GET)
    public EntityResponseBean <InformacionVacanteVO> search(@RequestParam(value="idVacante") int idVacante)throws GenericStatusException{

    InformacionVacanteVO response =  vacancyService.getVacancyInfo(idVacante);

    return this.buildEntitySuccessResponse(response);


    }


    @ResponseBody
    @RequestMapping(value="obtener-vacante-maestra", method = RequestMethod.GET)
    public EntityResponseBean <VacanteMaestraVO> obtenerVacanteMaestra(@RequestParam(value="idVacante") int idVacante)throws GenericStatusException{

        VacanteMaestraVO response = vacancyService.getMasterVacancy(idVacante);

        return this.buildEntitySuccessResponse(response);

    }

    public @ResponseBody
    @RequestMapping(value="detalle-vacante", method = RequestMethod.GET)
    EntityResponseBean<DetalleVacanteVO> getDetalleVacante(
            @RequestHeader("Authorization") String token,
            @RequestParam(value="idPublicacion") Integer idPublicacion) throws GenericStatusException{
        DetalleVacanteVO response = new DetalleVacanteVO();
        BuscarDatosVacanteDTO request =new BuscarDatosVacanteDTO();
        String idRole = tokenService.getUserRoleFromToken(this.getCleanToken(token));
        request.setIdPublicacion(idPublicacion);
        request.setIdAnalista(tokenService.getUserIdFromToken(this.getCleanToken(token)));
        request.setRole(tokenService.getUserRoleFromToken(this.getCleanToken(token)));
        response = vacancyService.vacancyDetail(request, idRole);
        return this.buildEntitySuccessResponse(response);
    }

    public @ResponseBody
    @RequestMapping(value="mostrar-buscar-observador", method = RequestMethod.GET)
    EntityResponseBean<IsObserverNeeded> getRecruitmentType(
            @RequestHeader("Authorization") String token,
            @RequestParam(value="idPublicacion") Integer idPublicacion) throws GenericStatusException{
        IsObserverNeeded response = new IsObserverNeeded();
        BuscarDatosVacanteDTO request =new BuscarDatosVacanteDTO();
        String idRole = tokenService.getUserRoleFromToken(this.getCleanToken(token));
        request.setIdPublicacion(idPublicacion);
        request.setIdAnalista(tokenService.getUserIdFromToken(this.getCleanToken(token)));
        response = vacancyService.getRecruitentType(request, idRole);
        return this.buildEntitySuccessResponse(response);
    }


    public @ResponseBody
    @RequestMapping(value="detalle-vacante-cerrada", method = RequestMethod.GET)
    EntityResponseBean<DetalleVacanteCerradaVO> getDetalleVacanteCerrada(@RequestParam(value="idPublicacion") Integer idVacante) throws GenericStatusException{
        DetalleVacanteCerradaVO response = new DetalleVacanteCerradaVO();

        response = vacancyService.getClosedVacancyDetail(idVacante);
        return this.buildEntitySuccessResponse(response);

    }

    public @ResponseBody
    @RequestMapping(value="editar-plazas", method = RequestMethod.POST)
    BaseResponseBean editVacancySpotsNumber(
            @RequestHeader("Authorization") String token
            ,@RequestBody EditarPlazasVacanteMaestraDTO editarPlazasDTO) throws GenericStatusException{


        editarPlazasDTO.setIdUsuario(tokenService.getUserIdFromToken(getCleanToken(token)));
        if(vacancyService.editVacancySpots(editarPlazasDTO)){
            return this.buildSuccessResponse();
        }
        throw new GenericStatusException();
    }


    public @ResponseBody
    @RequestMapping(value="obtener-contenido-publicacion-vacante-maestra", method = RequestMethod.GET)
    EntityResponseBean <InformacionPublicacionMaestra> verContenidoPublicacion(@RequestParam(value="idPublicacion") Integer idPublicacion)throws GenericStatusException{

        InformacionPublicacionMaestra response = new InformacionPublicacionMaestra();
        response =  vacancyService.getMasterVacancyInfo(idPublicacion);

        return this.buildEntitySuccessResponse(response);

    }


    public @ResponseBody
    @RequestMapping(value="guardar-estrategia-vacante-maestra", method = RequestMethod.POST)
    EntityResponseBean <InformacionPublicacionMaestra> guardarEstrategiaVacanteMaestra(@RequestBody InformacionEstrategiaVacanteMaestraDTO estrategia)throws GenericStatusException{

        BasicDTO response = new BasicDTO();
        response = vacancyService.saveMasterVacancyStrategy(estrategia);

        return this.buildEntitySuccessResponse(response);

    }




    //obtener-vacante
    public EntityResponseBean<VacanteVO> getVacancyById(BasicDTO dto){
        VacanteVO entity = vacancyService.getVacancyById(dto);
        return this.buildEntitySuccessResponse(entity);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="obtener-estrategia", method = RequestMethod.GET)
    public EntityResponseBean<EstrategiaVO> getEstrategyById(@RequestParam(value="idPublicacion") int idPublicacion)throws GenericStatusException{
        EstrategiaVO response = new EstrategiaVO();

        response = vacancyService.getStrategyById(idPublicacion);

        return this.buildEntitySuccessResponse(response);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="obtener-estrategia-vacante-maestra", method = RequestMethod.GET)
    public EntityResponseBean<EstrategiaVacanteMaestraVO> getMasterEstrategyById(@RequestParam(value="idPublicacion") int idPublicacion)throws GenericStatusException{
        EstrategiaVacanteMaestraVO response = new EstrategiaVacanteMaestraVO();

        response = vacancyService.getMasterVacancyStrategy(idPublicacion);
        return this.buildEntitySuccessResponse(response);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="obtener-niveles-puesto", method = RequestMethod.GET)
    public EntityResponseBean<ArrayList<CatalogoVO>> getPositionLevel()throws GenericStatusException{

        ArrayList<CatalogoVO> nivelesPuesto = new ArrayList<>();

        nivelesPuesto = vacancyService.getPositionLevels();

        return this.buildEntitySuccessResponse(nivelesPuesto);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="obtener-divisiones", method = RequestMethod.GET)
    public EntityResponseBean<LinkedList<CatalogoVO>> getDivisions( @RequestHeader("authorization")String token)throws GenericStatusException{

        LinkedList<CatalogoVO> gerencias = new LinkedList<>();

        String cleanToken = this.getCleanToken(token);
        Integer idRama = tokenService.getBranchId(cleanToken);
        gerencias = vacancyService.getDivisions(idRama);

        return this.buildEntitySuccessResponse(gerencias);
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="obtener-regiones", method = RequestMethod.GET)
    public EntityResponseBean<LinkedList<CatalogoVO>> getRegions(@RequestParam(value="idDivision") int idDivision)throws GenericStatusException{
        LinkedList<CatalogoVO> regionales = new LinkedList<>();


        regionales = vacancyService.getRegionsByBranch(idDivision);


        return this.buildEntitySuccessResponse(regionales);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="obtener-gerencias", method = RequestMethod.GET)
    public EntityResponseBean<LinkedList<CatalogoVO>> getManagement(@RequestParam(value="idRegional") int idRegional)throws GenericStatusException{

        LinkedList<CatalogoVO> gerencias = new LinkedList<>();


        gerencias = vacancyService.getDepartmentsByRegional(idRegional);
        return this.buildEntitySuccessResponse(gerencias);
    }


    @ResponseBody
    @RequestMapping(value="obtener-sedes", method = RequestMethod.GET)
    public EntityResponseBean<LinkedList<CatalogoVO>> getSedesDivision(
            @RequestHeader("authorization")String token,
            @RequestParam(value="idGerencia") int idGerencia)throws GenericStatusException{

        LinkedList<CatalogoVO> gerencias = new LinkedList<>();

        String cleanToken = this.getCleanToken(token);
        Integer idRama = tokenService.getBranchId(cleanToken);
        gerencias = vacancyService.getOffice(idRama,idGerencia);
        return this.buildEntitySuccessResponse(gerencias);


    }



    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="obtener-sugerencias-candidatos", method = RequestMethod.GET)
    public EntityResponseBean<ArrayList<SugerenciaCandidatoVO>> getSugerenciasCandidatos(
            @RequestHeader("Authorization") String token,
            @RequestParam int idPublicacion,
            @RequestParam int idSede,
            @RequestParam int idVacante) throws GenericStatusException{
        BuscarDatosVacanteDTO request =new BuscarDatosVacanteDTO();
        ArrayList<SugerenciaCandidatoVO> response = new ArrayList<>();

       response = vacancyService.getCandidateSuggestion(idVacante,idSede,idPublicacion);
       return buildEntitySuccessResponse(response);
    }

    @ResponseBody
    @RequestMapping(value="obtener-bolsas", method = RequestMethod.GET)
    public EntityResponseBean<ArrayList<BasicCatalogVO>> getBolsasDeTrabajo(@RequestParam(value="idVacante") int idVacante)throws GenericStatusException{


        ArrayList<BasicCatalogVO> bolsas = vacancyService.getJobBoard(idVacante);
        return this.buildEntitySuccessResponse(bolsas);
    }

    @ResponseBody
    @RequestMapping(value="obtener-bolsas-externas-por-rama", method = RequestMethod.GET)
    public EntityResponseBean<ArrayList<BasicCatalogVO>> getBolsasDeTrabajoDivision(@RequestHeader("authorization")String token)throws GenericStatusException{
        ArrayList<BasicCatalogVO> bolsas = new ArrayList<>();

        token = getCleanToken(token);

        Integer idRama = tokenService.getBranchId(token);

        bolsas = vacancyService.getExternalJobBoardBranch(idRama);

        return this.buildEntitySuccessResponse(bolsas);
    }



    @ResponseBody
    @RequestMapping(value="obtener-candidatos", method = RequestMethod.GET)
    EntityResponseBean<TablaPostuladosVO> getCandidates(
            @RequestHeader("Authorization") String token,
            @RequestParam(value="idPublicacion") Integer idPublicacion,
            @RequestParam(value="paginaActual") Integer paginaActual) throws GenericStatusException{
        BuscarDatosVacanteDTO request =new BuscarDatosVacanteDTO();

        String idRole = tokenService.getUserRoleFromToken(this.getCleanToken(token));

        request.setPaginaActual(paginaActual);
        request.setIdPublicacion(idPublicacion);
        request.setIdAnalista(tokenService.getUserIdFromToken(this.getCleanToken(token)));
        request.setRole(tokenService.getUserRoleFromToken(this.getCleanToken(token)));
        TablaPostuladosVO tablaPostuladosVO = new TablaPostuladosVO();
        tablaPostuladosVO = vacancyService.getCandidates(request,idRole,paginaActual);

        return this.buildEntitySuccessResponse(tablaPostuladosVO);
    }


    @ResponseBody
    @RequestMapping(value="cerrar-vacante", method = RequestMethod.GET)
    public BaseResponseBean cancelVacancy(
            @RequestHeader("Authorization") String token,
            @RequestParam(value="idPublicacion")int idPublicacion)throws GenericStatusException{

        vacancyService.cancelVacancy(idPublicacion,tokenService.getUserIdFromToken(getCleanToken(token)));
        return this.buildSuccessResponse();
    }


    @ResponseBody
    @RequestMapping(value="cancelar-publicacion", method = RequestMethod.GET)
    public BaseResponseBean cancelPublication(@RequestParam(value="idPublicacion")int idPublicacion){
        //vacancyService.cancelVacancy(dto);
        return this.buildSuccessResponse();
    }

    //TODO: implementar logiga para cancelar
    @ResponseBody
    @RequestMapping(value="cambiar-estado-vacante", method = RequestMethod.POST)
    public BaseResponseBean cambiarEstadoVacante(@RequestBody EstadoVacanteDTO dto) throws GenericStatusException{
        if(vacancyService.changeStateVacancy(dto)){
            return this.buildSuccessResponse();
        }
        throw new GenericStatusException();

    }

    @ResponseBody
    @RequestMapping(value="asignar-responsabilidad-publicacion", method = RequestMethod.POST)
    public BaseResponseBean asignarResponsabilidad(@RequestBody AsignarPublicacionDTO dto)throws GenericStatusException{

        boolean response =  vacancyService.assignPublication(dto);
        return this.buildSuccessResponse();
    }

    //TODO: obtener usuario de token
    @ResponseBody
    @RequestMapping(value="buscar-colaboradores", method = RequestMethod.GET)
    public EntityResponseBean<LinkedList<PersonaProfuturoVO>> searchCollaborators(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "email", required = false)String email)throws GenericStatusException{

        LinkedList<PersonaProfuturoVO> colaboradores = new LinkedList<>();
        String numeroEmpleado = tokenService.getUserIdFromToken(this.getCleanToken(token));
        Integer idRama = tokenService.getBranchId(this.getCleanToken(token));
        colaboradores = vacancyService.searchCollaborator(numeroEmpleado, email, idRama);

        return this.buildEntitySuccessResponse(colaboradores);
    }




    @ResponseBody
    @RequestMapping(value="buscar-observador", method = RequestMethod.GET)
    public EntityResponseBean<List<PersonaProfuturoVO>> buscarObservador(@RequestParam(value="emailObservador") String emailObservador)throws GenericStatusException{

        LinkedList<PersonaProfuturoVO> observadores = new LinkedList<>();
        observadores = vacancyService.searchObserver(emailObservador);

        return this.buildEntitySuccessResponse(observadores);

    }


    /*
     
    //obtener-formulario-estrategia
    public EntityResponseBean<CatalogoVO> getStrategyForm(BasicDTO dto){

        CatalogoVO entity = vacancyService.getStrategyForm(dto);
        return this.buildEntitySuccessResponse(entity);
    }

    //exportar-por-bolsa (GenerarContenidoPublico)
    public EntityResponseBean<List<PersonaProfuturoVO>> exportToExternalExchange(){
        return null;
    }




    //obtener-vacantes-maestras-por-rol
    public EntityResponseBean<List<PersonaProfuturoVO>> getMasterVacancies(){
        return null;
    }

    //abrir-plazas
    public BaseResponseBean openPlaces(PublicacionDTO dto){
        return null;
    }

    //asignar-responsabilidad-publicacion
    public BaseResponseBean asignVacancyTO(PublicacionDTO dto){
        return null;
    }
    //ver-contenido-publicacion
    public EntityResponseBean<List<PersonaProfuturoVO>> getPublicationDetail(){
        return null;
    }

    */


    @ResponseBody
    @RequestMapping(value="generar-contenido-por-bolsa", method = RequestMethod.GET)
    public EntityResponseBean<ArrayList<ContenidoCodigoBolsaVO>> getContentByExternalExchange(@RequestParam(value = "idPublicacion") Integer idPublicacion)throws GenericStatusException{

        ArrayList<ContenidoCodigoBolsaVO> response = new ArrayList<>();
        response = vacancyService.generateContent(idPublicacion);

        return this.buildEntitySuccessResponse(response);

    }

    @ResponseBody
    @RequestMapping(value="iniciar-proceso", method = RequestMethod.POST)
    public BaseResponseBean startProcess(
            @RequestBody IniciarProcesoDTO iniciarProcesoDTO)throws InterviewException,GenericStatusException{

        if(vacancyService.startProcess(iniciarProcesoDTO)){
            return this.buildSuccessResponse();
        }
        throw new GenericStatusException();

    }


    @ResponseBody
    @RequestMapping(value="descartar", method = RequestMethod.POST)
    public BaseResponseBean discartFromVacancy(
            @RequestBody IniciarProcesoDTO iniciarProcesoDTO)throws GenericStatusException{

        if(vacancyService.discardFromProcess(iniciarProcesoDTO)){
            return this.buildSuccessResponse();
        }
        throw new GenericStatusException();

    }


    /*@CrossOrigin
    @RequestMapping(value = "/estados-mexicanos", method = RequestMethod.GET)
    @ApiOperation(value = "home")
    public @ResponseBody
    EntityResponseBean<ArrayList<CatalogoVO>>  getMexicanStates()throws GenericStatusException{
        ArrayList<CatalogoVO>respuesta = new ArrayList<>();

        ArrayList<CatalogoVO> mexicanStates = new ArrayList<CatalogoVO>();

        mexicanStates.add(new CatalogoVO(1,"Aguascalientes"));
        mexicanStates.add(new CatalogoVO(2,"Baja California"));
        mexicanStates.add(new CatalogoVO(3,"Baja California Sur"));
        mexicanStates.add(new CatalogoVO(4,"Campeche"));
        mexicanStates.add(new CatalogoVO(5,"Coahuila"));
        mexicanStates.add(new CatalogoVO(6,"Colima"));
        mexicanStates.add(new CatalogoVO(7,"Chiapas"));
        mexicanStates.add(new CatalogoVO(8,"Chihuahua"));
        mexicanStates.add(new CatalogoVO(9,"Ciudad de México"));
        mexicanStates.add(new CatalogoVO(10,"Durango"));
        mexicanStates.add(new CatalogoVO(11,"Guanajuato"));
        mexicanStates.add(new CatalogoVO(12,"Guerrero"));
        mexicanStates.add(new CatalogoVO(13,"Hidalgo"));
        mexicanStates.add(new CatalogoVO(14,"Jalisco"));
        mexicanStates.add(new CatalogoVO(15,"México"));
        mexicanStates.add(new CatalogoVO(16,"Michoacán"));
        mexicanStates.add(new CatalogoVO(17,"Morelos"));
        mexicanStates.add(new CatalogoVO(18,"Nayarit"));
        mexicanStates.add(new CatalogoVO(19,"Nuevo León"));
        mexicanStates.add(new CatalogoVO(20,"Oaxaca"));
        mexicanStates.add(new CatalogoVO(21,"Puebla"));
        mexicanStates.add(new CatalogoVO(22,"Querétaro"));
        mexicanStates.add(new CatalogoVO(23,"Quintana Roo"));
        mexicanStates.add(new CatalogoVO(24,"San Luis Potosí"));
        mexicanStates.add(new CatalogoVO(25,"Sinaloa"));
        mexicanStates.add(new CatalogoVO(26,"Sonora"));
        mexicanStates.add(new CatalogoVO(27,"Tabasco"));
        mexicanStates.add(new CatalogoVO(28,"Tamaulipas"));
        mexicanStates.add(new CatalogoVO(29,"Tlaxcala"));
        mexicanStates.add(new CatalogoVO(30,"Veracruz"));
        mexicanStates.add(new CatalogoVO(31,"Yucatán"));
        mexicanStates.add(new CatalogoVO(32,"Zacatecas"));

        return this.buildEntitySuccessResponse(mexicanStates);
    } */

    @CrossOrigin
    @RequestMapping(value = "/estados-mexicanos", method = RequestMethod.GET)
    @ApiOperation(value = "home")
    public @ResponseBody
    EntityResponseBean<ArrayList<CatalogoVO>>  getMexicanStates()throws GenericStatusException{
        ArrayList<CatalogoVO>respuesta = vacancyService.getMexicanStates();

        return this.buildEntitySuccessResponse(respuesta);
    }


}
