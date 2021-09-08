<template>
  <b-modal id="edit-stage" modal-class="common-modal edit-stage" hide-footer>
    <template slot="modal-header">
      <h5 class="modal-title">{{ title }}</h5>
      <button @click="closeModal()" class="close">
        <i class="fas fa-times"></i>
      </button>
    </template>

    <template slot="default">
      <slot v-if="!loadingInfo">
        <div class="loading">Obteniendo información...</div>
      </slot>
      <slot v-if="loadingInfo">
        <div v-if="!selectedStageInfo" class="loading">No se encontró información disponible...</div>
        <div v-if="selectedStageInfo">
          <!-- Selected step -->
          <ft
            v-if="selectedStep.codigoFase === 'PE'"
            :currentInfo="selectedStageInfo"
            :showModal="showModal"
            :confirmResult="confirmResult"
            @updateCandidateInfo="update"
          ></ft>
          <peag
            v-if="(selectedStep.codigoFase === 'PE-AG') && selectedStep.idFase"
            :currentInfo="selectedStageInfo"
            :phase="{
              idEtapa: selectedStep.idFase,
              idReclutamiento: selectedStep.idReclutamiento,
              codigo: selectedStep.codigoFase,
              idEntrevista: selectedStep.idEntrevista,
            }"
            @updateCandidateInfo="update"
            :info="{
              idUsuario: info.idUsuario,
              idConsultor: info.idConsultor,
              masterVacancy: info.esVacanteMaestra,
            }"
          ></peag>
          <peagg
            v-if="(selectedStep.codigoFase === 'PE-AGG')"
            :currentInfo="selectedStageInfo"
            :info="{
              masterVacancy: info.esVacanteMaestra,
            }"
            :phase="{
              idEtapa: selectedStep.idFase,
              idReclutamiento: selectedStep.idReclutamiento,
              codigo: selectedStep.codigoFase,
              idEntrevista: selectedStep.idEntrevista,
            }"
            @updateCandidateInfo="update"
          ></peagg>
          <pere
            v-if="selectedStep.codigoFase === 'PE-RE'"
            :idEntrevista="info.idEntrevista"
            :currentInfo="selectedStageInfo"
            :showModal="showModal"
            :confirmResult="confirmResult"
            @updateCandidateInfo="update"
          ></pere>
          <exc
            v-if="selectedStep.codigoFase === 'EXC'"
            :currentInfo="selectedStageInfo"
            @updateCandidateInfo="update"
          ></exc>
          <exp
            v-if="selectedStep.codigoFase === 'EXP'"
            :currentInfo="selectedStageInfo"
            @updateCandidateInfo="update"
          ></exp>
          <seag
            v-if="selectedStep.codigoFase === 'SE-AG'"
            :currentInfo="selectedStageInfo"
            @updateCandidateInfo="update"
            :info="{
              idUsuario: info.idUsuario,
              masterVacancy: info.esVacanteMaestra,
            }"
            :phase="{
              idEtapa: selectedStep.idFase,
              idReclutamiento: selectedStep.idReclutamiento,
              codigo: selectedStep.codigoFase,
              idEntrevista: selectedStep.idEntrevista,
            }"
          ></seag>
          <seagg
            v-if="selectedStep.codigoFase === 'SE-AGG' || selectedStep.codigoFase === 'SE-AGSNR'"
            @updateCandidateInfo="update"
            :isEditForm="true"
            :idObservador="info.idObservador"
            :info="{
              masterVacancy: info.esVacanteMaestra,
            }"
            :phase="{
              idEtapa: selectedStep.idFase,
              idReclutamiento: selectedStep.idReclutamiento,
              codigo: 'SE-AGG',
              idEntrevista: selectedStep.idEntrevista,
            }"
          ></seagg>
          <sere
            v-if="selectedStep.codigoFase === 'SE-RE'"
            :idEntrevista="info.idEntrevista"
            :currentInfo="selectedStageInfo"
            :showModal="showModal"
            :confirmResult="confirmResult"
            @updateCandidateInfo="update"
          ></sere>
          <sees
            v-if="selectedStep.codigoFase === 'SE-ES'"
            :currentInfo="selectedStageInfo"
            @updateCandidateInfo="update"
          ></sees>
          <semr
            v-if="selectedStep.codigoFase === 'SE-MR'"
            :currentInfo="selectedStageInfo"
            :showModal="showModal"
            :confirmResult="confirmResult"
            @updateCandidateInfo="update"
          ></semr>
          <offer
            v-if="selectedStep.codigoFase === 'OF'"
            :currentInfo="selectedStageInfo"
            :showModal="showModal"
            :confirmResult="confirmResult"
            @updateCandidateInfo="update"
          ></offer>
          <fces
            v-if="selectedStep.codigoFase === 'FC-ES'"
            :currentInfo="selectedStageInfo"
            @updateCandidateInfo="update"
          ></fces>
          <fcrc
            v-if="selectedStep.codigoFase === 'FC-RC'"
            :currentInfo="selectedStageInfo"
            @updateCandidateInfo="update"
          ></fcrc>
          <frbu
            v-if="selectedStep.codigoFase === 'FR-BU'"
            :currentInfo="selectedStageInfo"
            @updateCandidateInfo="update"
          ></frbu>
          <fres
            v-if="selectedStep.codigoFase === 'FR-ES'"
            :currentInfo="selectedStageInfo"
            @updateCandidateInfo="update"
          ></fres>
          <fden
            v-if="selectedStep.codigoFase === 'FD-EN'"
            :currentInfo="selectedStageInfo"
            @updateCandidateInfo="update"
          ></fden>
          <fdes
            v-if="selectedStep.codigoFase === 'FD-ES'"
            :currentInfo="selectedStageInfo"
            @updateCandidateInfo="update"
          ></fdes>
          <fdrc
            v-if="selectedStep.codigoFase === 'FD-RC'"
            :currentInfo="selectedStageInfo"
            @updateCandidateInfo="update"
          ></fdrc>
          <es
            v-if="selectedStep.codigoFase === 'ES'"
            :currentInfo="selectedStageInfo"
            :showModal="showModal"
            :confirmResult="confirmResult"
            @updateCandidateInfo="update"
          ></es>
          <cnde
            v-if="selectedStep.codigoFase === 'CN-DE'"
            :currentInfo="selectedStageInfo"
            :showModal="showModal"
            :confirmResult="confirmResult"
            @updateCandidateInfo="update"
          ></cnde>
          <cnpc
            v-if="selectedStep.codigoFase === 'CN-PC'"
            :currentInfo="selectedStageInfo"
            @updateCandidateInfo="update"
          ></cnpc>
        </div>
      </slot>
    </template>
  </b-modal>
</template>

<script>
import prospectSrv from '@/services/prospect';
import Ft from '@/pages/Candidates/Vacancy/Forms/FT.vue';
import Peag from '@/pages/Candidates/Vacancy/Forms/PEAG.vue';
import Peagg from '@/pages/Candidates/Vacancy/Forms/PEAGG.vue';
import Pere from '@/pages/Candidates/Vacancy/Forms/PERE.vue';
import Exc from '@/pages/Candidates/Vacancy/Forms/EXC.vue';
import Exp from '@/pages/Candidates/Vacancy/Forms/EXP.vue';
import Seag from '@/pages/Candidates/Vacancy/Forms/SEAG.vue';
import Seagg from '@/pages/Candidates/Vacancy/Forms/SEAGG.vue';
import Sees from '@/pages/Candidates/Vacancy/Forms/SEES.vue';
import Sere from '@/pages/Candidates/Vacancy/Forms/SERE.vue';
import Offer from '@/pages/Candidates/Vacancy/Forms/OF.vue';
import Semr from '@/pages/Candidates/Vacancy/Forms/SEMR.vue';
import Fces from '@/pages/Candidates/Vacancy/Forms/FCES.vue';
import Fcrc from '@/pages/Candidates/Vacancy/Forms/FCRC.vue';
import Frbu from '@/pages/Candidates/Vacancy/Forms/FRBU.vue';
import Fres from '@/pages/Candidates/Vacancy/Forms/FRES.vue';
import Fden from '@/pages/Candidates/Vacancy/Forms/FDEN.vue';
import Fdes from '@/pages/Candidates/Vacancy/Forms/FDES.vue';
import Fdrc from '@/pages/Candidates/Vacancy/Forms/FDRC.vue';
import Es from '@/pages/Candidates/Vacancy/Forms/ES.vue';
import Cnde from '@/pages/Candidates/Vacancy/Forms/CNDE.vue';
import Cnpc from '@/pages/Candidates/Vacancy/Forms/CNPC.vue';

export default {
  props: {
    title: String,
    selectedStep: Object,
    close: Function,
    update: Function,
    showModal: Function,
    confirmResult: Boolean,
    info: {
      idUsuario: null,
      idConsultor: null,
      idObservador: null,
      idEntrevista: null,
    },
  },

  components: {
    Ft,
    Peag,
    Peagg,
    Pere,
    Exc,
    Exp,
    Seag,
    Seagg,
    Sees,
    Sere,
    Offer,
    Semr,
    Fces,
    Fcrc,
    Frbu,
    Fres,
    Fden,
    Fdes,
    Fdrc,
    Es,
    Cnde,
    Cnpc,
  },

  data() {
    return {
      loadingInfo: false,
      selectedStageInfo: null,
    };
  },

  methods: {
    closeModal() {
      this.close('edit-stage');
    },

    getStageInfo() {
      const idReclutamiento = Number(this.$route.params.vacancyId);
      const { idFase, codigoFase } = this.selectedStep;
      this.clearInfo();

      prospectSrv
        .getStageInfo({ idReclutamiento, idFase, codigoFase })
        .then(({ entity }) => {
          if (entity) {
            this.selectedStageInfo = entity;
          } else {
            this.selectedStageInfo = {};
          }
        })
        .finally(() => {
          this.loadingInfo = true;
        });
    },

    clearInfo() {
      this.loadingInfo = false;
      this.selectedStageInfo = null;
    },
  },

  watch: {
    selectedStep: {
      immediate: true,
      handler(newVal, oldVal) {
        if (!oldVal || newVal.idFase !== oldVal.idFase) this.getStageInfo();
      },
    },
  },
};
</script>
