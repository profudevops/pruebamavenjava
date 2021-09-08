<template>
  <div>
    <!-- Chart -->
    <div class="chart-wrapper">
      <!-- Section title -->
      <section-title :title="reportInfo.nombre"></section-title>

      <!-- Filters -->
      <common-filters v-if="currentFilter() === 'common'" @updateChartInfo="updateChart"></common-filters>
      <fre-filters v-if="currentFilter() === 'fre'" @updateChartInfo="updateChart"></fre-filters>
      <cpm-filters v-if="currentFilter() === 'cpm'" @updateChartInfo="updateChart"></cpm-filters>

      <LoadingMsg v-if="isLoading" />
      <div v-if="!isLoading && !reportInfo" class="loading">No hay información disponible</div>
      <slot v-if="!isLoading && reportInfo">
        <widget>
          <line-chart
            v-if="reportInfo.grafica.variant === 'line-chart'"
            :chart-data="reportInfo.grafica.value"
            :chartOptions="reportInfo.chartOpts"
          ></line-chart>
          <donut-chart
            class="donut"
            v-if="reportInfo.grafica.variant === 'donut-chart'"
            :chart-data="reportInfo.grafica.value"
            :chartOptions="reportInfo.chartOpts"
          ></donut-chart>
          <bar-chart
            v-if="reportInfo.grafica.variant === 'bar-chart'"
            :chart-data="reportInfo.grafica.value"
            :chartOptions="reportInfo.chartOpts"
          ></bar-chart>
          <bar-chart
            v-if="reportInfo.grafica.variant === 'bar-chart-stacked'"
            :variant="reportInfo.grafica.variant"
            :chart-data="reportInfo.grafica.value"
            :chartOptions="reportInfo.chartOpts"
          ></bar-chart>
        </widget>
      </slot>
    </div>

    <!-- Options -->
    <slot v-if="!isLoading && reportInfo">
      <!-- Options -->
      <div class="report-opts">
        <b-row>
          <b-col>
            <b-button variant="primary" @click="exportChartToImage()">Descargar Gráfica</b-button>
          </b-col>
          <b-col>
            <b-button variant="primary" @click="exportDataToExcel()">Descargar XLS</b-button>
          </b-col>
        </b-row>
      </div>
    </slot>
  </div>
</template>

<style lang="scss" scoped>
@import "./Report";
</style>

<script>
import downloadLink from '@/utils/downloadLink';
import getBlobXls from '@/utils/getBlobXls';
import getFieldName from '@/utils/getFieldName';
import metricsSrv from '@/services/metrics';
import SectionTitle from '@/components/SectionTitle/SectionTitle.vue';
import Widget from '@/components/Widget/Widget.vue';
import LineChart from '@/components/Charts/LineChart.vue';
import DonutChart from '@/components/Charts/DonutChart.vue';
import BarChart from '@/components/Charts/BarChart.vue';
import CommonFilters from './Filters/CommonFilters.vue';
import FreFilters from './Filters/FreFilters.vue';
import CpmFilters from './Filters/CpmFilters.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

export default {
  components: {
    SectionTitle,
    Widget,
    LineChart,
    DonutChart,
    BarChart,
    CommonFilters,
    FreFilters,
    CpmFilters,
    LoadingMsg,
  },

  data() {
    return {
      isLoading: true,
      reportCode: this.$route.params.reportType,
      rama: JSON.parse(sessionStorage.getItem('user')).branch,
      reportInfo: false,
      currentFilters: {},
    };
  },

  created() {
    this.getReportInfo();
  },

  methods: {
    currentFilter() {
      let filter = '';
      if (this.reportCode !== 'FRE' && this.reportCode !== 'CPM') {
        filter = 'common';
      } else if (this.reportCode === 'FRE') {
        filter = 'fre';
      } else {
        filter = 'cpm';
      }

      return filter;
    },

    getReportInfo(filters = {}) {
      const { reportCode, rama } = this;
      this.isLoading = true;

      return metricsSrv
        .getReportInfo({ reportCode, rama, filters })
        .then((res) => {
          this.reportInfo = res;
        })
        .finally(() => {
          this.isLoading = false;
        });
    },

    updateChart(filters) {
      this.getReportInfo(filters);
      this.currentFilters = { ...filters };
    },

    exportChartToImage() {
      const fileURL = this.$el
        .querySelector('canvas')
        .toDataURL('image/png')
        .replace('image/png', 'image/octet-stream');
      const fileName = getFieldName('imagen-reporte-', 'png');
      downloadLink(fileURL, fileName);
    },

    exportDataToExcel() {
      const { reportCode, rama } = this;

      return metricsSrv
        .getExcelData({ reportCode, rama, filters: this.currentFilters })
        .then((res) => {
          const fileURL = getBlobXls(res);
          const fileName = getFieldName('reporte-', 'xlsx');
          downloadLink(fileURL, fileName);
        });
    },
  },
};
</script>
