import requestSrv from './index';

function getColorByLength(length) {
  if (length > 6) {
    return ['#0e0027', '#1c004e', '#2b0075', '#39009c', '#4800c4', '#5600eb', '#6a13ff', '#823aff', '#9b62ff', '#b489fe', '#cdb0ff'];
  }

  return ['#4b00cc', '#7E33FF', '#9E66FF', '#BE99FF'];
}

function getInfo() {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/escritorio/obtener',
      method: 'GET',
    })
      .then((res) => {
        if (res.data.entity) {
          const { widgets } = res.data.entity;

          if (JSON.parse(sessionStorage.getItem('user')).idRole === 'ROLE_CRP_REC#') {
            // Donut chart
            widgets.push({
              type: 'chart',
              variant: 'donut-chart',
              title: 'Candidatos en Proceso',
              value: {
                labels: ['Aspirantes', 'En proceso', 'Concluído', 'Rechazados'],
                datasets: [66, 52, 28, 54],
              },
            }, {
              size: 'lg',
              type: 'chart',
              variant: 'line-chart',
              title: 'Candidatos en Proceso',
              value: {
                labels: ['Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
                datasets: [380, 700, 600, 1000, 1500, 2200],
              },
            }, {
              size: 'lg',
              type: 'chart',
              variant: 'bar-chart',
              title: 'Candidatos en fases',
              value: {
                labels: ['Fase 1', 'Fase 2', 'Fase 3', 'Fase 4', 'Fase 5', 'Fase 6'],
                datasets: [1300, 700, 500, 1400, 1000, 1250],
              },
            });
          }

          const response = widgets.map((item) => {
            if (item.variant === 'bar-chart-stacked') {
              const colors = ['#D1F9CC', '#99CAF3', '#FF667A', '#FFFF99', '#00B3E6', '#E6B333', '#3366E6', '#999966', '#99FF99', '#B34D4D', '#80B300', '#809900', '#E6B3B3', '#6680B3', '#66991A', '#FF99E6', '#CCFF1A', '#FF1A66', '#E6331A', '#33FFCC', '#66994D', '#B366CC', '#4D8000', '#B33300', '#CC80CC', '#66664D', '#991AFF', '#E666FF', '#4DB3FF', '#1AB399', '#E666B3', '#33991A', '#CC9999', '#B3B31A', '#00E680', '#4D8066', '#809980', '#E6FF80', '#1AFF33', '#999933', '#FF3380', '#CCCC00', '#66E64D', '#4D80CC', '#9900B3', '#E64D66', '#4DB380', '#FF4D4D', '#99E6E6', '#6666FF'];

              if (item.value) {
                item.value.datasets = item.value.datasets.map((data, idx) => ({
                  borderWidth: 0,
                  label: data.label,
                  backgroundColor: colors[idx],
                  data: data.data,
                }));
              }
            } else if (item.variant === 'donut-chart') {
              item.value.datasets = [{
                data: item.value.datasets,
                backgroundColor: getColorByLength(item.value.datasets.length),
                borderWidth: 1,
              }];
            } else if (item.variant === 'line-chart') {
              item.value.datasets = [{
                data: item.value.datasets,
                label: 'Información',
                fill: false,
              }];
            } else if (item.variant === 'bar-chart') {
              item.value.datasets = [{
                data: item.value.datasets,
                label: 'Candidatos',
              }];
            }

            return item;
          });

          resolve(response);
        }

        resolve([]);
      })
      .catch(error => reject(error));
  });
}

export default {
  getInfo,
};
