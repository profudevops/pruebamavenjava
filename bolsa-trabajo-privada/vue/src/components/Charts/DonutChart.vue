<script>
import Chart from 'chart.js';
import { Doughnut, mixins } from 'vue-chartjs';
import ChartLabels from 'chartjs-plugin-labels';
import _ from 'lodash';

Chart.plugins.register(ChartLabels);

export default {
  extends: Doughnut,

  mixins: [mixins.reactiveProp],

  props: ['chartOptions'],

  data() {
    const options = _.merge({}, {
      plugins: {
        labels: {
          render: args => `${args.value}`,
          fontColor: '#FFFFFF',
          fontSize: 12,
        },
      },
      rotation: 360,
      cutoutPercentage: 35,
      legend: {
        display: true,
        labels: {
          usePointStyle: true,
          boxWidth: 10,
          fontColor: '#333',
          fontSize: 16,
          generateLabels(chart) {
            const { data } = chart;

            if (data.labels.length && data.datasets.length) {
              return data.labels.map((label, i) => {
                const meta = chart.getDatasetMeta(0);
                const ds = data.datasets[0];
                const arc = meta.data[i];
                const custom = arc && (arc.custom || {});
                const { getValueAtIndexOrDefault } = Chart.helpers;
                const arcOpts = chart.options.elements.arc;
                const fill = custom.backgroundColor ? custom.backgroundColor : getValueAtIndexOrDefault(ds.backgroundColor, i, arcOpts.backgroundColor);
                const stroke = custom.borderColor ? custom.borderColor : getValueAtIndexOrDefault(ds.borderColor, i, arcOpts.borderColor);
                const bw = custom.borderWidth ? custom.borderWidth : getValueAtIndexOrDefault(ds.borderWidth, i, arcOpts.borderWidth);
                const total = chart.data.datasets[0].data.reduce((p, c) => p + c, 0);
                const percentage = Math.round(chart.data.datasets[0].data[i] * 100 / total);

                return {
                  text: `${label}: ${percentage}%`,
                  fillStyle: fill,
                  strokeStyle: stroke,
                  lineWidth: bw,
                  hidden: Number.isNaN(ds.data[i]) || meta.data[i].hidden,
                  index: i,
                };
              });
            }

            return [];
          },
        },
      },
      responsive: true,
      maintainAspectRatio: false,
    }, this.chartOptions);

    return {
      options,
    };
  },

  mounted() {
    this.addPlugin({
      beforeDraw(c) {
        const { ctx } = c.chart;
        ctx.fillStyle = '#F2F2F2';
        ctx.fillRect(0, 0, c.chart.width, c.chart.height);
      },
    });

    this.renderChart(this.chartData, this.options);
  },
};
</script>
