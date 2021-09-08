/* S T R A T E G Y */
const StrategyView = () => import('@/pages/Strategy/StrategyView.vue');
const StrategyForm = () => import('@/pages/Strategy/StrategyForm.vue');

/* V A C A N C Y */
const VacancyInfo = () => import('@/pages/Vacancies/Detail/Info/Info.vue');
const VacancyForm = () => import('@/pages/Vacancies/Form/VacancyForm.vue');

export default [
  {
    path: '/',
    name: 'info-vacancy',
    component: VacancyInfo,
  },
  {
    path: 'editar-vacante',
    name: 'edit-vacancy',
    component: VacancyForm,
    meta: {
      breadcrumb: 'Editar Vacante',
    },
  },
  {
    path: 'crear-estrategia',
    name: 'create-strategy-view',
    component: StrategyView,
    redirect: {
      name: 'create-strategy',
    },
    children: [
      {
        path: '/',
        name: 'create-strategy',
        component: StrategyForm,
        meta: {
          breadcrumb: 'Estrategia',
        },
      },
    ],
  },
  {
    path: 'estrategia/:idEstrategy/editar-estrategia',
    name: 'edit-strategy',
    component: StrategyForm,

    meta: {
      breadcrumb: 'Estrategia',
    },
  },
];
