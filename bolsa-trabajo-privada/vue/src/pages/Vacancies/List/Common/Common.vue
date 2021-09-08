<template>
  <div class="table-responsive mb-3">
    <table class="table b-table text-center">
      <thead>
        <tr>
          <th v-if="'tituloVacante' in vacancies[0]">Vacante</th>
          <th v-if="'idSede' in vacancies[0]">Sede</th>
          <th v-if="'diasActiva' in vacancies[0]">Días Activa</th>
          <th v-if="'candidatos' in vacancies[0]">Candidatos</th>
          <th v-if="'reclutadora' in vacancies[0]">Asignado a</th>
          <th v-if="'estatus' in vacancies[0]">Estatus</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(vacancy, key) in vacancies" :key="key">
          <td class="blue-cell" v-if="'tituloVacante' in vacancy">
            <router-link :to="renderURLVacancy(vacancy)">{{ vacancy.tituloVacante }}</router-link>
          </td>
          <td v-if="'idSede' in vacancy">{{ vacancy.idSede }}</td>
          <td v-if="'diasActiva' in vacancy">{{ vacancy.diasActiva }}</td>
          <td v-if="'candidatos' in vacancy">{{ vacancy.candidatos }}</td>
          <td v-if="'reclutadora' in vacancy">{{ vacancy.reclutadora }}</td>
          <td
            class="without-light"
            v-if="'estatus' in vacancy"
          >{{ vacancy.estatus ? vacancy.estatus: 'N/A' }}</td>
        </tr>
      </tbody>
    </table>

    <!-- Pagination -->
  </div>
</template>

<script>
export default {
  props: {
    vacancies: Array,
  },

  methods: {
    renderURLVacancy({ idPublicacion = null, estatus = '' }) {
      let url = {
        name: 'detail-vacancy',
        params: { idPublication: idPublicacion },
      };

      if (estatus === 'Cerrada') {
        url = {
          ...url,
          query: { isCloser: 1 },
        };
      }

      return url;
    },

    typeStatus(type) {
      switch (type) {
        case 'Expediente':
          return 'red';
        default:
          return 'red';
      }
    },
  },
};
</script>
