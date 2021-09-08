<template>
  <div class="sidebar" role="complementary">
    <div>
      <a href="#" role="button" class="close-btn-mobile" @click.prevent="closeSidebarMenu()">
        <i class="fas fa-times" aria-hidden="true"></i>
      </a>
      <div class="logo">
        <router-link :to="{ name: 'dashboard' }">
          <img src="@/assets/img/logos/profuturo-color.png" alt="Ir a Dashboard" />
          <span>Bolsa de Trabajo</span>
        </router-link>
      </div>
      <b-nav vertical>
        <slot
          v-if="['ROLE_MKT_EDITOR', 'ROLE_COM_METVIEW', 'ROLE_COM_INT', 'ROLE_CRP_OBS'].indexOf(currentUser.idRole) < 0"
        >
          <b-nav-item :to="{ name: 'dashboard' }" :active="activeLinkSidebar('dashboard')">
            <i class="fas fa-tachometer-alt"></i> Escritorio
          </b-nav-item>
          <b-nav-item :to="{ name: 'vacancies' }" :active="activeLinkSidebar('vacancies')">
            <i class="fas fa-briefcase"></i> Vacantes
          </b-nav-item>
          <b-nav-item :to="{ name: 'candidates-list' }" :active="activeLinkSidebar('candidates')">
            <i class="fas fa-address-book"></i> Candidatos
          </b-nav-item>
          <b-nav-item
            :to="{ name: 'areas-list' }"
            :active="activeLinkSidebar('areas')"
            v-if="['ROLE_CRP_ADMN', 'ROLE_COM_ADMN'].indexOf(currentUser.idRole) > -1"
          >
            <i class="fas fa-landmark"></i> Áreas
          </b-nav-item>
          <b-nav-item :to="{ name: 'interviews-list' }" :active="activeLinkSidebar('interviews')">
            <i class="fas fa-comments"></i> Entrevistas
          </b-nav-item>
          <b-nav-item :to="{ name: 'portfolio-list' }" :active="activeLinkSidebar('portfolio')">
            <i class="fas fa-book-open"></i> Cartera
          </b-nav-item>
          <b-nav-item
            :to="{ name: 'metrics-list' }"
            :active="activeLinkSidebar('metrics')"
            v-permission="['METRICS_CREATE']"
          >
            <i class="fas fa-chart-area"></i> Métricas
          </b-nav-item>
        </slot>
        <slot v-if="['ROLE_COM_INT', 'ROLE_CRP_OBS'].includes(currentUser.idRole)">
          <b-nav-item :to="{ name: 'dashboard' }" :active="activeLinkSidebar('dashboard')">
            <i class="fas fa-tachometer-alt"></i> Escritorio
          </b-nav-item>
          <b-nav-item :to="{ name: 'candidates-list' }" :active="activeLinkSidebar('candidates')">
            <i class="fas fa-address-book"></i> Candidatos
          </b-nav-item>
          <b-nav-item :to="{ name: 'interviews-list' }" :active="activeLinkSidebar('interviews')">
            <i class="fas fa-comments"></i> Entrevistas
          </b-nav-item>
        </slot>
        <b-nav-item
          :to="{ name: 'content-manager' }"
          :active="activeLinkSidebar('content-manager')"
          v-permission="['HOME_EDIT']"
        >
          <i class="fas fa-file-alt"></i> Contenido
        </b-nav-item>
        <b-nav-item
          :to="{ name: 'metrics-list' }"
          :active="activeLinkSidebar('metrics')"
          v-permission="['METRICS_CREATE']"
          v-if="currentUser.idRole === 'ROLE_COM_METVIEW'"
        >
          <i class="fas fa-chart-area"></i> Métricas
        </b-nav-item>
      </b-nav>
    </div>
    <div class="logout">
      <b-button block variant="light" @click="logout">
        <i class="fas fa-sign-out-alt icon-red"></i> Cerrar sesión
      </b-button>
    </div>
  </div>
</template>


<style lang="scss" scoped>
@import "./Sidebar";
</style>

<script>
export default {
  data() {
    return {
      currentRoute: '',
      currentUser: null,
    };
  },

  computed: {
    userExists() {
      return JSON.parse(sessionStorage.getItem('user')).firstName === 'Livi';
    },
  },

  created() {
    this.currentRoute = this.$route.matched[1].meta.name || this.$route.name;
    this.currentUser = JSON.parse(sessionStorage.getItem('user'));
  },

  watch: {
    $route(to) {
      this.currentRoute = to.matched[1].meta.name || to.name;
    },
  },

  methods: {
    logout: function logout() {
      this.$emit('closeSession');
    },

    closeSidebarMenu() {
      this.$store.commit('general/TWICE_MOBILE_MENU', false);
    },

    activeLinkSidebar: function activeLinkSidebar(routerType) {
      if (this.currentRoute.search(routerType) >= 0) {
        return true;
      }

      return false;
    },
  },
};
</script>
