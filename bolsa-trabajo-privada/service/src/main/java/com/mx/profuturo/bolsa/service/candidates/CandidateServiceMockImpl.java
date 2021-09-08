package com.mx.profuturo.bolsa.service.candidates;

import com.mx.profuturo.bolsa.model.service.areasinteres.dto.GenericTextResponseBean;
import com.mx.profuturo.bolsa.model.service.candidates.dto.*;
import com.mx.profuturo.bolsa.model.service.common.GenericRequestBean;
import com.mx.profuturo.bolsa.model.vo.Candidato.*;
import com.mx.profuturo.bolsa.model.vo.vacancy.VacanteListaVacanteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.mx.profuturo.bolsa.model.restclient.RequestBean;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import com.mx.profuturo.bolsa.util.mockclient.MockClient;

import java.util.*;

@Service
@Scope("request")
@Profile("mock")
public class CandidateServiceMockImpl extends CandidateServiceBase{

	@Value("classpath:/mock/common/candidate_getInitCandidates_response_bean.json")
	private Resource jsonGetInitCandidates;

	@Value("classpath:/mock/common/candidate_getDetailsCandidate_response_bean.json")
	private Resource jsonGetDetailsCandidate;

	@Value("classpath:/mock/common/candidate_getVacant_response_bean.json")
	private Resource jsonGetVacant;

	@Value("classpath:/mock/common/candidate_getVacant_response_bean.json")
	private Resource jsonAssignVacant;

	@Value("classpath:/mock/common/candidate_getVacant_response_bean.json")
	private Resource jsonQualifyCandidate;

	@Value("classpath:/mock/common/candidate_getVacant_response_bean.json")
	private Resource jsonCandidateDetail;

	@Value("classpath:/mock/common/candidate_getVacant_response_bean.json")
	private Resource jsonSendWallet;

	@Value("classpath:/mock/common/candidate_getVacant_response_bean.json")
	private Resource jsonRecruitmentCandidates;

	@Value("classpath:/mock/common/candidate_getVacant_response_bean.json")
	private Resource jsonCandidateHistory;

	@Value("classpath:/mock/common/candidate_getVacant_response_bean.json")
	private Resource jsonSimpleListMyVacancy;

	@Value("classpath:/mock/common/candidate_getVacant_response_bean.json")
	private Resource jsonSimpleListOtherVacancy;

	@Value("classpath:/mock/common/candidate_getVacant_response_bean.json")
	private Resource jsonRecommendVacancy;


	@Autowired
	MockClient mockClient;

	@Override
	protected ResumenCandidatosListVO callGetInitCandidates(RequestBean<CandidatesRecruitmentDTO> genericRequestBean) throws GenericStatusException {
		ArrayList<CandidatoResumenVO> response = new ArrayList<>();
		ResumenCandidatosListVO data = new ResumenCandidatosListVO();

		CandidatoResumenVO candidato1 = new CandidatoResumenVO();
		candidato1.setIdCandidato(1);
		candidato1.setNombres("Julio");
		candidato1.setApellidoPaterno("Moctezuma");
		candidato1.setApellidoMaterno("Llamas");
		candidato1.setNivelDeEstudio("Licenciatura");
		candidato1.setTelefono("5522481070");
		candidato1.setVacante("Desarollador Back");
		candidato1.setEstatus("Rechazado");

		CandidatoResumenVO candidato2 = new CandidatoResumenVO();
		candidato2.setIdCandidato(2);
		candidato2.setNombres("Julio");
		candidato2.setApellidoPaterno("Serrano");
		candidato2.setApellidoMaterno("Arredondo");
		candidato2.setNivelDeEstudio("Licenciatura");
		candidato2.setTelefono("5522481111");
		candidato2.setVacante("Desarollador Back");
		candidato2.setEstatus("Rechazado");

		response.add(candidato1);
		response.add(candidato2);

		data.setPaginaActual(1);
		data.setTotalPaginas(2);
		data.setResumenCandidatos(response);
		return data;
	}

	@Override
	protected GetDetailsCandidateResponseBean callGetDetailsCandidate(RequestBean<GetDetailsCandidateRequestBean> genericRequestBean) throws GenericStatusException {
		GetDetailsCandidateResponseBean response = new GetDetailsCandidateResponseBean();
		try {
			response = mockClient.getMock(jsonGetDetailsCandidate, GetDetailsCandidateResponseBean.class);
		}catch (Exception e){
			throw new GenericStatusException(e);
		}
		return response;
	}

	@Override
	protected GenericTextResponseBean callSetScoreCandidate(RequestBean<SetScoreCandidateInDTO> genericRequestBean) throws GenericStatusException {
		GenericTextResponseBean response = new GenericTextResponseBean();
		response.setRespuesta("Exito");
		return response;
	}


	@Override
	protected GenericTextResponseBean callEditEmail(RequestBean<CandidateEditEmailRequestBean> genericRequestBean) throws GenericStatusException {
		GenericTextResponseBean response = new GenericTextResponseBean();
		response.setRespuesta("Exito");
		return response;
	}

	@Override
	protected GenericTextResponseBean callGetCandidateInformation(RequestBean<CandidateInfoRequestBean> genericRequestBean) throws GenericStatusException {
		GenericTextResponseBean responseAux = new GenericTextResponseBean();
		ListCandidateInformation information = new ListCandidateInformation();

		ArrayList<CandidateComInfo> response = new ArrayList<>();
		CandidateComInfo candidate1 = new CandidateComInfo();
		candidate1.setFechaPostulacion("07-31-2020");
		candidate1.setPostuladoOcartera("SI");
		candidate1.setVacanteAplicada("Gerente");
		candidate1.setAreaInteres("Recursos Humanos");
		candidate1.setSubAreaInteres("Atraccion de talento");
		candidate1.setNombreCompleto("Ricardo Perez");
		candidate1.setFuente("Postulacion a traves de la pagina web");
		candidate1.setResidencia("Casa");
		candidate1.setMunicipio("Naucalpan");
		candidate1.setCelular("55-55555555");
		candidate1.setCorreo(("prueba@profuturo.com"));
		candidate1.setCurp("FOWN201911HDFLL9M0");
		candidate1.setRfc("FOWN201911");
		candidate1.setGenero("M");
		candidate1.setEdad(22);
		candidate1.setEscolaridad("Licenciatura");
		response.add(candidate1);
		response.add(candidate1);
		response.add(candidate1);

		information.setResultadoscom(response);

		return responseAux;
	}


	@Override
	protected GenericTextResponseBean callSetVacantCandidate(RequestBean<GenericIdCandidateInDTO> genericRequestBean) throws GenericStatusException {
		GenericTextResponseBean response = new GenericTextResponseBean();
		response.setRespuesta("Exito");
		return response;
	}

	@Override
	protected GenericTextResponseBean callRecommendCandidate(RequestBean<GenericIdCandidateInDTO> genericRequestBean) throws GenericStatusException {
		GenericTextResponseBean response = new GenericTextResponseBean();
		response.setRespuesta("Exito");
		return response;
	}

	@Override
	protected GetVacantResponseBean callGetVacant(RequestBean<GenericIdCandidateRequestBean> genericRequestBean) throws GenericStatusException {
		GetVacantResponseBean response = new GetVacantResponseBean();
		try {
			response = mockClient.getMock(jsonGetVacant, GetVacantResponseBean.class);
		}catch (Exception e){
			throw new GenericStatusException(e);
		}
		return response;
	}

	@Override
	protected AssignVacantResponseBean callAssignVacant(RequestBean<AssignVacantRequestBean> genericRequestBean) throws GenericStatusException{
		AssignVacantResponseBean response = new AssignVacantResponseBean();
		response.setRespuesta("Exito");
		/*AssignVacantResponseBean response = null;
		try {
			response = mockClient.getMock(jsonAssignVacant, AssignVacantResponseBean.class);
		}catch (Exception e){
			throw new GenericStatusException(e);
		}*/
		return response;
	}

	@Override
	protected QualifyCandidateResponseBean callQualifyCandidate(RequestBean<QualifyCandidateRequestBean> genericRequestBean) throws GenericStatusException{
		QualifyCandidateResponseBean response = new QualifyCandidateResponseBean();
		response.setRespuesta("Exito");
		/*QualifyCandidateResponseBean response = null;
		try {
			response = mockClient.getMock(jsonQualifyCandidate, QualifyCandidateResponseBean.class);
		}catch (Exception e){
			throw new GenericStatusException(e);
		}*/
		return response;
	}

	@Override
	protected RecruitmentHistoryVO __callGetCandidateHistory(RequestBean<Integer> genericRequestBean) throws GenericStatusException{

		
		RecruitmentHistoryVO vo = new RecruitmentHistoryVO();
		Integer idCandidato =  (Integer) genericRequestBean.getUriVariables().get("idCandidato");
		LinkedList<RecruitmentEventVO> actuales = new LinkedList<>();
		LinkedList<RecruitmentEventVO> anteriores = new LinkedList<>();
		RecruitmentEventVO ac1 = new RecruitmentEventVO();

		if(idCandidato==1) {

			ac1.setIdPublicacion(1);
			ac1.setEnProgreso(true);
			ac1.setIdReclutamiento(1);
			ac1.setTituloVacante("Desarrollador FullStack");
			ac1.setUltimaEtapaAlcanzada("Filtro Telefonico");
			ac1.setFecha("20 de marzo de 2019");
			actuales.add(ac1);
		}else if(idCandidato==2){
			ac1.setIdPublicacion(1);
			ac1.setEnProgreso(true);
			ac1.setIdReclutamiento(2);
			ac1.setTituloVacante("Desarrollador Python");
			ac1.setUltimaEtapaAlcanzada("Filtro Telefonico");
			ac1.setFecha("20 de marzo de 2019");
			actuales.add(ac1);
		}else{

			vo.setAnteriores(anteriores);
			return vo;

		}


		RecruitmentEventVO an1 = new RecruitmentEventVO();
		an1.setIdPublicacion(1);
		an1.setEnProgreso(false);
		an1.setIdReclutamiento(1);
		an1.setTituloVacante("Desarrollador FullStack");
		an1.setUltimaEtapaAlcanzada("Filtro Telefonico");
		ac1.setFecha("21 de marzo de 2019");
		anteriores.add(an1);

		RecruitmentEventVO an2 = new RecruitmentEventVO();
		an2.setIdPublicacion(1);
		an2.setEnProgreso(false);
		an2.setIdReclutamiento(1);
		an2.setTituloVacante("Desarrollador FullStack");
		an2.setUltimaEtapaAlcanzada("Filtro Telefonico");
		ac1.setFecha("22 de marzo de 2019");
		anteriores.add(an2);

		vo.setActuales(actuales);
		vo.setAnteriores(anteriores);

		return vo;
	}


	@Override
	protected CandidatoDetallesVO __callgetCanidateDetails(RequestBean<Integer> genericRequestBean) throws GenericStatusException{
		CandidatoDetallesVO detailsCandidate = new CandidatoDetallesVO();

		Integer idCandidato =  (Integer) genericRequestBean.getUriVariables().get("idCandidato");
		if(idCandidato==(1)){
			detailsCandidate.setNombre("Julio LLamas Moctezuma");
			detailsCandidate.setEnReclutamiento(true);
			detailsCandidate.setTieneCalificacion(true);
			detailsCandidate.setCorreo("juliomoctezuma@gmail.com");
			detailsCandidate.setTelefono("5545815165");
			detailsCandidate.setFechaNacimiento("10/10/1988");
			detailsCandidate.setCurriculum("");
			ScoreVO sc1 = new ScoreVO();
			sc1.setCodigo("TL");
			sc1.setDescripcion("Talento");
			TagVO tg1 = new ScoreVO();
			tg1.setCodigo("TLT1");
			tg1.setDescripcion("Talento tag1");
			TagVO tg2 = new ScoreVO();
			tg2.setCodigo("TLT2");
			tg2.setDescripcion("Talento tag2");
			sc1.getTags().add(tg1);
			sc1.getTags().add(tg2);

			detailsCandidate.setUrlCurriculum("http://www.pdf995.com/samples/pdf.pdf");
			detailsCandidate.setCalificacion(sc1);

		}

		else if(idCandidato==(2)){
			detailsCandidate.setNombre("Julio Arredondo");
			detailsCandidate.setTieneCalificacion(true);
			detailsCandidate.setCorreo("julioarredondo@gmail.com");
			detailsCandidate.setTelefono("5545815165");
			detailsCandidate.setFechaNacimiento("10/10/1988");
			detailsCandidate.setCurriculum("");
			ScoreVO sc1 = new ScoreVO();
			sc1.setCodigo("TL");
			sc1.setDescripcion("Talento");
			ScoreVO tg1 = new ScoreVO();
			tg1.setCodigo("TLT1");
			tg1.setDescripcion("Talento tag1");
			ScoreVO tg2 = new ScoreVO();
			tg2.setCodigo("TLT2");
			tg2.setDescripcion("Talento tag2");
			sc1.getTags().add(tg1);
			sc1.getTags().add(tg2);

			detailsCandidate.setUrlCurriculum("http://www.pdf995.com/samples/pdf.pdf");
			detailsCandidate.setCalificacion(sc1);


		}
		else if(idCandidato==(3)){
			detailsCandidate.setNombre("Erick Almaguer");
			detailsCandidate.setTieneCalificacion(true);
			detailsCandidate.setCorreo("erikalmaguer@gmail.com");
			detailsCandidate.setTelefono("5545815165");
			detailsCandidate.setFechaNacimiento("10/10/1988");
			detailsCandidate.setCurriculum("");
			ScoreVO sc1 = new ScoreVO();
			sc1.setCodigo("TL");
			sc1.setDescripcion("Talento");
			ScoreVO tg1 = new ScoreVO();
			tg1.setCodigo("TLT1");
			tg1.setDescripcion("Talento tag1");
			ScoreVO tg2 = new ScoreVO();
			tg2.setCodigo("TLT2");
			tg2.setDescripcion("Talento tag2");
			sc1.getTags().add(tg1);
			sc1.getTags().add(tg2);

			detailsCandidate.setUrlCurriculum("http://www.pdf995.com/samples/pdf.pdf");
			detailsCandidate.setCalificacion(sc1);


		}

		return detailsCandidate;

	}

	@Override
	protected GenericTextResponseBean callSendToPortofolio(RequestBean<SendWalletRequestBean> genericRequestBean) throws GenericStatusException{
		GenericTextResponseBean response = new GenericTextResponseBean();
		response.setRespuesta("Exito");
		/*SendWalletResponseBean response = null;
		try {
			response = mockClient.getMock(jsonSendWallet, SendWalletResponseBean.class);
		}catch (Exception e){
			throw new GenericStatusException(e);
		}*/
		return response;
	}

	@Override
	protected List<CandidatesBean> callRecruitmentCandidates(RequestBean<RecruitmentCandidatesRequestBean> genericRequestBean)  throws GenericStatusException{
		ArrayList<CandidatesBean> response = new ArrayList<>();
		CandidatesBean candidate1 = new CandidatesBean();
		CandidatesBean candidate2 = new CandidatesBean();
		candidate1.setIdCandidato(1);
		candidate1.setNombres("Juani");
		candidate1.setApellidoPaterno("Llamas");
		candidate1.setApellidoMaterno("Moctezuma");
		candidate1.setNivelDeEstudio("Kinder");
		candidate1.setTelefono("5522481070");
		candidate1.setTipo(null);
		candidate1.setVacante("Desarrollador Back");
		candidate1.setEstatus("Rechazado");
		candidate2.setIdCandidato(2);
		candidate2.setNombres("Juana");
		candidate2.setApellidoPaterno("Arredondo");
		candidate2.setApellidoMaterno("Serrano");
		candidate2.setNivelDeEstudio("Primaria");
		candidate2.setTelefono("5522481111");
		candidate2.setTipo(null);
		candidate2.setVacante("Desarrollador Back");
		candidate2.setEstatus("Rechazado");
		response.add(candidate1);
		response.add(candidate2);
		/*RecruitmentCandidatesResponseBean response = null;
		try {
			response = mockClient.getMock(jsonRecruitmentCandidates, RecruitmentCandidatesResponseBean.class);
		}catch (Exception e){
			throw new GenericStatusException(e);
		}*/
		return response;
	}

	@Override
	protected CandidateHistoryResponseBean callCandidateHistory(RequestBean<CandidateHistoryRequestBean> genericRequestBean) throws GenericStatusException{
		CandidateHistoryResponseBean response = new CandidateHistoryResponseBean();
		ArrayList<HistoryBean> actuales = new ArrayList<>();
		ArrayList<HistoryBean> anteriores = new ArrayList<>();
		HistoryBean actual = new HistoryBean();
		HistoryBean anterior1 = new HistoryBean();
		HistoryBean anterior2 = new HistoryBean();
		actual.setIdReclutamiento(1);
		actual.setEnProgreso(true);
		actual.setTituloVacante("Desarrollador FullStack");
		actual.setUltimaEtapaAlcanzada("");
		actual.setFecha("22 de marzo de 2019");
		anterior1.setIdReclutamiento(1);
		anterior1.setEnProgreso(false);
		anterior1.setTituloVacante("Desarrollador FullStack");
		anterior1.setUltimaEtapaAlcanzada("");
		anterior1.setFecha(null);
		anterior2.setIdReclutamiento(1);
		anterior2.setEnProgreso(false);
		anterior2.setTituloVacante("Desarrollador FullStack");
		anterior2.setUltimaEtapaAlcanzada("");
		anterior2.setFecha(null);
		actuales.add(actual);
		anteriores.add(anterior1);
		anteriores.add(anterior2);
		response.setActuales(actuales);
		response.setAnteriores(anteriores);
		/*CandidateHistoryResponseBean response = null;
		try {
			response = mockClient.getMock(jsonCandidateHistory, CandidateHistoryResponseBean.class);
		}catch (Exception e){
			throw new GenericStatusException(e);
		}*/
		return response;
	}

	@Override
	protected List<VacanteListaVacanteVO> callSimpleListMyVacancy(RequestBean<SimpleListMyVacancyRequestBean> genericRequestBean) throws GenericStatusException{
		List<VacanteListaVacanteVO> response =null;
		VacanteListaVacanteVO vacancy1 = new VacanteListaVacanteVO();
		VacanteListaVacanteVO vacancy2 = new VacanteListaVacanteVO();
		vacancy1.setId(1);
		vacancy1.setCodigo(null);
		vacancy1.setDescripcion("Desarrollador FullStack");
		vacancy2.setId(2);
		vacancy2.setCodigo(null);
		vacancy2.setDescripcion("Desarrollador JS");

		response.add(vacancy1);
		response.add(vacancy2);
		/*List<SimpleVacancy> response = null;
		try {
			response = mockClient.getMock(jsonSimpleListMyVacancy, List.class);
		}catch (Exception e){
			throw new GenericStatusException(e);
		}*/
		return response;
	}

	@Override
	protected List<VacanteListaVacanteVO> callSimpleListOtherVacancy(RequestBean<SimpleListMyVacancyRequestBean> genericRequestBean) throws GenericStatusException{
		ArrayList<VacanteListaVacanteVO> response = new ArrayList<>();
		VacanteListaVacanteVO vacancy10 = new VacanteListaVacanteVO();
		VacanteListaVacanteVO vacancy20 = new VacanteListaVacanteVO();
		vacancy10.setId(10);
		vacancy10.setCodigo(null);
		vacancy10.setDescripcion("Auditor Finanzas");
		vacancy20.setId(20);
		vacancy20.setCodigo(null);
		vacancy20.setDescripcion("Auditor Contratos");

		response.add(vacancy10);
		response.add(vacancy20);
		/*List<SimpleVacancy> response = null;
		try {
			response = mockClient.getMock(jsonSimpleListOtherVacancy, List.class);
		}catch (Exception e){
			throw new GenericStatusException(e);
		}*/
		return response;
	}

	@Override
	protected RecommendVacancyResponseBean callRecommendVacancy(RequestBean<RecommendVacancyRequestBean> genericRequestBean) throws GenericStatusException{
		RecommendVacancyResponseBean response = new RecommendVacancyResponseBean();
		response.setRespuesta("Exito");
		/*RecommendVacancyResponseBean response = null;
		try {
			response = mockClient.getMock(jsonRecommendVacancy, RecommendVacancyResponseBean.class);
		}catch (Exception e){
			throw new GenericStatusException(e);
		}*/
		return response;
	}

	@Override
	protected List<ScoreVO> __callGetScoreTags(RequestBean<GenericRequestBean> genericRequestBean) throws GenericStatusException{

		List<ScoreVO> vo = new LinkedList<>();

		ScoreVO sc1 = new ScoreVO();
		sc1.setCodigo("TL");
		sc1.setId(1);
		sc1.setDescripcion("Talento");
		TagVO tg1 = new TagVO();
		tg1.setCodigo("TLT1");
		tg1.setId(11);
		tg1.setDescripcion("Talento tag1");
		TagVO tg2 = new TagVO();
		tg2.setCodigo("TLT2");
		tg2.setId(12);
		tg2.setDescripcion("Talento tag2");

		sc1.getTags().add(tg1);
		sc1.getTags().add(tg2);


		ScoreVO sc2 = new ScoreVO();
		sc2.setCodigo("LN");
		sc2.setId(2);
		sc2.setDescripcion("Lista Negra");
		ScoreVO tg21 = new ScoreVO();
		tg21.setCodigo("LNT1");
		tg21.setDescripcion("Numero CONSAR");
		tg21.setId(21);
		ScoreVO tg22 = new ScoreVO();
		tg22.setCodigo("TLT2");
		tg22.setId(22);
		tg22.setDescripcion("Mala conducta");


		sc2.getTags().add(tg21);
		sc2.getTags().add(tg22);


		ScoreVO sc3 = new ScoreVO();
		sc3.setCodigo("OM");
		sc3.setDescripcion("Otra mala");
		TagVO tg31 = new TagVO();
		tg31.setCodigo("LNT1");
		tg31.setId(31);
		tg31.setDescripcion("Numero CONSAR");
		TagVO tg32 = new TagVO();
		tg32.setCodigo("TLT2");
		tg32.setId(32);
		tg32.setDescripcion("Mala conducta");

		sc3.getTags().add(tg31);
		sc3.getTags().add(tg32);



		ScoreVO sc4 = new ScoreVO();
		sc4.setCodigo("OB");
		sc4.setDescripcion("Otra buena");
		TagVO tg41 = new TagVO();
		tg41.setCodigo("LNT1");
		tg41.setId(41);
		tg41.setDescripcion("Numero CONSAR");
		TagVO tg42 = new TagVO();
		tg42.setCodigo("TLT2");
		tg42.setId(42);
		tg42.setDescripcion("Mala conducta");

		sc4.getTags().add(tg41);
		sc4.getTags().add(tg42);

		vo.add(sc1);
		vo.add(sc2);
		vo.add(sc3);
		vo.add(sc4);

		return vo;
	}



}
