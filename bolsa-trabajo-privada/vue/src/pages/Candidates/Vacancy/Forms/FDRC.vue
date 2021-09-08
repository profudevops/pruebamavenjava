<template>
  <b-form novalidate autocomplete="off" @submit.prevent="handleSubmit">
    <p>El candidato ha terminado de subir todos los documentos requeridos.</p>

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

export default {
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
      sendButtonText: 'Continuar',
      form: {
        continuar: 'SI',
      },
    };
  },

  methods: {
    handleSubmit() {
      const { vacancyId } = this.$route.params;
      const data = {
        idReclutamiento: Number(vacancyId),
        idUsuario: 0,
        codigoFase: 'FD-RC',
        repostulacion: this.repostulacion,
        fdrc: this.form,
      };
      this.sendButtonText = 'Enviando...';
      this.sendingForm = true;

      return prospectSrv
        .saveRecruitmentInfo(data, 'fdrc')
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
          this.sendButtonText = 'Continuar';
          this.sendingForm = false;
        });
    },
  },
};
</script>
