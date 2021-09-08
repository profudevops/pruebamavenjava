import Candidates from '@/pages/Candidates/Candidates.vue';
import CandidatesList from '@/pages/Candidates/List/List.vue';
import Prospect from '@/pages/Candidates/Prospect/Prospect.vue';
import HiringForm from '@/pages/Candidates/HiringForm/HiringForm.vue';
import ProspectVacancyDetail from '@/pages/Candidates/Vacancy/Vacancy.vue';

export default {
  path: 'candidatos',
  component: Candidates,
  meta: {
    name: 'candidates',
    breadcrumb: 'Candidatos',
  },
  children: [
    {
      path: '/',
      name: 'candidates-list',
      component: CandidatesList,
      meta: {
        roles: ['ROLE_CRP_ADMN', 'ROLE_CRP_ATN_CTS_REC', 'ROLE_CRP_REC', 'ROLE_CRP_OBS', 'ROLE_COM_ADMN', 'ROLE_COM_CON', 'ROLE_COM_ANL', 'ROLE_COM_INT', 'ROLE_COM_REC'],
      },
    },
    {
      path: ':prospectId',
      name: 'prospect',
      component: Prospect,
      meta: {
        breadcrumb: 'Candidato',
      },
    },
    {
      path: ':prospectId/solicitud-de-empleo',
      name: 'hiring-form',
      component: HiringForm,
      meta: {
        breadcrumb: 'Solicitud de empleo',
      },
    },
    {
      path: ':prospectId/vacante/:vacancyId/:step',
      name: 'prospect-vacancy-detail',
      component: ProspectVacancyDetail,
      meta: {
        breadcrumb: 'Vacante',
      },
    },
  ],
};
