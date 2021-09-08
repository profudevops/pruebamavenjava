<template>
  <b-modal id="update-token" modal-class="common-modal confirm-dialog" :no-close-on-backdrop="true" :hide-footer="true">
    <template slot="modal-header">
      <button @click="closeModal()" class="close">
        <i class="fas fa-times"></i>
      </button>
    </template>

    <template slot="default">
      <div class="text-center mb-5">
        Tu sesión esta a punto de expirar en
        <br />
        <strong>{{ remainingTime }}</strong>
        <br />¿Deseas mantener abierta tu sesión?
      </div>
      <div class="d-flex flex-column">
        <b-button variant="primary" class="mb-2" @click="updateToken">Sí, mantener sesión</b-button>
        <b-button variant="transparent" @click="closeSession">Cerrar sesión</b-button>
      </div>
    </template>
  </b-modal>
</template>

<script>
import authSrv from '@/services/auth';

export default {

  props: {
    remainingTime: {
      default: 0,
    },
  },

  methods: {
    closeModal() {
      this.$bvModal.hide('update-token');
    },

    closeSession() {
      this.$emit('closeSession', true);
    },

    updateToken() {
      authSrv.updateToken()
      .then(({ headers }) => {
        const token = headers.token;
        if(token){
          sessionStorage.setItem('token', token);
          console.log("Token renovado: " + token);
          this.$bvModal.hide('update-token');
          this.$emit('updateTime', true);
        }else{
          this.$notify({
            type: 'error',
            title: 'Error interno',
            text: 'Por el momento no es posible renovar la sesión.',
          });
          this.$bvModal.hide('update-token');
          this.$emit('logout');
        }
      })
      .catch(() => {
        this.$notify({
          type: 'error',
          title: 'Error interno',
          text: 'Por el momento no es posible renovar la sesión.',
        });
        this.$bvModal.hide('update-token');
        this.$emit('logout');
      });
    },
  },
};
</script>
