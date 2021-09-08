<template>
  <b-form novalidate autocomplete="off">
    <p>
      El candidato tiene el formulario de candidatos abierto, cuando lo termine de llenar podrás
      revisarlo.
    </p>

    <!-- Form actions -->
    <div class="form-actions">
      <b-button variant="primary" :disabled="sendingForm" @click="sendForm('SI')">
        {{ sendButtonText }}
        <b-spinner v-if="sendingForm" small></b-spinner>
      </b-button>
      <b-button variant="bordered" :disabled="sendingForm" @click="sendForm('omitir')">Omitir paso</b-button>
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
      form: {},
    };
  },

  methods: {
    sendForm(value) {
      this.form.continuar = value;
      this.handleSubmit();
    },

    handleSubmit() {
      const { vacancyId } = this.$route.params;
      const data = {
        idReclutamiento: Number(vacancyId),
        idUsuario: 0,
        codigoFase: 'FD-ES',
        repostulacion: this.repostulacion,
        fdes: this.form,
      };
      this.sendButtonText = 'Enviando...';
      this.sendingForm = true;

      return prospectSrv
        .saveRecruitmentInfo(data, 'fdes')
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
