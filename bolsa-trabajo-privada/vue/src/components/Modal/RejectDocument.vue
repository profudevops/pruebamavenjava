<template>
  <b-modal id="reject-document-modal" modal-class="common-modal" hide-footer>
    <template slot="modal-header">
      <h5 class="modal-title">{{ title }}</h5>
      <button @click="closeModal()" class="close"><i class="fas fa-times"></i></button>
    </template>

    <template slot="default">
      <b-form novalidate autocomplete="off" @submit.prevent="handleSubmit">

        <p>Selecciona un motivo para rechazar el documento. El candidato podrá subirlo de nuevo para validación.</p>

        <!-- Reasons -->
        <b-form-group label="Razón para rechazar el documento">
          <b-form-checkbox-group v-model="form.reasons" name="reasons">
            <b-form-checkbox value="bad-format">Mal formato</b-form-checkbox>
            <b-form-checkbox value="not-legible">Ilegible</b-form-checkbox>
            <b-form-checkbox value="wrong-content">Contenido incorrecto</b-form-checkbox>
          </b-form-checkbox-group>
        </b-form-group>

        <!-- Form actions -->
        <div class="form-actions">
          <b-button type="submit" variant="primary" :disabled="form.reasons.length === 0 || sendingForm">
            {{ sendButtonText }}
            <b-spinner v-if="sendingForm" small></b-spinner>
          </b-button>
        </div>

      </b-form>
    </template>

  </b-modal>

</template>

<script>
import prospectSrv from '@/services/prospect';

export default {
  props: {
    title: String,
    close: Function,
    document: Object,
  },

  data() {
    return {
      sendingForm: false,
      sendButtonText: 'Rechazar',
      form: {
        reasons: [],
        name: 'morpheus',
        job: 'leader',
      },
    };
  },

  methods: {
    resetForm() {
      this.form.reasons = [];
      this.sendingForm = false;
      this.sendButtonText = 'Rechazar';
    },

    handleSubmit() {
      this.sendButtonText = 'Enviando...';
      this.sendingForm = true;
      this.form.document = this.document;

      return prospectSrv.rejectDocument(this.form)
        .then(() => {
          this.closeModal(true);
          this.$notify({
            type: 'info',
            title: 'Documento rechazado',
            text: 'El documento ha sido rechazado',
          });
        })
        .catch(() => {
          console.log('Error');
        })
        .finally(() => {
          this.resetForm();
        });
    },

    closeModal(response = false) {
      this.close('reject-document-modal', response);
      this.resetForm();
    },
  },
};
</script>
