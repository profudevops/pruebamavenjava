
import vacancyRoutes from './vacancyRoutes';
import masterRoutes from './masterRoutes';
import VacancyView from '@/pages/Vacancies/Vacancies.vue';
import VacanciesList from '@/pages/Vacancies/List/List.vue';
import VacancyForm from '@/pages/Vacancies/Form/VacancyForm.vue';
import Wrapper from '@/pages/Vacancies/Wrapper.vue';

export default {
  path: 'vacantes',
  name: 'vacancies',
  component: VacancyView,
  redirect: {
    name: 'vacancies-list',
  },
  meta: {
    name: 'vacancies',
    breadcrumb: 'Vacantes',
  },
  children: [
    {
      path: '/',
      name: 'vacancies-list',
      component: VacanciesList,
      meta: {
        roles: ['ROLE_CRP_ADMN', 'ROLE_CRP_ATN_CTS_REC', 'ROLE_CRP_REC', 'ROLE_CRP_OBS', 'ROLE_COM_ADMN', 'ROLE_COM_CON', 'ROLE_COM_ANL', 'ROLE_COM_REC'],
      },
    },
    {
      path: 'crear',
      name: 'create-vacancy',
      component: VacancyForm,
      meta: {
        breadcrumb: 'Crear Vacante',
      },
    },
    {
      path: 'detalle/:idPublication',
      name: 'detail-vacancy',
      component: Wrapper,
      redirect: {
        name: 'info-vacancy',
      },
      meta: {
        breadcrumb: 'Vacante',
      },
      children: vacancyRoutes,
    },
    {
      path: 'maestras',
      component: Wrapper,
      meta: {
        name: 'master-vacancy',
      },
      children: masterRoutes,
    },
  ],
};
