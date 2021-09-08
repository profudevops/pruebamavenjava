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
        label="Niveles Puesto"
        label-for="positionLevels"
        :invalid-feedback="errors.first('positionLevels')"
        :state="!errors.has('positionLevels')"
      >
        <div class="select-wrapper">
          <b-form-select
            :state="errors.has('positionLevels') ? false : null"
            v-model="form.idNivelPuesto"
            name="positionLevels"
            ref="positionLevels"
            v-validate="'required'"
          >
            <option :value="null">Ingresa nivel de puesto</option>
            <option
              v-for="(el, key) in positionLevels"
              :key="key"
              :value="el.id"
            >{{ el.descripcion }}</option>
          </b-form-select>
        </div>
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

      <!-- Date fields -->
      <b-form-row v-if="form.tipo !== 'vacante-maestra'">
        <!-- Start date field -->
        <b-col>
          <b-form-group
            class="required"
            label="Fecha Inicio"
            :invalid-feedback="errors.first('startDate')"
            :state="!errors.has('startDate')"
          >
            <b-input-group>
              <datepicker
                required
                name="startDate"
                placeholder
                format="dd MMMM yyyy"
                v-model="form.fechaInicio"
                v-validate="'required'"
                :state="errors.has('startDate') ? false : null"
                :bootstrap-styling="true"
                :disabled-dates="disableDatesStartDate()"
                :language="datepicker.locale"
                :full-month-name="true"
                :calendar-button="true"
                calendar-button-icon="far fa-calendar-alt"
                :disabled="form.fechaInicio && $route.params.idPublication ? true : false"
              ></datepicker>
            </b-input-group>
          </b-form-group>
        </b-col>
        <!-- End date field -->
        <b-col>
          <b-form-group label="Fecha Fin">
            <b-input-group @mouseover="hoverPicker()" @mouseleave="hoverPicker(false)">
              <datepicker
                required
                name="endDate"
                placeholder
                format="dd MMMM yyyy"
                v-model="form.fechaFin"
                :bootstrap-styling="true"
                :disabled-dates="disableDatesEndDate()"
                :language="datepicker.locale"
                :full-month-name="true"
                :calendar-button="(!hover || !form.fechaFin)"
                calendar-button-icon="far fa-calendar-alt"
              ></datepicker>
              <b-input-group-append>
                <i
                  class="fas fa-times"
                  v-if="hover && form.fechaFin"
                  @click="cleanDatePicker"
                  role="button"
                ></i>
              </b-input-group-append>
            </b-input-group>
          </b-form-group>
        </b-col>
      </b-form-row>

      <!-- Salary range fields -->
      <b-form-row>
        <b-col>
          <b-form-group
            class="required"
            label="Rango de sueldo"
            label-for="range"
            :invalid-feedback="errors.first('range')"
            :state="!errors.has('range')"
          >
            <b-input-group class="prepend">
              <b-input-group-prepend>
                <i class="fas fa-dollar-sign"></i>
              </b-input-group-prepend>
              <b-form-input
                :state="errors.has('range') ? false : null"
                v-model="form.sueldoMin"
                name="range"
                ref="range"
                type="number"
                v-validate="'required|min_value:1'"
              />
            </b-input-group>
          </b-form-group>
        </b-col>
        <b-col class="d-flex align-items-end">
          <b-form-group
            class="required w-100"
            label
            label-for="rangeMax"
            :invalid-feedback="errors.first('rangeMax')"
            :state="!errors.has('rangeMax')"
          >
            <b-input-group class="prepend">
              <b-input-group-prepend>
                <i class="fas fa-dollar-sign"></i>
              </b-input-group-prepend>
              <b-form-input
                name="rangeMax"
                ref="rangeMax"
                type="number"
                v-model="form.sueldoMax"
                v-validate="(form.sueldoMin) ? 'min_value:' + form.sueldoMin: ''"
                :state="errors.has('rangeMax') ? false : null"
              />
            </b-input-group>
          </b-form-group>
        </b-col>
      </b-form-row>

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
import moment from 'moment';
import Datepicker from 'vuejs-datepicker';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import { es } from 'vuejs-datepicker/dist/locale';
import { mask } from 'vue-the-mask';
import {
  getVacancy,
  createVacancy,
  updateVacancy,
  getInterestAreas,
  getSubInterestArea,
  getPositionLevel,
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
    aboutYou: {
      required: 'No has ingresado la información',
    },
    indispensable: {
      required: 'No has ingresado la información',
    },
    startDate: {
      required: 'No has ingresado la fechan inicial',
    },
    range: {
      required: 'No has ingresado el rango',
    },
    positionLevels: {
      required: 'No has ingresado el nivel de puesto',
    },
    rangeMax: {
      required: 'No has ingresado el rango',
      min_value: 'El rango máximo es menor al mínimo',
    },
  },
};

export default {
  components: {
    SectionTitle,
    Datepicker,
  },

  directives: {
    mask,
  },

  data() {
    return {
      title: '',
      hover: false,
      interestAreas: null,
      subInterestAreas: null,
      sendButtonText: 'Crear Vacante',
      sendingForm: false,
      positionLevels: [],
      datepicker: {
        locale: es,
        disabledDates: {
          to: new Date(),
          days: [6, 0],
        },
      },
      price: 0,
      money: {
        decimal: ',',
        thousands: '.',
        prefix: '$ ',
        precision: 2,
        masked: false,
      },
      form: {
        idAreaInteres: null,
        idSubAreaInteres: null,
        fechaInicio: null,
        fechaFin: null,
        idNivelPuesto: null,
      },
    };
  },

  created() {
    this.$validator.localize('es', dict);
    this.title = this.$route.name === 'create-vacancy'
      ? 'Crear Vacante'
      : 'Editar Vacante';
    this.getAllInterestAreas();

    const { idPublication } = this.$route.params;

    this.getPositionLevel();

    if (idPublication) {
      this.sendButtonText = 'Editar Vacante';

      getVacancy(idPublication).then(({ entity }) => {
        this.form = Object.assign({}, entity, {
          indispensable: entity.indispensable.join('\n'),
          funciones: entity.funciones.join('\n'),
        });

        if (this.form.fechaInicio) {
          this.form.fechaInicio = moment(
            this.form.fechaInicio,
            'DD/MM/YYYY',
          ).format('MM/DD/YYYY');
        }

        if (this.form.fechaFin) {
          this.form.fechaFin = moment(this.form.fechaFin, 'DD/MM/YYYY').format(
            'MM/DD/YYYY',
          );
        }

        this.getAllSubInterestArea(true);
      });
    }
  },

  methods: {
    cleanDatePicker() {
      this.form = {
        ...this.form,
        fechaFin: null,
      };
    },

    hoverPicker(value = true) {
      this.hover = value;
    },

    getAllInterestAreas() {
      getInterestAreas().then(({ entity }) => {
        this.interestAreas = entity;
      });
    },

    getPositionLevel() {
      getPositionLevel().then(({ entity }) => {
        this.positionLevels = [...entity];
      });
    },

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

    validateMaxSalary() {
      if (this.form.sueldoMin > this.form.sueldoMax) {
        return 'required';
      }

      return false;
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

    disableDatesStartDate() {
      const now = new Date();
      const defaultDate = now - 1000 * 60 * 60 * 24 * 1;
      let dateConstraint = {
        to: new Date(defaultDate),
        days: [6, 0],
      };

      if (this.form.fechaFin) {
        dateConstraint = {
          ...dateConstraint,
          from: new Date(this.form.fechaFin),
        };
      }

      return dateConstraint;
    },

    disableDatesEndDate() {
      return {
        to: new Date(this.form.fechaInicio) || new Date(),
        days: [6, 0],
      };
    },

    handleSubmit() {
      this.sendButtonText = 'Creando...';
      this.sendingForm = true;

      const data = Object.assign({}, this.form, {
        indispensable: this.form.indispensable.split('\n'),
        funciones: this.form.funciones.split('\n'),
        fechaInicio: moment(this.form.fechaInicio).format('DD/MM/YYYY'),
        fechaFin: this.form.fechaFin
          ? moment(this.form.fechaFin).format('DD/MM/YYYY')
          : null,
      });

      if (this.$route.params.idPublication) {
        updateVacancy(data).then(() => {
          this.$router.push({
            name: 'detail-vacancy',
            params: { idPublication: this.$route.params.idPublication },
          });

          this.$notify({
            type: 'info',
            title: 'Vacante actualizada',
            text: 'La vacante ha sido actualizada correctamente',
          });
        })
          .catch(() => {
            this.$notify({
              type: 'error',
              title: 'Error interno',
              text: 'Hubo un error al procesar la solicitud.',
            });
          });
      } else {
        createVacancy(data).then(({ data: { entity } }) => {
          this.$router.push({
            name: 'create-strategy',
            params: { idPublication: entity.id },
          });

          this.$notify({
            type: 'info',
            title: 'Vacante creada',
            text: 'La vacante ha sido creada correctamente',
          });
        })
          .catch(() => {
            this.$notify({
              type: 'error',
              title: 'Error interno',
              text: 'Hubo un error al procesar la solicitud.',
            });
          });
      }
    },
  },
};
</script>
