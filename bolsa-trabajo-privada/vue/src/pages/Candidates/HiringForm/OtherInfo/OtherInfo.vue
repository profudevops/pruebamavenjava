<template>
  <div>
    <template v-if="!withData">
      <div class="py-4">
        <p class="text-center">El candidato no ha llenado esta información</p>
      </div>
    </template>
    <template v-if="withData">
      <b-form>
        <!-- 1 -->
        <b-form-group label="Ha trabajado en una empresa del mismo giro">
          <b-form-radio
            inline
            name="trabajadoEnMismoGiro"
            :value="true"
            ref="trabajadoEnMismoGiro"
            disabled
            v-model="form.trabajadoEnMismoGiro"
          >Sí</b-form-radio>
          <b-form-radio
            inline
            name="trabajadoEnMismoGiro"
            :value="false"
            ref="trabajadoEnMismoGiro"
            disabled
            v-model="form.trabajadoEnMismoGiro"
          >No</b-form-radio>
        </b-form-group>

        <!-- Name Order -->
        <b-form-group
          label="Cual"
          label-for="trabajadoEnMismoGiroDescripcion"
          v-if="form.trabajadoEnMismoGiro == true"
        >
          <b-form-input
            type="text"
            name="trabajadoEnMismoGiroDescripcion"
            ref="trabajadoEnMismoGiroDescripcion"
            placeholder
            v-model="form.trabajadoEnMismoGiroDescripcion"
            disabled
          ></b-form-input>
        </b-form-group>

        <!-- 2 -->
        <b-form-group label="Ha trabajado en una empresa de Grupo BAL">
          <b-form-radio
            inline
            name="trabajadoEnEmpresaBAL"
            :value="true"
            ref="trabajadoEnEmpresaBAL "
            v-model="form.trabajadoEnEmpresaBAL"
            disabled
          >Sí</b-form-radio>
          <b-form-radio
            inline
            name="trabajadoEnEmpresaBAL"
            :value="false"
            ref="trabajadoEnEmpresaBAL"
            v-model="form.trabajadoEnEmpresaBAL"
            disabled
          >No</b-form-radio>
        </b-form-group>

        <!-- Work Bal Visible -->
        <b-form-group label="Cual" label-for="empresaBAL" v-if="form.trabajadoEnEmpresaBAL == true">
          <b-form-input
            type="text"
            name="empresaBAL"
            ref="empresaBAL"
            placeholder
            v-model="form.empresaBAL"
            disabled
          ></b-form-input>
        </b-form-group>

        <!-- 3 -->
        <b-form-group class="required" label="Familiares o conocidos en grupo BAL">
          <b-form-radio
            inline
            name="familiaresConocidosBAL"
            :value="true"
            ref="familiaresConocidosBAL "
            v-model="form.familiaresConocidosBAL"
            disabled
          >Sí</b-form-radio>
          <b-form-radio
            inline
            name="familiaresConocidosBAL"
            :value="false"
            ref="familiaresConocidosBAL"
            v-model="form.familiaresConocidosBAL"
            disabled
          >No</b-form-radio>
        </b-form-group>

        <!-- Family Bal Visible -->
        <b-form-group
          class="required"
          label="Nombre"
          label-for="familiaresConocidosBALNombre"
          v-if="form.familiaresConocidosBAL == true"
        >
          <b-form-input
            type="text"
            name="familiaresConocidosBALNombre"
            ref="familiaresConocidosBALNombre"
            placeholder
            v-model="form.familiaresConocidosBALNombre"
            disabled
          ></b-form-input>
        </b-form-group>
        <!-- Family Bal Visible -->
        <b-form-group
          class="required"
          label="Apellido Paterno"
          label-for="familiaresConocidosBALApellidoPaterno"
          v-if="form.familiaresConocidosBAL == true"
        >
          <b-form-input
            type="text"
            name="familiaresConocidosBALApellidoPaterno"
            ref="familiaresConocidosBALApellidoPaterno"
            placeholder
            v-model="form.familiaresConocidosBALApellidoPaterno"
            disabled
          ></b-form-input>
        </b-form-group>

        <!-- Family Bal Visible -->
        <b-form-group
          class="required"
          label="Apellido Materno"
          label-for="familiaresConocidosBALApellidoMaterno"
          v-if="form.familiaresConocidosBAL == true"
        >
          <b-form-input
            type="text"
            name="familiaresConocidosBALApellidoMaterno"
            ref="familiaresConocidosBALApellidoMaterno"
            placeholder
            v-model="form.familiaresConocidosBALApellidoMaterno"
            disabled
          ></b-form-input>
        </b-form-group>

        <!-- 4 -->
        <b-form-group
          label="Ha trabajado en alguna empresa relacionada con Seguros y Fianzas o con la Comisión Nacional Bancaria y de Valores"
        >
          <b-form-radio
            inline
            name="trabajadoSegurosFinanzas"
            :value="true"
            ref="trabajadoSegurosFinanzas "
            v-model="form.trabajadoSegurosFinanzas"
            disabled
          >Sí</b-form-radio>
          <b-form-radio
            inline
            name="trabajadoSegurosFinanzas"
            :value="false"
            ref="trabajadoSegurosFinanzas"
            v-model="form.trabajadoSegurosFinanzas"
            disabled
          >No</b-form-radio>
        </b-form-group>

        <!-- Relationship Visible -->
        <b-form-group
          label="Cual"
          label-for="trabajadoSegurosFinanzasDescripcion"
          v-if="form.trabajadoSegurosFinanzas == true"
        >
          <b-form-input
            type="text"
            name="trabajadoSegurosFinanzasDescripcion"
            ref="trabajadoSegurosFinanzasDescripcion"
            placeholder
            v-model="form.trabajadoSegurosFinanzasDescripcion"
            disabled
          ></b-form-input>
        </b-form-group>

        <!-- 5 -->
        <b-form-group
          label="Cuenta con una certificación por parte de la Comisión Nacional de Seguros y Fianzas o de la Comisión Nacional Bancaria y de Valores"
        >
          <b-form-radio
            inline
            name="certificacionConsar"
            disabled
            :value="true"
            ref="certificacionConsar "
            v-model="form.certificacionConsar"
          >Sí</b-form-radio>
          <b-form-radio
            inline
            name="certificacionConsar"
            :value="false"
            ref="certificacionConsar"
            v-model="form.certificacionConsar"
            disabled
          >No</b-form-radio>
        </b-form-group>

        <!-- Certification Visible -->
        <b-form-group
          label="Cual"
          label-for="certificacionConsarDescripcion"
          v-if="form.certificacionConsar == true"
        >
          <b-form-input
            type="text"
            disabled
            name="certificacionConsarDescripcion"
            ref="certificacionConsarDescripcion"
            placeholder
            v-model="form.certificacionConsarDescripcion"
          ></b-form-input>
        </b-form-group>
      </b-form>
    </template>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {},
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
  },
};
</script>
