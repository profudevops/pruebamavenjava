<template>
  <b-modal id="recommend-modal" modal-class="common-modal" hide-footer>
    <template slot="modal-header">
      <h5 class="modal-title">{{ title }}</h5>
      <button @click="closeModal()" class="close">
        <i class="fas fa-times"></i>
      </button>
    </template>

    <template slot="default">
      <slot v-if="loadingInfo">
        <div class="loading">Obteniendo información...</div>
      </slot>

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
            :invalid-feedback="errors.first('idPublicacion')"
            :state="!errors.has('idPublicacion')"
          >
            <div class="select-wrapper">
              <b-form-select
                name="idPublicacion"
                v-model="form.idPublicacion"
                v-validate="'required'"
                :state="errors.has('idPublicacion') ? false : null"
              >
                <option :value="null">Selecciona vacante a recomendar</option>
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

const dict = {
  custom: {
    idPublicacion: {
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

  data() {
    return {
      loadingInfo: true,
      opportunities: [],
      sendingForm: false,
      sendButtonText: 'Recomendar a vacante',
      form: {
        idPublicacion: null,
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
      this.form.idPublicacion = null;
      this.sendingForm = false;
      this.sendButtonText = 'Recomendar a vacante';
    },

    init() {
      this.loadingInfo = true;

      return prospectSrv
        .getVacanciesForRecommendation()
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

    getVacancy(idPublicacion) {
      const [{ idSede }] = this.opportunities.filter(
        e => e.id === idPublicacion,
      );
      return idSede;
    },

    handleSubmit() {
      this.sendButtonText = 'Enviando...';
      this.sendingForm = true;
      this.form.idCandidato = this.prospectId;
      this.form.idSede = this.getVacancy(this.form.idPublicacion);

      return prospectSrv
        .recommendToOpportunity(this.form)
        .then(() => {
          this.$notify({
            type: 'info',
            title: 'Recomendación enviada',
            text: 'El candidato fue recomendado a la vacante',
          });
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
      this.close('recommend-modal', response);
      this.resetForm();
    },
  },

  beforeDestroy() {
    this.resetForm();
  },
};
</script>
