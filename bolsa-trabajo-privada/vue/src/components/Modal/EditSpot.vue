<template>
  <b-modal id="edit-spot" modal-class="common-modal edit-spot">
    <template slot="modal-header">
      <h5 class="modal-title">Editar número de plazas</h5>
      <button @click="closeModal()" class="close">
        <i class="fas fa-times"></i>
      </button>
    </template>

    <template slot="default">
      <b-form novalidate @submit.prevent="validateBeforeSubmit">
        <b-form-group
          class="required"
          label="Número de plazas"
          label-for="plazas"
          :invalid-feedback="errors.first('plazas')"
          :state="!errors.has('plazas')"
        >
          <b-form-input
            :state="errors.has('plazas') ? false : null"
            type="number"
            name="plazas"
            ref="plazas"
            placeholder="Número de plazas a abrir"
            v-model="form.plazas"
            min="1"
            v-validate="'required|min_value:1'"
          />
        </b-form-group>
      </b-form>
    </template>

    <template slot="modal-footer">
      <b-button variant="primary" @click="validateBeforeSubmit()">Editar plazas</b-button>
    </template>
  </b-modal>
</template>

<script>
import { getJobBoardInfo, editSpot } from '@/services/vacancies';


const dict = {
  custom: {
    plazas: {
      required: 'El número de plazas es requerido',
      min_value: 'Tienes que agregar como mínimo 1 plaza',
    },
  },
};

export default {
  props: {
    currentySpot: {
      type: Number,
    },
    idPublicacion: {
      type: Number,
    },
  },

  data() {
    return {
      loadingInfo: false,
      boards: null,
      form: {
        plazas: 0,
      },
    };
  },

  created() {
    this.$validator.localize('es', dict);

    this.form = {
      ...this.form,
      plazas: this.currentySpot,
    };
  },

  methods: {
    validateBeforeSubmit() {
      this.$validator.validate().then((result) => {
        if (result) {
          this.handleSubmit();
          return;
        }

        const firstError = Object.keys(this.errors.collect())[0];
        this.$refs[firstError].$el.scrollIntoView({
          behavior: 'smooth',
        });
      });
    },

    handleSubmit() {
      editSpot({
        numeroPlazas: this.form.plazas,
        idPublicacion: this.idPublicacion,
      })
        .then(() => {
          this.$notify({
            type: 'info',
            title: 'Plazas editadas',
            text: 'Se han editado las plazas de la vacante',
            duration: 500,
          });

          this.$emit('updateSpot', this.form.plazas);
          this.closeModal();
        })
        .catch(() => {
          this.$notify({
            type: 'error',
            title: 'Error interno',
            text: 'Hubo un error al procesar la solicitud.',
          });
        });
    },

    closeModal() {
      this.$bvModal.hide('edit-spot');
    },
  },
};
</script>
