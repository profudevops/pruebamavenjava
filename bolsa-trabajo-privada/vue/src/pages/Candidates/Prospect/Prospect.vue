<template>
  <div>
    <LoadingMsg v-if="isLoading" />

    <!-- No data -->
    <div v-if="!isLoading && !prospectInfo && !isError" class="text-center">
      <p class="error-msg text-center">No hay información del proceso disponible</p>
    </div>

    <div v-if="isError" class="text-center">
      <p class="error-msg">¡No se encontró información del Candidato!</p>
      <p class="error-msg">Por favor vuelve a intentarlo más tarde.</p>
    </div>

    <!-- Candidate data -->
    <div v-if="!isLoading && prospectInfo && !isError" class="candidate-data">
      <b-row>
        <b-col cols="12" xl="9" md="8">
          <!-- Section title -->
          <section-title :title="prospectInfo.nombre"></section-title>

          <!-- Topic -->
          <div class="topic">
            <strong>Datos personales</strong>

            <div class="personal-data">
              <ul>
                <li>
                  <b-form-group v-show="!isEditingEmail">
                    <span>Correo:</span>
                    {{ prospectInfo.correo }}
                    <i class="fas fa-pencil-alt fa-lg" v-on:click="activeEmailEdition" aria-hidden="true"></i>
                  </b-form-group>
                  <b-form-group v-show="isEditingEmail">
                    <span>Correo:</span>
                    <div class="email-edition">
                      <div class="email-edition-input">
                        <b-form-input
                          type="email"
                          name="email"
                          ref="email"
                          v-model="newEmail"
                          :value="newEmail"
                        ></b-form-input>
                        <i class="fa fa-check fa-2x icon" v-on:click="updateEmail" aria-hidden="true"></i>
                        <i class="fa fa-times fa-2x icon" v-on:click="deactiveEmailEdition" aria-hidden="true"></i>
                      </div>
                    </div>
                  </b-form-group>
                </li>
                <li x-ms-format-detection="none">
                  <span>Teléfono:</span>
                  {{ prospectInfo.telefono | phoneNumber }}
                </li>
                <li>
                  <span>Fecha nacimiento:</span>
                  {{ prospectInfo.fechaNacimiento }}
                </li>
                <li v-if="prospectInfo.estado">
                  <span>Estado:</span>
                  {{ prospectInfo.estado }}
                </li>
                <li v-if="prospectInfo.municipio">
                  <span>Municipio:</span>
                  {{ prospectInfo.municipio }}
                </li>
              </ul>
            </div>
          </div>

          <!-- Topic -->
          <slot v-if="prospectHistorical.actuales">
            <div class="topic" v-if="prospectHistorical.actuales.length">
              <strong>Procesos actuales</strong>
              <vacancy-resume
                v-for="(details, idx) of prospectHistorical.actuales"
                :key="idx"
                :prospectId="prospectId"
                :info="details"
              ></vacancy-resume>
            </div>
          </slot>

          <template>
            <div
              v-if="!prospectHistorical.actuales.length || prospectHistorical.actuales === null"
            >Actualmente no existen procesos actuales</div>
          </template>

          <!-- Topic -->
          <slot v-if="prospectHistorical.anteriores">
            <div class="topic" v-if="prospectHistorical.anteriores.length">
              <strong>Procesos anteriores anteriores</strong>
              <vacancy-resume
                v-for="(details, idx) of prospectHistorical.anteriores"
                :key="idx"
                :prospectId="prospectId"
                :info="details"
              ></vacancy-resume>
            </div>
          </slot>
        </b-col>
        <b-col cols="12" xl="3" sm="4" class="opts">
          <!-- Candidate score -->
          <candidate-score :score="candidateScore" v-if="Number(candidateScore.id) > 0"></candidate-score>

          <!-- Download resume -->
          <div class="download-resume">
            <a href="javascript:void(0);" @click="downloadResume()">
              <i class="far fa-file"></i>
              Descargar CV
            </a>
          </div>

          <!-- Other actions -->
          <div class="other-actions" v-if="['ROLE_COM_INT', 'ROLE_CRP_OBS'].indexOf(idRole) < 0">
            <strong>Otras Acciones</strong>
            <b-button
              v-if="!prospectHistorical.actuales.length && prospectInfo.contratado == false"
              variant="primary"
              block
              @click="showModal('assign-modal');"
            >Asignar a vacante</b-button>
            <b-button
              v-if="!prospectHistorical.actuales.length && prospectInfo.contratado == false"
              variant="primary"
              block
              @click="showModal('recommend-modal');"
            >Recomendar a vacante</b-button>
            <b-button
              variant="primary"
              block
              @click="showModal('score-modal');"
            >Cambiar calificación</b-button>
            <assign-modal
              v-if="currentModal === 'assign-modal'"
              title="Asignar a Vacante"
              :close="hideModal"
              :prospectId="prospectId"
            />
            <recommend-modal
              v-if="currentModal === 'recommend-modal'"
              title="Recomendar a Vacante"
              :close="hideModal"
              :prospectId="prospectId"
            />
            <score-modal
              v-if="currentModal === 'score-modal'"
              title="Cambiar calificación"
              :close="hideModal"
              @updateCandidateInfo="updateCandidateInfo"
              :prospectId="prospectId"
              v-on="$listeners"
            />
          </div>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import "./Prospect";
</style>

<script>
import axios from 'axios';
import downloadLink from '@/utils/downloadLink';
import prospectSrv from '@/services/prospect';
import phoneNumber from '@/filters/phone-number-format';
import base64toBlob from '@/utils/base64toBlob';
import getExtensionFieldByString from '@/utils/getExtensionFieldByString';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import VacancyResume from '@/components/VacancyResume/VacancyResume.vue';
import CandidateScore from '@/components/CandidateScore/CandidateScore.vue';
import AssignModal from '@/components/Modal/Assign.vue';
import RecommendModal from '@/components/Modal/Recommend.vue';
import ScoreModal from '@/components/Modal/Score.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

export default {
  filters: {
    phoneNumber,
  },

  components: {
    SectionTitle,
    CandidateScore,
    VacancyResume,
    AssignModal,
    RecommendModal,
    ScoreModal,
    LoadingMsg,
  },

  data() {
    return {
      isLoading: true,
      currentModal: null,
      prospectId: Number(this.$route.params.prospectId),
      prospectInfo: null,
      prospectHistorical: null,
      candidateScore: {
        icon: '',
        descripcion: '',
        tags: [],
      },
      isError: false,
      idRole: null,
      isEditingEmail: false,
      newEmail: '',
    };
  },

  created() {
    this.idRole = JSON.parse(sessionStorage.getItem('user')).idRole;
  },

  mounted() {
    this.init();
  },

  methods: {

    updateEmail() {
      const pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
      if (this.newEmail !== this.prospectInfo.correo) {
        if (pattern.test(this.newEmail)) {
          prospectSrv.updateProspectEmail(this.prospectId, this.newEmail)
            .then((data) => {
              if (data.success) {
                this.prospectInfo.correo = this.newEmail;
                this.$notify({
                  type: 'info',
                  title: 'Información actualizada',
                  text: 'La información del candidato se actualizó correctamente',
                });
                this.deactiveEmailEdition();
              }
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
            });

        } else {
          this.$notify({
            type: 'error',
            title: 'Error de datos',
            text: 'El correo ingresado no es válido.',
          });
        }
      } else {
        this.$notify({
          type: 'error',
          title: 'Error de datos',
          text: 'El correo ingresado coincide con el del candidato.',
        });
      }
    },

    activeEmailEdition() {
      this.isEditingEmail = true;
    },

    deactiveEmailEdition() {
      this.isEditingEmail = false;
    },

    updateCandidateInfo(value) {
      this.candidateScore = {
        ...value,
        icon: this.getIcon(value.codigo),
      };
    },

    showModal(modal) {
      this.currentModal = modal;
      setTimeout(() => {
        this.$bvModal.show(modal);
      }, 10);
    },

    hideModal(modal) {
      this.currentModal = null;
      this.$bvModal.hide(modal);
    },

    init() {
      this.isLoading = true;

      return axios
        .all([
          prospectSrv.getProspectInfo(this.prospectId),
          prospectSrv.getProspectVacanciesInfo(this.prospectId),
        ])
        .then((data) => {
          const [prospectInfo, prospectHistorical] = data;

          this.prospectInfo = prospectInfo;
          this.prospectHistorical = prospectHistorical;

          this.newEmail = this.prospectInfo.correo;
          this.candidateScore = {
            ...prospectInfo.calificacion,
            icon: this.getIcon(prospectInfo.calificacion.codigo),
          };

          if (!prospectInfo.correo) {
            this.prospectInfo = null;
          }
        })
        .catch(() => {
          this.isError = true;
        })
        .finally(() => {
          this.isLoading = false;
        });
    },

    getIcon(codigo) {
      switch (codigo) {
        case 'FV':
          return 'star';
        case 'VB':
          return 'smile';
        case 'NV':
          return 'ban';
        case 'LN':
          return 'frown';
        default:
          return '';
      }
    },

    reverseText(text) {
      return text.split('').reverse().join('');
    },

    getExtension(text) {
      const reverseWord = this.reverseText(text);
      const pointer = reverseWord.indexOf('.');


      return this.reverseText(reverseWord.substr(0, pointer));
    },

    getTypeConfField(ext) {
      switch (ext) {
        case 'docx':
          return 'application/vnd.openxmlformats-officedocument.wordprocessingml.document';
        case 'doc':
          return 'application/msword';
        case 'pdf':
          return 'application/pdf';
        default:
          return '';
      }
    },

    downloadResume() {
      const extensionField = getExtensionFieldByString(this.prospectInfo.urlCurriculum);

      const data = base64toBlob(
        this.prospectInfo.curriculum,
        this.getTypeConfField(extensionField),
      );
      const fileURL = this.prospectInfo.curriculum
        ? URL.createObjectURL(data)
        : this.prospectInfo.urlCurriculum;
      const fileName = `CV_${this.prospectInfo.nombre}.${extensionField}`;
      downloadLink(fileURL, fileName, this.prospectInfo.urlCurriculum || false);

      /*
        const fileURL = URL.createObjectURL(file);
        const win = window.open();
        win.document.write(`<iframe src="${fileURL}" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>`);
      */
    },
  },
};
</script>
