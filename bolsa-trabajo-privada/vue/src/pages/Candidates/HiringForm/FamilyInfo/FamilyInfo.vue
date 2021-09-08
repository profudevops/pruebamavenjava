<template>
  <div>
    <template v-if="!withData">
      <div class="py-4">
        <p class="text-center">El candidato no ha llenado sus datos familiares</p>
      </div>
    </template>
    <template v-if="withData">
      <b-form>
        <strong class="section-title-form">Contacto en caso de emergencia</strong>

        <!-- Name -->
        <b-form-group label="Nombre" label-for="nombre">
          <b-form-input
            type="text"
            name="nombre"
            ref="nombre"
            placeholder
            v-model="form.nombre"
            disabled
          ></b-form-input>
        </b-form-group>

        <!-- Relationship -->
        <b-form-group label="Parentesco" label-for="parentesco">
          <b-form-input
            type="text"
            name="parentesco"
            ref="parentesco"
            placeholder
            v-model="form.parentesco"
            disabled
          ></b-form-input>
        </b-form-group>

        <!-- Telephone -->
        <b-form-group label="Teléfono Particular" label-for="telefonoParticular">
          <b-form-input
            type="tel"
            name="telefonoParticular"
            ref="telefonoParticular"
            placeholder
            v-model="form.telefonoParticular"
            v-validate="'length:14'"
            v-mask="'(##) ####-####'"
            disabled
          ></b-form-input>
        </b-form-group>

        <!-- Cellphone -->
        <b-form-group label="Teléfono Celular" label-for="telefonoCelular">
          <b-form-input
            type="tel"
            name="telefonoCelular"
            ref="telefonoCelular"
            placeholder
            v-model="form.telefonoCelular"
            v-validate="'required|length:14'"
            v-mask="'(##) ####-####'"
            disabled
          ></b-form-input>
        </b-form-group>

        <!-- Beneficiaries -->
        <div class="section-subtitle">
          <strong>Beneficiarios</strong>
        </div>

        <!-- Fields -->
        <div v-for="(beneficiary, key) in form.beneficiarios" :key="key">
          <div class="section-subtitle d-flex justify-content-between align-items-center">
            <strong>Beneficiario {{ key + 1 }}</strong>
          </div>

          <!-- Name -->
          <b-form-group label="Nombre" :label-for="`nombre_${key}`">
            <b-form-input
              type="text"
              :name="`nombre_${key}`"
              :ref="`nombre_${key}`"
              placeholder
              v-model="beneficiary.nombre"
              disabled
            ></b-form-input>
          </b-form-group>

          <!-- First Name -->
          <b-form-group label="Apellido Paterno" :label-for="`apellidoPaterno_${key}`">
            <b-form-input
              type="text"
              :name="`apellidoPaterno_${key}`"
              :ref="`apellidoPaterno_${key}`"
              placeholder
              v-model="beneficiary.apellidoPaterno"
              disabled
            ></b-form-input>
          </b-form-group>

          <!-- Last Name -->
          <b-form-group label="Apellido Paterno" :label-for="`apellidoMaterno_${key}`">
            <b-form-input
              type="text"
              :name="`apellidoMaterno_${key}`"
              :ref="`apellidoMaterno_${key}`"
              placeholder
              v-model="beneficiary.apellidoMaterno"
              disabled
            ></b-form-input>
          </b-form-group>

          <!-- Relationship -->
          <b-form-group label="Parentesco" :label-for="`parentesco_${key}`">
            <b-form-input
              type="text"
              :name="`parentesco_${key}`"
              :ref="`parentesco_${key}`"
              v-model="beneficiary.parentesco"
              disabled
            ></b-form-input>
          </b-form-group>
        </div>
      </b-form>
    </template>
  </div>
</template>

<script>
import { mask } from 'vue-the-mask';

export default {
  directives: {
    mask,
  },

  data() {
    return {
      form: {
        beneficiarios: [],
      },
      withData: true,
    };
  },

  props: {
    info: {
      type: Object,
      default: () => ({}),
    },
  },

  created() {
    this.form = { ...this.info };

    if (!this.form.idParentesco) {
      this.withData = false;
    }
  },
};
</script>
