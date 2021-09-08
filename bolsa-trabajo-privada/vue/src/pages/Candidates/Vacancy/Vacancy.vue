<template>
  <div>
    <slot v-if="!vacancyChecked">Verificando vacante...</slot>

    <slot v-if="vacancyChecked">
      <!-- Candidate process -->
      <div class="candidate-process">
        <b-row>
          <b-col cols="12" md="8" xl="9">
            <section-title :title="vacancyInfo.nombreCandidato"></section-title>
            <strong class="vacancy-name">
              {{ vacancyInfo.tituloVacante }}
              <span
                v-if="vacancyInfo.esRepostulacion"
              >(Repostulación)</span>
            </strong>
            <span class="np" v-if="vacancyInfo.numeroProvisional">
              <b>Número provisional:</b>
              {{ vacancyInfo.numeroProvisional }}
            </span>
            <span class="np" v-if="vacancyInfo.folio">
              <b>Folio:</b>
              {{ vacancyInfo.folio }}
            </span>
            <!-- Flow options -->
            <div v-if="selectedStep.codigo" class="flow-opts">
              <!-- Step handler -->
              <div class="step-handler" @click="stepHandler()">
                <i v-if="!handlerStatus" class="fas fa-plus-circle"></i>
                <i v-if="handlerStatus" class="fas fa-times"></i>
              </div>

              <!-- Placeholder -->
              <div v-if="!handlerStatus && selectedStep" class="dropdown-placeholder">
                <div class="btn-group process-dropdown">
                  <button class="btn dropdown-toggle btn-secondary">{{ selectedStep.descripcion }}</button>
                </div>
              </div>

              <!-- Dropdown options -->
              <b-dropdown
                v-if="handlerStatus && availableSteps.length > 0"
                class="process-dropdown"
                :text="selectedStep.descripcion"
              >
                <b-dropdown-item
                  v-for="step in availableSteps"
                  :key="step.codigo"
                  :class="{ active: selectedStep.codigo === step.codigo }"
                  @click="setCurrentFlow(step.codigo)"
                >{{ step.descripcion }}</b-dropdown-item>
              </b-dropdown>

              <slot v-if="handlerStatus">
                <!-- Selected flow -->
                <div v-if="selectedStep" class="selected-flow">
                  <!-- Forms -->
                  <ft
                    v-if="selectedStep.codigo === 'FT'"
                    :showModal="showModal"
                    :confirmResult="confirmResult"
                    :repostulacion="vacancyInfo.esRepostulacion"
                    @updateCandidateInfo="statusUpdate"
                  ></ft>
                  <peag
                    v-if="selectedStep.codigo === 'PE-AG'"
                    @updateCandidateInfo="statusUpdate"
                    :repostulacion="vacancyInfo.esRepostulacion"
                    :info="{
                      idUsuario: vacancyInfo.idUsuario,
                      idConsultor: vacancyInfo.idConsultor,
                      masterVacancy: vacancyInfo.esVacanteMaestra,
                    }"
                  ></peag>
                  <peagg
                    v-if="selectedStep.codigo === 'PE-AGG'"
                    :repostulacion="vacancyInfo.esRepostulacion"
                    @updateCandidateInfo="statusUpdate"
                    :info="{
                      masterVacancy: vacancyInfo.esVacanteMaestra,
                    }"
                  ></peagg>
                  <pere
                    v-if="selectedStep.codigo === 'PE-RE'"
                    :showModal="showModal"
                    :confirmResult="confirmResult"
                    :idEntrevista="vacancyInfo.idCurrentInterview"
                    :repostulacion="vacancyInfo.esRepostulacion"
                    @updateCandidateInfo="statusUpdate"
                  ></pere>
                  <exc v-if="selectedStep.codigo === 'EXC'" @updateCandidateInfo="statusUpdate" :repostulacion="vacancyInfo.esRepostulacion"></exc>
                  <exp v-if="selectedStep.codigo === 'EXP'" @updateCandidateInfo="statusUpdate" :repostulacion="vacancyInfo.esRepostulacion"></exp>
                  <expg v-if="selectedStep.codigo === 'EXPG'" @updateCandidateInfo="statusUpdate" :repostulacion="vacancyInfo.esRepostulacion"></expg>
                  <seag
                    v-if="selectedStep.codigo === 'SE-AG'"
                    @updateCandidateInfo="statusUpdate"
                    :repostulacion="vacancyInfo.esRepostulacion"
                    :info="{
                      idUsuario: vacancyInfo.idUsuario,
                      masterVacancy: vacancyInfo.esVacanteMaestra,
                    }"
                  ></seag>
                  <seagg
                    v-if="selectedStep.codigo === 'SE-AGG'"
                    :idObservador="vacancyInfo.idObservador"
                    @updateCandidateInfo="statusUpdate"
                    :repostulacion="vacancyInfo.esRepostulacion"
                    :info="{
                      masterVacancy: vacancyInfo.esVacanteMaestra,
                    }"
                  ></seagg>
                  <sere
                    v-if="selectedStep.codigo === 'SE-RE'"
                    :showModal="showModal"
                    :confirmResult="confirmResult"
                    :idEntrevista="vacancyInfo.idCurrentInterview"
                    @updateCandidateInfo="statusUpdate"
                    :repostulacion="vacancyInfo.esRepostulacion"
                  ></sere>
                  <sees v-if="selectedStep.codigo === 'SE-ES'" :repostulacion="vacancyInfo.esRepostulacion"></sees>
                  <seesg
                    v-if="selectedStep.codigo === 'SE-ESG'"
                    :stageInfo="selectedStep"
                    :repostulacion="vacancyInfo.esRepostulacion"
                    @setCurrentStep="changeCurrentFlow"
                  ></seesg>
                  <semr
                    v-if="selectedStep.codigo === 'SE-MR'"
                    :showModal="showModal"
                    :confirmResult="confirmResult"
                    @updateCandidateInfo="statusUpdate"
                    :repostulacion="vacancyInfo.esRepostulacion"
                  ></semr>
                  <semrg
                    v-if="selectedStep.codigo === 'SE-MRG'"
                    :showModal="showModal"
                    :confirmResult="confirmResult"
                    @updateCandidateInfo="statusUpdate"
                    :repostulacion="vacancyInfo.esRepostulacion"
                    :idPreRechazo="vacancyInfo.idPreRechazo"
                  ></semrg>
                  <offer
                    v-if="selectedStep.codigo === 'OF'"
                    :showModal="showModal"
                    :confirmResult="confirmResult"
                    @updateCandidateInfo="statusUpdate"
                    :repostulacion="vacancyInfo.esRepostulacion"
                  ></offer>
                  <fces v-if="selectedStep.codigo === 'FC-ES'"
                    :repostulacion="vacancyInfo.esRepostulacion"
                    :resendNotificationData="{
                      codigo: 'FC',
                      modo: 'GD',
                      idCandidato: prospectId,
                      idReclutamiento: vacancyId}">
                  </fces>
                  <fcrc v-if="selectedStep.codigo === 'FC-RC'" @updateCandidateInfo="statusUpdate" :repostulacion="vacancyInfo.esRepostulacion"></fcrc>
                  <frbu
                    v-if="selectedStep.codigo === 'FR-BU'"
                    :showModal="showModal"
                    :confirmResult="confirmResult"
                    @updateCandidateInfo="statusUpdate"
                    :repostulacion="vacancyInfo.esRepostulacion"
                  ></frbu>
                  <fres v-if="selectedStep.codigo === 'FR-ES'" @updateCandidateInfo="statusUpdate" :repostulacion="vacancyInfo.esRepostulacion"></fres>
                  <fden v-if="selectedStep.codigo === 'FD-EN'" @updateCandidateInfo="statusUpdate" :repostulacion="vacancyInfo.esRepostulacion"></fden>
                  <fdes v-if="selectedStep.codigo === 'FD-ES'" @updateCandidateInfo="statusUpdate" :repostulacion="vacancyInfo.esRepostulacion"></fdes>
                  <fdrc v-if="selectedStep.codigo === 'FD-RC'" @updateCandidateInfo="statusUpdate" :repostulacion="vacancyInfo.esRepostulacion"></fdrc>
                  <es
                    v-if="selectedStep.codigo === 'ES'"
                    :showModal="showModal"
                    :confirmResult="confirmResult"
                    @updateCandidateInfo="statusUpdate"
                    :repostulacion="vacancyInfo.esRepostulacion"
                  ></es>
                  <cnde
                    v-if="selectedStep.codigo === 'CN-DE'"
                    :showModal="showModal"
                    :confirmResult="confirmResult"
                    @updateCandidateInfo="statusUpdate"
                    :repostulacion="vacancyInfo.esRepostulacion"
                  ></cnde>
                  <cndeg
                    v-if="selectedStep.codigo === 'CN-DEG'"
                    :showModal="showModal"
                    :confirmResult="confirmResult"
                    @updateCandidateInfo="statusUpdate"
                    :repostulacion="vacancyInfo.esRepostulacion"
                  ></cndeg>
                  <cnpc v-if="selectedStep.codigo === 'CN-PC'" :msg="selectedStep.signal"></cnpc>
                </div>
              </slot>
            </div>

            <!-- Flow feedback -->
            <div class="flow-feedback">
              <slot v-if="historicalLoader">Cargando...</slot>
              <slot v-if="!historicalLoader">
                <strong class="title">
                  Historial del proceso
                  <b-link role="button" @click="collapse()">
                    <i class="fas" :class="collapseClassLink"></i>
                  </b-link>
                </strong>
                <feedback
                  v-for="(item, idx) in historicalProcess"
                  :key="idx"
                  :item="item"
                  @canEdit="editStep"
                  :class="collapseClassContainer"
                ></feedback>

                <edit-stage
                  v-if="selectedFeedback"
                  title="Completa la información"
                  :selectedStep="selectedFeedback"
                  :close="closeModal"
                  :update="statusUpdate"
                  :showModal="showModal"
                  :confirmResult="confirmEditResult"
                  :info="{
                    idUsuario: vacancyInfo.idUsuario,
                    idConsultor: vacancyInfo.idConsultor,
                    idObservador: vacancyInfo.idObservador,
                    esVacanteMaestra: vacancyInfo.esVacanteMaestra,
                    idEntrevista: vacancyInfo.idCurrentInterview,
                  }"
                ></edit-stage>
              </slot>
            </div>
          </b-col>
          <b-col cols="12" md="4" xl="3" class="opts">
            <!-- Stepper -->
            <div class="stepper" v-if="checkStages()">
              <strong>Proceso de Selección</strong>
              <ul v-for="(group, idx) in availableStages" :key="idx">
                <li
                  v-for="stage in group.stages"
                  :class="{ checked: stage.status === 1, rejected: stage.status === -1 }"
                  :key="stage.code"
                >{{ stage.descripcion }}</li>
              </ul>
            </div>
          </b-col>
        </b-row>
      </div>
    </slot>

    <!-- Confirm modal -->
    <confirm
      :title="confirmOpts.title"
      :content="confirmOpts.content"
      :okTitle="confirmOpts.okTitle"
      :close="confirmOpts.close"
    ></confirm>

    <!-- Score modal -->
    <score-modal
      title="Cambiar calificación"
      :close="closeModal"
      :prospectId="prospectId"
      v-on="$listeners"
    />
  </div>
</template>

<style lang="scss" scoped>
@import "./Vacancy";
</style>

<script>
import _ from 'lodash';
import prospectSrv from '@/services/prospect';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import Ft from './Forms/FT.vue';
import Peag from './Forms/PEAG.vue';
import Peagg from './Forms/PEAGG.vue';
import Pere from './Forms/PERE.vue';
import Exc from './Forms/EXC.vue';
import Exp from './Forms/EXP.vue';
import Expg from './Forms/EXPG.vue';
import Seag from './Forms/SEAG.vue';
import Seagg from './Forms/SEAGG.vue';
import Sees from './Forms/SEES.vue';
import Seesg from './Forms/SEESG.vue';
import Sere from './Forms/SERE.vue';
import Offer from './Forms/OF.vue';
import Semr from './Forms/SEMR.vue';
import Semrg from './Forms/SEMRG.vue';
import Fces from './Forms/FCES.vue';
import Fcrc from './Forms/FCRC.vue';
import Frbu from './Forms/FRBU.vue';
import Fres from './Forms/FRES.vue';
import Fden from './Forms/FDEN.vue';
import Fdes from './Forms/FDES.vue';
import Fdrc from './Forms/FDRC.vue';
import Es from './Forms/ES.vue';
import Cnde from './Forms/CNDE.vue';
import Cndeg from './Forms/CNDEG.vue';
import Cnpc from './Forms/CNPC.vue';
import Feedback from '@/components/Feedback/Feedback.vue';
import Confirm from '@/components/Modal/Confirm.vue';
import EditStage from '@/components/Modal/EditStage.vue';
import ScoreModal from '@/components/Modal/Score.vue';

export default {
  components: {
    SectionTitle,
    Ft,
    Peag,
    Peagg,
    Pere,
    Exc,
    Exp,
    Expg,
    Seag,
    Seagg,
    Sees,
    Seesg,
    Sere,
    Offer,
    Semr,
    Semrg,
    Fces,
    Fcrc,
    Fres,
    Frbu,
    Fden,
    Fdes,
    Fdrc,
    Es,
    Cnde,
    Cndeg,
    Cnpc,
    Feedback,
    Confirm,
    EditStage,
    ScoreModal,
  },

  data() {
    return {
      prospectId: Number(this.$route.params.prospectId),
      vacancyId: Number(this.$route.params.vacancyId),
      currentStep: this.$route.params.step,
      confirmOpts: {},
      confirmResult: null,
      confirmEditResult: null,
      vacancyChecked: false,
      vacancyInfo: {},
      handlerStatus: true,
      selectedStep: null,
      availableSteps: [],
      availableStages: {},
      historicalProcess: [],
      historicalLoader: false,
      selectedFeedback: null,
      isCollapse: true,
    };
  },

  computed: {
    collapseClassLink() {
      return this.isCollapse ? 'fa-chevron-up' : 'fa-chevron-down';
    },

    collapseClassContainer() {
      return this.isCollapse ? 'd-none' : '';
    },
  },

  created() {
    this.initProspect();
  },

  methods: {
    checkStages() {
      return Object.keys(this.availableStages).length > 0;
    },

    stepHandler() {
      this.handlerStatus = !this.handlerStatus;
    },

    initProspect() {
      this.closeModal('edit-stage');
      this.checkVacancyStatus();
    },

    cleanData() {
      this.selectedFeedback = null;
      this.confirmResult = null;
      this.confirmEditResult = null;
      this.vacancyChecked = false;
      this.vacancyInfo = {};
      this.availableSteps = [];
      this.availableStages = {};
      this.historicalProcess = [];
    },

    checkVacancyStatus() {
      this.cleanData();

      return prospectSrv
        .checkVacancyStatus({
          role: JSON.parse(sessionStorage.getItem('user')).idRole,
          idReclutamiento: this.vacancyId,
          step: this.currentStep,
        })
        .then(({ entity }) => {
          this.vacancyChecked = true;
          this.vacancyInfo = entity;
          console.log(JSON.stringify(this.vacancyInfo));
          if (this.vacancyInfo.estado === 'EDITABLE') {
            this.getAvailableOperations(this.vacancyInfo.operaciones);
          } else if (this.vacancyInfo.estado === 'READONLY') {
            this.getAvailableOperations([
              {
                idFase: null,
                description: '',
                codigo: '',
                status: null,
                signal: null,
              },
            ]);
          }
          if (Object.keys(this.vacancyInfo.processMap).length > 0) {
            this.availableStages = this.vacancyInfo.processMap;
          }

          this.historicalLoader = true;
          this.getHistoricalProcess(this.vacancyInfo.history);
        })
        .catch(() => {
          this.$router.push({
            name: 'prospect',
            params: { prospectId: this.prospectId },
          });
        });
    },

    setCurrentFlow(type) {
      const [selected] = this.availableSteps.filter(
        step => step.codigo === type,
      );

      this.selectedStep = selected;
    },

    changeCurrentFlow(type) {
      this.selectedStep = {
        codigo: type,
        descripcion: 'Segundas entrevistas',
      };
    },

    getAvailableOperations(data) {
      window.scrollTo({ top: 0 });
      this.availableSteps = data;
      const [selected] = this.availableSteps;
      this.selectedStep = selected;
    },

    getHistoricalProcess(history) {
      this.historicalProcess = history;
      this.historicalLoader = false;
    },

    statusUpdate() {
      this.initProspect();
    },

    editStep(item) {
      this.selectedFeedback = item;
      setTimeout(() => {
        this.showModal('edit-stage');
      }, 250);
    },

    showModal(modal, customOptions = false) {
      if (modal === 'confirm-dialog') {
        this.confirmOpts = _.merge(
          {},
          {
            title: 'Rechazar Candidato',
            content:
              '¿Deseas rechazar al candidato? Si lo descartas podrás seguirlo viendo en los detalles de la vacante.',
            okTitle: 'Rechazar',
          },
          customOptions,
        );
        this.confirmOpts.close = customOptions
          ? this[customOptions.customCb]
          : this.discardCandidate;
      }

      this.$bvModal.show(modal);
    },

    closeModal(modal, response) {
      this.$bvModal.hide(modal);
      if (response) {
        this.checkVacancyStatus();
      }
    },

    setConfirmResponse(modal, response) {
      this.$bvModal.hide(modal);
      if (response) {
        if (!this.selectedFeedback) {
          this.confirmResult = response;
        } else {
          this.confirmEditResult = response;
        }
      }
    },

    discardCandidate(modal, response) {
      if (response) {
        return prospectSrv
          .discardProspect({
            idReclutamiento: Number(this.vacancyId),
            idUsuario: 0,
            rechazo: true,
          })
          .then(() => {
            this.closeModal('confirm-dialog', true);
            this.$notify({
              type: 'info',
              title: 'Información actualizada',
              text: 'La información del candidato se actualizó correctamente',
            });
            this.showModal('score-modal');
          });
      }

      return this.closeModal('confirm-dialog');
    },

    collapse() {
      this.isCollapse = !this.isCollapse;
    },
  },
};
</script>
