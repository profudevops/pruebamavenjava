<template>
  <b-form novalidate autocomplete="off" @submit.prevent="validateBeforeSubmit">
    <p>
      El candidato ha terminado de llenar la solicitud de empleo. Revísalo para poder enviarlo.
      <br />
      <b-link @click="showModal('hiring-form-modal')">Ir a la solicitud de empleo</b-link>
    </p>

    <hiring-form title="Revisar información" :close="closeModal"></hiring-form>

    <!-- Continue -->
    <b-form-group
      label="Aceptar la solicitud de empleo"
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
      >Aceptar</b-form-radio>
      <b-form-radio
        :state="errors.has('continuar') ? false : null"
        inline
        name="continuar"
        value="NO"
        ref="continuar"
        v-model="form.continuar"
        v-validate="'required'"
      >Rechazar</b-form-radio>
    </b-form-group>

    <slot v-if="form.continuar === 'NO'">
      <!-- Discard reasons -->
      <b-form-group
        class="required"
        label="Comentarios"
        :invalid-feedback="errors.first('comentario')"
        :state="!errors.has('comentario')"
      >
        <b-form-textarea
          required
          name="comentario"
          placeholder="Motivos de rechazo de formulario"
          rows="4"
          max-rows="6"
          v-model="form.comentario"
        ></b-form-textarea>
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
</template>

<script>
import prospectSrv from '@/services/prospect';
import HiringForm from '@/components/Modal/HiringFormResume.vue';

const dict = {
  custom: {
    continuar: {
      required: 'Selecciona una opción',
    },
    comentario: {
      required: 'El motivo es requerida',
    },
  },
};

export default {
  components: {
    HiringForm,
  },

  props: {
    currentInfo: {
      type: Object,
      required: false,
    },
    repostulacion: Boolean,
  },

  data() {
    return {
      sendingForm: false,
      sendButtonText: 'Validar expediente',
      form: {
        continuar: null,
        comentario: null,
      },
    };
  },

  created() {
    this.$validator.localize('es', dict);
  },

  mounted() {
    if (this.currentInfo && Object.keys(this.currentInfo).length > 0) this.form = this.currentInfo;
  },

  methods: {
    showModal(modal) {
      this.$bvModal.show(modal);
    },

    closeModal(modal) {
      this.$bvModal.hide(modal);
    },

    validateBeforeSubmit() {
      this.$validator.validate().then((result) => {
        if (result) this.handleSubmit();
      });
    },

    handleSubmit() {
      const { vacancyId } = this.$route.params;
      const data = {
        idReclutamiento: Number(vacancyId),
        idUsuario: 0,
        codigoFase: 'FC-RC',
        repostulacion: this.repostulacion,
        fcrc: this.form,
      };
      this.sendButtonText = 'Enviando...';
      this.sendingForm = true;

      return prospectSrv
        .saveRecruitmentInfo(data, 'fcrc')
        .then(() => {
          this.$notify({
            type: 'info',
            title: 'Información actualizada',
            text: 'La información del candidato se actualizó correctamente',
          });
          this.$emit('updateCandidateInfo');
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
          this.sendButtonText = 'Validar expediente';
          this.sendingForm = false;
        });
    },
  },
};
</script>
