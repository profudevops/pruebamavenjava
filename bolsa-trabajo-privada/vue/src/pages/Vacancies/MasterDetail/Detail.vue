<template>
  <div>
    <AnalystList :list="analystsList" />
    <!-- Section title -->
    <section-title title="Vacantes recientes">
      <b-dropdown class="opts-dropdown" right text="Opciones">
        <b-dropdown-item
          :to="{ name: 'edit-master-vacancy', params: { id: $route.params.vacancyId } }"
        >Editar vacante</b-dropdown-item>
      </b-dropdown>
    </section-title>

    <LoadingMsg v-if="loadingInfo" />

    <slot v-if="!loadingInfo">
      <div v-if="!vacancyDetail && !isError" class="text-center">
        <p class="error-msg">No hay información disponible</p>
      </div>

      <div v-if="isError" class="text-center">
        <p class="error-msg">¡No se encontró información de la Vacante!</p>
        <p class="error-msg">Por favor vuelve a intentarlo más tarde.</p>
      </div>
      <!-- Vacancies detail -->
      <div v-if="vacancyDetail" class="vacancy-detail">
        <!-- Results -->
        <div class="results">
          <div class="d-flex top-table">
            {{ vacancyDetail.tituloVacante }}
            <b-button
              variant="primary"
              size="sm"
              v-if="
                ['ROLE_CRP_ADMN', 'ROLE_COM_ADMN', 'ROLE_COM_CON', 'ROLE_CRP_ATN_CTS_REC'].includes(
                  idRole
                )
              "
              @click="sendToSquareActive(vacancyId)"
            >Abrir Plazas</b-button>
          </div>
          <div class="table-responsive" v-if="vacancyDetail.publicaciones.length">
            <table class="table b-table text-center">
              <thead>
                <tr>
                  <th>Gerencia</th>
                  <th>Sede</th>
                  <th>Plazas abiertas</th>
                  <th>Responsable</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(item, idx) in vacancyDetail.publicaciones" :key="idx">
                  <td>{{ item.gerencia }}</td>
                  <td>
                    <b-link
                      :to="{
                        name: 'info-master-vacancy',
                        params: { vacancyId: item.idVacante, idSede: item.idSede }
                      }"
                    >{{ item.sede }}</b-link>
                  </td>
                  <td>{{ item.plazasAbiertas }}</td>
                  <td>
                    <div v-if="item.analistAsignado">
                      {{ item.analistAsignado[0] }}
                      <template v-if="item.analistAsignado.length > 1">
                        y
                        <a
                          href="#"
                          role="button"
                          @click="showAnalysts(item.analistAsignado)"
                        >{{ item.analistAsignado.length - 1 }} más</a>
                      </template>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="text-center analyst-blank-box" v-if="!vacancyDetail.publicaciones.length">
            <p>No hay información disponible</p>
          </div>
        </div>
      </div>
    </slot>
  </div>
</template>

<style lang="scss" scoped>
@import "./Detail.scss";
</style>

<script>
import { masterVacancyDetail } from '@/services/vacancies';

import AnalystList from '@/components/Modal/AnalystsList.vue';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import PaginationNav from '@/components/PaginationNav/PaginationNav.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

export default {
  components: {
    AnalystList,
    SectionTitle,
    PaginationNav,
    LoadingMsg,
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

  created() {
    const { idRole } = JSON.parse(sessionStorage.getItem('user'));
    this.idRole = idRole;
  },

  mounted() {
    this.init();
  },

  methods: {
    init(page = 1) {
      const data = {
        idVacante: this.vacancyId,
        paginaActual: page,
      };
      this.loadingInfo = true;

      return this.getMasterVacancyDetail(data);
    },

    showAnalysts(analysts = []) {
      this.analystsList = [...analysts];
      this.$bvModal.show('analysts-list');
    },

    sendToSquareActive(vacancyId) {
      this.$router.push({ name: 'square-active', params: { vacancyId } });
    },

    getMasterVacancyDetail(data) {
      this.masterVacancies = null;

      masterVacancyDetail(data)
        .then(({ entity }) => {
          this.vacancyDetail = entity;
        })
        .catch(() => {
          this.isError = true;
        })
        .finally(() => {
          this.loadingInfo = false;
        });
    },

    updateRecutring(status) {
      this.vacancyDetail = Object.assign(this.vacancyDetail, { status });
    },
  },
};
</script>
