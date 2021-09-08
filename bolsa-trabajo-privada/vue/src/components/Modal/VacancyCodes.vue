<template>
  <b-modal id="vacancy-codes" modal-class="common-modal vacancy-codes">
    <template slot="modal-header">
      <h5 class="modal-title">Códigos para Bolsas externas</h5>
      <button @click="closeModal()" class="close">
        <i class="fas fa-times"></i>
      </button>
    </template>

    <template slot="default">
      <LoadingMsg v-if="isLoading" />

      <!-- No data -->
      <div v-if="!isLoading && boards.length === 0">
        <div v-if="!isError" class="text-center">
          <p>No hay información disponible</p>
        </div>
        <div v-if="isError" class="text-center">
          <p class="error-msg">¡No se encontró información !</p>
          <p class="error-msg">Por favor vuelve a intentarlo más tarde.</p>
        </div>
      </div>

      <slot v-if="!isLoading && boards.length > 0">
        <div v-if="!boards" class="loading">No se encontró información relacionada.</div>
        <div class="table-responsive">
          <table v-if="boards" class="table b-table text-center">
            <thead>
              <tr>
                <th>Bolsa externa</th>
                <th>Código</th>
                <th>Exportar</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, key) in boards" :key="key">
                <td class="without-light">{{ item.bolsaTrabajo }}</td>
                <td>{{ item.codigo }}</td>
                <td>
                  <b-button variant="light" @click="copyDescription(key)">
                    <i class="fas fa-copy"></i>
                  </b-button>
                  <textarea rows="4" cols="50" :value="item.contenido" :ref="key"></textarea>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </slot>
    </template>

    <template slot="modal-footer">
      <b-button variant="primary" @click="sendToStrategy()">Editar estrategia</b-button>
    </template>
  </b-modal>
</template>

<script>
import { getJobBoardInfo } from '@/services/vacancies';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

export default {
  props: {
    id: {
      type: Number,
    },
    idPublicacion: {
      type: Number,
    },
    isMasterVacancy: {
      type: Boolean,
    },
  },

  components: {
    LoadingMsg,
  },

  data() {
    return {
      isLoading: false,
      isError: false,
      boards: [],
    };
  },

  mounted() {
    this.setJobBoardInfo();
  },

  methods: {
    setJobBoardInfo() {
      this.isLoading = true;
      this.boards = null;

      getJobBoardInfo({
        idPublicacion: this.idPublicacion,
      })
        .then(({ entity }) => {
          this.boards = entity;
        })
        .catch(() => {
          this.isError = true;
        })
        .finally(() => {
          this.isLoading = false;
        });
    },

    copyDescription(ref) {
      const copyText = this.$refs[ref][0];
      copyText.select();
      copyText.setSelectionRange(0, 99999);

      document.execCommand('copy');

      this.$notify({
        type: 'info',
        title: 'Información copiada',
        text: 'La información se ha copiado al portapapeles correctamente',
        duration: 500,
      });
    },

    closeModal() {
      this.$bvModal.hide('vacancy-codes');
    },

    sendToStrategy() {
      this.closeModal();
      this.$router.push({
        name: this.isMasterVacancy ? 'edit-master-strategy' : 'edit-strategy',
        params: { vacancyId: this.id, idEstrategy: this.idPublicacion },
      });
    },
  },
};
</script>
