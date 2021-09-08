<template>
  <div>
    <section-title title="Candidatos en proceso"></section-title>

    <!-- Search filter -->
    <search
      placeholder="Buscar candidato"
      @search="searchHandler"
    />

    <LoadingMsg v-if="loadingInfo" />

    <!-- No data -->
    <div v-if="!loadingInfo && candidates.length === 0">
      <div v-if="!isError" class="text-center">
        <p>No hay información disponible</p>
      </div>
      <div v-if="isError" class="text-center">
        <p class="error-msg">¡No se encontraron Candidatos!</p>
        <p class="error-msg">Por favor vuelve a intentarlo más tarde.</p>
      </div>
    </div>

    <slot v-if="!loadingInfo && candidates.length > 0">
      <!-- Results -->
      <div class="results">
        <div class="table-responsive">
          <table class="table b-table text-center">
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Teléfono</th>
                <th>Vacante</th>
                <th>Estatus</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(candidate, idx) in candidates" :key="idx">
                <td class="blue-cell">
                  <router-link
                    :to="{ name: 'prospect', params: { prospectId: candidate.idCandidato } }"
                  >{{ candidate.nombres }} {{ candidate.apellidoPaterno }} {{ candidate.apellidoMaterno }}</router-link>
                </td>
                <td>{{ candidate.telefono | phoneNumber }}</td>
                <td>{{ candidate.vacante }}</td>
                <td class="without-light">
                  <status :color="'green'" :description="candidate.estatus" />
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </slot>

    <!-- Pagination -->
    <pagination-nav
      v-if="currentPage && totalPages > 1"
      :currentPage="currentPage"
      :totalPages="totalPages"
      @updateCurrentPage="init"
    ></pagination-nav>
  </div>
</template>

<style lang="scss" scoped>
@import "./List.scss";
</style>

<script>
import _ from 'lodash';
import prospectSrv from '@/services/prospect';
import phoneNumber from '@/filters/phone-number-format';
import Search from '@/components/Search/Search.vue';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import Status from '@/components/Status/Status.vue';
import PaginationNav from '@/components/PaginationNav/PaginationNav.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

export default {
  filters: {
    phoneNumber,
  },

  components: {
    Search,
    SectionTitle,
    Status,
    PaginationNav,
    LoadingMsg,
  },

  data() {
    return {
      loadingInfo: true,
      search: '',
      totalPages: 0,
      currentPage: 0,
      candidates: [],
      isError: false,
    };
  },

  mounted() {
    this.init();
  },

  methods: {
    init(page = 1) {
      if (page) {
        const data = { paginaActual: page };
        this.loadingInfo = true;

        if (this.search) {
          data.search = this.search;
        }

        return prospectSrv
          .getCandidates(data)
          .then(({ entity: { resumenCandidatos, totalPaginas } }) => {
            this.candidates = resumenCandidatos === null ? [] : resumenCandidatos;
            this.totalPages = totalPaginas;
            this.currentPage = 1;
          })
          .catch(() => {
            this.isError = true;
          })
          .finally(() => {
            this.loadingInfo = false;
          });
      }

      return true;
    },

    searchHandler: _.debounce(function debounce(text) {
      this.search = text;
      this.currentPage = 0;
      this.init(1);
    }, 200),
  },
};
</script>
