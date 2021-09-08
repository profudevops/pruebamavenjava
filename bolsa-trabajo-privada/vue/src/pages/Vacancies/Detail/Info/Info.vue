<template>
  <div class="vacancy-detail">
    <LoadingMsg v-if="isLoading" />
    <slot v-if="!isLoading">
      <!-- Title -->
      <section-title :title="vacancyInfo.tituloVacante">
        <b-dropdown class="opts-dropdown" right text="Opciones" v-if="$route.query.isCloser != 1">
          <!-- VACANCY OPTIONS -->
          <b-dropdown-item
            v-if="['ROLE_CRP_ADMN', 'ROLE_COM_ADMN', 'ROLE_COM_CON'].includes(idRole)"
            href="#"
            @click="openModal('asign-recutring-vacancy')"
          >Asignar Vacante</b-dropdown-item>
          <b-dropdown-item
            v-if="$route.name !== 'info-master-publication' && ['ROLE_CRP_ADMN', 'ROLE_CRP_REC', 'ROLE_CRP_ATN_CTS_REC', 'ROLE_COM_ADMN'].includes(idRole)"
            href="#"
            :to="{ name: 'edit-vacancy', params: { id: vacancyId } }"
          >Editar vacante</b-dropdown-item>
          <!-- MASTER VACANCY OPTIONS -->
          <b-dropdown-item
            v-if="$route.name === 'info-master-publication' && ['ROLE_CRP_ADMN', 'ROLE_COM_CON', 'ROLE_COM_ADMN', 'ROLE_COM_ANL', 'ROLE_CRP_REC', 'ROLE_CRP_ATN_CTS_REC', 'ROLE_COM_REC'].includes(idRole)"
            href="#"
            :to="{ name: 'edit-master-strategy', params: { idEstrategy: $route.params.idPublication } }"
          >Editar estrategia</b-dropdown-item>
          <b-dropdown-item
            v-if="$route.name !== 'info-master-publication'"
            href="#"
            :to="{ name: 'edit-strategy', params: { idEstrategy: $route.params.idPublication } }"
          >Editar estrategia</b-dropdown-item>
          <b-dropdown-item
            v-if="['ROLE_CRP_ADMN', 'ROLE_COM_REC', 'ROLE_COM_ADMN', 'ROLE_COM_CON', 'ROLE_CRP_REC', 'ROLE_COM_ANL', 'ROLE_CRP_ATN_CTS_REC'].includes(idRole)"
            href="#"
            @click="openModal('vacancy-codes')"
          >Bolsas Externas</b-dropdown-item>
          <b-dropdown-item
            v-if="$route.name === 'info-master-publication' && ['ROLE_COM_CON', 'ROLE_CRP_ADMN', 'ROLE_COM_ADMN', 'ROLE_CRP_REC', 'ROLE_CRP_ATN_CTS_REC'].includes(idRole)"
            href="#"
            @click.prevent="openModal('edit-spot')"
          >Editar Plazas</b-dropdown-item>
          <b-dropdown-item
            href="#"
            @click.prevent="showConfirm('close-vacancy')"
            v-if="['ROLE_CRP_ADMN', 'ROLE_COM_ADMN', 'ROLE_COM_REC', 'ROLE_COM_CON', 'ROLE_CRP_REC', 'ROLE_CRP_ATN_CTS_REC', 'ROLE_COM_ANL'].includes(idRole)"
          >Cerrar vacante</b-dropdown-item>
        </b-dropdown>
      </section-title>

      <!-- Confirm modal -->
      <confirm
        :title="confirmOpts.title"
        :content="confirmOpts.content"
        :okTitle="confirmOpts.okTitle"
        :close="confirmOpts.close"
      ></confirm>

      <!-- Vacancy Codes Modal -->
      <VacancyCodesModal
        :id="this.vacancyId"
        :idPublicacion="Number($route.params.idPublication)"
        :isMasterVacancy="($route.name === 'info-master-publication')? true: false"
      />

      <!-- Edit Spot -->
      <EditSpot
        :currentySpot="vacancyInfo.plazasAbiertas"
        :idPublicacion="publicationId"
        @updateSpot="updateSpot"
      />

      <!-- Asign Recutring Vacancy-->
      <AsignRecutringVacancyModal
        :id="$route.params.idPublication"
        @updateRecutring="updateRecutring"
      />
      <div class="info" v-if="vacancyInfo.sede || vacancyInfo.gerencia || vacancyInfo.analista">
        <p v-if="vacancyInfo.sede">
          Sede:
          <strong>{{ vacancyInfo.sede }}</strong>
        </p>
        <p v-if="vacancyInfo.gerencia">
          Gerencia:
          <strong>{{ vacancyInfo.gerencia }}</strong>
        </p>
        <p v-if="vacancyInfo.analista">
          Responsable:
          <strong>{{ vacancyInfo.analista }}</strong>
        </p>
      </div>

      <!-- Info vacancy -->
      <div class="info">
        <!-- TODO Add validation depending of permision -->
        <p>
          Postulados:
          <strong>{{ vacancyInfo.numeroPostulados }}</strong>
        </p>
        <p>
          En Proceso:
          <strong>{{ vacancyInfo.numeroEnProeso }}</strong>
        </p>
      </div>

      <div class="info" v-if="vacancyInfo.estatus === 'Cerrada'">
        <p>
          Motivo del cierre:
          <strong>{{ vacancyInfo.motivoCierre }}</strong>
        </p>
        <p v-if="vacancyInfo.candidatoSeleccionado">
          Candidato Seleccionado:
          <strong>{{ vacancyInfo.candidatoSeleccionado }}</strong>
        </p>
      </div>

      <!-- Success card -->
      <SuccessCard
        v-if="typeCurrentAction"
        :title="successContent.title"
        :description="successContent.description"
      />

      <b-tabs class="special-nav" fill>
        <b-tab title="En Proceso" active>
          <div v-if="loadingCandidates">Obteniendo candidatos disponibles...</div>

          <div v-if="!loadingCandidates">
            <div v-if="candidates.length === 0">No hay información disponible</div>

            <!-- Candidates List -->
            <div v-if="candidates.length > 0">
              <div class="table-responsive">
                <table aria-busy="false" aria-colcount="4" class="table b-table text-center">
                  <thead role="rowgroup">
                    <tr role="row">
                      <th role="columnheader" scope="col" aria-colindex="1">Nombre</th>
                      <th role="columnheader" scope="col" aria-colindex="2">Télefono</th>
                      <th role="columnheader" scope="col" aria-colindex="3">Correo eléctronico</th>
                      <th role="columnheader" scope="col" aria-colindex="5">Estatus</th>
                    </tr>
                  </thead>
                  <tbody role="rowgroup">
                    <tr role="row" v-for="(candidate, key) in candidates" :key="key">
                      <td role="cell" aria-colindex="1" class="blue-cell">
                        <router-link
                          :to="{ name: 'prospect', params: { prospectId: candidate.idCandidato } }"
                        >{{ candidate.nombrePostulado }}</router-link>
                      </td>
                      <td role="cell" aria-colindex="2">{{ candidate.telefono | phoneNumber }}</td>
                      <td role="cell" aria-colindex="3">{{ candidate.correo }}</td>
                      <td role="cell" aria-colindex="5">{{ candidate.estatus }}</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

          <!-- Pagination -->
          <pagination-nav
            v-if="totalPagesPostulates > 1"
            :currentPage="currentPagePostulates"
            :totalPages="totalPagesPostulates"
            @updateCurrentPage="getPostulates"
          ></pagination-nav>
        </b-tab>
        <b-tab title="Postulados y sugerencias" v-if="vacancyInfo.estatus !== 'Cerrada'">
          <div v-if="loadingSuggestion">Obteniendo candidatos disponibles...</div>

          <div v-if="!loadingSuggestion">
            <div v-if="postulatesSuggestion.length === 0">No hay información disponible</div>

            <!-- Postulate & suggestion -->
            <div v-if="postulatesSuggestion.length > 0">
              <div>
                <h3>Postulados y Sugerencias</h3>
                <div class="table-responsive">
                  <table aria-busy="false" aria-colcount="4" class="table b-table text-center">
                    <thead role="rowgroup">
                      <tr role="row">
                        <th role="columnheader" scope="col" aria-colindex="1">Nombre</th>
                        <th role="columnheader" scope="col" aria-colindex="2">Nivel de Estudios</th>
                        <th role="columnheader" scope="col" aria-colindex="3" width="15%">Télefono</th>
                        <th role="columnheader" scope="col" aria-colindex="4">Residencia</th>
                        <th role="columnheader" scope="col" aria-colindex="5">Estatus</th>
                        <th role="columnheader" scope="col" aria-colindex="6" width="95">Acciones</th>
                      </tr>
                    </thead>
                    <tbody role="rowgroup">
                      <tr role="row" v-for="(prospect, key) in postulatesSuggestion" :key="key">
                        <td role="cell" aria-colindex="1" class="blue-cell">
                          <router-link
                            :to="{ name: 'prospect', params: { prospectId: prospect.idCandidato } }"
                          >{{ prospect.nombre }}</router-link>
                        </td>
                        <td role="cell" aria-colindex="2">{{ prospect.nivelEstudios }}</td>
                        <td role="cell" aria-colindex="3">{{ prospect.telefono | phoneNumber }}</td>
                        <td role="cell" aria-colindex="4">{{ prospect.residencia }}</td>
                        <td role="cell" aria-colindex="5">{{ prospect.tipoCandidato }}</td>
                        <td role="cell" aria-colindex="6" class="without-light">
                          <b-dropdown class="opts-dropdown horizontal" right no-caret>
                            <template slot="button-content">
                              <i class="fas fa-ellipsis-h"></i>
                            </template>
                            <b-dropdown-item @click="initProcess(prospect)">Iniciar Proceso</b-dropdown-item>
                            <b-dropdown-item @click="discardProspect(prospect)">Descartar</b-dropdown-item>
                          </b-dropdown>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </b-tab>
      </b-tabs>
    </slot>
  </div>
</template>

<style lang="scss" scoped>
@import "./Info.scss";
</style>

<script>
import _ from 'lodash';
import phoneNumber from '@/filters/phone-number-format';
import {
  getVacancyDetail,
  getPostulates,
  getSuggestCandidates,
  confirmCloseVacancy,
  initCandidateProcess,
  saveDiscardProspect,
} from '@/services/vacancies';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import Search from '@/components/Search/Search.vue';
import SuccessCard from '@/components/SuccessCard/SuccessCard.vue';
import VacancyCodesModal from '@/components/Modal/VacancyCodes.vue';
import EditSpot from '@/components/Modal/EditSpot.vue';
import PaginationNav from '@/components/PaginationNav/PaginationNav.vue';
import AsignRecutringVacancyModal from '@/components/Modal/AsignRecutringVacancy.vue';
import Confirm from '@/components/Modal/Confirm.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

export default {
  data() {
    return {
      isLoading: true,
      confirmOpts: {},
      selectedCandidate: null,
      vacancyId: this.$route.params.vacancyId
        ? Number(this.$route.params.vacancyId)
        : null,
      publicationId: Number(this.$route.params.idPublication),
      loadingCandidates: false,
      loadingSuggestion: true,
      vacancyInfo: {},
      currentPagePostulates: 0,
      totalPagesPostulates: 0,
      candidates: [],
      postulatesSuggestion: [],
      typeCurrentAction: null,
      successContent: {},
      idRole: '',
    };
  },

  filters: {
    phoneNumber,
  },

  components: {
    SectionTitle,
    SuccessCard,
    Search,
    VacancyCodesModal,
    AsignRecutringVacancyModal,
    PaginationNav,
    Confirm,
    EditSpot,
    LoadingMsg,
  },

  created() {
    this.asignIdRole();
    this.getVacancyDetail();
    this.asignSuccessMsg();
  },

  methods: {
    asignSuccessMsg() {
      if (this.$store.state.vacancy.currentAction) {
        this.typeCurrentAction = this.$store.state.vacancy.currentAction;

        if (this.typeCurrentAction === 'CREATED') {
          this.successContent = Object.assign({}, this.successContent, {
            title: '¡Vacante publicada!',
            description: 'La vacante ha sido creada con éxito.',
          });

          this.$store.commit('vacancy/SET_CURRENT_ACTION', null);
        } else if (this.typeCurrentAction === 'UPDATED') {
          this.successContent = Object.assign({}, this.successContent, {
            title: '¡Vacante actualizada!',
            description: 'La vacante ha sido actualizada con éxito.',
          });

          this.$store.commit('vacancy/SET_CURRENT_ACTION', null);
        }
      } else {
        this.successContent = {};
      }
    },

    asignIdRole() {
      const { idRole } = JSON.parse(sessionStorage.getItem('user'));
      this.idRole = idRole;
    },

    updateSpot(spot) {
      this.vacancyInfo = {
        ...this.vacancyInfo,
        plazasAbiertas: spot,
      };
    },

    updateRecutring(asignada) {
      this.vacancyInfo = Object.assign(this.vacancyInfo, { asignada });
    },

    getVacancyDetail() {
      this.isLoading = true;
      this.vacancyInfo = {};
      window.scrollTo({ top: 0 });

      const { isCloser } = this.$route.query;

      getVacancyDetail(
        this.$route.params.idPublication,
        this.vacancyId,
        isCloser,
      )
        .then(({ entity }) => {
          this.vacancyInfo = { ...entity };
          this.vacancyId = this.vacancyInfo.idVacante;

          this.getPostulates();
          this.getSuggestCandidates();
        })
        .catch(() => {
          console.log('error service');
        })
        .finally(() => {
          this.isLoading = false;
        });
    },

    getPostulates(page = 1) {
      let data = {
        paginaActual: page,
      };

      if (this.$route.params.idPublication) {
        data = {
          ...data,
          idPublicacion: this.$route.params.idPublication,
        };
      }

      this.loadingCandidates = true;

      if (this.search) {
        data.busqueda = this.search;
      }

      getPostulates(data)
        .then(({ entity: { postulados, paginaActual, totalPaginas } }) => {
          this.candidates = postulados;
          this.currentPagePostulates = paginaActual;
          this.totalPagesPostulates = totalPaginas;

          this.loading = false;
        })
        .finally(() => {
          this.loadingCandidates = false;
        });
    },

    getSuggestCandidates(page = 1, idSede = null) {
      const data = {
        paginaActual: page,
        idPublicacion: this.vacancyInfo.idPublicacion,
        idVacante: this.vacancyInfo.idVacante,
        idSede: this.vacancyInfo.idSede,
      };

      this.loadingSuggestion = true;

      getSuggestCandidates(data)
        .then(({ entity }) => {
          this.postulatesSuggestion = entity;
        })
        .finally(() => {
          this.loadingSuggestion = false;
        });
    },

    searchHandler: _.debounce(function debounce(text) {
      this.search = text;
      this.currentPagePostulates = 0;

      this.getPostulates(1);
    }, 200),

    openModal(type) {
      this.$bvModal.show(type);
    },

    showConfirm(type) {
      switch (type) {
        case 'close-vacancy':
          this.confirmOpts = {
            title: 'Cerrar vacante',
            content: `¿Deseas cerrar la vacante: <b>${this.vacancyInfo.tituloVacante}</b>?`,
            okTitle: 'Confirmar',
            close: this.closeVacancy,
          };

          break;
        case 'init-process':
          this.confirmOpts = {
            title: 'Iniciar proceso',
            content: `¿Deseas iniciar un proceso para: <b>${this.selectedCandidate.nombre}</b>?`,
            okTitle: 'Confirmar',
            close: this.confirmInitProcess,
          };

          break;
        case 'discard-prospect':
          this.confirmOpts = {
            title: 'Rechazar Candidato',
            content:
              '¿Deseas rechazar al candidato? Si lo descartas podrás seguirlo viendo en los detalles de la vacante.',
            okTitle: 'Rechazar',
            close: this.confirmDiscardProspect,
          };

          break;
        default:
          this.confirmOpts = {};
      }

      this.$bvModal.show('confirm-dialog');
    },

    closeVacancy(modal, res) {
      this.$bvModal.hide(modal);

      if (res) {
        this.loadingCandidates = true;

        confirmCloseVacancy(this.$route.params.idPublication).then(() => {
          this.$notify({
            type: 'info',
            title: 'Vacante cerrada',
            text: 'La vacante ha sido cerrada correctamente',
          });
          this.$router.push({ name: 'vacancies' });
        });
      }
    },

    initProcess(prospect) {
      this.selectedCandidate = prospect;
      this.showConfirm('init-process');
    },

    discardProspect(prospect) {
      this.selectedCandidate = prospect;
      this.showConfirm('discard-prospect');
    },

    confirmInitProcess(modal, res) {
      this.$bvModal.hide(modal);
      if (res) {
        this.isLoading = true;

        initCandidateProcess({
          idPublicacion: Number(this.$route.params.idPublication),
          idCandidato: this.selectedCandidate.idCandidato,
        }).then(() => {
          this.$notify({
            type: 'info',
            title: 'Aviso',
            text: 'El candidato ha iniciado el proceso de selección',
          });
          this.getVacancyDetail();
        })
          .catch((error) => {
            console.log(error);
            let text = 'Hubo un error al procesar la solicitud.';

            if (error.data.statusText) {
              text = error.data.statusText;
            }

            this.$notify({
              type: 'error',
              title: 'Aviso',
              text,
            });
          })
          .finally(() => {
            this.isLoading = false;
          });
      }
    },

    confirmDiscardProspect(modal, res) {
      this.$bvModal.hide(modal);

      if (res) {
        this.isLoading = true;

        saveDiscardProspect({
          idPublicacion: this.$route.params.idPublication,
          idCandidato: this.selectedCandidate.idCandidato,
        }).then(() => {
          this.$notify({
            type: 'error',
            title: 'Aviso',
            text: 'El candidato ha sido descartado',
          });
          this.getVacancyDetail();
        }).catch(() => {
          this.isLoading = false;
        });
      }
    },
  },
};
</script>
