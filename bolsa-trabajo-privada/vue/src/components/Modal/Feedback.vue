<template>
  <div class="feedback-modal">
    <slot v-if="loadingInfo">
      <div class="loading">Obteniendo información</div>
    </slot>

    <slot v-if="!loadingInfo">
      <!-- List -->
      <div class="list">
        <ul class="d-flex">
          <li
            v-for="(type, idx) in feedbackTags"
            :key="idx"
            :class="{ [ type.tipo ]: true, selected: checkTypeSelected(type.codigo) }"
            @click="setScoreType(type.codigo)"
          >
            <i :class="`fas fa-${ type.icono }`"></i>
            {{ type.descripcion }}
          </li>
        </ul>
      </div>

      <!-- Tagging -->
      <div v-if="scoreType" class="tagging">
        <span>Agrega un etiquetado al candidato para poder recomendarlos más adelante a otras vacantes.</span>
        <ul>
          <li
            v-for="tag in selectedTags"
            :key="tag.codigo"
            :class="{ selected: checkTagSelected(tag.codigo) }"
            @click="setTag(tag.codigo)"
          >{{ tag.descripcion }}</li>
        </ul>
      </div>

      <!-- Actions -->
      <div class="actions">
        <b-button
          variant="primary"
          :disabled="!scoreType || !scoreTags.length || sendingForm"
          @click="sendScore()"
        >
          {{ sendButtonText }}
          <b-spinner v-if="sendingForm" small></b-spinner>
        </b-button>
      </div>
    </slot>
  </div>
</template>

<script>
import prospectSrv from '@/services/prospect';

export default {
  props: {
    notify: {
      default: true,
      type: Boolean,
    },
    closeFeedback: Function,
    prospectId: Number,
  },

  data() {
    return {
      loadingInfo: true,
      scoreType: null,
      scoreTags: [],
      selectedTags: [],
      feedbackTags: [],
      sendingForm: false,
      sendButtonText: 'Guardar',
    };
  },

  mounted() {
    this.initFeedback();
  },

  methods: {
    resetForm() {
      this.scoreType = null;
      this.scoreTags = [];
      this.sendingForm = false;
      this.sendButtonText = 'Guardar';
    },

    initFeedback() {
      this.loadingInfo = true;
      return prospectSrv
        .getFeedbackTags()
        .then((res) => {
          this.feedbackTags = res;
        })
        .finally(() => {
          this.loadingInfo = false;
        });
    },

    setScoreType(type) {
      this.scoreTags = [];
      this.scoreType = type;
      this.selectedTags = this.feedbackTags.filter(
        e => e.codigo === type,
      )[0].tags;
    },

    setTag(tag) {
      if (!this.scoreTags.includes(tag)) {
        this.scoreTags.push(tag);
      } else {
        this.scoreTags = this.scoreTags.filter(e => e !== tag);
      }
    },

    checkTypeSelected(type) {
      return this.scoreType === type;
    },

    checkTagSelected(tag) {
      return this.scoreTags.includes(tag);
    },

    getMyScoreValues(scoreType, scoreTags) {
      const [score] = this.feedbackTags.filter(e => e.codigo === scoreType);
      const subCalificacion = score.tags
        .filter(e => scoreTags.includes(e.codigo))
        .reduce((oldValue, newValue) => {
          oldValue.push(newValue.id);

          return oldValue;
        }, []);

      return { calificacion: score.id, subCalificacion };
    },

    sendScore() {
      const { calificacion, subCalificacion } = this.getMyScoreValues(
        this.scoreType,
        this.scoreTags,
      );

      const form = {
        calificacion,
        subCalificacion,
        id: this.prospectId,
      };

      this.sendButtonText = 'Enviando...';
      this.sendingForm = true;

      return prospectSrv
        .candidateFeedback(form)
        .then(({ data: { entity } }) => {
          if (this.notify) {
            this.$notify({
              type: 'info',
              title: 'Calificación',
              text: 'La calificación fue actualizada correctamente',
            });
          }

          if (entity) {
            this.$emit('updateCandidateInfo', entity);
          }
        })
        .catch(() => {
          this.$notify({
            type: 'error',
            title: 'Error interno',
            text: 'Hubo un error al procesar la solicitud.',
          });
        })
        .finally(() => {
          this.closeFeedback(true);
          this.resetForm();
        });
    },
  },

  beforeDestroy() {
    this.resetForm();
  },
};
</script>
