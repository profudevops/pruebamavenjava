<template>
  <div>

    <b-dropdown class="candidate-opts-dropdown" :class="{ talent: true == 1, banned: true == 1, unfit: true == 1, hired: true == 1 }" right>
      <!--
        <template slot="button-content"><i class="fas fa-star"></i> Talento</template>
        <template slot="button-content"><i class="fas fa-ban"></i> Lista negra</template>
        <template slot="button-content"><i class="fas fa-crown"></i> Contratado</template>
      -->
      <template slot="button-content"><i class="fas fa-frown"></i> No apto</template>
      <b-dropdown-item @click="showModal('assign-modal');">Asignar a vacante</b-dropdown-item>
      <b-dropdown-item @click="showModal('recommend-modal');">Recomendar</b-dropdown-item>
      <b-dropdown-item @click="showModal('score-modal');">Cambiar calificación</b-dropdown-item>
    </b-dropdown>
    <assign-modal title="Asignar a Vacante" :close="hideModal" />
    <recommend-modal title="Recomendar a Vacante" :close="hideModal" />
    <score-modal
      title="Cambiar calificación"
      :close="hideModal"
      v-on="$listeners"
    />

  </div>
</template>

<script>
import AssignModal from '@/components/Modal/Assign.vue';
import RecommendModal from '@/components/Modal/Recommend.vue';
import ScoreModal from '@/components/Modal/Score.vue';

export default {
  components: {
    AssignModal,
    RecommendModal,
    ScoreModal,
  },

  methods: {
    showModal(modal) {
      this.$bvModal.show(modal);
    },

    hideModal(modal) {
      this.$bvModal.hide(modal);
    },
  },
};
</script>
