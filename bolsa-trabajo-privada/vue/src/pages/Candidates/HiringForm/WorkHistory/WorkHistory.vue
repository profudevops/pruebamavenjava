<template>
  <b-form>
    <strong class="section-title-form">Escolaridad</strong>

    <!-- Max Level -->
    <b-form-group label="Nivel máximo de estudios" label-for="nivelEstudios">
      <b-form-input
        type="text"
        name="nivelEstudios"
        ref="nivelEstudios"
        placeholder
        disabled
        v-model="form.nivelEstudiosTexto"
      ></b-form-input>
    </b-form-group>

    <!-- Status -->
    <b-form-group label="Estatus" label-for="estatus">
      <b-form-input
        type="text"
        name="estatus"
        ref="estatus"
        placeholder
        disabled
        v-model="form.estatusTexto"
      ></b-form-input>
    </b-form-group>

    <!-- Career -->
    <b-form-group label="Carrera" label-for="carrera">
      <b-form-input
        type="text"
        name="carrera"
        ref="carrera"
        placeholder
        disabled
        v-model="form.carrera"
      ></b-form-input>
    </b-form-group>
    <strong class="section-title-form">Historia Laboral</strong>
    <!-- Company Name -->
    <b-form-group label="Nombre de la Empresa" label-for="nombreEmpresa">
      <b-form-input
        type="text"
        name="nombreEmpresa"
        ref="nombreEmpresa"
        placeholder
        disabled
        v-model="form.nombreEmpresa"
      ></b-form-input>
    </b-form-group>
    <!-- Function -->
    <b-form-group label="Giro o actividad principal" label-for="actividad">
      <b-form-input
        type="text"
        name="actividad"
        ref="actividad"
        placeholder
        disabled
        v-model="form.actividad"
      ></b-form-input>
    </b-form-group>
    <!-- Boss Name -->
    <b-form-group label="Nombre Jefe Inmediato" label-for="nombreJefeInmediato">
      <b-form-input
        type="text"
        name="nombreJefeInmediato"
        ref="nombreJefeInmediato"
        placeholder
        disabled
        v-model="form.nombreJefeInmediato"
      ></b-form-input>
    </b-form-group>
    <!-- Boss Name -->
    <b-form-group label="Puesto Último" label-for="puestoJefe">
      <b-form-input
        type="text"
        name="puestoJefe"
        ref="puestoJefe"
        placeholder
        disabled
        v-model="form.puestoJefe"
      ></b-form-input>
    </b-form-group>
    <!-- Boss Name -->
    <b-form-group label="Periodo Laborado" label-for="periodoLaborado">
      <b-form-input
        type="text"
        name="periodoLaborado"
        ref="periodoLaborado"
        placeholder
        disabled
        :value="`${form.periodoLaboradoInicio} - ${form.periodoLaboradoFin}`"
      ></b-form-input>
    </b-form-group>

    <!-- work phone -->
    <b-form-group
      class="required"
      label="Télefono"
      label-for="telefono"
      :invalid-feedback="errors.first('telefono')"
      :state="!errors.has('telefono')"
    >
      <b-form-input
        :state="errors.has('telefono') ? false : null"
        type="tel"
        name="telefono"
        ref="telefono"
        placeholder
        disabled
        v-model="form.telefono"
        v-validate="'required|length:14'"
        v-mask="'(##) ####-####'"
      ></b-form-input>
    </b-form-group>

    <!-- Activities -->
    <b-form-group label="Funciones" label-for="functiones">
      <b-form-input
        type="text"
        name="functiones"
        ref="functiones"
        placeholder
        disabled
        v-model="form.funciones"
      ></b-form-input>
    </b-form-group>
  </b-form>
</template>

<script>
import { mask } from 'vue-the-mask';

export default {
  data() {
    return {
      form: {
        nivelEstudios: 'Facultad',
        estatus: 'Titulado',
        carrera: 'Ciencias de la computación',
        nombreEmpresa: 'So Cal Solutions',
        actividad: 'Desarrollo humano',
        nombreJefeInmediato: 'Arturo Ruaz',
        puestoJefe: 'Director General',
        periodoLaborado: '2019',
        telefono: '3320506090',
        funciones: 'Actividades',
      },
    };
  },

  directives: {
    mask,
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
};
</script>
