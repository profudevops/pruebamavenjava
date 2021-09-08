package com.mx.profuturo.bolsa.service.vacantes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.mx.profuturo.bolsa.model.common.GenericParamRequestBean;
import com.mx.profuturo.bolsa.model.restclient.ConnectionConfigBean;
import com.mx.profuturo.bolsa.model.role.CommercialRoles;
import com.mx.profuturo.bolsa.model.role.CorporateRoles;
import com.mx.profuturo.bolsa.model.service.BaseResponseBean;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.service.vacancies.base.Persona;
import com.mx.profuturo.bolsa.model.service.vacancies.base.VacanteDatosBasicos;
import com.mx.profuturo.bolsa.model.service.vacancies.dto.*;
import com.mx.profuturo.bolsa.model.service.vacancies.vo.*;
import com.mx.profuturo.bolsa.model.vo.common.BasicCatalogVO;
import com.mx.profuturo.bolsa.model.vo.common.SucursalVO;
import com.mx.profuturo.bolsa.model.vo.input.GenerarContenidoBolsaInput;
import com.mx.profuturo.bolsa.model.vo.vacancy.*;
import com.mx.profuturo.bolsa.service.colecciones.ColeccionVacantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.model.service.auth.dto.BasicDTO;
import com.mx.profuturo.bolsa.model.service.candidates.dto.SuccessCreateResponseBean;
import com.mx.profuturo.bolsa.model.service.candidates.dto.SuccessGetSeatsResponseBean;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.mockclient.MockClient;

@Service
@Scope("request")
@Profile("mock")
public class VacancyServiceMockImpl extends VacancyServiceBase {


	@Value("classpath:/mock/common/vacant_succesCreateResponse_response_bean.json")
	private Resource jsonSuccesCreateResponse;

	@Value("classpath:/mock/common/vacant_successGetSeatsResponseBean_response_bean.json")
	private Resource jsonSuccessGetSeatsResponseBean;

	@Autowired
	MockClient mockClient;

	@Autowired
	ColeccionVacantes coleccionVacantes;

	@Override
	protected BasicDTO _callcreateSeat(RequestBean request) throws GenericStatusException{
		BasicDTO response = null;
		response.setId(3);
		return response;
	}

/*	@Override
	protected SuccessGetSeatsResponseBean _callGetSeats() throws GenericStatusException{
		SuccessGetSeatsResponseBean response = null;
		try {
			response = mockClient.getMock(jsonSuccessGetSeatsResponseBean, SuccessGetSeatsResponseBean.class);
		}catch (Exception e){
			throw new GenericStatusException(e);
		}
	    return response;
	}*/


	@Override
	protected BasicDTO _callUpdateVacant(RequestBean request) throws GenericStatusException{
		BasicDTO response = new BasicDTO();
		response.setId(8);
		return  response;
	}

	@Override
	protected BasicDTO _callSaveStrategy(RequestBean<GuardarEstrategiaCorpDTO> request) throws GenericStatusException{
		BasicDTO response = new BasicDTO();
		response.setId(1);
		return response;
	}

	@Override
	protected BasicDTO _callCreateStrategy(RequestBean request) throws GenericStatusException{
		BasicDTO response = new BasicDTO();
		response.setId(6);
		return response;
	}

	@Override
	protected BasicDTO _callSaveMasterVacancyStrategy(RequestBean<InformacionEstrategiaVacanteMaestraDTO> genericRequestBean)throws GenericStatusException{
		BasicDTO response = new BasicDTO();
		response.setId(8);
		return response;
	}

	@Override
	protected BasicDTO __callSaveMasterVacancyPublication(RequestBean<GuardarPublicacionDTO> genericRequestBean) throws GenericStatusException{
		BasicDTO response = new BasicDTO();
		response.setId(10);
		return response;
	}

	@Override
	protected  BasicDTO __callSaveVacancy(RequestBean<CrearVacanteDTO> genericRequestBean) throws GenericStatusException{
		BasicDTO response = new BasicDTO();
		response.setId(12);
		return response;

	}

	@Override
	protected ResumenVacantesVO __callGetallVacancies(RequestBean<ObtenerEscritorioVacanteDTO> genericRequestBean) throws GenericStatusException{
	String role = genericRequestBean.getPayload().getIdRol();
	ResumenVacantesVO response = null;
		switch (role){
			case CorporateRoles.ADMN:
				response = this.getAllAdministratorVacancies(genericRequestBean);
				break;

			case CorporateRoles.RECRUITER:
				response = this.getAllRecruiterVacancies(genericRequestBean);
				break;

			case CommercialRoles.ADMN:
				response = this.getMastaerVacanciesByUser(genericRequestBean);
				break;

			case CommercialRoles.CONSULTOR:
				response = this.getMastaerVacanciesByUser(genericRequestBean);
				break;

			case CommercialRoles.ANALYST:
				response = this.getAnalystMasterVacancies(genericRequestBean);
				break;

			case CorporateRoles.COSTUMER_RECRUITER:
				response = this.getClientAtnMasterVacancies(genericRequestBean);
				break;

			case CommercialRoles.RECRUITER:
				response = this.getCommercialRecruiterVacancies(genericRequestBean);
				break;

			case CommercialRoles.OBSERVER:
				response = this.getCommerCialObserverVacancies(genericRequestBean);

				break;
		}

		return  response;

	}


	@Override
	protected GenericTextResponseBean __callStartProcess(RequestBean<IniciarProcesoDTO> genericRequestBean) throws GenericStatusException{

		GenericTextResponseBean response = new GenericTextResponseBean();
		response.setRespuesta("Exito");

		return response;

	}

	@Override
	protected GenericTextResponseBean __callDiscardFromProcess(RequestBean<IniciarProcesoDTO> genericRequestBean) throws GenericStatusException{
		GenericTextResponseBean response = new GenericTextResponseBean();
		response.setRespuesta("Exito");

		return response;

	}

	@Override
	protected InformacionPublicacionMaestra __callMasterVacancyInfo(RequestBean<Integer> genericRequestBean) throws GenericStatusException{
		InformacionPublicacionMaestra response = new InformacionPublicacionMaestra();
		BasicCatalogVO responsable = new BasicCatalogVO();
		responsable.setId(1);
		responsable.setDescripcion("Jessy");
		response.setTituloVacante("Asistente atención a clientes");
		response.setNumeroPlazas(5);
		response.setIdResponsable("1");
		return response;

	}

	@Override
	protected LinkedList<PersonaProfuturoVO> __callSearchCollaborator(RequestBean<Object> genericRequestBean)throws GenericStatusException{
		LinkedList<PersonaProfuturoVO> colaboradores = new LinkedList<>();

		PersonaProfuturoVO colab1 = new PersonaProfuturoVO();
		PersonaProfuturoVO colab2 = new PersonaProfuturoVO();
		PersonaProfuturoVO colab3 = new PersonaProfuturoVO();
		PersonaProfuturoVO colab4 = new PersonaProfuturoVO();

		colab1.setNombre("Karen Padierna");
		colab1.setEmail("karen.padierna@profuturo.com");
		colab1.setIdUsuario("01");

		colab2.setNombre("Jessy Padierna");
		colab2.setEmail("jessy.padierna@profuturo.com");
		colab2.setIdUsuario("03");

		colab3.setNombre("Steph Fuentes");
		colab3.setEmail("steph.fuentes@profuturo.com");
		colab3.setIdUsuario("04");

		colab4.setNombre("Karla Castillo");
		colab4.setEmail("karla.castillo@profuturo.com");
		colab4.setIdUsuario("05");
		colaboradores.add(colab1);
		colaboradores.add(colab2);
		colaboradores.add(colab3);
		colaboradores.add(colab4);

		return colaboradores;
	}

	@Override
	protected LinkedList<PersonaProfuturoVO> __callSearchObserver(RequestBean<String> genericRequestBean){
		LinkedList<PersonaProfuturoVO> colaboradores = new LinkedList<>();

		PersonaProfuturoVO colab1 = new PersonaProfuturoVO();
		PersonaProfuturoVO colab2 = new PersonaProfuturoVO();
		PersonaProfuturoVO colab3 = new PersonaProfuturoVO();
		PersonaProfuturoVO colab4 = new PersonaProfuturoVO();

		colab1.setNombre("Karen Padierna");
		colab1.setEmail("karen.padierna@profuturo.com");
		colab1.setIdUsuario("01");

		colab2.setNombre("Jessy Padierna");
		colab2.setEmail("jessy.padierna@profuturo.com");
		colab2.setIdUsuario("93");

		colab3.setNombre("Steph Fuentes");
		colab3.setEmail("steph.fuentes@profuturo.com");
		colab3.setIdUsuario("74");

		colab4.setNombre("Karla Castillo");
		colab4.setEmail("karla.castillo@profuturo.com");
		colab4.setIdUsuario("85");
		colaboradores.add(colab1);
		colaboradores.add(colab2);
		colaboradores.add(colab3);
		colaboradores.add(colab4);

		return colaboradores;
	}

	@Override
	protected LinkedList<InformacionGeneralVacanteMaestraVO> _callGetMasterVacanciesByUser(RequestBean request) throws GenericStatusException{

		LinkedList<InformacionGeneralVacanteMaestraVO> resumenPublicaciones = new LinkedList<>();

		resumenPublicaciones = coleccionVacantes.crearColleccionCardsVacantesMaestras();

		return resumenPublicaciones;

	}

	@Override
	protected VacanteGerenteComercialVO _callGetCommercialRecruiterVacancies(RequestBean request) throws GenericStatusException{

        VacanteGerenteComercialVO vacanteGerenteComercialVO = new VacanteGerenteComercialVO();
        vacanteGerenteComercialVO.setIdUsuario("ANA");
        vacanteGerenteComercialVO.setIdVacante(2);
        vacanteGerenteComercialVO.setTituloVacante("Gerente Comercial");
		LinkedList<ResumenReclutadorComercialVO> resumenPublicaciones = new LinkedList<>();

		resumenPublicaciones = coleccionVacantes.crearColeccionResumenPublicacionReclutadoraComercial();
        vacanteGerenteComercialVO.setPublicaciones(resumenPublicaciones);
		return vacanteGerenteComercialVO;

	}


	@Override
	protected ArrayList<SugerenciaCandidatoVO> __callGetCandidateSuggestions(RequestBean<Integer> genericRequestBean)throws GenericStatusException{
		SugerenciaCandidatoVO sug1 = new SugerenciaCandidatoVO();
		SugerenciaCandidatoVO sug2 = new SugerenciaCandidatoVO();
		SugerenciaCandidatoVO sug3 = new SugerenciaCandidatoVO();
		SugerenciaCandidatoVO sug4 = new SugerenciaCandidatoVO();
		SugerenciaCandidatoVO sug5 = new SugerenciaCandidatoVO();

		sug1.setIdCandidato(17);
		sug1.setNivelEstudios("Licenciado en informática");
		sug1.setNombre("Edgar Nuñez");
		sug1.setTelefono("5565244444");
		sug1.setTipoCandidato("sugerencia de cartera");

		sug2.setIdCandidato(18);
		sug2.setNivelEstudios("Ingeniero en computación");
		sug2.setNombre("Eduardo Reyes");
		sug2.setTelefono("5565244444");
		sug2.setTipoCandidato("Postulado");

		sug3.setIdCandidato(19);
		sug3.setNivelEstudios("Ingeniero en sistemas");
		sug3.setNombre("Phillip Anselmo");
		sug3.setTelefono("5565244444");
		sug3.setTipoCandidato("sugerencia de cartera");

		sug4.setIdCandidato(20);
		sug4.setNivelEstudios("Actuario");
		sug4.setNombre("Bruce Dickinson");
		sug4.setTelefono("5565244444");
		sug4.setTipoCandidato("Postulado");

		sug5.setIdCandidato(21);
		sug5.setNivelEstudios("Licenciado en informática");
		sug5.setNombre("Rusell Allen");
		sug5.setTelefono("5565244444");
		sug5.setTipoCandidato("sugerencia de cartera");

		ArrayList<SugerenciaCandidatoVO> sugerencias = new ArrayList<>();
		sugerencias.add(sug1);
		sugerencias.add(sug2);
		sugerencias.add(sug3);
		sugerencias.add(sug4);
		sugerencias.add(sug5);

		return sugerencias;

	}

	@Override
	public ArrayList<CatalogoVO> __callGetMexicanStates(RequestBean<GenericParamRequestBean> genericRequestBean) throws GenericStatusException{
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

		return mexicanStates;

	}


	@Override
	protected ListaPublicacionesVO __callGetPublications(RequestBean<ObtenerPublicacionesDTO> genericRequestBean) throws GenericStatusException{

		ListaPublicacionesVO response = new ListaPublicacionesVO();
		response.setIdSede(4);
		response.setSede("Mixcoac");
		response.setVacante("Vavante de atención a clientes");

		DatosPullicacionVO publicacion1 = new DatosPullicacionVO();
		DatosPullicacionVO publicacion2 = new DatosPullicacionVO();
		DatosPullicacionVO publicacion3 = new DatosPullicacionVO();

		publicacion1.setAnalista("Jessica Echegollén");
		publicacion1.setGerencia("Gerencia de Atención a Clientes");
		publicacion1.setIdGerencia(2);
		publicacion1.setIdAnalista(6);
		publicacion1.setPlazasAbiertas(4);
		publicacion1.setPlazasCubiertas(2);
		publicacion1.setIdPublicacion(1);

		publicacion2.setAnalista("Jessica Echegollén");
		publicacion2.setGerencia("Gerencia de Atención a Clientes");
		publicacion2.setIdGerencia(2);
		publicacion2.setIdAnalista(6);
		publicacion2.setPlazasAbiertas(4);
		publicacion2.setPlazasCubiertas(2);
		publicacion2.setIdPublicacion(1);

		publicacion3.setAnalista("Jessica Echegollén");
		publicacion3.setGerencia("Gerencia de Atención a Clientes");
		publicacion3.setIdGerencia(2);
		publicacion3.setIdAnalista(6);
		publicacion3.setPlazasAbiertas(4);
		publicacion3.setPlazasCubiertas(2);
		publicacion3.setIdPublicacion(1);

		ArrayList<DatosPullicacionVO> publicaciones = new ArrayList<>();
		publicaciones.add(publicacion1);
		publicaciones.add(publicacion2);
		publicaciones.add(publicacion3);

		response.setPublicaciones(publicaciones);

		return response;
	}

	@Override
	protected List<ContenidoCodigoBolsaVO> __callGenerateContent(RequestBean<GenerarContenidoBolsaInput> genericRequestBean)throws GenericStatusException{
		ContenidoCodigoBolsaVO contenido1 = new ContenidoCodigoBolsaVO();
		ContenidoCodigoBolsaVO contenido2 = new ContenidoCodigoBolsaVO();
		ContenidoCodigoBolsaVO contenido3 = new ContenidoCodigoBolsaVO();

		contenido1.setBolsaTrabajo("OCC");
		contenido1.setCodigo("123abc");
		contenido1.setContenido("Asistente de atención a clientes\n" +
				"Intégrate a un gran equipo\n" +
				"con est código"+contenido1.getCodigo());

		contenido2.setBolsaTrabajo("Computrabajo");
		contenido2.setCodigo("345abc");
		contenido2.setContenido("Asistente de atención a clientes\n" +
				"Intégrate a un gran equipo\n" +
				"con est código"+contenido2.getCodigo());


		contenido3.setBolsaTrabajo("Bumeran");
		contenido3.setCodigo("890xyz");
		contenido3.setContenido("Asistente de atención a clientes\n" +
				"Intégrate a un gran equipo\n" +
				"con est código"+contenido3.getCodigo());

		List<ContenidoCodigoBolsaVO> response = new ArrayList<>();
		response.add(contenido1);
		response.add(contenido2);
		response.add(contenido3);

		return response;
	}


	@Override
	protected TablaPostuladosVO __callGetCandidates(RequestBean<BuscarDatosVacanteDTO> genericRequestBean){
		TablaPostuladosVO tablaPostuladosVO = new TablaPostuladosVO();

       ArrayList<ResumenAplicacionVO> postulados = new ArrayList<>();
        ResumenAplicacionVO postulado1 = new ResumenAplicacionVO();
        ResumenAplicacionVO postulado2 = new ResumenAplicacionVO();
        ResumenAplicacionVO postulado3 = new ResumenAplicacionVO();
        ResumenAplicacionVO postulado4 = new ResumenAplicacionVO();


        postulado1.setCorreo("julio.llamas@gmail.com");
        postulado1.setEstatus("Primeras entrevistas");
        postulado1.setReclutador("Lorena Vázquez");
        postulado1.setIdCandidato(1);
        postulado1.setNombrePostulado("Julio LLamas Moctezuma");
        postulado1.setTelefono("5522481070");



        postulado2.setCorreo("julio.arredondo@gmail.com");
        postulado2.setEstatus("Examen Psicométrico");
        postulado2.setReclutador("Brenda Rodríguez");
        postulado2.setIdCandidato(2);
        postulado2.setNombrePostulado("Julio Arredondo");
        postulado2.setTelefono("55114783599");

        postulado3.setCorreo("julio.arredondo@gmail.com");
        postulado3.setEstatus("rechazado");
        postulado3.setReclutador("Daniela Fuentes");
        postulado3.setIdCandidato(3);
        postulado3.setNombrePostulado("Arnulfo Loredo");
        postulado3.setTelefono("55114783599");


        postulados.add(postulado1);
        postulados.add(postulado2);
        postulados.add(postulado3);

        tablaPostuladosVO.setPostulados(postulados);
        tablaPostuladosVO.setPaginaActual(1);
        tablaPostuladosVO.setTotalPaginas(2);
		return tablaPostuladosVO;
	}


	public ResumenVacantesAdminCorpVO __callGetAdministratorVacancies(RequestBean requestBean){

		LinkedList<ResumenVacanteCorp> resumenVacantes = coleccionVacantes.createResumenVacantesCorp();
        LinkedList<InformacionGeneralVacanteMaestraVO>resumenPublicaciones = new LinkedList<>();

        InformacionGeneralVacanteMaestraVO v1 = new InformacionGeneralVacanteMaestraVO();
		InformacionGeneralVacanteMaestraVO v2 = new InformacionGeneralVacanteMaestraVO();

        v1.setIdVacante(3);
        v1.setTituloVacante("Asistente de Atención a clientes");
        v1.setTotalPlazasAbiertas(10);
        v1.setTotalProcesos(45);
        resumenPublicaciones.add(v1);

		ResumenVacantesAdminCorpVO resumenAdminCorp = new ResumenVacantesAdminCorpVO();
		resumenAdminCorp.setVacantes(resumenVacantes);
		resumenAdminCorp.setVacantesMaestrasAnalistaClientes(resumenPublicaciones);

		return resumenAdminCorp;
	}

	public LinkedList<ResumenVacanteCorp> __callGetCorpObserverVacancies(RequestBean requestBean){

		LinkedList<ResumenVacanteCorp> resumenVacantes = coleccionVacantes.createResumenVacantesCorp();

		return resumenVacantes;
	}

	public ResumenPublicacionesVO  __callGetRecruiterVacancies(RequestBean requestBean){

		ResumenPublicacionesVO resumenPublicaciones = new ResumenPublicacionesVO();

		resumenPublicaciones = coleccionVacantes.crearColeccionResumenPublicacionReclutadora();

		return resumenPublicaciones;
	}

	public LinkedList<ResumenVacanteMaestraAnalistaVO> __callGetAnalystVacancies(RequestBean requestBean){

		LinkedList<ResumenVacanteMaestraAnalistaVO> resumenPublicaciones = new LinkedList<>();

		resumenPublicaciones = coleccionVacantes.createResumenVacantesAnalista();

		return resumenPublicaciones;
	}


	@Override
	protected ArrayList<BasicCatalogVO> callGetJobBoard(RequestBean<VacanteDTO> genericRequestBean)throws GenericStatusException{
        ArrayList<BasicCatalogVO> bolsas = new ArrayList<>();
        BasicCatalogVO bolsas1 =new BasicCatalogVO();
        BasicCatalogVO bolsas2 =new BasicCatalogVO();
        BasicCatalogVO bolsas3 =new BasicCatalogVO();
        BasicCatalogVO bolsas4 =new BasicCatalogVO();
        bolsas1.setDescripcion("Computrabajo");
        bolsas1.setId(1);

        bolsas2.setDescripcion("OCC");
        bolsas2.setId(2);

        bolsas3.setDescripcion("Bumeran");
        bolsas3.setId(3);

        bolsas4.setDescripcion("Monster");
        bolsas4.setId(4);

        bolsas.add(bolsas1);
        bolsas.add(bolsas2);
        bolsas.add(bolsas3);
        bolsas.add(bolsas4);
		return bolsas;
	}

	@Override
	protected ArrayList<BasicCatalogVO> callGetExternalJobBoardBranch(RequestBean<GetExternalBranchRequest> genericRequestBean){
		BasicCatalogVO bolsas1 =new BasicCatalogVO();
		BasicCatalogVO bolsas2 =new BasicCatalogVO();
		BasicCatalogVO bolsas3 =new BasicCatalogVO();
		BasicCatalogVO bolsas4 =new BasicCatalogVO();
		bolsas1.setDescripcion("Computrabajo");
		bolsas1.setId(1);

		bolsas2.setDescripcion("OCC");
		bolsas2.setId(2);

		bolsas3.setDescripcion("Bumeran");
		bolsas3.setId(3);

		bolsas4.setDescripcion("Monster");
		bolsas4.setId(4);

		ArrayList<BasicCatalogVO> bolsas = new ArrayList<>();
		bolsas.add(bolsas1);
		bolsas.add(bolsas2);
		bolsas.add(bolsas3);
		bolsas.add(bolsas4);

		return bolsas;
	}

	@Override
	protected GetVacancyResponse callGetVacancy(RequestBean<VacanteDTO> genericRequestBean){
		GetVacancyResponse getVacancyResponse = new GetVacancyResponse();
		PostuladosBean postuladosBean = new PostuladosBean();
		postuladosBean.setNombrePostulado("MAURICIO CHAVEZ VARGAS");
		postuladosBean.setCorreo("MAU@GMAIL.COM");
		postuladosBean.setEstatus("EL CANDIDATO HA INICIADO EL PROCESO DE RECLUTAMIENTO");
		postuladosBean.setIdCandidato(1);
		postuladosBean.setTelefono("7772223334");
		getVacancyResponse.getPostulados().add(postuladosBean);
		return getVacancyResponse;
	}

	@Override
	protected DetalleVacanteVO callVacancyDetail(RequestBean<BuscarDatosVacanteDTO> genericRequestBean){
		DetalleVacanteVO vacancyDetailResponse = new DetalleVacanteVO();

		if(genericRequestBean.getPayload().getIdPublicacion()==1){
			vacancyDetailResponse.setTituloVacante("Desarrollador Android");
			vacancyDetailResponse.setIdAreaInteres(25);
			vacancyDetailResponse.setSubArea(51);
			vacancyDetailResponse.setFechaInicio("2019-06-13");
			vacancyDetailResponse.setFechaFin("2019-11-14");
			vacancyDetailResponse.setSueldoMin(10000);
			vacancyDetailResponse.setSueldoMax(25000);
			vacancyDetailResponse.setIdPublicacion(1);
			vacancyDetailResponse.setDescripcion("Persona capaz de resolver conflictos");
			vacancyDetailResponse.setPlazasAbiertas(15);
			ArrayList<String> expectativas = new ArrayList<String>();
			expectativas.add("Liderazgo");
			expectativas.add("Negociación entre Líderes");
			expectativas.add("Negociación entre Gerentes");
			vacancyDetailResponse.setExpectativas(expectativas);
			ArrayList<String> funciones = new ArrayList<String>();
			expectativas.add("Experiencia en despliegues Websphere");
			expectativas.add("Conocimiento avanzado Oracle 11g");
			expectativas.add("Inglés avanzado");
			expectativas.add("Estadística");
			vacancyDetailResponse.setExpectativas(funciones);
			vacancyDetailResponse.setIdVacante(0);
			vacancyDetailResponse.setAsignada("002893");
			vacancyDetailResponse.setNumeroPostulados(0);
			vacancyDetailResponse.setIdEstrategia(4);
			vacancyDetailResponse.setIdSede(1);
			vacancyDetailResponse.setEstatus("PRIMERAS ENTREVISTAS");
			vacancyDetailResponse.setVacanteMaestra(false);
			vacancyDetailResponse.setTipoReclutamiento("GE");
		}else{
			vacancyDetailResponse.setTituloVacante("Asistente de Atención a clientes");
			vacancyDetailResponse.setIdAreaInteres(25);
			vacancyDetailResponse.setSubArea(51);
			vacancyDetailResponse.setFechaInicio("2019-06-13");
			vacancyDetailResponse.setFechaFin("2019-11-14");
			vacancyDetailResponse.setSueldoMin(10000);
			vacancyDetailResponse.setSueldoMax(25000);
			vacancyDetailResponse.setIdPublicacion(1);
			vacancyDetailResponse.setDescripcion("Persona capaz de resolver conflictos");
			vacancyDetailResponse.setPlazasAbiertas(15);
			ArrayList<String> expectativas = new ArrayList<String>();
			expectativas.add("Liderazgo");
			expectativas.add("Negociación entre Líderes");
			expectativas.add("Negociación entre Gerentes");
			vacancyDetailResponse.setExpectativas(expectativas);
			ArrayList<String> funciones = new ArrayList<String>();
			expectativas.add("Experiencia en despliegues Websphere");
			expectativas.add("Conocimiento avanzado Oracle 11g");
			expectativas.add("Inglés avanzado");
			expectativas.add("Estadística");
			vacancyDetailResponse.setExpectativas(funciones);
			vacancyDetailResponse.setIdVacante(0);
			vacancyDetailResponse.setAsignada("002893");
			vacancyDetailResponse.setNumeroPostulados(0);
			vacancyDetailResponse.setIdEstrategia(4);
			vacancyDetailResponse.setEstatus("PRIMERAS ENTREVISTAS");
			vacancyDetailResponse.setVacanteMaestra(false);
			vacancyDetailResponse.setAnalista("Jessy Echegollén");
			vacancyDetailResponse.setIdSede(1);
			vacancyDetailResponse.setGerencia("Gerecnia de Atención a clientes");
			vacancyDetailResponse.setSede("Corporativo");
			vacancyDetailResponse.setTipoReclutamiento("AS");
		}

		return vacancyDetailResponse;
	}

	@Override
	protected BaseResponseBean callChangeStateVacancyResponse(RequestBean<EstadoVacanteDTO> genericRequestBean){
		ChangeStateVacancyResponse changeStateVacancyResponse = new ChangeStateVacancyResponse();
		BaseResponseBean response = new BaseResponseBean();
		response.setSuccess(true);
		return response;
	}

	@Override
	protected GenericTextResponseBean __callEditMasterVacancySpots(RequestBean<EditarPlazasVacanteMaestraDTO> genericRequestBean)throws GenericStatusException{
		GenericTextResponseBean response = new GenericTextResponseBean();

		response.setRespuesta("Exito");

		return response;
	}




	@Override
	public List<VacanteDatosBasicos> searchVacanciesByName(BusquedaDTO busquedaDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VacanteVO getVacancyById(BasicDTO basicDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicDTO updateVacancy(VacanteDTO vacanteDTO) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CatalogoVO getStrategyForm(BasicDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<CatalogoVO> __callGetDepartments(RequestBean<Integer> genericRequestBean){

		List<CatalogoVO> gerencias = new LinkedList<>();

		CatalogoVO gerencia1 = new CatalogoVO();
		CatalogoVO gerencia2 = new CatalogoVO();
		CatalogoVO gerencia3 = new CatalogoVO();
		CatalogoVO gerencia4 = new CatalogoVO();

		gerencia1.setId(1);
		gerencia1.setDescripcion("Traspasos");

		gerencia2.setId(2);
		gerencia2.setDescripcion("Asesores Premium");

		gerencia3.setId(3);
		gerencia3.setDescripcion("Cuentas Especiales");



		gerencias.add(gerencia1);
		gerencias.add(gerencia2);
		gerencias.add(gerencia3);

		return gerencias;

	}



	@Override
	protected List<CatalogoVO> __callFindOffice(RequestBean<Integer> genericRequestBean)throws GenericStatusException{
		LinkedList<CatalogoVO> gerencias = new LinkedList<>();


		gerencias.add(new CatalogoVO(13,"San Pedro Garza"));
		gerencias.add(new CatalogoVO(14,"Monterrey"));
		gerencias.add(new CatalogoVO(15,"Querétaro"));


		return gerencias;

	}

	@Override
	protected InformacionVacanteVO __callGetVacancyInfo(RequestBean<Integer> genericRequestBean) throws GenericStatusException {
		InformacionVacanteVO response = new InformacionVacanteVO();
		ArrayList<String> expectativas = new ArrayList<>();
		ArrayList<String> funciones = new ArrayList<>();
		Integer idVacante = 1;
		if(idVacante==1){
			String exp1="Liderazgo";
			String exp2="Negociacion";
			String exp3="Trabajo orientado a resultados";
			String func1="Desarrollar apps";
			String func2="Mantenimiento de apps";
			String func3="Pruebas QA de apps";

			funciones.add(func1);
			funciones.add(func2);
			funciones.add(func3);

			expectativas.add(exp1);
			expectativas.add(exp2);
			expectativas.add(exp3);

			response.setTituloVacante("Desarrollador Android");
			response.setIdAreaInteres(1);
			response.setSubArea(51);
			response.setFechaInicio("01/09/2019");
			response.setFechaFin("30/09/2019");
			response.setSueldoMin(30000);
			response.setSueldoMax(38000);
			response.setDescripcion("Intégrate a un equipo de desarrolladaores profesionales");
			response.setExpectativas(expectativas);
			response.setFunciones(expectativas);
			response.setNivelPuesto("");
			response.setIdNivelPuesto(1);
			response.setIdAreaInteres(1);
			response.setSubArea(10);
			response.setNombreSubArea("Desarrollo");
			response.setIdVacante(10);}
		else if(idVacante==2){


			String exp1="Liderazgo";
			String exp2="Negociacion";
			String exp3="Trabajo orientado a resultados";
			String func1="Desarrollar apps";
			String func2="Mantenimiento de apps";
			String func3="Pruebas QA de apps";

			funciones.add(func1);
			funciones.add(func2);
			funciones.add(func3);

			expectativas.add(exp1);
			expectativas.add(exp2);
			expectativas.add(exp3);

			response.setTituloVacante("Asistente atención a clientes");
			response.setIdAreaInteres(1);
			response.setSubArea(51);
			response.setFechaInicio("01/09/2019");
			response.setFechaFin("30/09/2019");
			response.setSueldoMin(30000);
			response.setSueldoMax(38000);
			response.setDescripcion("Intégrate a un equipo de desarrolladaores profesionales");
			response.setExpectativas(expectativas);
			response.setFunciones(expectativas);
			response.setIdVacante(10);
			response.setTipo("vacante-maestra");
		}else{
			String exp1="Liderazgo";
			String exp2="Negociacion";
			String exp3="Trabajo orientado a resultados";
			String func1="Desarrollar apps";
			String func2="Mantenimiento de apps";
			String func3="Pruebas QA de apps";

			funciones.add(func1);
			funciones.add(func2);
			funciones.add(func3);

			expectativas.add(exp1);
			expectativas.add(exp2);
			expectativas.add(exp3);

			response.setTituloVacante("Desarrollador Android");
			response.setIdAreaInteres(1);
			response.setSubArea(51);
			response.setFechaInicio("01/09/2019");
			response.setFechaFin("30/09/2019");
			response.setSueldoMin(30000);
			response.setSueldoMax(38000);
			response.setDescripcion("Intégrate a un equipo de desarrolladaores profesionales");
			response.setExpectativas(expectativas);
			response.setFunciones(expectativas);
			response.setIdVacante(10);
		}

		return response;
	}

	@Override
	protected DetalleVacanteCerradaVO __callGetClosedVacancyDetail(RequestBean<Integer> genericRequestBean) throws GenericStatusException {
		DetalleVacanteCerradaVO response = new DetalleVacanteCerradaVO();

		response.setNumeroEnProeso(19);
		response.setNumeroEnProeso(2);
		response.setAsignada("Jessy");
		response.setEstatus("Cerrada");
		response.setMotivoCierre("Contratación");
		response.setCandidatoSeleccionado("Juan Eduardo Reyes");

		ArrayList<String> expectativas = new ArrayList<>();
		ArrayList<String> funciones = new ArrayList<>();
		String exp1="Liderazgo";
		String exp2="Negociacion";
		String exp3="Trabajo orientado a resultados";
		String func1="Desarrollar apps";
		String func2="Mantenimiento de apps";
		String func3="Pruebas QA de apps";

		funciones.add(func1);
		funciones.add(func2);
		funciones.add(func3);

		expectativas.add(exp1);
		expectativas.add(exp2);
		expectativas.add(exp3);

		response.setTituloVacante("Desarrollador Android");
		response.setIdAreaInteres(5);
		response.setSubArea(51);
		response.setFechaInicio("01/09/2019");
		response.setFechaFin("30/07/2019");
		response.setSueldoMin(30000);
		response.setSueldoMax(38000);
		response.setDescripcion("Intégrate a un equipo de desarrolladaores profesionales");
		response.setExpectativas(expectativas);
		response.setFunciones(expectativas);
		response.setIdVacante(456321);

		return response;
	}

	@Override
	protected EstrategiaVO __callGetStrategyById(RequestBean<Integer> genericRequestBean)throws GenericStatusException{
		EstrategiaVO response = new EstrategiaVO();
		ArrayList<Integer> bolsas = new ArrayList<>();
		bolsas.add(1);
		bolsas.add(2);
		bolsas.add(5);
		response.setBolsasExternas(bolsas);
		response.setExamenConfianza(1);
		response.setObservador("Karen Padierna");
		response.setNumeroEmpleadoObservador("01");
		response.setCorreoObservador("karen.padierna@profuturo.com");
		response.setReclutador("Jessy López");
		response.setVisibilidad(1);
		response.setIdPublicacion(10);
		response.setIdVacante(1);

		return response;
	}

	@Override
	protected ResumenVacanteMaestraVO __callGetMasterVacancySummary(RequestBean<Integer> genericRequestBean)throws GenericStatusException{
		ResumenVacanteMaestraVO resumen = new ResumenVacanteMaestraVO();
		ResumenPlazaMaestraVO resumenPublicacion1 = new ResumenPlazaMaestraVO();
		ResumenPlazaMaestraVO resumenPublicacion2 = new ResumenPlazaMaestraVO();
		ResumenPlazaMaestraVO resumenPublicacion3 = new ResumenPlazaMaestraVO();
		ResumenPlazaMaestraVO resumenPublicacion4 = new ResumenPlazaMaestraVO();
		ResumenPlazaMaestraVO resumenPublicacion5 = new ResumenPlazaMaestraVO();
		ResumenPlazaMaestraVO resumenPublicacion6 = new ResumenPlazaMaestraVO();

		Map<String, ?> map = genericRequestBean.getUriVariables();
		Integer idVacante = (Integer) map.get("idVacante");

		if(idVacante==1){
			ArrayList<String> analistaAsignado1 = new ArrayList<>();
			analistaAsignado1.add("Luis Méndez");
			analistaAsignado1.add("Arturo Govea");
			analistaAsignado1.add("María Helena López");
			analistaAsignado1.add("Daniela Fuentes");

			ArrayList<String> analistaAsignado2 = new ArrayList<>();
			analistaAsignado2.add("Laura Roldán");

			ArrayList<String> analistaAsignado3 = new ArrayList<>();
			analistaAsignado3.add("Enrique Sánchez");
			analistaAsignado3.add("Edgar Nuñez");
			analistaAsignado3.add("Ricardo Moctezuma");

			ArrayList<String> analistaAsignado4 = new ArrayList<>();
			analistaAsignado4.add("Janice Jaimes");
			analistaAsignado4.add("Mauricio Chávez");
			analistaAsignado4.add("Juan Reyes");

			ArrayList<String> analistaAsignado5 = new ArrayList<>();
			analistaAsignado5.add("Ricky Llamas");

			ArrayList<String> analistaAsignado6 = new ArrayList<>();
			analistaAsignado6.add("Julio Arredondo");
			analistaAsignado6.add("Erik Almaguer");


			resumenPublicacion1.setGerencia("Nuevo Leon");
			resumenPublicacion1.setSede("San Pedro");
			resumenPublicacion1.setIdVacante(10);
			resumenPublicacion1.setIdSede(5);
			resumenPublicacion1.setPlazasAbiertas(11);
			resumenPublicacion1.setAnalistAsignado(analistaAsignado1);


			resumenPublicacion2.setGerencia("Tamaulipas");
			resumenPublicacion2.setSede("Matamoros");
			resumenPublicacion2.setIdVacante(10);
			resumenPublicacion2.setIdSede(5);
			resumenPublicacion2.setPlazasAbiertas(9);
			resumenPublicacion2.setAnalistAsignado(analistaAsignado2);

			resumenPublicacion3.setGerencia("Tamaulipas");
			resumenPublicacion3.setSede("Tampico");
			resumenPublicacion3.setIdVacante(10);
			resumenPublicacion3.setIdSede(6);
			resumenPublicacion3.setPlazasAbiertas(11);
			resumenPublicacion3.setAnalistAsignado(analistaAsignado3);

			resumenPublicacion4.setGerencia("Tamaulipas");
			resumenPublicacion4.setSede("Altamira");
			resumenPublicacion4.setIdVacante(10);
			resumenPublicacion4.setIdVacante(7);
			resumenPublicacion4.setPlazasAbiertas(12);
			resumenPublicacion4.setAnalistAsignado(analistaAsignado4);

			resumenPublicacion5.setGerencia("Chihuahua");
			resumenPublicacion5.setSede("Chihuahua");
			resumenPublicacion5.setIdVacante(10);
			resumenPublicacion5.setIdSede(8);
			resumenPublicacion5.setPlazasAbiertas(3);
			resumenPublicacion5.setAnalistAsignado(analistaAsignado5);

			resumenPublicacion6.setGerencia("Aguascalientes");
			resumenPublicacion6.setSede("La enramada");
			resumenPublicacion6.setIdVacante(10);
			resumenPublicacion6.setIdSede(8);
			resumenPublicacion6.setPlazasAbiertas(7);
			resumenPublicacion6.setAnalistAsignado(analistaAsignado6);

			LinkedList<ResumenPlazaMaestraVO> resumenPublicaciones = new LinkedList<>();
			/*resumenPublicaciones.add(resumenPublicacion1);
			resumenPublicaciones.add(resumenPublicacion2);
			resumenPublicaciones.add(resumenPublicacion3);
			resumenPublicaciones.add(resumenPublicacion4);
			resumenPublicaciones.add(resumenPublicacion5);
			resumenPublicaciones.add(resumenPublicacion6);*/

			resumen.setIdVacante(1);
			resumen.setTituloVacante("Asesor Traspasos");
			resumen.setPublicaciones(resumenPublicaciones);}
		else if(idVacante==2){
			ArrayList<String> analistaAsignado1 = new ArrayList<>();
			analistaAsignado1.add("Luis Méndez");
			analistaAsignado1.add("Arturo Govea");
			analistaAsignado1.add("María Helena López");
			analistaAsignado1.add("Daniela Fuentes");

			ArrayList<String> analistaAsignado2 = new ArrayList<>();
			analistaAsignado2.add("Laura Roldán");

			ArrayList<String> analistaAsignado3 = new ArrayList<>();
			analistaAsignado3.add("Enrique Sánchez");
			analistaAsignado3.add("Edgar Nuñez");
			analistaAsignado3.add("Ricardo Moctezuma");

			ArrayList<String> analistaAsignado4 = new ArrayList<>();
			analistaAsignado4.add("Janice Jaimes");
			analistaAsignado4.add("Mauricio Chávez");
			analistaAsignado4.add("Juan Reyes");

			ArrayList<String> analistaAsignado5 = new ArrayList<>();
			analistaAsignado5.add("Ricky Llamas");

			ArrayList<String> analistaAsignado6 = new ArrayList<>();
			analistaAsignado6.add("Julio Arredondo");
			analistaAsignado6.add("Erik Almaguer");


			resumenPublicacion1.setGerencia("Nuevo Leon");
			resumenPublicacion1.setSede("San Pedro");
			resumenPublicacion1.setIdVacante(10);
			resumenPublicacion1.setIdSede(6);
			resumenPublicacion1.setPlazasAbiertas(11);
			resumenPublicacion1.setAnalistAsignado(analistaAsignado1);


			resumenPublicacion2.setGerencia("Tamaulipas");
			resumenPublicacion2.setSede("Matamoros");
			resumenPublicacion2.setIdVacante(10);
			resumenPublicacion2.setIdSede(7);
			resumenPublicacion2.setPlazasAbiertas(9);
			resumenPublicacion2.setAnalistAsignado(analistaAsignado2);

			resumenPublicacion3.setGerencia("Tamaulipas");
			resumenPublicacion3.setSede("Tampico");
			resumenPublicacion3.setIdVacante(10);
			resumenPublicacion3.setIdSede(8);
			resumenPublicacion3.setPlazasAbiertas(11);
			resumenPublicacion3.setAnalistAsignado(analistaAsignado3);

			resumenPublicacion4.setGerencia("Tamaulipas");
			resumenPublicacion4.setSede("Altamira");
			resumenPublicacion4.setIdVacante(10);
			resumenPublicacion4.setIdSede(5);
			resumenPublicacion4.setPlazasAbiertas(12);
			resumenPublicacion4.setAnalistAsignado(analistaAsignado4);

			resumenPublicacion5.setGerencia("Chihuahua");
			resumenPublicacion5.setSede("Chihuahua");
			resumenPublicacion5.setIdVacante(10);
			resumenPublicacion5.setIdVacante(8);
			resumenPublicacion5.setPlazasAbiertas(3);
			resumenPublicacion5.setAnalistAsignado(analistaAsignado5);

			resumenPublicacion6.setGerencia("Aguascalientes");
			resumenPublicacion6.setSede("La enramada");
			resumenPublicacion6.setIdVacante(10);
			resumenPublicacion6.setIdSede(9);
			resumenPublicacion6.setPlazasAbiertas(7);
			resumenPublicacion6.setAnalistAsignado(analistaAsignado6);

			LinkedList<ResumenPlazaMaestraVO> resumenPublicaciones = new LinkedList<>();
			resumenPublicaciones.add(resumenPublicacion1);
			resumenPublicaciones.add(resumenPublicacion2);
			resumenPublicaciones.add(resumenPublicacion3);
			resumenPublicaciones.add(resumenPublicacion4);
			resumenPublicaciones.add(resumenPublicacion5);
			resumenPublicaciones.add(resumenPublicacion6);

			resumen.setIdVacante(2);
			resumen.setTituloVacante("Asesor Traspasos");
			resumen.setPublicaciones(resumenPublicaciones);}

		else if(idVacante==3){

			ArrayList<String> analistaAsignado1 = new ArrayList<>();
			analistaAsignado1.add("Luis Méndez");
			analistaAsignado1.add("Arturo Govea");
			analistaAsignado1.add("María Helena López");
			analistaAsignado1.add("Daniela Fuentes");

			ArrayList<String> analistaAsignado2 = new ArrayList<>();
			analistaAsignado2.add("Laura Roldán");

			ArrayList<String> analistaAsignado3 = new ArrayList<>();
			analistaAsignado3.add("Enrique Sánchez");
			analistaAsignado3.add("Edgar Nuñez");
			analistaAsignado3.add("Ricardo Moctezuma");

			ArrayList<String> analistaAsignado4 = new ArrayList<>();
			analistaAsignado4.add("Janice Jaimes");
			analistaAsignado4.add("Mauricio Chávez");
			analistaAsignado4.add("Juan Reyes");

			ArrayList<String> analistaAsignado5 = new ArrayList<>();
			analistaAsignado5.add("Ricky Llamas");

			ArrayList<String> analistaAsignado6 = new ArrayList<>();
			analistaAsignado6.add("Julio Arredondo");
			analistaAsignado6.add("Erik Almaguer");


			resumenPublicacion1.setGerencia("Nuevo Leon");
			resumenPublicacion1.setSede("San Pedro");
			resumenPublicacion1.setIdVacante(10);
			resumenPublicacion1.setIdSede(5);
			resumenPublicacion1.setPlazasAbiertas(11);
			resumenPublicacion1.setAnalistAsignado(analistaAsignado1);


			resumenPublicacion2.setGerencia("Tamaulipas");
			resumenPublicacion2.setSede("Matamoros");
			resumenPublicacion2.setIdVacante(10);
			resumenPublicacion2.setIdSede(5);
			resumenPublicacion2.setPlazasAbiertas(9);
			resumenPublicacion2.setAnalistAsignado(analistaAsignado2);

			resumenPublicacion3.setGerencia("Tamaulipas");
			resumenPublicacion3.setSede("Tampico");
			resumenPublicacion3.setIdVacante(10);
			resumenPublicacion3.setIdSede(5);
			resumenPublicacion3.setPlazasAbiertas(11);
			resumenPublicacion3.setAnalistAsignado(analistaAsignado3);

			resumenPublicacion4.setGerencia("Tamaulipas");
			resumenPublicacion4.setSede("Altamira");
			resumenPublicacion4.setIdVacante(10);
			resumenPublicacion4.setIdSede(5);
			resumenPublicacion4.setPlazasAbiertas(12);
			resumenPublicacion4.setAnalistAsignado(analistaAsignado4);

			resumenPublicacion5.setGerencia("Chihuahua");
			resumenPublicacion5.setSede("Chihuahua");
			resumenPublicacion5.setIdVacante(10);
			resumenPublicacion5.setIdSede(5);
			resumenPublicacion5.setPlazasAbiertas(3);
			resumenPublicacion5.setAnalistAsignado(analistaAsignado5);

			resumenPublicacion6.setGerencia("Aguascalientes");
			resumenPublicacion6.setSede("La enramada");
			resumenPublicacion6.setIdVacante(10);
			resumenPublicacion6.setIdSede(5);
			resumenPublicacion6.setPlazasAbiertas(7);
			resumenPublicacion6.setAnalistAsignado(analistaAsignado6);

			LinkedList<ResumenPlazaMaestraVO> resumenPublicaciones = new LinkedList<>();
			resumenPublicaciones.add(resumenPublicacion1);
			resumenPublicaciones.add(resumenPublicacion2);
			resumenPublicaciones.add(resumenPublicacion3);
			resumenPublicaciones.add(resumenPublicacion4);
			resumenPublicaciones.add(resumenPublicacion5);
			resumenPublicaciones.add(resumenPublicacion6);

			resumen.setIdVacante(3);
			resumen.setTituloVacante("Asistente de atención a Clientes");
			resumen.setPublicaciones(resumenPublicaciones);

		}

		return resumen;
	}

	@Override
	protected List<CatalogoVO> __callGetRegions(RequestBean<Integer> genericRequestBean)throws GenericStatusException{
		LinkedList<CatalogoVO> gerencias = new LinkedList<>();

		gerencias.add(new CatalogoVO(13,"San Pedro Garza"));
		gerencias.add(new CatalogoVO(14,"Monterrey"));
		gerencias.add(new CatalogoVO(15,"Querétaro"));


		return gerencias;

	}

    @Override
    protected VacanteMaestraVO __callGetMasterVacancy(RequestBean<Integer> genericRequestBean)throws GenericStatusException{

        VacanteMaestraVO response = new VacanteMaestraVO();
        ArrayList<String> expectativas = new ArrayList<>();
        ArrayList<String> funciones = new ArrayList<>();
        String exp1="Liderazgo";
        String exp2="Negociacion";
        String exp3="Trabajo orientado a resultados";
        String func1="Desarrollar apps";
        String func2="Mantenimiento de apps";
        String func3="Pruebas QA de apps";

        funciones.add(func1);
        funciones.add(func2);
        funciones.add(func3);

        expectativas.add(exp1);
        expectativas.add(exp2);
        expectativas.add(exp3);

        response.setTituloVacante("Asesor Traspasos");
        response.setIdAreaInteres(1);
        response.setSubArea(2);
        response.setIdSubAreaInteres(2);
        response.setFechaInicio("01/09/2019");
        response.setFechaFin("30/09/2019");
        response.setSueldoMin(30000);
        response.setSueldoMax(38000);
        response.setDescripcion("Intégrate a un equipo de desarrolladaores profesionales");
        response.setExpectativas(expectativas);
        response.setFunciones(expectativas);
        response.setIdVacante(456321);
        response.setVideo("Hl9TyGQ9KTk");
        response.setIdLineaNegocio("Afores");
        response.setVideo("");
        return response;

    }

	@Override
	protected GenericTextResponseBean __callCancelVacancy(RequestBean<Object> genericRequestBean)throws GenericStatusException{

		GenericTextResponseBean response = new GenericTextResponseBean();
		response.setRespuesta("Exito");
		return response;

	}

	@Override
	protected GenericTextResponseBean _callAssignPublication(RequestBean<AsignarPublicacionDTO> genericRequestBean)throws GenericStatusException{

		GenericTextResponseBean response = new GenericTextResponseBean();
		response.setRespuesta("Exito");
		return response;

	}


    @Override
    protected  ArrayList<CatalogoVO>  __callGetPositionLevels(RequestBean<GenericRequestBean> genericRequestBean)throws GenericStatusException{

        ArrayList<CatalogoVO> bolsasExternas = new ArrayList<>();

        bolsasExternas.add(new CatalogoVO(13,"Becario"));
        bolsasExternas.add(new CatalogoVO(14,"Analista"));
        bolsasExternas.add(new CatalogoVO(15,"Gerente"));

        return bolsasExternas;
    }

    @Override
    protected EstrategiaVacanteMaestraVO __callGetMasterVacanctStrategy(RequestBean<Integer> genericRequestBean) throws GenericStatusException {
		EstrategiaVacanteMaestraVO estrategia = new EstrategiaVacanteMaestraVO();
		ArrayList<Integer> bolsasExternas = new ArrayList<>();

        bolsasExternas.add(1);
        bolsasExternas.add(2);
        bolsasExternas.add(3);
        bolsasExternas.add(4);


		estrategia.setBolsasExternas(bolsasExternas);
		estrategia.setEmailObservador("karen.padierna@profuturo.com");
		estrategia.setCorreoObservador("karen.padierna@profuturo.com");
		estrategia.setNumeroEmpleadoObservador("AP5212");

        return estrategia;
    }


    @Override
	protected LinkedList<CatalogoVO> __callGetDivisions(RequestBean<Integer> genericRequestBean)throws GenericStatusException{

		LinkedList<CatalogoVO> gerencias = new LinkedList<>();

		gerencias.add(new CatalogoVO(13,"Centro"));
		gerencias.add(new CatalogoVO(14,"Sur"));
		gerencias.add(new CatalogoVO(15,"Norte"));


		return gerencias;

	}


}
