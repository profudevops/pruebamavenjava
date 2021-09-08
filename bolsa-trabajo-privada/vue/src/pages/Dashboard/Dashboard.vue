<template>
  <div class="dashboard">
    <!-- <div>DASHBOARD</div>
    <div v-permission="['VACANCIES_CREATE']">
      VACANCIES CREATE
    </div>-->

    <!-- Section header -->
    <div v-if="userInfo" class="section-header">
      <h1 class="title">¡Hola, {{ userInfo.firstName }}!</h1>
    </div>

    <!-- Loader -->
    <LoadingMsg v-if="loadedInfo" />

    <!-- No data -->
    <div v-if="!loadedInfo && widgets.length === 0">No hay información disponible</div>

    <!-- Statistics -->
    <div
      v-if="!loadedInfo && widgets.length > 0"
      v-masonry
      class="statistics"
      item-selector=".widget"
    >
      <slot v-for="(widget, idx) in widgets">
        <slot v-if="widget.type !== 'chart'">
          <!-- Widget -->
          <widget v-masonry-tile :title="widget.title" :key="idx">
            <div :class="'stat ' + widget.status">
              <strong>{{ widget.value }}</strong>
            </div>
          </widget>
        </slot>

        <slot v-if="widget.type === 'chart'">
          <!-- Chart -->
          <widget
            v-masonry-tile
            :title="widget.title"
            :class="`chart ${ widget.size || 'sm' }`"
            :key="idx"
          >
            <bar-chart
              v-if="widget.variant === 'bar-chart-stacked'"
              :chart-data="widget.value"
              :variant="'bar-chart-stacked'"
            ></bar-chart>
            <donut-chart
              v-if="widget.variant === 'donut-chart'"
              :chart-data="widget.value"
              :chartOptions="{ legend: { position: 'right', labels: { fontSize: 12 } } }"
            ></donut-chart>
            <line-chart v-if="widget.variant === 'line-chart'" :chart-data="widget.value"></line-chart>
            <bar-chart v-if="widget.variant === 'bar-chart'" :chart-data="widget.value"></bar-chart>
          </widget>
        </slot>
      </slot>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import "./Dashboard.scss";
</style>

<script>
import dashboardSrv from '@/services/dashboard';
import Widget from '@/components/Widget/Widget.vue';
import LineChart from '@/components/Charts/LineChart.vue';
import DonutChart from '@/components/Charts/DonutChart.vue';
import BarChart from '@/components/Charts/BarChart.vue';
import LoadingMsg from '@/components/LoadingMsg/LoadingMsg.vue';

export default {
  components: {
    Widget,
    LineChart,
    DonutChart,
    BarChart,
    LoadingMsg,
  },

  data() {
    return {
      userInfo: null,
      loadedInfo: true,
      widgets: [],
    };
  },

  created() {
    this.getDashboardInfo();
  },

  mounted() {
    this.userInfo = JSON.parse(sessionStorage.getItem('user'));
  },

  methods: {
    getDashboardInfo() {
      this.loadedInfo = true;

      return dashboardSrv
        .getInfo()
        .then((res) => {
          this.widgets = res;
        })
        .finally(() => {
          this.loadedInfo = false;
        });
    },
  },
};
</script>
