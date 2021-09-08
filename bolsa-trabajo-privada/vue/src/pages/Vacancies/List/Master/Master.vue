<template>
  <div class="d-flex vacancies-list">
    <b-link class="d-flex" v-for="(vacancy, idx) of vacancies" :key="idx" :to="{ name: 'detail-master-vacancy', params: { vacancyId: vacancy.idVacante } }">
      <div>
        <strong>{{ vacancy.tituloVacante }}</strong>
        <span>Total plazas abiertas: <b>{{ vacancy.totalPlazasAbiertas }}</b></span>
        <span>Total procesos: <b>{{ vacancy.totalProcesos }}</b></span>
      </div>
    </b-link>
  </div>
</template>

<style lang="scss" scoped>
@import "./Master.scss";
</style>

<script>
export default {
  props: {
    vacancies: Array,
  },
};
</script>
