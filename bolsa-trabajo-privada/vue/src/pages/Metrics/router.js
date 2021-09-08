import Metrics from '@/pages/Metrics/Metrics.vue';
import List from '@/pages/Metrics/List/List.vue';
import Report from '@/pages/Metrics/Report/Report.vue';

export default {
  path: 'metricas',
  component: Metrics,
  meta: {
    name: 'metrics',
    breadcrumb: 'Metricas',
  },
  children: [
    {
      path: '/',
      name: 'metrics-list',
      component: List,
      meta: {
        roles: ['ROLE_COM_ADMN', 'ROLE_CRP_ADMN', 'ROLE_COM_CON', 'ROLE_COM_ANL', 'ROLE_COM_REC', 'ROLE_COM_METVIEW'],
      },
    },
    {
      path: ':reportType',
      name: 'report-type',
      component: Report,
      meta: {
        roles: ['ROLE_COM_ADMN', 'ROLE_CRP_ADMN', 'ROLE_COM_CON', 'ROLE_COM_ANL', 'ROLE_COM_REC', 'ROLE_COM_METVIEW'],
      },
    },
  ],
};
