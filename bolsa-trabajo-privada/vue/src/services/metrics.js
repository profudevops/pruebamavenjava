import requestSrv from './index';
import hex2rgba from '@/utils/hexToRGB';

function getAvailableReports() {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/metricas/obtener-lista-reportes',
      method: 'GET',
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function cleanObject(data) {
  const clonedData = JSON.parse(JSON.stringify(data));
  Object.keys(clonedData.filters).forEach(key => (clonedData.filters[key] === null || key.indexOf('Clean') > 0) && delete clonedData.filters[key]);

  return clonedData;
}

function getReportInfo(data) {
  const { reportCode, rama, filters } = cleanObject(data);
  filters.reportCode = reportCode;
  filters.rama = rama;

  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/metricas/generar-grafica',
      method: 'POST',
      data: filters,
    })
      .then(({ data: { entity } }) => {
        const item = entity;
        // const item = {
        //   name: 'Candidatos Finalistas y Contratados',
        //   chart: {
        //     variant: reportCode,
        //     labelY: 'Candidatos',
        //     labelX: 'Candidatos que llegan a terna y son contratados',
        //     value: reportCode !== 'bar-chart-stacked' ? {
        //       labels: ['Aspirantes', 'En proceso', 'Concluído', 'Rechazados'],
        //       datasets: [66, 52, 28, 54],
        //     } : {
        //       labels: ['R1', 'R2', 'R3', 'R4', 'R5', 'R6'],
        //       datasets: [{
        //         label: '1ras Entrevistas',
        //         data: [1000, 1500, 700, 300, 1500, 200, 1000],
        //       }, {
        //         label: 'Exámenes',
        //         data: [200, 100, 400, 150, 500, 680, 280],
        //       }, {
        //         label: '2das Entrevistas',
        //         data: [600, 750, 950, 300, 1200, 200, 100],
        //       }],
        //     },
        //   },
        // };
        const colors = ['#D1F9CC', '#99CAF3', '#FF667A', '#FFFF99', '#00B3E6', '#E6B333', '#3366E6', '#999966', '#99FF99', '#B34D4D', '#80B300', '#809900', '#E6B3B3', '#6680B3', '#66991A', '#FF99E6', '#CCFF1A', '#FF1A66', '#E6331A', '#33FFCC', '#66994D', '#B366CC', '#4D8000', '#B33300', '#CC80CC', '#66664D', '#991AFF', '#E666FF', '#4DB3FF', '#1AB399', '#E666B3', '#33991A', '#CC9999', '#B3B31A', '#00E680', '#4D8066', '#809980', '#E6FF80', '#1AFF33', '#999933', '#FF3380', '#CCCC00', '#66E64D', '#4D80CC', '#9900B3', '#E64D66', '#4DB380', '#FF4D4D', '#99E6E6', '#6666FF'];

        if (item.grafica.variant === 'bar-chart-stacked') {
          if (item.grafica.value) {
            item.grafica.value.datasets = item.grafica.value.datasets.map((info, idx) => ({
              borderWidth: 0,
              label: info.label,
              backgroundColor: colors[idx],
              data: info.data,
            }));

            item.chartOpts = {
              layout: {
                padding: {
                  top: 40,
                  right: 30,
                },
              },
              scales: {
                yAxes: [{
                  ticks: {
                    fontSize: 14,
                  },
                  scaleLabel: {
                    labelString: item.grafica.labelY,
                    fontSize: 16,
                    fontStyle: 'bold',
                  },
                }],
                xAxes: [{
                  ticks: {
                    fontSize: 14,
                  },
                  scaleLabel: {
                    labelString: item.grafica.labelX,
                    fontSize: 16,
                    fontStyle: 'bold',
                  },
                }],
              },
            };
          }
        } else if (item.grafica.variant === 'donut-chart') {
          item.grafica.value.datasets = [{
            data: item.grafica.value.datasets,
            backgroundColor: colors,
            borderWidth: 3,
          }];

          item.chartOpts = {
            plugins: {
              labels: {
                fontSize: 24,
              },
            },
            elements: {
              arc: {
                borderColor: '#F2F2F2',
              },
            },
          };
        } else if (item.grafica.variant === 'line-chart') {
          item.grafica.value.datasets = [{
            data: item.grafica.value.datasets,
            label: 'Información',
            backgroundColor: hex2rgba('#48E733', 0.2),
            pointBackgroundColor: '#48E733',
            pointHoverBackgroundColor: '#48E733',
            borderColor: '#48E733',
            pointBorderColor: '#48E733',
            pointHoverBorderColor: '#48E733',
          }];

          item.chartOpts = {
            layout: {
              padding: {
                top: 40,
                right: 30,
              },
            },
            scales: {
              yAxes: [{
                ticks: {
                  fontSize: 14,
                },
                scaleLabel: {
                  labelString: item.grafica.labelY,
                  fontSize: 16,
                  fontStyle: 'bold',
                },
              }],
              xAxes: [{
                ticks: {
                  fontSize: 14,
                },
                scaleLabel: {
                  labelString: item.grafica.labelX,
                  fontSize: 16,
                  fontStyle: 'bold',
                },
              }],
            },
          };
        } else if (item.grafica.variant === 'bar-chart') {
          item.grafica.value.datasets = [{
            data: item.grafica.value.datasets,
            label: 'Candidatos',
            backgroundColor: colors,
          }];

          item.chartOpts = {
            layout: {
              padding: {
                top: 40,
                right: 30,
              },
            },
            scales: {
              yAxes: [{
                ticks: {
                  fontSize: 14,
                },
                scaleLabel: {
                  labelString: item.grafica.labelY,
                  fontSize: 16,
                  fontStyle: 'bold',
                },
              }],
              xAxes: [{
                barThickness: 45,
                ticks: {
                  fontSize: 14,
                },
                scaleLabel: {
                  labelString: item.grafica.labelX,
                  fontSize: 16,
                  fontStyle: 'bold',
                },
              }],
            },
          };
        }

        if (data.reportType === '1') {
          reject(new Error('Error'));
        }

        resolve(item);
      })
      .catch(error => reject(error));
  });
}

function getReportFilters(params) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/metricas/cargar-filtros-iniciales',
      method: 'GET',
      params,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getChildFilters(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/metricas/encadenar-filtros',
      method: 'GET',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getUserListField(modo) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/candidatos/descarga-informacion',
      method: 'POST',
//      responseType: 'blob',
      data: { modo },
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getExcelData(data) {
  const { reportCode, rama, filters } = cleanObject(data);
  filters.reportCode = reportCode;
  filters.rama = rama;

  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/metricas/descargar-reporte',
      method: 'POST',
      responseType: 'blob',
      data: filters,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

export default {
  getAvailableReports,
  getReportInfo,
  getReportFilters,
  getChildFilters,
  getUserListField,
  getExcelData,
};
