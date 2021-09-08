<template>
  <div class="square-active">
    <!-- common vancancies -->
    <section-title title="Abrir plazas" :subtitle="publicationTitle" />

    <LoadingMsg v-if="loadingInfo" />

    <slot v-if="!loadingInfo">
      <div v-if="!allInfo">No hay información disponible</div>

      <!-- Form -->
      <b-form v-if="allInfo" novalidate @submit.prevent="validateBeforeSubmit">
        <b-form-group
          class="required"
          label="Divisional"
          label-for="divisional"
          :invalid-feedback="errors.first('divisional')"
          :state="!errors.has('divisional')"
        >
          <b-form-select
            :state="errors.has('divisional') ? false : null"
            v-model="extraFields.division"
            name="divisional"
            ref="divisional"
            v-validate="'required'"
            @change="selectField(extraFields.division, 'DIVISION')"
          >
            <option :value="null">Selecciona una división</option>
            <option v-for="item in divisions" :key="item.id" :value="item.id">
              {{
              item.descripcion
              }}
            </option>
          </b-form-select>
        </b-form-group>

        <b-form-group
          class="required"
          label="Regional"
          label-for="region"
          :invalid-feedback="errors.first('region')"
          :state="!errors.has('region')"
        >
          <b-form-select
            :state="errors.has('region') ? false : null"
            v-model="extraFields.region"
            name="region"
            ref="region"
            v-validate="'required'"
            :disabled="!extraFields.division"
            @change="selectField(extraFields.region, 'REGION')"
          >
            <option :value="null">Selecciona una región</option>
            <option v-for="item in regions" :key="item.id" :value="item.id">
              {{
              item.descripcion
              }}
            </option>
          </b-form-select>
        </b-form-group>

        <b-form-group
          class="required"
          label="Gerencia"
          label-for="management"
          :invalid-feedback="errors.first('management')"
          :state="!errors.has('management')"
        >
          <b-form-select
            :state="errors.has('management') ? false : null"
            v-model="extraFields.management"
            name="management"
            ref="management"
            v-validate="'required'"
            :disabled="!extraFields.region"
            @change="selectField(extraFields.management, 'MANAGEMENT')"
          >
            <option :value="null">Selecciona una gerencia</option>
            <option v-for="item in managementList" :key="item.id" :value="item.id">
              {{
              item.descripcion
              }}
            </option>
          </b-form-select>
        </b-form-group>

        <!-- Headquarters -->
        <b-form-group
          class="required"
          label="Sede"
          label-for="headquarter"
          :invalid-feedback="errors.first('headquarter')"
          :state="!errors.has('headquarter')"
        >
          <b-form-select
            :state="errors.has('headquarter') ? false : null"
            v-model="form.headquarter"
            name="headquarter"
            ref="headquarter"
            v-validate="'required'"
            :disabled="!extraFields.management"
          >
            <option :value="null">Nombre de la sede</option>
            <option v-for="item in headquarters" :key="item.id" :value="item.id">
              {{
              item.descripcion
              }}
            </option>
          </b-form-select>
        </b-form-group>

        <!-- Vacancies -->
        <b-form-group
          class="required"
          label="Número de plazas"
          label-for="vacancies"
          v-if="!this.analystId"
          :invalid-feedback="errors.first('vacancies')"
          :state="!errors.has('vacancies')"
        >
          <b-form-input
            :state="errors.has('vacancies') ? false : null"
            type="number"
            name="vacancies"
            ref="vacancies"
            placeholder="Número de plazas a abrir"
            v-model="form.vacancies"
            min="1"
            v-validate="'required|min_value:1'"
          />
        </b-form-group>

        <!-- Analyst -->
        <b-form-group
          v-if="setFormFieldPermission('VACANCIES', 'ASSIGN') || !this.analystId"
          class="required"
          label="Responsable"
          label-for="analyst"
          :invalid-feedback="errors.first('analyst')"
          :state="!errors.has('analyst')"
        >
          <b-form-select
            :state="errors.has('analyst') ? false : null"
            v-model="form.analyst"
            name="analyst"
            ref="analyst"
            v-validate="'required'"
          >
            <option :value="null">Selecciona a un responsable</option>
            <option
              v-for="item in analysts"
              :key="item.idUsuario"
              :value="item.idUsuario"
            >{{ item.nombre }}</option>
          </b-form-select>
        </b-form-group>

        <!-- Form Actions -->
        <div class="form-actions">
          <b-button type="submit" variant="primary" :disabled="sendingForm">
            {{ formButtonText }}
            <b-spinner v-if="sendingForm" small></b-spinner>
          </b-button>
        </div>
      </b-form>
    </slot>
  </div>
</template>

<script>
import setFormFieldPermission from '@/utils/setFormFieldPermission';
import {
  getStates,
  getAnalysts,
  savePublication,
  getPublicationInfo,
  getDivisions,
  getRegional,
  getManagement,
  getHeadquarters,
} from '@/services/vacancies';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

const dict = {
  custom: {
    state: {
      required: 'Tienes que seleccionar un estado',
    },
    headquarter: {
      required: 'Tienes que seleccionar una sede',
    },
    divisional: {
      required: 'Tienes que seleccionar una división',
    },
    region: {
      required: 'Tienes que seleccionar una región',
    },
    management: {
      required: 'Tienes que seleccionar una gerencia',
    },
    vacancies: {
      required: 'Tienes que ingresar el número de plazas',
      min_value: 'Tienes que agregar como mínimo 1 plaza',
    },
    analyst: {
      required: 'Tienes que seleccionar un responsable',
    },
  },
};

export default {
  data() {
    return {
      vacancyId: Number(this.$route.params.vacancyId),
      idPublication: Number(this.$route.params.idPublication),
      loadingInfo: false,
      allInfo: false,
      states: null,
      analysts: null,
      divisions: [],
      regions: [],
      managementList: [],
      headquarters: null,
      formButtonText: 'Abrir Plazas',
      sendingForm: false,
      publicationTitle: null,
      analystId: null,
      extraFields: {
        division: null,
        region: null,
        management: null,
      },
      form: {
        state: null,
        headquarter: null,
        vacancies: null,
        analyst: null,
      },
    };
  },

  created() {
    this.$validator.localize('es', dict);
    this.init();
  },

  components: {
    SectionTitle,
    LoadingMsg,
  },

  methods: {
    setFormFieldPermission,

    init() {
      this.loadingInfo = true;
      this.allInfo = false;

      if (this.$route.query.analystId) {
        this.analystId = this.$route.query.analystId;
      }

      return Promise.all([getStates(), getAnalysts(), getDivisions()])
        .then((res) => {
          const [states, analysts, divisions] = res;

          this.divisions = divisions.entity;
          this.states = states.entity;
          this.analysts = analysts.entity;
          this.allInfo = true;
        })
        .finally(() => {
          this.loadingInfo = false;
        });
    },

    /* Execute event when submit form */
    validateBeforeSubmit() {
      this.$validator.validate().then((result) => {
        if (result) {
          this.submitEvnt();
          return;
        }

        const firstError = Object.keys(this.errors.collect())[0];
        this.$refs[firstError].$el.scrollIntoView({
          behavior: 'smooth',
        });
      });
    },

    selectField(value, type) {
      if (type === 'DIVISION') {
        this.extraFields = {
          ...this.extraFields,
          region: null,
          management: null,
        };
        this.form.headquarter = null;

        getRegional(value).then(({ entity }) => {
          this.regions = entity;
        });
      } else if (type === 'REGION') {
        this.extraFields = {
          ...this.extraFields,
          management: null,
        };
        this.form.headquarter = null;

        getManagement(value).then(({ entity }) => {
          this.managementList = entity;
        });
      } else if (type === 'MANAGEMENT') {
        this.form.headquarter = null;
        getHeadquarters(value).then(({ entity }) => {
          this.headquarters = entity;
        });
      }
    },

    sendToStrategy(idPublication, vacancyId, idSede) {
      this.$router.push({
        name: 'create-master-strategy',
        params: {
          idPublication,
          vacancyId,
          idSede,
        },
      });
    },

    submitEvnt() {
      const form = {
        numeroPlazas: this.analystId ? 1 : Number(this.form.vacancies),
        idAnalista: this.form.analyst,
        idVacante: this.vacancyId,
        idSede: this.form.headquarter,
      };

      this.formButtonText = 'Activando Plazas ...';
      this.sendingForm = true;
      console.log(form);
      savePublication(form)
        .then(({ entity }) => {
          this.$notify({
            type: 'info',
            title: 'Vacante actualizada',
            text: 'Se han abierto las plazas a la vacante',
          });

          this.sendToStrategy(entity.id, form.idVacante, form.idSede);
        })
        .catch(() => {
          this.$notify({
            type: 'error',
            title: 'Error interno',
            text: 'Hubo un error al procesar la solicitud.',
          });
        })
        .finally(() => {
          this.sendingForm = false;
        });
    },
  },
};
</script>
