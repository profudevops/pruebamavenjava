<template>
  <b-modal id="asign-recutring-vacancy" modal-class="common-modal asign-vacancy">
    <!-- Modal Header -->
    <template slot="modal-header">
      <h5 class="modal-title">Asignar Responsable</h5>
      <button @click="closeModal()" class="close">x</button>
    </template>

    <!-- Modal Content -->
    <template slot="default">
      <b-form novalidate @submit.prevent="validateBeforeSubmit">
        <!-- Observer field -->
        <b-form-group
          class="required"
          label="Responsable a cargo de la vacante"
          label-for="recruiter"
          :invalid-feedback="errors.first('recruiter')"
          :state="!errors.has('recruiter')"
        >
          <div class="select-wrapper">
            <b-form-select
              :state="errors.has('recruiter') ? false : null"
              v-model="form.recruiter"
              name="recruiter"
              ref="recruiter"
              v-validate="'required'"
            >
              <option :value="null" disabled>Nombre del responsable</option>
              <option
                v-for="(recutring, key) in recutringList"
                :key="key"
                :value="recutring.idUsuario"
              >{{ recutring.nombre }} {{ recutring.apellido }}</option>
            </b-form-select>
          </div>
        </b-form-group>
      </b-form>
    </template>

    <!-- Modal Footer -->
    <template slot="modal-footer">
      <b-button @click="validateBeforeSubmit" variant="primary" :disabled="sendingForm">
        {{ formButtonText }}
        <b-spinner v-if="sendingForm" small></b-spinner>
      </b-button>
    </template>
  </b-modal>
</template>

<script>
import { asignVacancy, getRecutrings } from '@/services/vacancies';

const dict = {
  custom: {
    recruiter: {
      required: 'Tienes que seleccionar un responsable',
    },
  },
};

export default {
  data() {
    return {
      formButtonText: 'Asignar Vacante',
      sendingForm: false,
      recutringList: [],
      form: {
        recruiter: null,
      },
    };
  },

  created() {
    this.$validator.localize('es', dict);
    this.getRecutrings();
  },

  props: {
    id: {
      default: null,
    },
  },

  methods: {
    /*
     * Get recutrings
     */
    getRecutrings() {
      getRecutrings().then(({ entity }) => {
        this.recutringList = entity;
      });
    },

    closeModal() {
      this.$bvModal.hide('asign-recutring-vacancy');
    },

    getRecutringName(id) {
      const [recutring] = this.recutringList.filter(
        recutring => recutring.idUsuario === id,
      );

      return `${recutring.nombre}`;
    },

    validateBeforeSubmit() {
      this.$validator.validate().then((result) => {
        if (result) {
          this.submitEvnt(this.form.recruiter, this.id);
        }
      });
    },

    submitEvnt(idEmpleado, idPublicacion) {
      this.formButtonText = 'Asignando Vacante';
      this.sendingForm = true;

      asignVacancy(idEmpleado, idPublicacion).then(() => {
        this.formButtonText = 'Asignar Vacante';
        this.sendingForm = false;

        const name = this.getRecutringName(idEmpleado);

        this.$emit('updateRecutring', name);

        this.$notify({
          type: 'info',
          title: 'Información actualizada',
          text: 'La información del candidato se actualizó correctamente',
        });

        this.$bvModal.hide('asign-recutring-vacancy');
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
