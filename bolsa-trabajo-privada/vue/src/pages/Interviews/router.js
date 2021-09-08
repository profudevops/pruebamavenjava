import Interviews from '@/pages/Interviews/Interviews.vue';
import InterviewsList from '@/pages/Interviews/List/List.vue';

export default {
  path: 'entrevistas',
  component: Interviews,
  meta: {
    name: 'interviews',
    breadcrumb: 'Entrevistas',
  },
  children: [
    {
      path: '/',
      name: 'interviews-list',
      component: InterviewsList,
      meta: {
        roles: ['ROLE_COM_INT', 'ROLE_CRP_ADMN', 'ROLE_CRP_ATN_CTS_REC', 'ROLE_CRP_REC', 'ROLE_CRP_OBS', 'ROLE_COM_ADMN', 'ROLE_COM_CON', 'ROLE_COM_ANL', 'ROLE_COM_REC'],
      },
    },
  ],
};
