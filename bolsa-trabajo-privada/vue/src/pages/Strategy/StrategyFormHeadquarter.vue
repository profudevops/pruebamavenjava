<template>
  <div class="strategy">
    <!-- Title page -->
    <section-title :title="title" :subtitle="getTitle" />

    <!-- Success card -->
    <SuccessCard
      title="¡Vacante Creada!"
      description="La vacante ha sido creada con éxito"
      v-if="checkTypeView"
    />

    <div v-if="loadingInfo">Obteniendo bolsas disponibles...</div>

    <slot v-if="!loadingInfo">
      <div v-if="!boards">No hay información disponible</div>

      <b-form v-if="boards" novalidate @submit.prevent="validateBeforeSubmit">
        <b-form-group class="required" label="Asignar Observador (cliente)" v-if="showObserver">
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
        <div>
          <strong class="section-title-form">Publicación en bolsas externas</strong>

          <!-- Other publications -->
          <b-form-group>
            <b-form-checkbox-group id="bolsas-externas" v-model="form.bolsasExternas" name="bolsas">
              <b-form-checkbox
                v-for="board in boards"
                :key="board.id"
                :value="board.id"
                inline
              >{{ board.descripcion }}</b-form-checkbox>
            </b-form-checkbox-group>
          </b-form-group>
        </div>

        <!-- Form actions -->
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
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import SuccessCard from '@/components/SuccessCard/SuccessCard.vue';
import AutoSuggest from '@/components/AutoSuggest/AutoSuggest.vue';
import {
  getMasterStrategy,
  createMasterStrategy,
  updateMasterStrategy,
  getExternalJobBoards,
} from '@/services/strategy';

import { withObserver } from '@/services/vacancies';

const dict = {
  custom: {
    observer: {
      required: 'Tienes que seleccionar un observador',
    },
    area: {
      required: 'El area es requirido',
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
      formStatus: false,
      emptyInterviewer: false,
      loadingInfo: false,
      boards: null,
      title: 'Estrategia y Publicación',
      formButtonText: 'Guardar',
      sendingForm: false,
      idRole: null,
      showObserver: false,
      form: {
        bolsasExternas: [],
        visibilidad: '1',
      },
    };
  },

  created() {
    this.$validator.localize('es', dict);
    this.handleStrategy();
    this.setExternalJobBoards();

    this.handleObserver();
  },

  computed: {
    checkTypeView() {
      if (this.$route.name === 'create-master-strategy') {
        return true;
      }

      return false;
    },

    getTitle() {
      return this.form.title || '';
    },
  },

  methods: {
    handleObserver() {
      const { vacancyId, idPublication } = this.$route.params;
      withObserver({ idVacante: vacancyId, idPublicacion: idPublication }).then(
        ({ entity }) => {
          this.showObserver = entity.tieneObservador;
        },
      );
    },

    setExternalJobBoards() {
      this.boards = null;
      this.loadingInfo = true;

      getExternalJobBoards()
        .then(({ entity }) => {
          this.boards = entity;
        })
        .finally(() => {
          this.loadingInfo = false;
        });
    },

    handleStrategy() {
      this.idRole = JSON.parse(sessionStorage.getItem('user')).idRole;

      const { idPublication } = this.$route.params;
      getMasterStrategy(idPublication).then(({ entity }) => {
        this.form = { ...entity };

        if (this.form.numeroEmpleadoObservador) {
          this.setCurrentValue = {
            id: this.form.numeroEmpleadoObservador,
            email: this.form.correoObservador,
          };
        } else {
          this.setCurrentValue = null;
        }
      });

      if (this.$route.name === 'edit-master-strategy') {
        this.title = 'Editar la Estrategia';
      } else {
        this.title = 'Crear la Estrategia';
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
        this.$refs[firstError].$el.scrollIntoView({
          behavior: 'smooth',
        });
      });
    },

    checkInterviewer() {
      if (this.showObserver) {
        if (!this.form.observador) {
          this.emptyInterviewer = true;
          return false;
        }

        this.emptyInterviewer = false;
        return true;
      }

      return true;
    },

    submitForm() {
      this.formStatus = true;
      this.sendingForm = true;
      const actioType = this.$route.name.indexOf('edit-master-strategy') > -1
        ? 'UPDATED'
        : 'CREATED';
      this.formButtonText = actioType === 'CREATED' ? 'Creando ...' : 'Editando...';

      this.updateStrategy(actioType);
    },

    updateStrategy() {
      const { idPublication, idEstrategy } = this.$route.params;

      this.form = {
        ...this.form,
        idPublicacion: idPublication || idEstrategy,
      };

      updateMasterStrategy(this.form).then(() => {
        this.sendToVacancyDetail('UPDATED');
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

      this.sendToDetailVacancy();
    },

    sendToDetailVacancy() {
      this.$router.push({
        name: 'info-master-publication',
        params: {
          vacancyId: this.$route.params.vacancyId,
          idSede: this.$route.params.idSede,
          idPublication: this.$route.params.idPublication,
        },
      });
    },

    getInterviewerValue(data = {}) {
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
  },
};
</script>
