<template>
  <b-modal id="score-modal" modal-class="common-modal score-modal" hide-footer>
    <template slot="modal-header">
      <h5 class="modal-title">{{ title }}</h5>
      <button @click="closeModal()" class="close">
        <i class="fas fa-times"></i>
      </button>
    </template>

    <template slot="default">
      <p>Elige una nueva calificación para el candidato.</p>
      <candidate-feedback
        @updateCandidateInfo="updateCandidateInfo"
        :prospectId="prospectId"
        :closeFeedback="closeModal"
        v-on="$listeners"
      />
    </template>
  </b-modal>
</template>

<script>
import CandidateFeedback from '@/components/Modal/Feedback.vue';

export default {
  props: {
    title: String,
    close: Function,
    prospectId: Number,
  },

  components: {
    CandidateFeedback,
  },

  methods: {
    closeModal(response = false) {
      this.close('score-modal', response);
    },

    updateCandidateInfo(value) {
      console.log('estoy actualizando la calificación');
      this.$emit('updateCandidateInfo', value);
    },
  },
};
</script>
