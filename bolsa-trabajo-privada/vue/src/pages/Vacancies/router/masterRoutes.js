/* S T R A T E G Y */
const StrategyFormHeadquarter = () => import('@/pages/Strategy/StrategyFormHeadquarter.vue');

/* V A C A N C Y */
const MasterDetail = () => import('@/pages/Vacancies/MasterDetail/Detail.vue');
const VacancyForm = () => import('@/pages/Vacancies/Form/MasterVacancyForm.vue');
const VacancyInfo = () => import('@/pages/Vacancies/Detail/Info/Info.vue');
const SquareActive = () => import('@/pages/Vacancies/SquareActive/SquareActive.vue');
const AgentList = () => import('@/pages/Vacancies/List/Agents/AgentsList.vue');

export default [
  {
    path: ':vacancyId',
    name: 'detail-master-vacancy',
    component: MasterDetail,
    meta: {
      breadcrumb: 'Vacante Maestra',
      roles: ['ROLE_CRP_ADMN', 'ROLE_COM_ADMN', 'ROLE_COM_CON'],
    },
  },
  {
    path: 'editar-vacante-maestra/:vacancyId',
    name: 'edit-master-vacancy',
    component: VacancyForm,
    meta: {
      breadcrumb: 'Editar Vacante Maestra',
    },
  },
  {
    path: ':vacancyId/sede/:idSede',
    name: 'info-master-vacancy',
    component: AgentList,
  },
  {
    path: ':vacancyId/sede/:idSede/publicacion/:idPublication',
    name: 'info-master-publication',
    component: VacancyInfo,
  },
  {
    path: ':vacancyId/sede/:idSede/publicacion/:idPublication/crear-estrategia',
    name: 'create-master-strategy',
    component: StrategyFormHeadquarter,
    meta: {
      breadcrumb: 'Estrategia',
    },
  },
  {
    path: ':vacancyId/sede/:idSede/publicacion/:idPublication/editar-estrategia',
    name: 'edit-master-strategy',
    component: StrategyFormHeadquarter,
    meta: {
      breadcrumb: 'Estrategia',
    },
  },
  {
    path: 'activar-plazas/:vacancyId',
    name: 'square-active',
    component: SquareActive,
    meta: {
      breadcrumb: 'Activar Plazas',
    },
  },
  {
    path: 'editar-publicacion/:idPublication',
    name: 'edit-master-publication',
    component: SquareActive,
    meta: {
      breadcrumb: 'Editar publicación',
    },
  },
];
