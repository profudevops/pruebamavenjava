<template>
  <div class="vacancy-detail">
    <!-- Header section -->
    <section-title :title="info.vacante" :withSmallMargin="true" />
    <div class="info">
      <p>
        Sede:
        <strong>{{info.sede}}</strong>
      </p>
    </div>
    <!-- Loading Msg -->
    <LoadingMsg v-if="isLoading" />

    <!-- No Data -->
    <div v-if="!isLoading && publications.length === 0">
      <div v-if="!isError" class="text-center">
        <p>No hay información disponible</p>
      </div>
      <div v-if="isError" class="text-center">
        <p class="error-msg">¡No se encontraron Analistas!</p>
        <p class="error-msg">Por favor vuelve a intentarlo más tarde.</p>
      </div>
    </div>

    <!-- Table -->
    <slot v-if="!isLoading && publications.length > 0">
      <div class="table-responsive mb-3">
        <table class="table b-table text-center">
          <thead>
            <tr>
              <th>Analista</th>
              <th>Gerencia</th>
              <th>Plazas Abiertas</th>
              <th>Plazas Cubiertas</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(publication, key) in publications" :key="key">
              <td class="blue-cell">
                <router-link
                  :to="{ name: 'info-master-publication', params: { vacancyId: $route.params.vacancyId, idSede: $route.params.idSede, idPublication: publication.idPublicacion } }"
                >{{ publication.analista }}</router-link>
              </td>
              <td>{{ publication.gerencia }}</td>
              <td>{{ publication.plazasAbiertas }}</td>
              <td>{{ publication.plazasCubiertas }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </slot>
  </div>
</template>

<script>
import { getAgents } from '@/services/vacancies.js';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

export default {
  data() {
    return {
      isLoading: true,
      isError: false,
      vacancyDetail: {},
      info: {},
      publications: [],
    };
  },

  components: {
    SectionTitle,
    LoadingMsg,
  },

  mounted() {
    this.getAgents();
  },

  methods: {
    getAgents() {
      this.isLoading = true;

      getAgents({
        idSede: this.$route.params.idSede,
        idVacante: this.$route.params.vacancyId,
      })
        .then(({ entity }) => {
          this.publications = [...entity.publicaciones];
          this.info = { ...entity };
        })
        .catch(() => {
          this.isError = true;
        })
        .finally(() => {
          this.isLoading = false;
        });
    },
  },
};
</script>
