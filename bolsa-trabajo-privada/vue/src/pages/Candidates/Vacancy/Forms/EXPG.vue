<template>
  <b-form novalidate autocomplete="off" @submit.prevent="validateBeforeSubmit">
    <!-- Continue -->
    <b-form-group
      label="Resultado de la entrevista"
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
      >Descartado</b-form-radio>
    </b-form-group>

    <!-- Psychometric -->
    <b-form-group
      class="required"
      label="Resultado de examen"
      :invalid-feedback="errors.first('resultado')"
      :state="!errors.has('resultado')"
    >
      <b-form-input
        required
        type="tel"
        name="resultado"
        v-model="form.resultado"
        v-validate="'required'"
        v-mask="'###'"
        :state="errors.has('resultado') ? false : null"
      ></b-form-input>
    </b-form-group>

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
import { mask } from 'vue-the-mask';
import prospectSrv from '@/services/prospect';

const dict = {
  custom: {
    continuar: {
      required: 'Selecciona una opción',
    },
    resultado: {
      required: 'Resultado requerido',
    },
  },
};

export default {
  directives: {
    mask,
  },

  props: {
    currentInfo: {
      type: Object,
      required: false,
    },
  },

  data() {
    return {
      sendingForm: false,
      sendButtonText: 'Guardar resultado',
      form: {
        continuar: null,
        d: null,
        i: null,
        s: null,
        c: null,
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
        rechazo: this.form.continuar === 'no',
        codigoFase: 'EXP',
        repostulacion: this.repostulacion,
        exp: this.form,
      };
      this.sendButtonText = 'Enviando...';
      this.sendingForm = true;

      return prospectSrv
        .saveRecruitmentInfo(data, 'exp')
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
          this.sendButtonText = 'Guardar resultado';
          this.sendingForm = false;
        });
    },
  },
};
</script>
