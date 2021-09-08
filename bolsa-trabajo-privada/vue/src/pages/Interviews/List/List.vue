<template>
  <div>
    <section-title title="Mis Entrevistas"></section-title>

    <LoadingMsg v-if="loadingInfo" />

    <!-- No data -->
    <div v-if="!loadingInfo && interviews.length === 0">
      <p class="error-msg text-center" v-if="!isError">No hay información disponible</p>
      <div v-if="isError" class="text-center">
        <p class="error-msg">¡No se encontraron Entrevistas!</p>
        <p class="error-msg">Por favor vuelve a intentarlo más tarde.</p>
      </div>
    </div>

    <slot v-if="!loadingInfo && interviews.length > 0">
      <!-- Results -->
      <div class="results">
        <div class="table-responsive">
          <table class="table b-table text-center">
            <thead>
              <tr>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Candidato</th>
                <th>Vacante</th>
                <th>Lugar</th>
                <th>Acción</th>
              </tr>
            </thead>
            <tbody v-if="interviews.length === 0">
              <tr>
                <td colspan="6">
                  <div>No hay resultados</div>
                </td>
              </tr>
            </tbody>
            <tbody v-if="interviews.length > 0">
              <tr v-for="(item, key) in interviews" :key="key">
                <td>{{ item.fecha }}</td>
                <td>{{ item.hora }}</td>
                <td>
                  <router-link
                    :to="{ name: 'prospect', params: { prospectId: item.idCandidato } }"
                  >{{ item.candidato }}</router-link>
                </td>
                <td>{{ item.vacante }}</td>
                <td>{{ item.lugar }}</td>
                <td>
                  <router-link
                    :to="{ name: 'prospect-vacancy-detail', params: { prospectId: item.idCandidato, vacancyId: item.idReclutamiento, step: 0 } }"
                  >Detalle de entrevista</router-link>
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
import interviewsSrv from '@/services/interviews';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import PaginationNav from '@/components/PaginationNav/PaginationNav.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

export default {
  components: {
    SectionTitle,
    PaginationNav,
    LoadingMsg,
  },

  data() {
    return {
      loadingInfo: false,
      interviews: [],
      totalPages: 0,
      currentPage: 0,
      isError: false,
    };
  },

  mounted() {
    this.init();
  },

  methods: {
    init(page = 1) {
      const data = {
        paginaActual: page,
      };
      this.loadingInfo = true;

      return this.getInterviews(data);
    },

    getInterviews(data) {
      this.loadingInfo = true;

      return interviewsSrv
        .getInterviews(data)
        .then(({ entity }) => {
          const { paginaActual, totalPaginas, entrevistas } = entity;

          this.interviews = entrevistas;
          this.totalPages = totalPaginas;
          this.currentPage = paginaActual;
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
