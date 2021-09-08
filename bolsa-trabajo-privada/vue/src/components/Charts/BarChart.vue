<script>
import { Bar, mixins } from 'vue-chartjs';
import _ from 'lodash';

export default {
  extends: Bar,

  mixins: [mixins.reactiveProp],

  props: ['chartOptions', 'variant'],

  data() {
    let options = _.merge({}, {
      plugins: {
        labels: {
          render: () => '',
        },
      },
      elements: {
        rectangle: {
          backgroundColor: '#BE99FF',
        },
      },
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true,
          },
          gridLines: {
            display: true,
          },
          scaleLabel: {
            display: true,
            labelString: 'Candidatos',
            fontSize: 18,
            padding: 15,
          },
        }],
        xAxes: [{
          gridLines: {
            display: false,
          },
          scaleLabel: {
            display: true,
            labelString: 'Fase de Reclutamiento',
            fontSize: 18,
            padding: 20,
          },
        }],
      },
      legend: {
        display: false,
      },
      responsive: true,
      maintainAspectRatio: false,
    }, this.chartOptions);

    if (this.variant === 'bar-chart-stacked') {
      options = _.merge({}, {
        legend: {
          display: true,
          position: 'bottom',
          labels: {
            usePointStyle: true,
            boxWidth: 10,
            padding: 30,
            fontColor: '#333',
            fontSize: 14,
          },
          onClick: null,
        },
        scales: {
          xAxes: [{
            gridLines: {
              display: false,
            },
            stacked: true,
            scaleLabel: {
              display: true,
              labelString: 'Reclutadores',
              fontSize: 18,
              padding: 20,
            },
          }],
          yAxes: [{
            stacked: true,
            scaleLabel: {
              display: true,
              labelString: 'Candidatos',
              fontSize: 18,
              padding: 20,
            },
          }],
        },
      }, options);
    }

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
