<template>
  <div>
    <slot v-if="loadingFormInfo">
      <div class="loading">Obteniendo información...</div>
    </slot>

    <slot v-if="!loadingFormInfo">
      <div v-if="!formInfo" class="loading">No se encontró información suficiente para continuar.</div>

      <b-form v-if="formInfo" novalidate autocomplete="off" @submit.prevent="validateBeforeSubmit">
        <!-- Interviewer -->
        <b-form-group label="Seleccionar Gerente">
          <auto-suggest
            v-if="setCurrentValue !== -1"
            label-property="email"
            path="vacantes/buscar-observador"
            queryStringParam="emailObservador"
            fieldValidation="El gerente es requerido"
            :currentValue="setCurrentValue"
            :formSubmission="formStatus"
            :emptyField="emptyManager"
            :firstCall="withData"
            @setFieldfValue="getManagerValue"
          ></auto-suggest>
        </b-form-group>

        <!-- Appointment date -->
        <b-form-group
          class="required"
          label="Fecha"
          :invalid-feedback="errors.first('fecha')"
          :state="!errors.has('fecha')"
        >
          <b-input-group>
            <datepicker
              required
              name="fecha"
              placeholder="Seleccionar fecha"
              format="dd MMMM yyyy"
              v-model="form.fecha"
              v-validate="'required'"
              :state="errors.has('fecha') ? false : null"
              :bootstrap-styling="true"
              :disabled-dates="datepicker.disabledDates"
              :language="datepicker.locale"
              @input="getSchedules()"
              :full-month-name="true"
              :calendar-button="true"
              calendar-button-icon="far fa-calendar-alt"
            ></datepicker>
          </b-input-group>
        </b-form-group>

        <div
          v-if="changeMsgHour"
          class="invalid-feedback d-block mb-3"
        >Recuerda que solo puedes programar entrevistas en fechas futuras</div>

        <!-- Appointment hour -->
        <b-form-group
          class="required"
          label="Hora"
          :invalid-feedback="errors.first('hora')"
          :state="!errors.has('hora')"
        >
          <div class="select-wrapper hour-select">
            <b-form-select
              name="hora"
              v-model="form.hora"
              :disabled="horas.length === 0"
              v-validate="'required'"
              :state="errors.has('hora') ? false : null"
            >
              <option :value="null">Seleccionar hora</option>
              <option v-for="hour in horas" :key="hour.id" :value="hour.id">
                {{
                hour.descripcion
                }}
              </option>
            </b-form-select>
          </div>
        </b-form-group>

        <!-- Interview type -->
        <b-form-group
          class="required"
          label="Tipo de entrevista"
          :invalid-feedback="errors.first('tipoEntrevista')"
          :state="!errors.has('tipoEntrevista')"
        >
          <div class="select-wrapper">
            <b-form-select
              name="tipoEntrevista"
              v-model="form.tipoEntrevista"
              v-validate="'required'"
              :state="errors.has('tipoEntrevista') ? false : null"
              @change="changeTypeInterview(form.tipoEntrevista)"
            >
              <option :value="null">Seleccionar tipo de entrevista</option>
              <option v-for="type in formInfo.tipoEntrevista" :key="type.id" :value="type.id">
                {{
                type.descripcion
                }}
              </option>
            </b-form-select>
          </div>
        </b-form-group>
        <slot v-if="checkFaceToFace(form.tipoEntrevista)">
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
              <option
                v-for="item in divisions"
                :key="item.id"
                :value="item.id"
              >{{ item.descripcion }}</option>
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
              <option :value="null">Selecciona una división</option>
              <option v-for="item in regions" :key="item.id" :value="item.id">{{ item.descripcion }}</option>
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
              <option :value="null">Selecciona una división</option>
              <option
                v-for="item in managementList"
                :key="item.id"
                :value="item.id"
              >{{ item.descripcion }}</option>
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
              v-model="form.sede"
              name="headquarter"
              ref="headquarter"
              v-validate="'required'"
              :disabled="!extraFields.management"
            >
              <option :value="null">Nombre de la sede</option>
              <option
                v-for="item in headquarters"
                :key="item.id"
                :value="item.id"
              >{{ item.descripcion }}</option>
            </b-form-select>
          </b-form-group>
        </slot>

        <!-- Form actions -->
        <div class="form-actions">
          <b-button type="submit" variant="primary" :disabled="sendingForm || changeMsgHour">
            {{ sendButtonText }}
            <b-spinner v-if="sendingForm" small></b-spinner>
          </b-button>
        </div>
      </b-form>
    </slot>
  </div>
</template>

<script>
import moment from 'moment';
import Datepicker from 'vuejs-datepicker';
import { es } from 'vuejs-datepicker/dist/locale';
import prospectSrv from '@/services/prospect';
import AutoSuggest from '@/components/AutoSuggest/AutoSuggest.vue';
import {
  getRegional,
  getManagement,
  getHeadquarters,
} from '@/services/vacancies';
import getYesterdayDate from '@/utils/yesterdayDate';

const dict = {
  custom: {
    geqrente: {
      required: 'Selecciona una opción',
    },
    fecha: {
      required: 'La fecha es requerida',
    },
    hora: {
      required: 'La hora es requerida',
    },
    tipoEntrevista: {
      required: 'El tipo de entrevista es requerido',
    },
    sede: {
      required: 'La sede de la entrevista es requerida',
    },
  },
};

export default {
  components: {
    Datepicker,
    AutoSuggest,
  },

  props: {
    info: {
      idUsuario: null,
      masterVacancy: null,
    },
    phase: {
      type: Object,
      default: () => ({
        idEtapa: null,
        idReclutamiento: null,
        codigo: null,
        idEntrevista: null,
      }),
    },
    repostulacion: Boolean,
  },

  data() {
    return {
      setCurrentValue: -1,
      editSchedule: false,
      changeMsgHour: false,
      loadingFormInfo: false,
      formInfo: null,
      emptyManager: false,
      formStatus: false,
      sendingForm: false,
      sendButtonText: 'Agendar cita',
      withData: true,
      divisions: [],
      regions: [],
      managementList: [],
      horas: [],
      headquarters: null,
      datepicker: {
        locale: es,
        disabledDates: {
          to: getYesterdayDate(),
          days: [6, 0],
        },
      },
      extraFields: {
        division: null,
        region: null,
        management: null,
      },
      form: {
        idEntrevistador: null,
        fecha: null,
        hora: null,
        tipoEntrevista: null,
        sede: null,
      },
    };
  },

  created() {
    this.$validator.localize('es', dict);
    console.log('S E A G');
  },

  mounted() {
    this.getFormInfo();
    if (this.phase.idEtapa) {
      this.idFase = this.phase.idEtapa;
      this.getDataByPhase(this.phase);
    } else {
      this.setCurrentValue = null;
    }
  },

  methods: {
    changeTypeInterview(value) {
      if (value == 814) {
        prospectSrv.getDivisions(this.info.masterVacancy).then(({ entity }) => {
          this.divisions = entity;
        });
      }
    },

    getDataByPhase(phase) {
      prospectSrv
        .getInterviewDataByPhase(phase)
        .then(({ entity }) => {
          this.form = { ...entity };
          this.validateDateDiff();

          this.setCurrentValue = {
            id: this.form.idEntrevista,
            email: this.form.correoEntrevistador,
          };
          this.withData = false;

          this.editSchedule = true;

          const { idDivision, idRegional, idGerencia } = entity;

          delete this.form.idDivision;
          delete this.form.idRegional;
          delete this.form.idGerencia;

          this.extraFields = {
            ...this.extraFields,
            division: idDivision,
            region: idRegional,
            management: idGerencia,
          };

          if (this.form.tipoEntrevista == 814) {
            prospectSrv
              .getDivisions(this.info.masterVacancy)
              .then(({ entity }) => {
                this.divisions = entity;
              });

            getRegional(idDivision).then(({ entity }) => {
              this.regions = entity;
            });

            getManagement(idRegional).then(({ entity }) => {
              this.managementList = entity;
            });

            getHeadquarters(idGerencia).then(({ entity }) => {
              this.headquarters = entity;
            });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

    checkFaceToFace(id) {
      if (this.formInfo.tipoEntrevista.length) {
        const [value] = this.formInfo.tipoEntrevista.filter(el => el.id === id);
        if (value) {
          if (value.descripcion === 'Presencial') {
            return true;
          }
        }
      }

      return false;
    },

    validateDateDiff() {
      if (moment(this.form.fecha).diff(moment(new Date()), 'days') < 0) {
        this.changeMsgHour = true;
      } else {
        this.changeMsgHour = false;
      }
    },

    getSchedules() {
      this.validateDateDiff();

      if (this.form.fecha && this.form.idEntrevistador) {
        prospectSrv
          .getSchedules({
            codigoFase: 'SE-AG',
            repostulacion: this.repostulacion,
            fecha: moment(this.form.fecha).format('DD/MM/YYYY'),
            idConsultor: null,
            idUsuario: this.form.idEntrevistador,
            tipoEntrevistador: null,
          })
          .then((schedules) => {
            this.horas = [...schedules];

            if (!this.editSchedule) {
              this.form = {
                ...this.form,
                hora: null,
              };
            } else {
              this.editSchedule = false;
            }
          });
      } else {
        this.setCurrentValue = null;
      }
    },

    getManagerValue(idEntrevistador) {
      if (Object.entries(idEntrevistador).length) {
        this.form.idEntrevistador = idEntrevistador.idUsuario;
        this.getSchedules();
      }
      this.checkManager();
    },

    checkManager() {
      if (!this.form.idEntrevistador) {
        this.form = {
          ...this.form,
          idEntrevistador: this.idObservador,
        };
        this.emptyManager = true;
        return true;
      }

      this.emptyManager = false;
      return true;
    },

    getFormInfo() {
      this.loadingFormInfo = true;

      prospectSrv.getDivisions(this.info.masterVacancy).then(({ entity }) => {
        this.divisions = entity;
      });

      return prospectSrv
        .getFormInfo('SE-AG')
        .then((res) => {
          this.formInfo = res;
        })
        .finally(() => {
          this.loadingFormInfo = false;
        });
    },

    validateBeforeSubmit() {
      this.formStatus = true;
      this.$validator.validate().then((result) => {
        console.log('C H E C K');
        if (this.checkManager()) {
          if (result) this.handleSubmit();
        }
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

    handleSubmit() {
      const { vacancyId } = this.$route.params;
      const seag = {
        ...this.form,
        idEntrevistador: this.form.idEntrevistador
          ? this.form.idEntrevistador.toString()
          : null,
        codigoEtapa: 'SE',
        modo: 'AG',
        idUsuario: this.info.idUsuario,
        fecha: moment(this.form.fecha).format('DD/MM/YYYY'),
      };

      const data = {
        idReclutamiento: Number(vacancyId),
        idUsuario: 0,
        codigoFase: 'SE-AG',
        repostulacion: this.repostulacion,
        seag,
      };

      this.sendButtonText = 'Enviando...';
      this.sendingForm = true;

      return prospectSrv
        .saveRecruitmentInfo(data, 'seag')
        .then(() => {
          this.$notify({
            type: 'info',
            title: 'Información actualizada',
            text: 'La información del candidato se actualizó correctamente',
          });
          this.$emit('updateCandidateInfo');
        })
        .catch((error) => {
          if (error.data) {
            if (error.data.status === 500) {
              this.$notify({
                type: 'error',
                title: 'Aviso',
                text: error.data.statusText,
              });
            }
          }
        })
        .finally(() => {
          this.sendButtonText = 'Agendar cita';
          this.sendingForm = false;
        });
    },
  },
};
</script>
