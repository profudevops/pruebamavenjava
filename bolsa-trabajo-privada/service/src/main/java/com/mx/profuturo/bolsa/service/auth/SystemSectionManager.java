package com.mx.profuturo.bolsa.service.auth;

import com.mx.profuturo.bolsa.model.role.CommercialRoles;
import com.mx.profuturo.bolsa.model.role.CommonActions;
import com.mx.profuturo.bolsa.model.role.CorporateRoles;
import com.mx.profuturo.bolsa.model.role.SystemSections;
import com.mx.profuturo.bolsa.model.vo.common.SystemSectionVO;

import java.util.ArrayList;

public class SystemSectionManager {

    public static ArrayList<SystemSectionVO> getSystemSectionsByRole(String Role){
        ArrayList<SystemSectionVO> vo = null;
        if(CorporateRoles.ADMN.equals(Role)) {
            vo = SystemSectionManager.getCRP_ADMON_Operations();
        }else if (CommercialRoles.ADMN.equals(Role)){
        	 vo = SystemSectionManager.getCOM_ADMON_Operations();
        }
        else {
            vo = SystemSectionManager.getOTHER_Operations(Role);
        }
        return vo;
    }

    private static ArrayList<SystemSectionVO> getCRP_ADMON_Operations(){

        //Vacancies
        ArrayList<SystemSectionVO> sections = new ArrayList<>();
        SystemSectionVO section = new SystemSectionVO();
        SystemSectionVO section2 = new SystemSectionVO();
        SystemSectionVO section3 = new SystemSectionVO();
        SystemSectionVO section4 = new SystemSectionVO();

        section.setCode(SystemSections.VACANCIES.getValue());
        section.setDescription("Vacantes");
        ArrayList<String> operations = new ArrayList<>();
        operations.add(CommonActions.CREATE.getValue());
        operations.add(CommonActions.UPDATE.getValue());
        operations.add(CommonActions.ASSIGN.getValue());
        operations.add(CommonActions.ACTIVATE.getValue());
        section.setOperations(operations);
        sections.add(section);


        section2.setCode("AREAS");
        section2.setDescription("Areas");
        ArrayList<String> operations2 = new ArrayList<>();
        operations2.add(CommonActions.CREATE.getValue());
        operations2.add(CommonActions.UPDATE.getValue());
        section2.setOperations(operations);
        sections.add(section2);

        section3.setCode("METRICS");
        section3.setDescription("Metrics");
        ArrayList<String> operations3 = new ArrayList<>();
        operations3.add("CORP");
        operations3.add(CommonActions.CREATE.getValue());
        operations3.add(CommonActions.UPDATE.getValue());
        section3.setOperations(operations3);
        sections.add(section3);

        section4.setCode("PORTOFOLIO");
        section4.setDescription("Portofolio");
        ArrayList<String> operations4 = new ArrayList<>();
        operations4.add("CORP");
        operations4.add(CommonActions.CREATE.getValue());
        operations4.add(CommonActions.UPDATE.getValue());
        section4.setOperations(operations4);
        sections.add(section4);


        return sections;

    }
    
    
    private static ArrayList<SystemSectionVO> getCOM_ADMON_Operations(){

        //Vacancies
        ArrayList<SystemSectionVO> sections = new ArrayList<>();
        SystemSectionVO section = new SystemSectionVO();
        SystemSectionVO section2 = new SystemSectionVO();
        SystemSectionVO section3 = new SystemSectionVO();
        SystemSectionVO section4 = new SystemSectionVO();

        section.setCode(SystemSections.VACANCIES.getValue());
        section.setDescription("Vacantes");
        ArrayList<String> operations = new ArrayList<>();
        operations.add(CommonActions.CREATE.getValue());
        operations.add(CommonActions.ACTIVATE.getValue());
        operations.add(CommonActions.SEE_RESPONSIBLE.getValue());
        operations.add(CommonActions.ASSIGN.getValue());
        section.setOperations(operations);
        sections.add(section);


        section2.setCode("AREAS");
        section2.setDescription("Areas");
        ArrayList<String> operations2 = new ArrayList<>();
        operations2.add(CommonActions.CREATE.getValue());
        operations2.add(CommonActions.UPDATE.getValue());
        operations2.add(CommonActions.ACTIVATE.getValue());
        section2.setOperations(operations);
        sections.add(section2);

        section3.setCode("METRICS");
        section3.setDescription("Metrics");
        ArrayList<String> operations3 = new ArrayList<>();
        operations3.add("COM");
        operations3.add(CommonActions.CREATE.getValue());
        section3.setOperations(operations3);
        sections.add(section3);

        section4.setCode("PORTOFOLIO");
        section4.setDescription("Portofolio");
        ArrayList<String> operations4 = new ArrayList<>();
        operations4.add("COM");
        operations4.add(CommonActions.CREATE.getValue());
        operations4.add(CommonActions.UPDATE.getValue());
        section4.setOperations(operations4);
        sections.add(section4);


        return sections;

    }

    private static ArrayList<SystemSectionVO> getOTHER_Operations(String role){


        //Vacancies
        ArrayList<SystemSectionVO> sections = new ArrayList<>();
        SystemSectionVO section = new SystemSectionVO();
        SystemSectionVO section4 = new SystemSectionVO();

        if(CorporateRoles.MARKETING.equals(role)){
            section.setCode("HOME");
            section.setDescription("Content_manager");
            ArrayList<String> operations = new ArrayList<>();
            operations.add("EDIT");
            section.setOperations(operations);
            sections.add(section);
            return sections;

        }

        if(CommercialRoles.METRIC_VIEWER.equals(role)){
            section.setCode("METRICS");
            section.setDescription("Metrics");
            ArrayList<String> operations = new ArrayList<>();
            operations.add(CommonActions.CREATE.getValue());
            section.setOperations(operations);
            sections.add(section);
            return sections;

        }

        section.setCode(SystemSections.VACANCIES.getValue());
        section.setDescription("Vacantes");
        ArrayList<String> operations = new ArrayList<>();
        operations.add(CommonActions.CREATE.getValue());

        if(CorporateRoles.COSTUMER_RECRUITER.equals(role) || CommercialRoles.RECRUITER.equals(role)){
            operations.add(CommonActions.ACTIVATE.getValue());
        }
        if(CommercialRoles.CONSULTOR.equals(role)){
            operations.add(CommonActions.ACTIVATE.getValue());
            operations.add(CommonActions.SEE_RESPONSIBLE.getValue());
            operations.add(CommonActions.ASSIGN.getValue());
        }
        section.setOperations(operations);
        sections.add(section);


        section4.setCode("PORTOFOLIO");
        section4.setDescription("Portofolio");
        ArrayList<String> operations4 = new ArrayList<>();
        if(CommercialRoles.ANALYST.equals(role) || CommercialRoles.CONSULTOR.equals(role) || CommercialRoles.OBSERVER.equals(role) || CommercialRoles.RECRUITER.equals(role))
        {operations4.add("COM");}

        if(CorporateRoles.COSTUMER_RECRUITER.equals(role))
        {operations4.add("ATN");}

        else if(CorporateRoles.OBSERVER.equals(role) ||
                CorporateRoles.RECRUITER.equals(role))
        {

            operations4.add("CORP");
        }

        operations4.add(CommonActions.CREATE.getValue());
        operations4.add(CommonActions.UPDATE.getValue());

        section.setOperations(operations);
        sections.add(section);
        section4.setOperations(operations4);
        sections.add(section4);


        return sections;
    }
}
