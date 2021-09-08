<template>
  <div>
    <!-- Title page -->
    <section-title :title="title" />

    <b-form novalidate @submit.prevent="validateBeforeSubmit">
      <b-form-group
        class="required"
        label="Nombre Área"
        label-for="nombre"
        :invalid-feedback="errors.first('nombre')"
        :state="!errors.has('nombre')"
      >
        <b-form-input
          :state="errors.has('nombre') ? false : null"
          type="text"
          name="nombre"
          ref="nombre"
          placeholder
          v-model="form.nombre"
          v-validate="'required'"
        ></b-form-input>
      </b-form-group>

      <b-form-group
        class="required"
        label="Descripción"
        label-for="descripcion"
        :invalid-feedback="errors.first('descripcion')"
        :state="!errors.has('descripcion')"
      >
        <b-form-textarea
          :state="errors.has('descripcion') ? false : null"
          v-model="form.descripcion"
          name="descripcion"
          ref="descripcion"
          v-validate="'required'"
        ></b-form-textarea>
      </b-form-group>

      <Subareas
        v-if="!loading"
        :idArea="getIdArea()"
        :subareas="form.currentSubareas"
        @addSubarea="addSubarea"
        @editSubarea="editSubarea"
        @deleteSubarea="deleteSubarea"
      />

      <div class="form-actions">
        <b-button type="submit" variant="primary" :disabled="sendingForm">
          {{ sendButtonText }}
          <b-spinner v-if="sendingForm" small></b-spinner>
        </b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import Subareas from '@/pages/Areas/Subareas/Subareas.vue';

import areaReq from '@/services/areas.js';

const dict = {
  custom: {
    nombre: {
      required: 'El nombre del área es requerido',
    },
    descripcion: {
      required: 'La descripción del área es requerida',
    },
  },
};

export default {
  components: {
    SectionTitle,
    Subareas,
  },

  created() {
    this.$validator.localize('es', dict);

    if (this.$route.name.indexOf('edit') > -1) {
      this.title = 'Editar Área';
      this.sendButtonText = 'Editar Área';

      this.getArea();
    } else {
      this.loading = false;
    }
  },

  data() {
    return {
      title: 'Agregar Área',
      sendButtonText: 'Agregar Área',
      sendingForm: false,
      loading: true,
      form: {
        nombre: '',
        descripcion: '',
        subareas: [],
        currentSubareas: [],
      },
    };
  },

  methods: {
    getIdArea() {
      return this.$route.params.id;
    },

    addSubarea(areas) {
      this.form = Object.assign(this.form, {
        subareas: [...areas],
      });
    },

    editSubarea({ key, value }) {
      const list = this.form.subareas.map((subarea, index) => {
        if (index === key) {
          subarea.nombre = value;
        }

        return subarea;
      });

      this.form = Object.assign({}, this.form, {
        subareas: list,
      });
    },

    deleteSubarea(key) {
      const list = [...this.form.subareas];
      list.splice(key, 1);

      this.form = Object.assign({}, this.form, {
        subareas: list,
      });
    },

    getArea() {
      this.loading = true;

      areaReq.getArea(this.$route.params.id).then(({ data: { entity } }) => {
        this.form = { ...entity };

        this.form = Object.assign(this.form, {
          currentSubareas: this.form.subareas,
          subareas: [],
        });
        this.loading = false;
      });
    },

    validateBeforeSubmit() {
      this.$validator.validate().then((result) => {
        if (result) {
          this.handleSubmit();
        }
      });
    },

    handleSubmit() {
      if (this.$route.name.indexOf('edit') > -1) {
        areaReq
          .updateArea(this.form)
          .then((res) => {
            this.$router.push({ name: 'areas-list' });
          })
          .catch(() => {
            this.$notify({
              type: 'error',
              title: 'Error',
              text:
                'Ha ocurrido un error inesperado, por favor intentalo más tarde',
            });
          });
      } else {
        areaReq
          .createArea(this.form)
          .then((res) => {
            this.$router.push({ name: 'areas-list' });
          })
          .catch(() => {
            this.$notify({
              type: 'error',
              title: 'Error',
              text:
                'Ha ocurrido un error inesperado, por favor intentalo más tarde',
            });
          });
      }
    },
  },
};
</script>
