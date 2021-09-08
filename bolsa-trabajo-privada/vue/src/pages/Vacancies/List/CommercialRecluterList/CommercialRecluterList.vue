<template>
  <div>
    <slot>
      <!-- Vacancies detail -->
      <div class="vacancy-detail">
        <!-- Results -->
        <div class="results">
          <div class="d-flex top-table">
            {{ vacancies.tituloVacante }}
            <b-button
              variant="primary"
              size="sm"
              @click="sendToSquareActive(vacancies.idVacante, vacancies.idUsuario)"
            >Abrir Plazas</b-button>
          </div>
          <div class="table-responsive" v-if="vacancies.publicaciones.length">
            <table class="table b-table text-center">
              <thead>
                <tr>
                  <th>Sede</th>
                  <th>Días activa</th>
                  <th>Candidatos</th>
                  <th>Estatus</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(item, idx) in vacancies.publicaciones" :key="idx">
                  <td>
                    <b-link
                      :to="{
                        name: 'info-master-publication',
                        params: {
                          vacancyId: item.idVacante,
                          idSede: item.idSede,
                          idPublication: item.idPublicacion
                        }
                      }"
                    >{{ item.sede }}</b-link>
                  </td>
                  <td>{{ item.diasActiva }}</td>
                  <td>{{ item.candidatos }}</td>
                  <td>{{ item.estatus }}</td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="text-center analyst-blank-box" v-if="!vacancies.publicaciones.length">
            <p>No hay información disponible</p>
          </div>
        </div>
      </div>
    </slot>
  </div>
</template>

<style lang="scss" scoped>
@import "./CommercialRecluterList.scss";
</style>

<script>
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import PaginationNav from '@/components/PaginationNav/PaginationNav.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

export default {
  components: {
    SectionTitle,
    PaginationNav,
    LoadingMsg,
  },

  props: {
    vacancies: {
      default: () => [],
    },
  },

  data() {
    return {
      vacancyId: this.$route.params.vacancyId,
      loadingInfo: true,
      vacancyDetail: null,
      analystsList: [],
      idRole: '',
      isError: false,
    };
  },

  mounted() {},

  methods: {
    sendToSquareActive(vacancyId, analystId = null) {
      this.$router.push({
        name: 'square-active',
        params: { vacancyId },
        query: { analystId },
      });
    },
  },
};
</script>
