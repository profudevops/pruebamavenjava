import Portfolio from '@/pages/Portfolio/Portfolio.vue';
import PortfolioList from '@/pages/Portfolio/List/List.vue';

export default {
  path: 'cartera',
  component: Portfolio,
  meta: {
    name: 'portfolio',
    breadcrumb: 'Cartera',
  },
  children: [
    {
      path: '/',
      name: 'portfolio-list',
      component: PortfolioList,
      meta: {
        roles: ['ROLE_CRP_ADMN', 'ROLE_CRP_ATN_CTS_REC', 'ROLE_CRP_REC', 'ROLE_CRP_OBS', 'ROLE_COM_ADMN', 'ROLE_COM_CON', 'ROLE_COM_ANL', 'ROLE_COM_REC'],
      },
    },
  ],
};
