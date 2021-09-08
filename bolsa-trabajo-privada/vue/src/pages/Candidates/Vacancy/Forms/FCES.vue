<template>
  <div>
    <p>
      El candidato tiene la solicitud de empleo sin terminar, cuando lo termine de llenar podrás revisarlo.
    </p>
    <b-button variant="primary" @click="resendNotification()">
      Reenviar notificación
    </b-button>
  </div>
</template>

<script>
import prospectService from '@/services/prospect';


export default {

  props: {
    resendNotificationData: Object
  },

  data() {
    return {

    };
  },

  methods: {

    resendNotification() {
      prospectService.resendFormNotification(this.resendNotificationData.codigo, this.resendNotificationData.modo, 
        this.resendNotificationData.idCandidato, this.resendNotificationData.idReclutamiento)
      .then((data) => {
        if(data.success){
          this.$notify({
            type: 'info',
            title: 'Información enviada',
            text: 'La notificación ha sido enviada al candidato.',
          });
        }
      })
      .catch(() => {
          this.$notify({
            type: 'error',
            title: 'Error interno',
            text: 'Hubo un error al procesar la solicitud.',
          });
        });
    },

  },
};
</script>
