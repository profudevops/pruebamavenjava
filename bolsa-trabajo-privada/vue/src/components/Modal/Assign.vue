<template>
  <b-modal id="assign-modal" modal-class="common-modal" hide-footer>
    <template slot="modal-header">
      <h5 class="modal-title">{{ title }}</h5>
      <button @click="closeModal()" class="close">
        <i class="fas fa-times"></i>
      </button>
    </template>

    <template slot="default">
      <LoadingMsg v-if="loadingInfo" />

      <slot v-if="!loadingInfo">
        <div v-if="!opportunities" class="loading">No se encontraron vacantes disponibles.</div>

        <b-form
          v-if="opportunities"
          novalidate
          autocomplete="off"
          @submit.prevent="validateBeforeSubmit"
        >
          <!-- Opportunity -->
          <b-form-group
            class="required"
            label="Vacante"
            :invalid-feedback="errors.first('idVacante')"
            :state="!errors.has('idVacante')"
          >
            <div class="select-wrapper">
              <b-form-select
                name="idVacante"
                v-model="form.idVacante"
                v-validate="'required'"
                :state="errors.has('idVacante') ? false : null"
              >
                <option :value="null">Selecciona una de tus vacantes</option>
                <option
                  v-for="opportunity in opportunities"
                  :key="opportunity.id"
                  :value="opportunity.id"
                >{{ opportunity.descripcion }}</option>
              </b-form-select>
            </div>
          </b-form-group>

          <!-- Form actions -->
          <div class="form-actions">
            <b-button type="submit" variant="primary" :disabled="sendingForm">
              {{ sendButtonText }}
              <b-spinner v-if="sendingForm" small></b-spinner>
            </b-button>
          </div>
        </b-form>
      </slot>
    </template>
  </b-modal>
</template>

<script>
import prospectSrv from '@/services/prospect';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

const dict = {
  custom: {
    idVacante: {
      required: 'La vacante es requerida',
    },
  },
};

export default {
  props: {
    title: String,
    close: Function,
    prospectId: Number,
  },

  components: {
    LoadingMsg,
  },

  data() {
    return {
      loadingInfo: true,
      opportunities: [],
      sendingForm: false,
      sendButtonText: 'Asignar a vacante',
      form: {
        idVacante: null,
      },
    };
  },

  created() {
    this.$validator.localize('es', dict);
  },

  mounted() {
    this.init();
  },

  methods: {
    resetForm() {
      this.form.idVacante = null;
      this.sendingForm = false;
      this.sendButtonText = 'Asignar a vacante.';
    },

    init() {
      this.loadingInfo = true;

      return prospectSrv
        .getVacanciesForAssignment()
        .then(({ data }) => {
          this.opportunities = data.entity;
        })
        .finally(() => {
          this.loadingInfo = false;
        });
    },

    validateBeforeSubmit() {
      this.$validator.validate().then((result) => {
        if (result) this.handleSubmit();
      });
    },

    getVacancy(idVacante) {
      const [{ idSede }] = this.opportunities.filter(e => e.id === idVacante);
      return idSede;
    },

    handleSubmit() {
      this.sendButtonText = 'Enviando...';
      this.sendingForm = true;
      this.form.idCandidato = this.prospectId;
      this.form.idSede = this.getVacancy(this.form.idVacante);

      return prospectSrv
        .assignToOpportunity(this.form)
        .then(() => {
          this.$notify({
            type: 'info',
            title: 'Asignación exitosa',
            text: 'El candidato fue asignado a la vacante',
          });
          this.resetForm();
        })
        .catch(() => {
          this.$notify({
            type: 'error',
            title: 'Error interno',
            text: 'Hubo un error al procesar la solicitud.',
          });
        })
        .finally(() => {
          this.closeModal(true);
          this.resetForm();
        });
    },

    closeModal(response = false) {
      this.close('assign-modal', response);
      this.resetForm();
    },
  },

  beforeDestroy() {
    this.resetForm();
  },
};
</script>
