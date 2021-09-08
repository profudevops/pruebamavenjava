import requestSrv from './index';
import cleanObject from '@/utils/cleanObject';

function searchCandidates(data) {
  const info = { ...data };

  Object.keys(info).forEach((k) => {
    if (Array.isArray(info[k])) {
      info[k] = info[k].map((e) => {
        if (Object.keys(e).length > 0) {
          return e.idSubArea || e.idArea || e.id;
        }

        return e;
      });

      if (info[k].length === 0) info[k] = null;
    }
  });

  const filters = cleanObject(info);

  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/cartera/buscar',
      method: 'POST',
      data: filters,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getFilters(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/cartera/cargar-filtros',
      method: 'GET',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

function getSubAreas(data) {
  return new Promise((resolve, reject) => {
    requestSrv({
      path: '/cartera/obtener-subarea-interes',
      method: 'POST',
      data,
    })
      .then(res => resolve(res.data))
      .catch(error => reject(error));
  });
}

export default {
  searchCandidates,
  getFilters,
  getSubAreas,
};
