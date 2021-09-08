<template>
  <div class="vacancy-resume" @click="goToResume(info.idReclutamiento)">
    <div class="name">{{ info.tituloVacante }} <em>{{ info.fecha }}</em></div>
    <div class="details">Fase final: <span>{{ info.ultimaEtapaAlcanzada }}</span></div>
  </div>
</template>

<style lang="scss" scoped>
@import './VacancyResume';
</style>


<script>
export default {
  props: {
    prospectId: Number,
    info: Object,
  },

  methods: {
    goToResume(vacancyId) {
      const { prospectId } = this;
      this.$router.push({ name: 'prospect-vacancy-detail', params: { prospectId, vacancyId, step: 0 } });
    },
  },
};
</script>
