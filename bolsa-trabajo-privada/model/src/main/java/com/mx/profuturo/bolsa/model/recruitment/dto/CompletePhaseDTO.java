package com.mx.profuturo.bolsa.model.recruitment.dto;

import com.mx.profuturo.bolsa.model.recruitment.dto.form.CNStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.ESStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.EXCStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.EXPStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.FCRCStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.FDESStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.FDRCStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.FRStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.FTStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.OFSTtageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.PEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.PEREStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEAGStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SEREStageForm;
import com.mx.profuturo.bolsa.model.recruitment.dto.form.SERMRStageForm;

public class CompletePhaseDTO {
	
	private Boolean rechazo;
	protected String codigoFase;
	protected Integer idReclutamiento;
	protected Boolean repostulacion=true;
	
	protected FTStageForm ft;
	protected PEAGStageForm peag;
	protected PEREStageForm pere;
	protected EXPStageForm exp;
	protected EXPStageForm expg;
	protected EXCStageForm exc;
	
	protected SEAGStageForm seag;
	protected SEREStageForm sere;
	protected SERMRStageForm semr;
	
	protected OFSTtageForm of;
	protected FCRCStageForm fcrc;
	
	protected FRStageForm fr;
	
	protected FDRCStageForm fdrc;
	protected FDESStageForm fdes;
	
	protected ESStageForm es;
	protected CNStageForm cnde;
	
	protected Integer idFaseReclutamiento;
	protected String idUsuario;
	protected String fechaEvento;
	
	
	

	public Integer getIdReclutamiento() {
		return idReclutamiento;
	}
	public void setIdReclutamiento(Integer idReclutamiento) {
		this.idReclutamiento = idReclutamiento;
	}
	
	
	

	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getFechaEvento() {
		return fechaEvento;
	}
	public void setFechaEvento(String fechaEvento) {
		this.fechaEvento = fechaEvento;
	}
	
	public String getCodigoFase() {
		return codigoFase;
	}

	public void setCodigoFase(String codigoFase) {
		this.codigoFase = codigoFase;
	}

	public Boolean getRechazo() {
		return rechazo;
	}

	public void setRechazo(Boolean rechazo) {
		this.rechazo = rechazo;
	}

	public Integer getIdFaseReclutamiento() {
		return idFaseReclutamiento;
	}
	public void setIdFaseReclutamiento(Integer idFaseReclutamiento) {
		this.idFaseReclutamiento = idFaseReclutamiento;
	}
	public FTStageForm getFt() {
		return ft;
	}
	public void setFt(FTStageForm ft) {
		this.ft = ft;
	}
	public PEAGStageForm getPeag() {
		return peag;
	}
	public void setPeag(PEAGStageForm peag) {
		this.peag = peag;
	}
	public PEREStageForm getPere() {
		return pere;
	}
	public void setPere(PEREStageForm pere) {
		this.pere = pere;
	}
	public EXPStageForm getExp() {
		return exp;
	}
	public void setExp(EXPStageForm exp) {
		this.exp = exp;
	}
	public EXCStageForm getExc() {
		return exc;
	}
	public void setExc(EXCStageForm exc) {
		this.exc = exc;
	}
	public SEAGStageForm getSeag() {
		return seag;
	}
	public void setSeag(SEAGStageForm seag) {
		this.seag = seag;
	}
	public SEREStageForm getSere() {
		return sere;
	}
	public void setSere(SEREStageForm sere) {
		this.sere = sere;
	}

	public SERMRStageForm getSemr() {
		return semr;
	}
	public void setSemr(SERMRStageForm semr) {
		this.semr = semr;
	}
	public OFSTtageForm getOf() {
		return of;
	}
	public void setOf(OFSTtageForm of) {
		this.of = of;
	}
	public FCRCStageForm getFcrc() {
		return fcrc;
	}
	public void setFcrc(FCRCStageForm fcrc) {
		this.fcrc = fcrc;
	}
	public FRStageForm getFr() {
		return fr;
	}
	public void setFr(FRStageForm fr) {
		this.fr = fr;
	}
	public FDRCStageForm getFdrc() {
		return fdrc;
	}
	public void setFdrc(FDRCStageForm fdrc) {
		this.fdrc = fdrc;
	}
	public FDESStageForm getFdes() {
		return fdes;
	}
	public void setFdes(FDESStageForm fdes) {
		this.fdes = fdes;
	}
	public ESStageForm getEs() {
		return es;
	}
	public void setEs(ESStageForm es) {
		this.es = es;
	}
	public CNStageForm getCn() {
		return cnde;
	}
	public void setCn(CNStageForm cnde) {
		this.cnde = cnde;
	}
	public EXPStageForm getExpg() {
		return expg;
	}
	public void setExpg(EXPStageForm expg) {
		this.expg = expg;
	}
	public CNStageForm getCnde() {
		return cnde;
	}
	public void setCnde(CNStageForm cnde) {
		this.cnde = cnde;
	}
	public Boolean getRepostulacion() {
		return repostulacion;
	}
	public void setRepostulacion(Boolean repostulacion) {
		this.repostulacion = repostulacion;
	}	
	
}
