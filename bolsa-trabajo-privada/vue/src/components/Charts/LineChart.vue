<script>
import { Line, mixins } from 'vue-chartjs';
import _ from 'lodash';

export default {
  extends: Line,

  mixins: [mixins.reactiveProp],

  props: ['chartOptions'],

  data() {
    const options = _.merge({}, {
      elements: {
        line: {
          borderColor: '#5E00FF',
          borderWidth: 2,
          tension: 0,
        },
        point: {
          backgroundColor: '#5E00FF',
          radius: 4,
          hoverRadius: 4,
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
            labelString: 'Día',
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
