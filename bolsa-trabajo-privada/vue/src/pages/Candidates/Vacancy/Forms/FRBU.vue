<template>
  <b-form novalidate autocomplete="off" @submit.prevent="validateBeforeSubmit">
    <p>La solicitud de empleo fue aprobada, por favor ingresa el folio de requisición para poder enviarlo.</p>

    <!-- Search folio -->
    <b-form-group class="required search-inside" label="Buscar folio de requisición">
      <div class="d-flex">
        <b-form-input
          name="searchedFolio"
          placeholder="Folio"
          v-model="searchedFolio"
          @keypress="onlyNumber"
          :class="{ 'is-invalid': checkFolio() }"
        ></b-form-input>
        <b-button
          variant="bordered"
          :disabled="!searchedFolio || loadingFolios"
          @click="getAvailableFolios()"
        >
          <b-spinner small v-if="loadingFolios"></b-spinner>
          <i class="fa fa-search" v-if="!loadingFolios"></i>
          {{ sendFolioSearch }}
        </b-button>
        <b-form-invalid-feedback>No se encontraron folios disponibles.</b-form-invalid-feedback>
      </div>
    </b-form-group>

    <slot v-if="availableFolios && availableFolios.length > 0">
      <!-- Folio -->
      <b-form-group
        label="Confirma el folio:"
        :invalid-feedback="errors.first('folio')"
        :state="!errors.has('folio')"
      >
        <b-form-radio
          v-for="(folio, idx) of availableFolios"
          :key="idx"
          :state="errors.has('folio') ? false : null"
          inline
          name="folio"
          :value="folio.id"
          v-model="form.folio"
          v-validate="'required'"
        >{{ folio.codigo }} - {{ folio.descripcion }}</b-form-radio>
      </b-form-group>
    </slot>

    <!-- Form actions -->
    <div class="form-actions">
      <b-button
        type="submit"
        variant="primary"
        @click="handleSubmit"
        :disabled="sendingForm || !(availableFolios && availableFolios.length > 0)"
      >
        {{ sendButtonText }}
        <b-spinner v-if="sendingForm" small></b-spinner>
      </b-button>
    </div>
  </b-form>
</template>

<script>
import prospectSrv from '@/services/prospect';

const dict = {
  custom: {
    folio: {
      required: 'Selecciona un folio',
    },
  },
};

export default {
  props: {
    currentInfo: {
      type: Object,
      required: false,
    },
    confirmResult: Boolean,
    repostulacion: Boolean,
    showModal: Function,
  },

  data() {
    return {
      loadingFolios: false,
      sendingForm: false,
      sendButtonText: 'Enviar folio',
      sendFolioSearch: 'Buscar',
      searchedFolio: null,
      availableFolios: null,
      form: {
        folio: null,
      },
    };
  },

  created() {
    this.$validator.localize('es', dict);
  },

  mounted() {
    if (this.currentInfo && Object.keys(this.currentInfo).length > 0) this.form = this.currentInfo;
  },

  methods: {

    onlyNumber($event) {
      const keyCode = ($event.keyCode ? $event.keyCode : $event.which);
      if (keyCode < 48 || keyCode > 57) {
        $event.preventDefault();
      }
    },

    checkFolio() {
      if (!this.availableFolios) return false;

      return (
        this.availableFolios
        && this.availableFolios.length === 0
        && !this.loadingFolios
      );
    },

    getAvailableFolios() {
      this.availableFolios = null;
      this.loadingFolios = true;
      this.sendFolioSearch = 'Buscando...';
      this.$nextTick(() => {
        this.form.folio = null;
      });

      return prospectSrv
        .getAvailableFolios(this.searchedFolio)
        .then(({ entity }) => {
          this.availableFolios = entity;
        })
        .finally(() => {
          this.loadingFolios = false;
          this.sendFolioSearch = 'Buscar';
        });
    },

    validateBeforeSubmit() {
      this.$validator.validate().then((result) => {
        if (result) {
          this.showModal('confirm-dialog', {
            title: 'Confirmar folio',
            content:
              'Una vez enviado el folio no se puede corregir ni editar ¿Estás seguro de enviar este folio?',
            okTitle: 'Aceptar',
            customCb: 'setConfirmResponse',
          });
        }
      });
    },

    handleSubmit() {
      const { vacancyId } = this.$route.params;
      const data = {
        idReclutamiento: Number(vacancyId),
        idUsuario: 0,
        codigoFase: 'FR-GD',
        repostulacion: this.repostulacion,
        fr: this.form,
      };
      this.sendButtonText = 'Enviando...';
      this.sendingForm = true;

      return prospectSrv
        .saveRecruitmentInfo(data, 'fr')
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
                title: 'Error interno',
                text: error.data.statusText,
              });
            }
          }
        })
        .finally(() => {
          this.sendButtonText = 'Enviar expediente';
          this.sendingForm = false;
        });
    },
  },

  /*watch: {
    confirmResult() {
      this.handleSubmit();
    },
  },*/
};
</script>
