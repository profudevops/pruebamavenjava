<template>
  <div class="vacancies">
    <section-title title="Vacantes">
      <b-button
        v-permission="['PORTOFOLIO_CORP']"
        variant="primary"
        @click="sendToCreateVacancy"
      >Crear Vacante</b-button>
    </section-title>

    <LoadingMsg v-if="loadingInfo" />

    <slot v-if="!loadingInfo">
      <div v-if="!vacancies && !isError">
        <div class="text-center">
          <p>No hay información disponible</p>
        </div>
      </div>
      <div v-if="isError" class="text-center">
        <p class="error-msg">¡No se encontraron Vacantes!</p>
        <p class="error-msg">Por favor vuelve a intentarlo más tarde.</p>
      </div>

      <!-- Vacancies list -->
      <div v-if="resumenVacantesAdminCorpVO">
        <common-list :vacancies="resumenVacantesAdminCorpVO.vacantes"></common-list>
        <pagination-nav
          :currentPage="currentPage"
          :totalPages="resumenVacantesAdminCorpVO.totalPaginas"
          v-if="resumenVacantesAdminCorpVO.totalPaginas > 1"
          @updateCurrentPage="getAllVacancies"
        ></pagination-nav>
        <master-list :vacancies="resumenVacantesAdminCorpVO.vacantesMaestrasAnalistaClientes"></master-list>
      </div>

      <div v-if="vacantesMaestras">
        <master-list :vacancies="vacantesMaestras"></master-list>
      </div>

      <div v-if="vacantesMaestrasAtnClientes">
        <analyst-list
          v-for="(list, key) in vacantesMaestrasAtnClientes"
          :key="key"
          :vacancies="list"
        ></analyst-list>
      </div>

      <div v-if="resumenPublicaciones">
        <common-list :vacancies="resumenPublicaciones.resumenPublicaciones"></common-list>
        <pagination-nav
          :currentPage="currentPage"
          :totalPages="resumenPublicaciones.totalPaginas"
          v-if="resumenPublicaciones.totalPaginas > 1"
          @updateCurrentPage="getAllVacancies"
        ></pagination-nav>
      </div>

      <div v-if="vacantesMaestrasAnalista">
        <analyst-list v-for="(list, key) in vacantesMaestrasAnalista" :key="key" :vacancies="list"></analyst-list>
      </div>

      <div v-if="vacantesReclutadorComercial">
        <CommercialRecluterList :vacancies="vacantesReclutadorComercial"></CommercialRecluterList>
      </div>
    </slot>
  </div>
</template>

<script>
import { getVacancies } from '@/services/vacancies';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import CommonList from './Common/Common.vue';
import MasterList from './Master/Master.vue';
import AnalystList from './Analyst/Analyst.vue';
import PaginationNav from '@/components/PaginationNav/PaginationNav.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';
import CommercialRecluterList from './CommercialRecluterList/CommercialRecluterList.vue';

export default {
  data() {
    return {
      loadingInfo: true,
      vacancies: null,
      resumenPublicaciones: null,
      resumenVacantesAdminCorpVO: null,
      vacantesMaestras: null,
      vacantesMaestrasAnalista: null,
      vacantesMaestrasAtnClientes: null,
      vacantesReclutadorComercial: null,
      totalPages: 0,
      currentPage: 0,
      isError: false,
      currentUser: null,
    };
  },

  components: {
    SectionTitle,
    CommonList,
    MasterList,
    AnalystList,
    PaginationNav,
    LoadingMsg,
    CommercialRecluterList,
  },

  mounted() {
    this.getAllVacancies();
  },

  methods: {
    sendToCreateVacancy() {
      this.$router.push({ name: 'create-vacancy' });
    },

    clearResults() {
      this.loadingInfo = true;
      this.vacancies = null;
      this.resumenPublicaciones = null;
      this.resumenVacantesAdminCorpVO = null;
      this.vacantesMaestras = null;
      this.vacantesMaestrasAnalista = null;
      this.vacantesMaestrasAtnClientes = null;
      this.vacantesReclutadorComercial = null;
    },

    getAllVacancies(page = 1) {
      this.clearResults();

      this.currentPage = page;

      return getVacancies({ paginaActual: page })
        .then(({ entity }) => {
          const {
            resumenPublicaciones,
            resumenVacantesAdminCorpVO,
            vacantesMaestras,
            vacantesMaestrasAnalista,
            vacantesMaestrasAtnClientes,
            vacantesReclutadorComercial,
          } = entity;

          this.vacancies = Object.keys(entity).filter(e => e.length > 0).length > 0 || false;
          this.resumenPublicaciones = resumenPublicaciones;
          this.resumenVacantesAdminCorpVO = resumenVacantesAdminCorpVO;
          this.vacantesMaestras = vacantesMaestras;
          this.vacantesMaestrasAnalista = vacantesMaestrasAnalista;
          this.vacantesMaestrasAtnClientes = vacantesMaestrasAtnClientes;
          this.vacantesReclutadorComercial = vacantesReclutadorComercial;
        })
        .catch(() => {
          this.isError = true;
        })
        .finally(() => {
          this.loadingInfo = false;
        });
    },
  },
};
</script>
