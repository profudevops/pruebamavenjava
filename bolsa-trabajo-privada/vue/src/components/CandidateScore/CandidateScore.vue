<template>
  <div :class="`candidate-score ${ score.tags.length > 0 ? score.icon : '' }`">
    <div class="score">
      <span>Calificación</span>
      <i :class="`fas fa-${ score.icon }`"></i>
      <strong>{{ score.descripcion }}</strong>
    </div>
    <div v-if="score.tags.length > 0" class="status">
      <ul>
        <li v-for="(status, idx) of score.tags" :key="idx">{{ status.descripcion }}</li>
      </ul>
    </div>
  </div>
</template>

<style lang="scss">
@import "./CandidateScore";
</style>

<script>
export default {
  props: {
    score: Object,
  },
};
</script>
