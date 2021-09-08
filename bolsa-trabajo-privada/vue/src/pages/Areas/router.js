import Areas from '@/pages/Areas/Areas.vue';
import List from '@/pages/Areas/List/List.vue';
import Form from '@/pages/Areas/Form/Form.vue';

export default {
  path: 'areas',
  name: 'areas',
  component: Areas,
  redirect: {
    name: 'areas-list',
  },
  meta: {
    name: 'areas',
    breadcrumb: 'Areas',
  },
  children: [
    {
      path: '/',
      name: 'areas-list',
      component: List,
      meta: {
        roles: ['ROLE_COM_ADMN', 'ROLE_CRP_ADMN'],
      },
    },
    {
      path: 'crear',
      name: 'create-area',
      component: Form,
      meta: {
        breadcrumb: 'Agregar Área',
        roles: ['ROLE_COM_ADMN', 'ROLE_CRP_ADMN'],
      },
    },
    {
      path: ':id/editar',
      name: 'edit-area',
      component: Form,
      meta: {
        breadcrumb: 'Editar Área',
        roles: ['ROLE_COM_ADMN', 'ROLE_CRP_ADMN'],
      },
    },
  ],
};
