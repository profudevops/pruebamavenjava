<template>
  <div>
    <b-form>
      <strong class="section-title-form">Datos personales</strong>
      <!-- First Surname -->
      <b-form-group label="Apellido Paterno" label-for="apellidoPaterno">
        <b-form-input
          type="text"
          name="apellidoPaterno"
          ref="apellidoPaterno"
          placeholder
          disabled
          v-model="form.apellidoPaterno"
        ></b-form-input>
      </b-form-group>

      <!-- Second Surname -->
      <b-form-group label="Apellido Materno" label-for="apellidoMaterno">
        <b-form-input
          type="text"
          name="apellidoMaterno"
          ref="apellidoMaterno"
          placeholder
          disabled
          v-model="form.apellidoMaterno"
        ></b-form-input>
      </b-form-group>

      <!-- Name -->
      <b-form-group label="Nombres" label-for="nombre">
        <b-form-input
          type="text"
          name="nombre"
          ref="nombre"
          placeholder
          v-model="form.nombre"
          disabled
        ></b-form-input>
      </b-form-group>

      <!-- Gender -->
      <b-form-group class="required" label="Género">
        <b-form-radio
          inline
          name="idGenero"
          value="M"
          ref="idGenero "
          v-model="form.genero"
          disabled
        >Femenino</b-form-radio>
        <b-form-radio
          inline
          name="idGenero"
          value="H"
          ref="idGenero"
          v-model="form.genero"
          disabled
        >Masculino</b-form-radio>
      </b-form-group>

      <!-- CURP -->
      <b-form-group label="CURP" label-for="curp">
        <b-form-input type="text" name="curp" ref="curp" placeholder v-model="form.curp" disabled></b-form-input>
      </b-form-group>

      <!-- RFC -->
      <b-form-group label="RFC" label-for="rfc">
        <b-form-input type="text" name="rfc" ref="rfc" placeholder v-model="form.rfc" disabled></b-form-input>
      </b-form-group>

      <!-- NSS -->
      <b-form-group label="Número de Seguro Social" label-for="nss">
        <b-form-input type="text" name="nss" ref="nss" placeholder v-model="form.nss" disabled></b-form-input>
      </b-form-group>

      <strong class="section-title-form">Datos de contacto</strong>

      <!-- Cellphone Number -->
      <b-form-group label="Teléfono celular" label-for="telefonoCelular">
        <b-form-input
          type="tel"
          name="telefonoCelular"
          ref="telefonoCelular"
          placeholder
          v-model="form.telefonoCelular"
          v-mask="'(##) ####-####'"
          disabled
        ></b-form-input>
      </b-form-group>

      <!-- Personal Phone -->
      <b-form-group label="Teléfono particular" label-for="telefonoParticular">
        <b-form-input
          type="tel"
          name="telefonoParticular"
          ref="telefonoParticular"
          placeholder
          v-model="form.telefonoParticular"
          v-mask="'(##) ####-####'"
          disabled
        ></b-form-input>
      </b-form-group>

      <!-- Email -->
      <b-form-group label="Correo electrónico" label-for="email">
        <b-form-input
          type="email"
          name="email"
          ref="email"
          placeholder
          v-model="form.email"
          disabled
        ></b-form-input>
      </b-form-group>

      <strong class="section-title-form">Dirección</strong>

      <!-- Stret -->
      <b-form-group label="Calle" label-for="calle">
        <b-form-input
          type="text"
          name="calle"
          ref="calle"
          placeholder
          v-model="form.calle"
          disabled
        ></b-form-input>
      </b-form-group>

      <!-- Exterior Number -->
      <b-form-group label="No Exterior" label-for="numExt">
        <b-form-input
          type="text"
          name="numExt"
          ref="numExt"
          placeholder
          v-model="form.numExt"
          disabled
        ></b-form-input>
      </b-form-group>

      <!-- Interior Number -->
      <b-form-group label="No Interior" label-for="numInt">
        <b-form-input
          type="text"
          name="numInt"
          ref="numInt"
          placeholder
          v-model="form.numInt"
          disabled
        ></b-form-input>
      </b-form-group>

      <!-- Neighborhood -->
      <b-form-group label="Colonia" label-for="colonia">
        <b-form-input
          type="text"
          name="colonia"
          ref="colonia"
          placeholder
          v-model="form.colonia"
          disabled
        ></b-form-input>
      </b-form-group>

      <!-- Municipality -->
      <b-form-group label="Alcaldia / Municipio" label-for="alcaldia">
        <b-form-input
          type="text"
          name="alcaldia"
          ref="alcaldia"
          placeholder
          v-model="form.alcaldia"
          disabled
        ></b-form-input>
      </b-form-group>

      <!-- Residence -->
      <b-form-group label="Estado" label-for="idEstado">
        <b-form-input
          type="text"
          name="idEstado"
          ref="idEstado"
          placeholder
          v-model="form.estado"
          disabled
        ></b-form-input>
      </b-form-group>

      <!-- Postal Code -->
      <b-form-group label="Código Postal" label-for="cp">
        <b-form-input type="text" name="cp" ref="cp" placeholder v-model="form.cp" disabled></b-form-input>
      </b-form-group>
    </b-form>
  </div>
</template>

<script>
import { mask } from 'vue-the-mask';
import { mapGetters } from 'vuex';

export default {
  directives: {
    mask,
  },

  data() {
    return {
      form: {},
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
  },

  computed: {
    ...mapGetters(['mexicanStates']),
  },
};
</script>
