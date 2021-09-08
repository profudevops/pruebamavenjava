<template>
  <div class="results">
    <div class="d-flex top-table">
      {{ vacancies.tituloVacante }}
      <div class="d-flex">
        <b-button
          v-permission="['VACANCIES_UPDATE']"
          variant="bordered"
          size="sm"
          @click="$router.push({ name: 'edit-master-vacancy', params: { vacancyId: vacancies.idVacante } });"
        >Editar</b-button>
        <b-button
          v-permission="['VACANCIES_ACTIVATE']"
          variant="primary"
          size="sm"
          @click="$router.push({ name: 'square-active', params: { vacancyId: vacancies.idVacante } });"
        >Abrir Plazas</b-button>
      </div>
    </div>
    <slot v-if="vacancies.resumen">
      <div class="table-responsive" v-if="vacancies.resumen.length">
        <table class="table b-table text-center">
          <thead>
            <tr>
              <th v-if="'sede' in vacancies.resumen[0]">Sede</th>
              <th v-if="'plazasAbiertas' in vacancies.resumen[0]">Plazas abiertas</th>
              <th v-if="'plazasCubiertas' in vacancies.resumen[0]">Plazas cubiertas</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, idx) in vacancies.resumen" :key="idx">
              <td v-if="'sede' in item">
                <b-link
                  :to="{ name: 'info-master-vacancy', params: { vacancyId: vacancies.idVacante, idSede: item.idSede } }"
                >{{ item.sede }}</b-link>
              </td>
              <td v-if="'plazasAbiertas' in item">{{ item.plazasAbiertas }}</td>
              <td v-if="'plazasCubiertas' in item">{{ item.plazasCubiertas }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </slot>
    <div class="text-center analyst-blank-box" v-if="!vacancies.resumen.length">
      <p>No hay información disponible</p>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import "./Analyst.scss";
</style>

<script>
export default {
  props: {
    vacancies: Object,
  },
};
</script>
