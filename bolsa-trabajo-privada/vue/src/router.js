import Vue from 'vue';
import Router from 'vue-router';
import Login from './pages/Login/Login.vue';
import Admin from './pages/Admin/Admin.vue';
import Dashboard from './pages/Dashboard/Dashboard.vue';

/* R O U T E S */
import candidatesRouter from '@/pages/Candidates/router';
import vacancyRouter from '@/pages/Vacancies/router/router';
import portfolioRouter from '@/pages/Portfolio/router';
import interviewsRouter from '@/pages/Interviews/router';
import metricsRouter from '@/pages/Metrics/router';
import areasRouter from '@/pages/Areas/router';
import contentManager from './pages/Content/ContentManager.vue';

Vue.use(Router);

const router = new Router({
  mode: 'history',
  base: process.env.NODE_ENV === 'production' ? '/bolsa-de-trabajo-admin/' : '/',

  scrollBehavior() {
    return { x: 0, y: 0 };
  },

  routes: [
    {
      path: '/',
      name: 'login',
      component: Login,
    },
    {
      path: '/admin',
      component: Admin,
      meta: {
        requiresAuth: true,
      },
      children: [
        {
          path: 'dashboard',
          name: 'dashboard',
          component: Dashboard,
          meta: [
            'ROLE_CRP_ADMN', 'ROLE_CRP_ATN_CTS_REC', 'ROLE_CRP_REC', 'ROLE_CRP_OBS', 'ROLE_COM_ADMN', 'ROLE_COM_CON', 'ROLE_COM_ANL', 'ROLE_COM_REC',
          ],
        },
        {
          ...candidatesRouter,
        },
        {
          ...vacancyRouter,
        },
        {
          ...interviewsRouter,
        },
        {
          ...portfolioRouter,
        },
        {
          ...metricsRouter,
        },
        {
          ...areasRouter,
        },
        {
          path: 'administrador-de-contenido',
          name: 'content-manager',
          component: contentManager,
          meta: {
            roles: ['ROLE_MKT_EDITOR'],
          },
        },
      ],
    },
    {
      path: '*',
      redirect: '/',
    },
  ],
});

router.beforeEach((to, from, next) => {
  const user = JSON.parse(sessionStorage.getItem('user'));
  document.querySelector('body').classList.remove('full-page');

  if (to.matched.some(route => route.meta.requiresAuth)) {
    if (sessionStorage.getItem('token') === null) {
      next({ name: 'login' });
    } else {
      const activeRoles = [];

      to.matched.forEach((route) => {
        if (route.meta.roles) {
          activeRoles.push(...route.meta.roles);
        }
      });

      if (to.meta.roles && to.meta.roles.length > 0) {
        if (activeRoles.includes(user.idRole)) {
          next();
        } else {
          if (to.name !== 'dashboard') { //eslint-disable-line
            next({ name: 'dashboard' });
          }
        }
      } else {
        if (user.idRole === 'ROLE_MKT_EDITOR') {
          next({ name: 'content-manager' });
        } else if (user.idRole === 'ROLE_COM_METVIEW') {
          next({ name: 'metrics-list' });
        } else {
          next();
        }

        return;
      }
    }

    if (to.name === 'admin' || to.name === 'login') {
      next({ name: 'dashboard' });
    }
  } else {
    next();
  }

  if (sessionStorage.getItem('token') !== null && to.name === 'login') {
    next({ name: 'dashboard' });
  }
});

export default router;
