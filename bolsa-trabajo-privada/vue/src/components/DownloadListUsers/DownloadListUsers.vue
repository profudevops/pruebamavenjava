<template>
  <div class="download__container">
    <p>Descarga la información de los candidatos</p>
    <LoadingMsg v-if="loadingInfo" />
    <b-button
        variant="primary"
        @click="downloadList"
        :disabled="loadingInfo"
        v-if="!loadingInfo"
      >Descargar</b-button>
  </div>
</template>

<style lang="scss">
@import "./DownloadListUsers.scss";
</style>

<script>
import metrics from '@/services/metrics.js';

//import downloadLink from '@/utils/downloadLink';
//import getBlobXls from '@/utils/getBlobXls';
import getFieldName from '@/utils/getFieldName';

import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

export default {
  
  components: {
    LoadingMsg,
  },

  data() {
    return {
      loadingInfo: false,
      branch: '',
    };
  },

  created() {
    const { branch } = JSON.parse(sessionStorage.getItem('user'));
    this.branch = branch;
  },

  methods: {
    downloadList() {
      this.loadingInfo = true;
      metrics.getUserListField(this.branch)
        .then((res) => {
            if (res.success) {
              this.$notify({
                type: 'info',
                title: 'Información en proceso',
                text: 'La información de candidatos se está procesando, revisar /expedientes/reportes/ , de lo contrario solicitarlo a Sistemas',
              });
            }
        })
        .catch(() => {
          this.activeErrorMsg();
        })
        .finally(() => {
          this.loadingInfo = false;
        });
    },

    activeErrorMsg() {
      this.$notify({
        type: 'error',
        title: 'Error interno',
        text: 'Por el momento no podemos descagar la información, intentalo más tarde.',
      });
    }
  }
}
</script>
