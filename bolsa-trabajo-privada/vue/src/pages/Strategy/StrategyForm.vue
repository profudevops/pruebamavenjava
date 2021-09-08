<template>
  <div class="strategy">
    <!-- Title page -->
    <section-title :title="title" :subtitle="getTitle" />

    <!-- Success card -->
    <SuccessCard
      v-if="checkTypeView"
      title="¡Vacante Creada!"
      description="La vacante ha sido creada con éxito"
    />

    <b-form novalidate @submit.prevent="validateBeforeSubmit" autocomplete="off">
      <!-- Observer field -->
      <b-form-group class="required" label="Asignar Observador (cliente)">
        <auto-suggest
          v-if="setCurrentValue !== -1"
          label-property="email"
          path="vacantes/buscar-observador"
          queryStringParam="emailObservador"
          fieldValidation="El observador es requerido"
          :currentValue="setCurrentValue"
          :formSubmission="formStatus"
          :emptyField="emptyInterviewer"
          @setFieldfValue="getInterviewerValue"
        ></auto-suggest>
      </b-form-group>
      <!-- Technical review -->
      <b-form-group
        class="required"
        label="Examen de confianza"
        :invalid-feedback="errors.first('trustTest')"
        :state="!errors.has('trustTest')"
      >
        <b-form-radio
          :state="errors.has('trustTest') ? false : null"
          inline
          name="trustTest"
          :value="'1'"
          ref="trustTest "
          v-model="form.examenConfianza"
          v-validate="'required'"
        >Sí</b-form-radio>
        <b-form-radio
          :state="errors.has('trustTest') ? false : null"
          inline
          name="trustTest"
          :value="'0'"
          ref="trustTest"
          v-model="form.examenConfianza"
          v-validate="'required'"
        >No</b-form-radio>
      </b-form-group>

      <!-- Visibility -->
      <b-form-group
        label="Visibilidad"
        :invalid-feedback="errors.first('visibility')"
        :state="!errors.has('visibility')"
      >
        <b-form-radio
          :state="errors.has('visibility') ? false : null"
          inline
          name="visibility"
          :value="'1'"
          ref="visibility"
          v-model="form.visibilidad"
          v-validate="'required'"
        >Pública</b-form-radio>
        <b-form-radio
          :state="errors.has('visibility') ? false : null"
          inline
          name="visibility"
          :value="'0'"
          ref="visibility"
          v-model="form.visibilidad"
          v-validate="'required'"
        >Privada</b-form-radio>
      </b-form-group>

      <strong class="section-title-form">Publicación en bolsas externas</strong>

      <b-form-group>
        <b-form-checkbox-group id="bolsas-externas" v-model="form.bolsasExternas" name="bolsas">
          <b-form-checkbox
            v-for="board in jobBoards"
            :key="board.id"
            :value="board.id"
            inline
          >{{ board.descripcion }}</b-form-checkbox>
        </b-form-checkbox-group>
      </b-form-group>

      <!-- Form actions -->
      <div class="form-actions">
        <b-button type="submit" variant="primary" :disabled="sendingForm">
          {{ formButtonText }}
          <b-spinner v-if="sendingForm" small></b-spinner>
        </b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import SuccessCard from '@/components/SuccessCard/SuccessCard.vue';
import AutoSuggest from '@/components/AutoSuggest/AutoSuggest.vue';

import {
  getStrategy,
  updateStrategy,
  createStrategy,
} from '@/services/strategy';
import { getJobBoardByIdDivision } from '@/services/vacancies';

const dict = {
  custom: {
    observer: {
      required: 'Tienes que seleccionar un observador',
    },
    technicalReview: {
      required: 'Define si el examen técnico es requirido',
    },
    trustTest: {
      required: 'Define si el examen de confianza es requirido',
    },
    visibility: {
      required: 'No has ingresado la visibilidad de la vacante',
    },
  },
};

export default {
  components: {
    SectionTitle,
    SuccessCard,
    AutoSuggest,
  },

  data() {
    return {
      setCurrentValue: -1,
      title: 'Estrategia y Publicación',
      formButtonText: 'Publicar Vacante',
      formStatus: false,
      emptyInterviewer: false,
      sendingForm: false,
      jobBoards: [],
      form: {
        observador: null,
        visibilidad: '1',
        examenConfianza: null,
        bolsasExternas: [],
      },
    };
  },

  created() {
    this.$validator.localize('es', dict);
    this.getCatalogs();
    this.handleStrategy();
  },

  computed: {
    checkTypeView() {
      if (this.$route.name === 'create-strategy') {
        return true;
      }

      return false;
    },

    getTitle() {
      return this.form.title || '';
    },
  },

  methods: {
    /*
     * Get Catalogs 'Bolsas externas' by branch value
     */
    getCatalogs() {
      getJobBoardByIdDivision().then(({ entity }) => {
        this.jobBoards = entity;
      });
    },

    handleStrategy() {
      if (this.$route.name.indexOf('edit') > -1) {
        const { idEstrategy } = this.$route.params;
        getStrategy(idEstrategy).then(({ entity }) => {
          this.form = entity;
          this.form = {
            ...this.form,
            observador: entity.numeroEmpleadoObservador,
          }
          this.setCurrentValue = {
            id: this.form.numeroEmpleadoObservador,
            email: this.form.correoObservador,
          };
        });

        this.title = 'Editar la Estrategia';
        this.formButtonText = 'Editar Estrategia';
      } else {
        this.setCurrentValue = null;
      }
    },

    validateBeforeSubmit() {
      this.formStatus = true;
      this.$validator.validate().then((result) => {
        if (this.checkInterviewer()) {
          if (result) {
            this.submitForm();
            return;
          }
        }

        const firstError = Object.keys(this.errors.collect())[0];
        if (firstError) {
          this.$refs[firstError].$el.scrollIntoView({
            behavior: 'smooth',
          });
        }
      });
    },

    submitForm() {
      this.formButtonText = 'Creando ...';
      this.sendingForm = true;

      let data = {
        ...this.form,
        idVacante: Number(this.$route.params.idPublication),
      };

      if (this.$route.name === 'edit-strategy') {
        data = {
          ...data,
          idPublicacion: Number(this.$route.params.idEstrategy),
        };
        this.reqStrategy(updateStrategy, data, ('UPDATED'));
      } else if (this.$route.name === 'create-strategy') {
        this.reqStrategy(createStrategy, data, ('CREATED'));
      }
    },

    getInterviewerValue(data) {
      if (Object.entries(data).length) {
        this.formStatus = false;
        this.emptyInterviewer = false;
        this.form = { ...this.form, observador: data.idUsuario.toString() };
      } else {
        this.form = {
          ...this.form,
          observador: null,
        };
      }
    },

    checkInterviewer() {
      if (!this.form.observador) {
        this.emptyInterviewer = true;
        return false;
      }

      this.emptyInterviewer = false;
      return true;
    },

    reqStrategy(fn, data = {}, type) {
      fn(data)
        .then(() => {
          this.sendToVacancyDetail(type);
        })
        .catch(() => {
          this.$notify({
            type: 'error',
            title: 'Error interno',
            text: 'Hubo un error al procesar la solicitud.',
          });
        })
        .finally(() => {
          this.formButtonText = 'Editar Estrategia';
          this.sendingForm = false;
        });
    },

    sendToVacancyDetail(actionType) {
      this.$store.commit('vacancy/SET_CURRENT_ACTION', actionType);
      this.$notify({
        type: 'info',
        title:
          actionType === 'CREATED'
            ? 'Estrategía creada'
            : 'Estrategía actualizada',
        text: `La estrategía ha sido ${
          actionType === 'CREATED' ? 'creada' : 'actualizada'
        } correctamente`,
      });

      this.$router.push({
        name: 'detail-vacancy',
        params: { idPublication: this.$route.params.idPublication },
      });
    },
  },
};
</script>
