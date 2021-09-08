package com.mx.profuturo.bolsa.web.controller.common;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.mx.profuturo.bolsa.model.service.areasinteres.dto.AreaInteresDTO;
import com.mx.profuturo.bolsa.model.service.areasinteres.dto.SubAreaInteresDTO;
import com.mx.profuturo.bolsa.model.service.cartera.LoadFiltersResponse;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletReultListVO;
import com.mx.profuturo.bolsa.service.cartera.CarteraService;
import com.mx.profuturo.bolsa.service.portofolio.PortofolioService;
import com.mx.profuturo.bolsa.util.exception.custom.GenericStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.mx.profuturo.bolsa.model.service.EntityResponseBean;
import com.mx.profuturo.bolsa.model.vo.Candidato.RecruitmentHistoryVO;
import com.mx.profuturo.bolsa.model.vo.common.CatalogoVO;
import com.mx.profuturo.bolsa.model.wallet.dto.WalletSearchDTO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletFiltersVO;
import com.mx.profuturo.bolsa.model.wallet.vo.WalletResultVO;

@Controller("ProtofolioControl")
@RequestMapping(value="cartera")
@Scope("request") 
@CrossOrigin
public class PortofolioControllerImpl extends BaseController {

	@Autowired
	CarteraService carteraService;

	@ResponseBody
	@RequestMapping(value="cargar-filtros", method = RequestMethod.GET)
	public EntityResponseBean <WalletFiltersVO> getFilters(@RequestParam String modo)throws GenericStatusException {
        WalletFiltersVO vo = new WalletFiltersVO();
                vo = carteraService.loadFilters(modo);

		return this.buildEntitySuccessResponse(vo);

	}
	
	@ResponseBody
    @RequestMapping(value="buscar", method = RequestMethod.POST)
	public EntityResponseBean <WalletReultListVO> search(@RequestBody WalletSearchDTO dto)throws GenericStatusException {


		return this.buildEntitySuccessResponse(carteraService.findCandidates(dto));


	}

    @ResponseBody
    @RequestMapping(value="obtener-subarea-interes", method = RequestMethod.POST)
    public EntityResponseBean <ArrayList<AreaInteresDTO>> searchInterestSubarea(@RequestBody ArrayList<Integer> areaFilter) throws GenericStatusException{

        ArrayList<AreaInteresDTO> areasList = new ArrayList<>();
	    areasList = carteraService.getSubInterestArea(areaFilter);

        return this.buildEntitySuccessResponse(areasList);
    }
}

