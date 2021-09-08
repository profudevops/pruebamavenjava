<template>
  <div>
    <LoadingMsg v-if="loadingInfo" />
    <div v-if="!loadingInfo && !reports">
      <p class="error-msg" v-if="!isError">No hay información disponible</p>
      <div v-if="isError" class="text-center">
        <p class="error-msg">¡No se encontraron datos en Cartera de Candidatos!</p>
        <p class="error-msg">Por favor vuelve a intentarlo más tarde.</p>
      </div>
    </div>

    <slot v-if="!loadingInfo && reports">
      <!-- Reports list -->
      <div class="d-flex reports-list">
        <b-link
          class="d-flex"
          v-for="(report, idx) of reports"
          :key="idx"
          :to="{ name: 'report-type', params: { reportType: report.codigo } }"
        >
          <span>{{ report.descripcion }}</span>
        </b-link>
      </div>
    </slot>
    <DownloadListUsers />
  </div>
</template>

<style lang="scss" scoped>
@import "./List";
</style>

<script>
import metricsSrv from '@/services/metrics';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';
import DownloadListUsers from '@/components/DownloadListUsers/DownloadListUsers.vue';

export default {
  data() {
    return {
      loadingInfo: true,
      reports: null,
      isError: false,
    };
  },

  components: {
    LoadingMsg,
    DownloadListUsers,
  },

  mounted() {
    this.getReports();
  },

  methods: {
    getReports() {
      this.loadingInfo = true;
      this.reports = null;

      metricsSrv
        .getAvailableReports()
        .then(({ entity }) => {
          this.reports = entity;
        })
        .catch(() => {
          this.isError = true;
        })
        .finally(() => {
          this.loadingInfo = false;
        });
    },
  },
};
</script>
