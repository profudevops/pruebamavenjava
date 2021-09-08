<template>
  <div>
    <slot v-if="loadingInfo">
      <div class="loading">Obteniendo información...</div>
    </slot>

    <slot v-if="!loadingInfo">
      <p>Tu cliente tiene una entrevista en proceso. Puedes agregar más entrevistas si es necesario.</p>

      <div v-if="interviews.length > 0" class="scheduled-interviews">
        <strong>Entrevistas generadas:</strong>
        <ul>
          <li v-for="(interview, k) of interviews" :key="k">{{ interview }}</li>
        </ul>
      </div>

      <div class="form-actions">
        <b-button variant="primary" @click="setStep()">Agendar nueva entrevista</b-button>
      </div>
    </slot>
  </div>
</template>

<script>
import prospectSrv from '@/services/prospect';

export default {
  props: {
    setCurrentStep: Function,
    stageInfo: Object,
    repostulacion: Boolean,
  },

  data() {
    return {
      interviews: [],
      loadingInfo: false,
    };
  },

  mounted() {
    this.getAvailableInterviews();
  },

  methods: {
    setStep() {
      this.$emit('setCurrentStep', 'SE-AGG');
    },

    getAvailableInterviews() {
      const idReclutamiento = Number(this.$route.params.vacancyId);
      const { idFase, codigo } = this.stageInfo;
      this.loadingInfo = true;

      return prospectSrv
        .getStageInfo({ idReclutamiento, idFase, codigoFase: codigo, repostulacion: this.repostulacion, })
        .then(({ entity }) => {
          this.interviews = entity.entrevistas;
        })
        .finally(() => {
          this.loadingInfo = false;
        });
    },
  },
};
</script>
