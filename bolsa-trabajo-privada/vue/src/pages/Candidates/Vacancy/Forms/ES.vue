<template>
  <div>
    <slot v-if="loadingReasons">
      <div class="loading">Obteniendo información...</div>
    </slot>

    <slot v-if="!loadingReasons">
      <div
        v-if="!discardReasons"
        class="loading"
      >No se encontró información suficiente para continuar.</div>

      <b-form
        v-if="discardReasons"
        novalidate
        autocomplete="off"
        @submit.prevent="validateBeforeSubmit"
      >
        <!-- Continue -->
        <b-form-group
          label="Resultado del estudio socioeconómico"
          :invalid-feedback="errors.first('continuar')"
          :state="!errors.has('continuar')"
        >
          <b-form-radio
            :state="errors.has('continuar') ? false : null"
            inline
            name="continuar"
            value="SI"
            ref="continuar"
            v-model="form.continuar"
            v-validate="'required'"
          >Aprobado</b-form-radio>
          <b-form-radio
            :state="errors.has('continuar') ? false : null"
            inline
            name="continuar"
            value="NO"
            ref="continuar"
            v-model="form.continuar"
            v-validate="'required'"
          >No Aprobado</b-form-radio>
          <b-form-radio
            :state="errors.has('continuar') ? false : null"
            inline
            name="continuar"
            value="n/a"
            ref="continuar"
            v-model="form.continuar"
            v-validate="'required'"
          >N/A</b-form-radio>
        </b-form-group>

        <slot v-if="form.continuar === 'NO'">
          <!-- Discard reasons -->
          <b-form-group
            class="required"
            label="Motivos"
            :invalid-feedback="errors.first('motivoRechazo')"
            :state="!errors.has('motivoRechazo')"
          >
            <div class="select-wrapper">
              <b-form-select
                name="motivoRechazo"
                v-model="form.motivoRechazo"
                v-validate="'required'"
                :state="errors.has('motivoRechazo') ? false : null"
              >
                <option :value="null">Seleccionar motivo</option>
                <option v-for="(reason, idx) in discardReasons" :key="idx" :value="reason.id">
                  {{
                  reason.descripcion
                  }}
                </option>
              </b-form-select>
            </div>
          </b-form-group>
        </slot>

        <!-- Form actions -->
        <div class="form-actions">
          <b-button type="submit" variant="primary" :disabled="sendingForm">
            {{ sendButtonText }}
            <b-spinner v-if="sendingForm" small></b-spinner>
          </b-button>
        </div>
      </b-form>
    </slot>
  </div>
</template>

<script>
import prospectSrv from '@/services/prospect';

const dict = {
  custom: {
    continuar: {
      required: 'Selecciona una opción',
    },
    motivoRechazo: {
      required: 'El motivo es requerida',
    },
  },
};

export default {
  props: {
    currentInfo: {
      type: Object,
      required: false,
    },
    confirmResult: Boolean,
    repostulacion: Boolean,
    showModal: Function,
  },

  data() {
    return {
      loadingReasons: false,
      discardReasons: [],
      sendingForm: false,
      sendButtonText: 'Guardar resultado',
      form: {
        continuar: null,
        motivoRechazo: null,
      },
    };
  },

  created() {
    this.$validator.localize('es', dict);
  },

  mounted() {
    if (this.currentInfo && Object.keys(this.currentInfo).length > 0) this.form = this.currentInfo;
    this.getDiscardReasons();
  },

  methods: {
    getDiscardReasons() {
      this.loadingReasons = true;

      return prospectSrv
        .getDiscardReasons('ES')
        .then(({ entity }) => {
          this.discardReasons = entity;
        })
        .finally(() => {
          this.loadingReasons = false;
        });
    },

    validateBeforeSubmit() {
      this.$validator.validate().then((result) => {
        if (result) {
          if (this.form.continuar === 'no') {
            this.showModal('confirm-dialog', {
              okTitle: 'Descartar',
              customCb: 'setConfirmResponse',
            });
          }
          if (this.form.continuar !== 'no') this.handleSubmit();
        }
      });
    },

    handleSubmit() {
      const { vacancyId } = this.$route.params;
      const data = {
        idReclutamiento: Number(vacancyId),
        idUsuario: 0,
        rechazo: this.form.continuar === 'no',
        codigoFase: 'ES',
        repostulacion: this.repostulacion,
        es: this.form,
      };
      this.sendButtonText = 'Enviando...';
      this.sendingForm = true;

      return prospectSrv
        .saveRecruitmentInfo(data, 'es')
        .then(() => {
          this.$notify({
            type: 'info',
            title: 'Información actualizada',
            text: 'La información del candidato se actualizó correctamente',
          });
          this.$emit('updateCandidateInfo');
          if (this.form.continuar === 'no') this.showModal('score-modal');
        })
        .catch((error) => {
          if (error.data) {
            if (error.data.status === 500) {
              this.$notify({
                type: 'error',
                title: 'Error interno',
                text: error.data.statusText,
              });
            }
          }
        })
        .finally(() => {
          this.sendButtonText = 'Guardar resultado';
          this.sendingForm = false;
        });
    },
  },

  watch: {
    confirmResult() {
      this.handleSubmit();
    },
  },
};
</script>
