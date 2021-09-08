<template>
  <div class="content-manager">
    <section-title title="Administrador de contenido"></section-title>

    <LoadingMsg v-if="loadingFormInfo" />

    <slot v-if="!loadingFormInfo">
      <b-form novalidate @submit.prevent="validateBeforeSubmit">
        <!-- Tabs -->
        <b-tabs class="special-nav" v-model="selectedTab">
          <b-tab title="Encabezado" active class="cabecera">
            <!-- Field -->
            <div class="field">
              <!-- Title -->
              <b-form-group
                class="required"
                label="Título"
                :invalid-feedback="errors.first('headingTitle')"
                :state="!errors.has('headingTitle')"
              >
                <b-form-input
                  required
                  name="headingTitle"
                  v-model="form.cabecera.titulo"
                  v-validate="{ required: currentTab === 'cabecera' }"
                  :state="errors.has('headingTitle') ? false : null"
                ></b-form-input>
              </b-form-group>

              <!-- Subtitle -->
              <b-form-group
                class="required"
                label="Subtítulo"
                :invalid-feedback="errors.first('headingSubtitle')"
                :state="!errors.has('headingSubtitle')"
              >
                <b-form-input
                  required
                  name="headingSubtitle"
                  v-model="form.cabecera.subtitulo"
                  v-validate="{ required: currentTab === 'cabecera' }"
                  :state="errors.has('headingSubtitle') ? false : null"
                ></b-form-input>
              </b-form-group>
            </div>
          </b-tab>
          <b-tab title="¿Quiénes somos?" class="quienesSomos">
            <!-- Field -->
            <div class="field">
              <!-- Title -->
              <b-form-group
                class="required"
                label="Título"
                :invalid-feedback="errors.first('aboutTitle')"
                :state="!errors.has('aboutTitle')"
              >
                <b-form-input
                  required
                  name="aboutTitle"
                  v-model="form.quienesSomos.titulo"
                  v-validate="{ required: currentTab === 'quienesSomos' }"
                  :state="errors.has('aboutTitle') ? false : null"
                ></b-form-input>
              </b-form-group>

              <!-- Subtitle -->
              <b-form-group
                class="required"
                label="Subtítulo"
                :invalid-feedback="errors.first('aboutSubtitle')"
                :state="!errors.has('aboutSubtitle')"
              >
                <b-form-input
                  required
                  name="aboutSubtitle"
                  v-model="form.quienesSomos.subtitulo"
                  v-validate="{ required: currentTab === 'quienesSomos' }"
                  :state="errors.has('aboutSubtitle') ? false : null"
                ></b-form-input>
              </b-form-group>

              <!-- Text -->
              <b-form-group
                class="required"
                label="Texto"
                :invalid-feedback="errors.first('aboutText')"
                :state="!errors.has('aboutText')"
              >
                <b-form-input
                  required
                  name="aboutText"
                  v-model="form.quienesSomos.texto"
                  v-validate="{ required: currentTab === 'quienesSomos' }"
                  :state="errors.has('aboutText') ? false : null"
                ></b-form-input>
              </b-form-group>
            </div>
          </b-tab>
          <b-tab title="Cultura" class="cultura">
            <!-- Field -->
            <div class="field">
              <!-- Title -->
              <b-form-group
                class="required"
                label="Título"
                :invalid-feedback="errors.first('cultureTitle')"
                :state="!errors.has('cultureTitle')"
              >
                <b-form-input
                  required
                  name="cultureTitle"
                  v-model="form.cultura.titulo"
                  v-validate="{ required: currentTab === 'cultura' }"
                  :state="errors.has('cultureTitle') ? false : null"
                ></b-form-input>
              </b-form-group>

              <!-- Subtitle -->
              <b-form-group
                class="required"
                label="Subtítulo"
                :invalid-feedback="errors.first('cultureSubtitle')"
                :state="!errors.has('cultureSubtitle')"
              >
                <b-form-input
                  required
                  name="cultureSubtitle"
                  v-model="form.cultura.subtitulo"
                  v-validate="{ required: currentTab === 'cultura' }"
                  :state="errors.has('cultureSubtitle') ? false : null"
                ></b-form-input>
              </b-form-group>

              <!-- Video -->
              <b-form-group
                class="required"
                label="Link de video"
                :invalid-feedback="errors.first('cultureVideo')"
                :state="!errors.has('cultureVideo')"
              >
                <b-form-input
                  required
                  name="cultureVideo"
                  v-model="form.cultura.mediaUrl"
                  v-validate="{ required: currentTab === 'cultura' }"
                  :state="errors.has('cultureVideo') ? false : null"
                ></b-form-input>
              </b-form-group>
            </div>
          </b-tab>
          <b-tab title="Buscador código" class="buscadorCodigo">
            <!-- Field -->
            <div class="field">
              <!-- Title -->
              <b-form-group
                class="required"
                label="Título"
                :invalid-feedback="errors.first('codeSearchTitle')"
                :state="!errors.has('codeSearchTitle')"
              >
                <b-form-input
                  required
                  name="codeSearchTitle"
                  v-model="form.buscadorCodigo.titulo"
                  v-validate="{ required: currentTab === 'buscadorCodigo' }"
                  :state="errors.has('codeSearchTitle') ? false : null"
                ></b-form-input>
              </b-form-group>

              <!-- Subtitle -->
              <b-form-group
                class="required"
                label="Subtítulo"
                :invalid-feedback="errors.first('codeSearchSubtitle')"
                :state="!errors.has('codeSearchSubtitle')"
              >
                <b-form-input
                  required
                  name="codeSearchSubtitle"
                  v-model="form.buscadorCodigo.subtitulo"
                  v-validate="{ required: currentTab === 'buscadorCodigo' }"
                  :state="errors.has('codeSearchSubtitle') ? false : null"
                ></b-form-input>
              </b-form-group>

              <!-- Placeholder: text -->
              <b-form-group
                class="required"
                label="Placeholder"
                :invalid-feedback="errors.first('placeholder')"
                :state="!errors.has('placeholder')"
              >
                <b-form-input
                  required
                  name="placeholder"
                  v-model="form.buscadorCodigo.texto"
                  v-validate="{ required: currentTab === 'buscadorCodigo' }"
                  :state="errors.has('placeholder') ? false : null"
                ></b-form-input>
              </b-form-group>
            </div>
          </b-tab>
          <b-tab title="Galería" class="galeria">
            <!-- Field -->
            <div class="field">
              <!-- Title -->
              <b-form-group
                class="required"
                label="Título"
                :invalid-feedback="errors.first('galleryTitle')"
                :state="!errors.has('galleryTitle')"
              >
                <b-form-input
                  required
                  name="galleryTitle"
                  v-model="form.galeria.titulo"
                  v-validate="{ required: currentTab === 'galeria' }"
                  :state="errors.has('galleryTitle') ? false : null"
                ></b-form-input>
              </b-form-group>

              <!-- Subtitle -->
              <b-form-group
                class="required"
                label="Subtítulo"
                :invalid-feedback="errors.first('gallerySubtitle')"
                :state="!errors.has('gallerySubtitle')"
              >
                <b-form-input
                  required
                  name="gallerySubtitle"
                  v-model="form.galeria.subtitulo"
                  v-validate="{ required: currentTab === 'galeria' }"
                  :state="errors.has('gallerySubtitle') ? false : null"
                ></b-form-input>
              </b-form-group>

              <!-- Text -->
              <b-form-group
                class="required"
                label="Texto"
                :invalid-feedback="errors.first('galleryText')"
                :state="!errors.has('galleryText')"
              >
                <b-form-input
                  required
                  name="galleryText"
                  v-model="form.galeria.texto"
                  v-validate="{ required: currentTab === 'galeria' }"
                  :state="errors.has('galleryText') ? false : null"
                ></b-form-input>
              </b-form-group>

              <!-- First image text -->
              <b-form-group
                class="required"
                label="Texto de primera imágen"
                :invalid-feedback="errors.first('galleryFirstImageText')"
                :state="!errors.has('galleryFirstImageText')"
              >
                <b-form-input
                  required
                  name="galleryFirstImageText"
                  v-model="form.galeria.textoPrimeraImagen"
                  v-validate="{ required: currentTab === 'galeria' }"
                  :state="errors.has('galleryFirstImageText') ? false : null"
                ></b-form-input>
              </b-form-group>

              <!-- Images -->
              <b-form-group
                class="required"
                v-for="idx in imagenes.length"
                :key="idx"
                :label="`Imagen ${idx}`"
                description="Están permitidos los siguientes formatos de archivo: .jpg, .png y jpeg."
                :invalid-feedback="errors.first(`galleryImage${idx}`)"
                :state="!errors.has(`galleryImage${idx}`)"
              >
                <b-form-file
                  :state="errors.has(`galleryImage${idx}`) ? false : null"
                  :name="`galleryImage${idx}`"
                  :placeholder="`Subir imagen ${idx}`"
                  v-model="originalImages[idx - 1]"
                  accept=".jpg, .png, .jpeg"
                  v-validate="'size:500'"
                  @change="addImage($event, idx)"
                ></b-form-file>
                <b-link
                  class="show-image"
                  v-if="form.galeria.imagenesOut && form.galeria.imagenesOut[idx]"
                  @click="showGalleryImage(form.galeria.imagenesOut[idx])"
                >Ver imagen actual</b-link>
              </b-form-group>
              <image-modal :image="selectedImage"></image-modal>
            </div>
          </b-tab>
        </b-tabs>

        <!-- Form actions -->
        <div class="form-actions">
          <b-button type="submit" variant="primary" :disabled="sendingForm">
            {{ sendButtonText }}
            <b-spinner v-if="sendingForm" small></b-spinner>
          </b-button>
        </div>
      </b-form>
    </slot>
  </div>
</template>

<style lang="scss" scoped>
@import "./ContentManager";
</style>

<script>
import getBase64 from '@/utils/getBase64';
import {
  getContentInfo,
  saveContent,
  saveImagesContent,
} from '@/services/content';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import ImageModal from '@/components/Modal/Image.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

const dict = {
  custom: {
    headingTitle: { required: 'El título es requerido' },
    headingSubtitle: { required: 'El subtítulo es requerido' },
    aboutTitle: { required: 'El título es requerido' },
    aboutSubtitle: { required: 'El subtítulo es requerido' },
    aboutText: { required: 'El texto es requerido' },
    cultureTitle: { required: 'El título es requerido' },
    cultureSubtitle: { required: 'El subtítulo es requerido' },
    cultureVideo: { required: 'El enlace del video es requerido' },
    codeSearchTitle: { required: 'El título es requerido' },
    codeSearchSubtitle: { required: 'El subtítulo es requerido' },
    placeholder: { required: 'El subtítulo es requerido' },
    galleryTitle: { required: 'El título es requerido' },
    gallerySubtitle: { required: 'El subtítulo es requerido' },
    galleryText: { required: 'El texto es requerido' },
    galleryFirstImageText: {
      required: 'El texto de primera imágen es requerido',
    },
  },
};

for (let x = 1; x <= 5; x += 1) {
  dict.custom[`galleryImage${x}`] = {
    size: 'El tamaño del archivo deber ser menor a 500KB',
  };
}

export default {
  components: {
    SectionTitle,
    ImageModal,
    LoadingMsg,
  },

  data() {
    return {
      selectedTab: 0,
      currentTab: null,
      loadingFormInfo: false,
      sendingForm: false,
      sendButtonText: 'Guardar',
      originalImages: [],
      imagenes: [],
      selectedImage: null,
      uploadImages: [],
      form: {
        cabecera: {},
        quienesSomos: {},
        cultura: {},
        buscadorCodigo: {},
        galeria: {},
      },
    };
  },

  created() {
    this.$validator.localize('es', dict);
    this.init();
  },

  methods: {
    init() {
      this.loadingFormInfo = true;
      this.setSelectedTab(0);
      this.imagenes = [...Array(5)].map(() => null);
      this.originalImages = [...this.imagenes];

      getContentInfo()
        .then(({ entity }) => {
          this.form = entity;
        })
        .finally(() => {
          this.loadingFormInfo = false;
        });
    },

    setSelectedTab(idx) {
      switch (idx) {
        case 0:
          this.currentTab = 'cabecera';
          break;
        case 1:
          this.currentTab = 'quienesSomos';
          break;
        case 2:
          this.currentTab = 'cultura';
          break;
        case 3:
          this.currentTab = 'buscadorCodigo';
          break;
        case 4:
          this.currentTab = 'galeria';
          break;
        default:
      }
    },

    showGalleryImage(image) {
      this.selectedImage = image;

      setTimeout(() => {
        this.$bvModal.show('image-modal');
      });
    },

    validateBeforeSubmit() {
      this.$validator.validateAll().then((result) => {
        if (result) {
          this.handleSubmit();
        } else {
          document
            .querySelectorAll(`.${this.currentTab} .form-group.is-invalid`)[0]
            .scrollIntoView({
              behavior: 'smooth',
            });
        }
      });
    },

    handleSubmit() {
      let form = this.form[this.currentTab];
      this.sendingForm = true;

      form = {
        ...form,
        imagenes: [...this.uploadImages],
      };

      saveContent({
        [this.currentTab]: form,
      }).then(() => {
        if (this.imagenes.filter(Boolean).length === 0) {
          this.successResponse();
        } else {
          this.setImagePromise();
        }
      });
    },

    successResponse() {
      this.$notify({
        type: 'info',
        title: 'Aviso',
        text: 'La información se guardó correctamente',
      });
      this.init();
      window.scrollTo({ top: 0 });
      this.sendingForm = false;
    },

    addImage(evt, idx) {
      const [file] = evt.target.files;

      if (file) {
        const withImage = this.uploadImages.findIndex(el => el.orden === idx);
        if (withImage > -1) {
          this.uploadImages[withImage] = {
            extension: file.type.replace('image/', ''),
            orden: idx,
          };
        } else {
          this.uploadImages.push({
            extension: file.type.replace('image/', ''),
            orden: idx,
          });
        }

        getBase64(file).then((data) => {
          this.imagenes[idx - 1] = data;
        });
      } else {
        this.imagenes[idx - 1] = null;
      }
    },

    setImagePromise() {
      const promises = [];

      this.imagenes.forEach((e, idx) => {
        if (e) promises.push(saveImagesContent({ imagen: e, orden: idx + 1 }));
      });

      this.saveImages(promises);
    },

    saveImages(promises) {
      return Promise.all(promises)
        .then(() => {
          this.successResponse();
        })
        .catch(() => {
          this.$notify({
            type: 'error',
            title: 'Aviso',
            text: 'Error al proceso la petición',
          });
        });
    },
  },

  watch: {
    selectedTab(value) {
      this.$validator.reset();
      this.setSelectedTab(value);
    },
  },
};
</script>
