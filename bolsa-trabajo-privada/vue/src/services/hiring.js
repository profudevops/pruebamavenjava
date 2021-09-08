import reqService from '@/services/index';

function getAplication(IdReclutamiento) {
  return new Promise((resolve) => {
    reqService({ path: '/hiringForm/obtener-aplicacion', data: { IdReclutamiento } })
      .then(({ data }) => {
        resolve(data);
      });
  });
}


export {
  getAplication,
};
