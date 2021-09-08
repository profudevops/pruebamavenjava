<template>
  <div class="create-vacancies">
    <!-- Title page -->
    <section-title :title="title" />

    <b-form novalidate @submit.prevent="validateBeforeSubmit">
      <!-- Title field -->
      <b-form-group
        class="required"
        label="Titulo"
        label-for="title"
        :invalid-feedback="errors.first('title')"
        :state="!errors.has('title')"
      >
        <b-form-input
          :state="errors.has('title') ? false : null"
          type="text"
          name="title"
          ref="title"
          placeholder="Nombre de la vacante"
          v-model="form.tituloVacante"
          v-validate="'required'"
        />
      </b-form-group>

      <!-- Area field -->
      <b-form-group
        class="required"
        label="Área"
        label-for="area"
        :invalid-feedback="errors.first('area')"
        :state="!errors.has('area')"
      >
        <div class="select-wrapper">
          <b-form-select
            :state="errors.has('area') ? false : null"
            v-model="form.idAreaInteres"
            name="area"
            ref="area"
            v-validate="'required'"
            @change="getAllSubInterestArea()"
          >
            <option :value="null">Ingresa el área de la vacante</option>
            <option
              v-for="(area, key) in interestAreas"
              :key="key"
              :value="area.idArea"
            >{{ area.nombre }}</option>
          </b-form-select>
        </div>
      </b-form-group>

      <!-- SubArea field -->
      <b-form-group
        class="required"
        label="Subárea"
        label-for="subarea"
        :invalid-feedback="errors.first('subarea')"
        :state="!errors.has('subarea')"
      >
        <div class="select-wrapper">
          <b-form-select
            :state="errors.has('subarea') ? false : null"
            v-model="form.idSubAreaInteres"
            name="subarea"
            ref="subarea"
            v-validate="'required'"
            :disabled="!subInterestAreas"
          >
            <option :value="null">Ingresa la subárea de la vacante</option>
            <option
              v-for="(area, key) in subInterestAreas"
              :key="key"
              :value="area.idSubArea"
            >{{ area.nombre }}</option>
          </b-form-select>
        </div>
      </b-form-group>

      <!-- URL field video -->
      <b-form-group label="Enlace del video" label-for="urlVideo">
        <b-form-input type="text" name="urlVideo" ref="urlVideo" placeholder v-model="form.video" />
      </b-form-group>

      <!-- Description field -->
      <b-form-group
        label="Descripción"
        label-for="description"
      >
        <b-form-textarea
          v-model="form.descripcion"
          name="description"
          ref="description"
        ></b-form-textarea>
      </b-form-group>

      <!-- Indispensable field -->
      <b-form-group
        class="required"
        label="Indispensable"
        label-for="indispensable"
        :invalid-feedback="errors.first('indispensable')"
        :state="!errors.has('indispensable')"
      >
        <b-form-textarea
          :state="errors.has('indispensable') ? false : null"
          v-model="form.indispensable"
          name="indispensable"
          ref="indispensable"
          v-validate="'required'"
        ></b-form-textarea>
      </b-form-group>

      <!-- About you field -->
      <b-form-group
        class="required"
        label="Funciones"
        label-for="aboutYou"
        :invalid-feedback="errors.first('aboutYou')"
        :state="!errors.has('aboutYou')"
      >
        <b-form-textarea
          :state="errors.has('aboutYou') ? false : null"
          v-model="form.funciones"
          name="aboutYou"
          ref="aboutYou"
          v-validate="'required'"
        ></b-form-textarea>
      </b-form-group>

      <!-- Form actions -->
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
import { es } from 'vuejs-datepicker/dist/locale';

import {
  getMasterVacancy,
  createVacancy,
  updateMasterVacancy,
  getInterestAreas,
  getSubInterestArea,
} from '@/services/vacancies';

const dict = {
  custom: {
    title: {
      required: 'El titulo es requirido',
    },
    area: {
      required: 'El area es requirida',
    },
    subarea: {
      required: 'La súbarea es requirida',
    },
    urlVideo: {
      required: 'La URL del Video es requerida',
    },
    aboutYou: {
      required: 'No has ingresado la información',
    },
    indispensable: {
      required: 'No has ingresado la información',
    },
  },
};

export default {
  components: {
    SectionTitle,
  },

  data() {
    return {
      title: '',
      sendButtonText: 'Crear Vacante Maestra',
      sendingForm: false,
      interestAreas: null,
      subInterestAreas: null,
      datepicker: {
        locale: es,
        disabledDates: {
          to: new Date(),
          days: [6, 0],
        },
      },
      form: {
        title: '',
        area: null,
        startDate: null,
      },
    };
  },

  created() {
    this.$validator.localize('es', dict);
    this.title = this.$route.name === 'create-vacancy'
      ? 'Crear Vacante Maestra'
      : 'Editar Vacante Maestra';

    this.getAllInterestAreas();
    const { vacancyId } = this.$route.params;

    if (vacancyId) {
      this.sendButtonText = 'Editar Vacante Maestra';

      getMasterVacancy(vacancyId).then(({ entity }) => {
        this.form = Object.assign({}, entity, {
          indispensable: entity.indispensable.join('\n'),
          funciones: entity.funciones.join('\n'),
        });

        this.getAllSubInterestArea(true);
      });
    }
  },

  methods: {
    getAllSubInterestArea(edit = false) {
      this.subInterestAreas = null;
      if (!edit) {
        this.form.idSubAreaInteres = null;
      }

      getSubInterestArea({
        idArea: this.form.idAreaInteres,
      }).then(({ entity }) => {
        this.subInterestAreas = entity.subareas;
      });
    },
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

    getAllInterestAreas() {
      getInterestAreas().then(({ entity }) => {
        this.interestAreas = entity;
      });
    },

    handleSubmit: function handleSubmit() {
      this.sendButtonText = 'Creando...';
      this.sendingForm = true;

      const form = {
        ...this.form,
        funciones: this.form.funciones.split('\n'),
        indispensable: this.form.indispensable.split('\n'),
      };

      updateMasterVacancy(form).then(() => {
        this.$router.push({
          name: 'detail-master-vacancy',
          params: { vacancyId: this.$route.params.vacancyId },
        });

        this.$notify({
          type: 'info',
          title: 'Vacante actualizada',
          text: 'La vacante ha sido actualizada correctamente',
        });
      });
    },
  },
};
</script>
